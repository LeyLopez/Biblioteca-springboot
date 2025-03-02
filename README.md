# Biblioteca-springboot

Este es el backend de una aplicación construida con Spring Boot, creada para proporcionar servicios de reservas y prestamos a los clientes de una biblioteca.
Permite la administración de libros, y visualización de reservas y préstamos realizados por los usuarios registrados en la plataforma.
Utiliza tecnologías como Hibernate, Lombok, JPA, Spring Security, PostgreSQL y JWT para la autenticación y autorización.

## Tecnologías Utilizadas

- **Spring Boot** - Framework principal del backend.
- **Hibernate** - Implementación de JPA para el manejo de la base de datos.
- **Lombok** - Reducción de código repetitivo en las entidades por medio de anotaciones.
- **JPA** - API para la gestión de datos.
- **Spring Security** - Seguridad y manejo de autenticación/autorización.
- **PostgreSQL** - Base de datos utilizada.
- **JWT (JSON Web Token)** - Mecanismo de autenticación.

## Requisitos Previos

Antes de ejecutar la aplicación, asegúrate de tener instalado:

- **Java 17**
- **Gradle o Maven**
- **PostgreSQL**
- **Docker (opcional, para contenerización)**

## Instalación y Configuración

1. Clonar el repositorio:
   ```bash
   git clone https://github.com/tu-usuario/tu-repositorio.git
   cd tu-repositorio
   ```

2. Configurar la base de datos en `application.properties` o `application.yml`:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/tu_base_de_datos
   spring.datasource.username=tu_usuario
   spring.datasource.password=tu_contraseña
   spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
   ```

3. Ejecutar la aplicación con Gradle:
   ```bash
   ./gradlew bootRun
   ```
   o con Maven:
   ```bash
   mvn spring-boot:run
   ```

## Endpoints Principales

- **Autenticación**
    - `POST /api/auth/login` - Iniciar sesión y obtener token JWT.
    - `POST /api/auth/signup` - Registrar un nuevo usuario a la plataforma.

- **Endpoints de la aplicación (Requieren autenticación)**
    - `GET /api/usuario` - Obtener lista de usuarios.
    - `GET /api/libro` -Obtener lista de libros.
    - `GET /api/reserva` -Obtener lista de reservas.
    - `GET /api/prestamo` -Obtener lista de reservas.
    - `GET /api/libro/{id}` -Obtener libro por ID.
    - `GET /api/usuario/{id}` -Obtener usuario por ID.
    - `GET /api/reserva/{id}` -Obtener reserva por ID.
    - `GET /api/prestamo/{id}` -Obtener prestamo por ID.
    - `GET /api/autor/{id}` -Obtener autor por ID.
    - `GET /api/genero/{id}` -Obtener genero de un libro por ID del genero.
    - `POST /api/genero` -Agregar genero a la biblioteca.
    - `POST /api/libro` -Agregar libro a la biblioteca.
    - `POST /api/autor` -Agregar autor a la biblioteca.
    - `POST /api/prestamo` -Agregar prestamo a la biblioteca.
    - `POST /api/reserva` -Agregar reserva a la biblioteca.
    - `PUT /api/usuario/{id}` -Actualizar un usuario por su ID.
    - `PUT /api/libro/{id}` -Actualizar un libro por su ID.
    - `PUT /api/genero/{id}` -Actualizar un genero por su ID.
    - `PUT /api/autor/{id}` -Actualizar un autor por su ID.
    - `PUT /api/reserva/{id}` -Actualizar una reserva por su ID.
    - `PUT /api/prestamo/{id}` -Actualizar un préstamo por su ID.
    - `DELETE /api/usuario/{id}` -Eliminar un usuario por su ID.
    - `DELETE /api/libro/{id}` -Eliminar un libro por su ID.
    - `DELETE /api/genero/{id}` -Eliminar un genero por su ID.
    - `DELETE /api/autor/{id}` -Eliminar un autor por su ID.
    - `DELETE /api/reserva/{id}` -Eliminar una reserva por su ID.
    - `DELETE /api/prestamo/{id}` -Eliminar un préstamo por su ID.

## Seguridad y JWT

La aplicación usa Spring Security con JWT para la autenticación. El token debe enviarse en la cabecera de cada petición:
```http
Authorization: Bearer <tu_token_jwt>
```

## Contenerización con Docker

Para ejecutar la aplicación en Docker, utiliza el siguiente `Dockerfile`:

```dockerfile
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY build/libs/tu-app.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
```

Construir y ejecutar el contenedor:
```bash
docker build -t mi-aplicacion .
docker run -p 8080:8080 mi-aplicacion
```

## Contribuciones

Si deseas contribuir, por favor abre un *Pull Request* o crea un *Issue* para discutir cambios.


