package org.exemplo.batchdemo.pessoa.job.process;


import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Service;
import org.exemplo.batchdemo.pessoa.model.Pessoa;

@Service
public class PessoaBatchProcessor implements ItemProcessor<Pessoa, Pessoa> {
    @Override
    public Pessoa process(Pessoa pessoa) throws Exception {
        pessoa.setNome(pessoa.getNome() + " 123");
        pessoa.setProcessado(true);
        return pessoa;
    }
}
