systemctl start docker

printf '=================================================\n'
printf 'Derrubando Docker\n'
printf '=================================================\n'
docker-compose down

printf '=================================================\n'
printf 'Subindo Docker com base existente\n'
printf '=================================================\n'
docker-compose up -d