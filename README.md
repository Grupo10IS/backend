# Requerimientos

- [Wildfly 18](https://drive.google.com/file/d/1YqprEej75ofErCmDE92nF7WkZh2xVEBa/view)
- Postgresql
- Java 8 (versiones posteriores son incompatibles)
- Maven

# Preparacion

Luego de descargar wildfly 18, debe correr el script `bd_scheme.sql` dentro de una nueva base de datos postgres.

Para configurar wildfly para correr en conjunto con postgres, debe de ir al archivo
"standalone/configuration/standalone.xml" dentro de la carpeta wildfly previamente descargada.

Luego cambie el contenido dentro del tag `<connection-url>` ubicado dentro del datasource `java:jboss/datasources/parcial1`.

# Compilacion del proyecto con maven

Para compilar el proyecto solo debe de correr el comando:
```bash
mvn clean install
```

Ahora el proyecto ya queda listo para desplegar con wildfly.

## Error de dependecias

En algunos sistemas maven no permite la descarga de las dependecias necesarias.
Para solucionar esto vaya a la carpeta de configuracion de maven, luego vaya al archivo "settings.xml" (si no existe
debe crearlo) y agregue: 

```xml
<settings>
    <mirrors>
        <mirror>
            <id>maven-default-http-blocker</id>
            <mirrorOf>external:dummy:*</mirrorOf>
            <name>Pseudo repository to mirror external repositories initially using HTTP.</name>
            <url>http://0.0.0.0/</url>
            <blocked>true</blocked>
        </mirror>
    </mirrors>
</settings>
```

# Deploy del proyecto

### Manual

Si va a realizar la configuracion y el despliegue manual le recomendamos estos videos.

- [Configuracion del entorno de desarrollo](https://drive.google.com/file/d/10mOpcU_6aq_CQQ8g21TRFzCN5MRBPPsw/view?usp=sharing)
- [Deploy](https://drive.google.com/file/d/1tCM9ESRBanLIluA5E8FvBumDrJvHNlUb/view?usp=sharing)

### Utilizando Make

Para sistemas basados en Unix, el proyecto cuenta con un Makefile, el cual permite compilar,
desplegar e inclusive actualizar la aplicacion con wildfly.

Para ello es necesario que cree una variable de entorno llamada `WILDFLYPATH` que contenga la
direccion de la carpeta donde descargo wildfly. Otra opcion es que modifique
directamente la variable del mismo nombre dentro del Makefile.

#### Comandos disponibles

- _run_: pone en marcha el servidor wildfly. (NO la utilice si el servidor ya se encuentra corriendo)
- _wf-cli_: corre la consola de administrador de wildfly.
- _deploy_: compila la aplicacion y la despliega en el servidor wildfly. (debe
correrse solo si el servidor wildfly ya se encuentra corriendo).
- _add-user_: agrega un nuevo usuario al servidor wildfly
