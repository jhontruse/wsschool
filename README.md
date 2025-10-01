<<<<<<< HEAD
# Mi Intranet Spring Boot
=======
# 🏫 Colegio API – Intranet Escolar

Proyecto backend en **Spring Boot 3** para la gestión de un colegio.  
Incluye autenticación con JWT, control de acceso por roles y módulos para alumnos, profesores, directores y administradores.

---

## 🚀 Características principales

- **Spring Boot 3.x** + **Java 17/21**
- **Autenticación y autorización con JWT**
- Gestión de **usuarios, roles y menús** (seguridad basada en perfiles)
- Módulos principales:
  - 👩‍🎓 **Alumnos** – registro, notas, pagos
  - 👨‍🏫 **Profesores** – asignación de cursos, notas
  - 🎓 **Directores** – reportes y control académico
  - ⚙️ **Administrador** – configuración general
- Persistencia con **MySQL** + **JDBC Template**
- Validación de datos y manejo centralizado de excepciones
- Documentación interactiva con **Swagger/OpenAPI**
- Arquitectura en capas (**controller**, **service**, **repository**, **dto**)

---

## 🛠️ Requisitos previos

- **Java** 17 o superior
- **Maven** 3.9+
- **MySQL** 8.x
- **Git** instalado
- Opcional: **Docker** para levantar la base de datos

---

## ⚙️ Configuración

-----Properties-----

spring.application.name=wsschool
server.port=8080

spring.datasource.url=jdbc:mysql://localhost:3306/bd_school?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=admin123
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.hikari.maximum-pool-size=10

spring.sql.init.mode=never
spring.jpa.open-in-view=false

security.jwt.issuer=wsschool
security.jwt.key=wsschool_KEY1
security.jwt.secret-base64=Nr1RzuxLf4cAjVzMU5PLVx6kDYdmN6FD56VAq2Hn/mA=
jwt.secret.key=$2a$12$.qy.6yn0geTRmkwaaPaJMewc72nlyfwPjwgGB.4SjEbfTYElPUavi
jwt.expiration=600000
jwt.refresh.expiration=86400000
security.cors.allowed-origins:http://localhost:4200,http://localhost:5173

server.error.include-message=always

spring.jackson.date-format=yyyy-MM-dd HH:mm:ss
spring.jackson.serialization.write-dates-as-timestamps=false

logging.level.root=INFO
logging.level.org.springframework.security=INFO
logging.level.pe.com.colegio=DEBUG
>>>>>>> b3c5c7f (Update README.md)
