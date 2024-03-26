# revisar si existe la variable de entorno donde apunta el servidor
WILDFLYPATH ?= $(error Please set the WILDFLYPATH environment variable)

wf=${WILDFLYPATH}
cwd=$(shell pwd)

run:
	${wf}/bin/standalone.sh

wf-cli:
	${wf}/bin/jboss-cli.sh

deploy:
	mvn clean install
	${wf}/bin/jboss-cli.sh --connect --command="deploy ${cwd}/target/prueba.war --force"

add-user:
	${wf}/bin/add-user.sh

