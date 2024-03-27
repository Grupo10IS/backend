# revisar si existe la variable de entorno donde apunta el servidor
WILDFLYPATH ?= $(error Please set the WILDFLYPATH environment variable)

wf=${WILDFLYPATH}
cwd=$(shell pwd)

# -- mvn fast utilities -- 
package:
	mvn clean package

install:
	mvn clean package

# -- deploy and server control -- 
deploy:
	mvn clean install
	${wf}/bin/jboss-cli.sh --connect --command="deploy ${cwd}/target/parcial1.war --force"

update:
	${wf}/bin/jboss-cli.sh --connect --command="deploy ${cwd}/target/parcial1.war --force"

# -- jboss fast utilities --
run:
	${wf}/bin/standalone.sh

wf-cli:
	${wf}/bin/jboss-cli.sh

add-user:
	${wf}/bin/add-user.sh

