package org.exemplo.batchdemo.pessoa.job.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.builder.RepositoryItemReaderBuilder;
import org.springframework.batch.item.data.builder.RepositoryItemWriterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.exemplo.batchdemo.pessoa.model.Pessoa;
import org.exemplo.batchdemo.pessoa.model.PessoaRepository;
import org.exemplo.batchdemo.pessoa.job.process.PessoaBatchProcessor;

import java.util.HashMap;
import java.util.Map;

@Service
public class PessoaBatchConfigService {
    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private PessoaBatchProcessor pessoaBatchProcessor;

    public Job criarJob() {
        return jobBuilderFactory.get("pessoaJob_" + System.currentTimeMillis())
                .incrementer(new RunIdIncrementer())
                .flow(stepUnico())
                .end()
                .build();
    }

    private ItemReader<Pessoa> reader() {
        Map<String, Sort.Direction> sorts = new HashMap<>();
        sorts.put("nome", Sort.Direction.ASC);
        return new RepositoryItemReaderBuilder<Pessoa>()
                .repository(pessoaRepository)
                .methodName("findByProcessadoIsFalse")
                .sorts(sorts)
                .maxItemCount(100)
                .saveState(false)
                .pageSize(10)
                .build();
    }

    private ItemWriter<Pessoa> writer() {
        RepositoryItemWriterBuilder.RepositoryMethodReference<PessoaRepository>
                methodReference = new RepositoryItemWriterBuilder
                    .RepositoryMethodReference<>(pessoaRepository);

        return new RepositoryItemWriterBuilder<Pessoa>()
                .methodName("save")
                .repository(methodReference)
                .build();
    }

    private Step stepUnico() {
        return stepBuilderFactory.get("stepUnico")
                .<Pessoa, Pessoa> chunk(10)
                .reader(reader())
                .processor(pessoaBatchProcessor)
                .writer(writer())
                .build();
    }
}
