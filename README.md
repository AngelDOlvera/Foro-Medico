# Foro-Medico
Este proyecto implementa una API RESTful para un foro médico donde los usuarios pueden crear, gestionar y consultar temas continua en desarrolo y hasta ahora solo implementa los requisitos minimos como parte del challenge Foro Hub de el Programa Oracle One de Alura Latam (Gracias por la oportunidad)

Requisitos del Sistema
Java JDK 11 o superior
Maven 3.6.3 o superior
Spring Boot 2.5.4
MySQL Server 8.0
IntelliJ IDEA (o IDE similar)
Dependencias
El proyecto utiliza las siguientes dependencias principales:

Spring Boot Starter Web
Spring Boot Starter Data JPA
Spring Boot Starter Security
MySQL Connector Java
Spring Boot Starter Validation
Estas dependencias se gestionan a través de Maven y están especificadas en el archivo pom.xml.

Configuración de la Base de Datos
Instalar MySQL Server 8.0.

Crear una base de datos llamada foro_med_TU_NOMBRE en MySQL. te dejo un ejemplo para implementarlo en el WorkBench
```
CREATE DATABASE foro_med_TU_NOMBRE;
```

Configurar las credenciales de acceso a MySQL en application.properties:
```
spring.datasource.url=jdbc:mysql://localhost:3306/foro_medico
spring.datasource.username=root
spring.datasource.password=your_password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect

api.security.secret={$JWT_SECRET}
```
Ejecutar el script de migraciones de base de datos proporcionado para crear las tablas necesarias.

Uso de la Aplicación
Autenticación
Login: Envía una solicitud POST a /login con las credenciales de usuario para obtener un token JWT. y de Body JSON Ejemplo:
 http://localhost:8080/login 
```
{
    "login": "usuario",
    "password": "contraseña"
}'
```
(No olvides realizar el Hash en BCrypt para agregarlo en tu base de datos en la columna password, de igual manera en la requisicion debes colocar el password no el hash creado)
El endpoint devuelve un token JWT que debe incluirse en las cabeceras de las solicitudes posteriores como Authorization: Bearer <token>.

CRUD de Topics
Crear Topic: Envía una solicitud POST a /topics para crear un nuevo topic. Ejemplo:
```
'{
    "titulo": "Título del topic",
    "mensaje": "Mensaje del topic",
    "fecha": "2024-07-15",
    "estatus": "Activo",
    "autor": 1,
    "curso": "Nombre del curso"
}'
```
Actualizar Topic HTTP PUT para actualizar un topic en la API del foro médico, utilizando un token JWT para la autenticación:
```
{
         "titulo": "Nuevo título actualizado",
         "mensaje": "Nuevo mensaje actualizado"
     }'
```
Eliminar Topic: Envía una solicitud DELETE a /topics/{id} para eliminar un topic existente.
No olvides copiar tu Bearer Token y colocarlo en Auth en cada requisicion que hagas y no olvides que caduca en cierto tiempo.

Ejecución del Proyecto

Descargar el repositorio y abrir el proyecto en IntelliJ IDEA.

Configurar las dependencias y ejecutar la aplicación desde la clase principal Application.java.

Utilizar las solicitudes HTTP como se ha descrito anteriormente para interactuar con la API.

Creditos Angel Eduardo Olvera Perez con Ayude de la mentoria del equipo de Oracle One de Alura LATAM muchas gracias por la oportunidad y seguiremos mejorando en el desarrollo de proyectos
Con amor estudiante del Grupo 6 <3
