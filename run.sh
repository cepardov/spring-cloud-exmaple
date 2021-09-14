#!/bin/bash
echo "### Aplicando permisos de ejecucion ###"
chmod +x catalogo/gradlew
chmod +x configserver/gradlew
echo "### Generando Servicio catalogo ###"
catalogo/gradlew -p catalogo clean
catalogo/gradlew -p catalogo build
echo "### Generando Servicio configserver ###"
catalogo/gradlew -p configserver clean
catalogo/gradlew -p configserver build
echo "### Ejecutando docker-compose ###"
docker-compose -f docker-compose.yml -p catalogo_cristian_pardo up -d
