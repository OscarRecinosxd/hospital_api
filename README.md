# Pasos para ejecutar el proyecto

1. Clonar el repositorio

```
git clone https://github.com/OscarRecinosxd/hospital_api
```

2. Crear una base de datos PostgreSQL con el nombre **hospital_db**
3. En el directorio **src/main** crear el directorio **resources**
4. Dentro de **src/main/resources** crear el archivo **application.properties** con el siguiente contenido:
```
spring.jpa.database=POSTGRESQL

spring.datasource.url=jdbc:postgresql://localhost:<PORT>/<DATABASE_NAME>
spring.datasource.username=<USERNAME>
spring.datasource.password=<PASSWORD>

# TODO: Mostrar instrucciones SQL en la consola
# spring.jpa.show-sql=true
# spring.jpa.properties.hibernate.format_sql=true

spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

# TODO: Todas los identificadores van entre comillas
# spring.jpa.properties.hibernate.globally_quoted_identifiers=true

spring.jpa.properties.hibernate.globally_quoted_identifiers=true

# jwt secret
jwt.secret=<SECRET>

logging.level.org.springframework.security=DEBUG

#server.error.include-stacktrace=always
#server.error.include-message=always

spring.sendgrid.api-key=<API-KEY>
```

5. Ejecutar el script de la base de datos
6. Abrir el proyecto en el IDE de preferencia
7. Correr el proyecto
