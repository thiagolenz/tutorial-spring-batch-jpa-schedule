
systemctl start docker
printf '=================================================\n'
printf 'Derrubando Docker\n'
printf '=================================================\n'
docker-compose down

printf '=================================================\n'
printf 'Removendo diretório\n'
printf '=================================================\n'
rm -rf postgres-data/*
printf 'Pasta postgres-data excluída\n'

printf '=================================================\n'
printf 'Subindo Docker\n'
printf '=================================================\n'
docker-compose up -d