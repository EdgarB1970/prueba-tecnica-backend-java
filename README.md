# Prueba Técnica Backend Java - Arquitectura de Microservicios

Este proyecto es una implementación de una arquitectura de microservicios en Java utilizando **Spring Boot**, **IntelliJ Community**, y una base de datos **PostgreSQL**. Los microservicios están diseñados para gestionar clientes, cuentas y movimientos bancarios, con comunicación asincrónica a través de **Apache Kafka**. El proyecto cumple con los requisitos de una prueba técnica para un perfil **Senior**, incluyendo la implementación de **CRUDs**, validaciones de negocio, reportes, pruebas unitarias y de integración, y despliegue en contenedores Docker. Además, se ha implementado una **arquitectura hexagonal** (puertos y adaptadores) para cumplir con los principios **SOLID**.

---

## **Tabla de Contenidos**
1. [Requisitos](#requisitos)
2. [Instrucciones de Despliegue](#instrucciones-de-despliegue)
3. [Ejecución de Pruebas](#ejecución-de-pruebas)
4. [Endpoints de la API](#endpoints-de-la-api)
5. [Estructura del Proyecto](#estructura-del-proyecto)
6. [Comunicación entre Microservicios](#comunicación-entre-microservicios)
7. [Consideraciones de Diseño](#consideraciones-de-diseño)
8. [Contacto](#contacto)

---

## **Requisitos**

Para ejecutar este proyecto, necesitas las siguientes herramientas instaladas en tu entorno de desarrollo:

- **Java 17**: JDK 17 o superior.
- **Maven 3.8+**: Para la gestión de dependencias y construcción del proyecto.
- **Docker**: Para desplegar los microservicios y dependencias (Kafka, Zookeeper).
- **Docker Compose**: Para orquestar los contenedores.
- **Postman**: Para probar los endpoints de la API.
- **Kafka**: Para la comunicación asincrónica entre microservicios.

---

## **Instrucciones de Despliegue**

### 1. Clonar el Repositorio

Clona el repositorio en tu máquina local:
git clone https://github.com/tu-usuario/prueba-tecnica-backend-java.git
cd prueba-tecnica-backend-java.

### 2. Construir el Proyecto
Compila el proyecto usando Maven
mvn clean install

### 3. Desplegar con Docker Compose
El proyecto incluye un archivo docker-compose.yml para desplegar los microservicios, Kafka y Zookeeper. Ejecuta el siguiente comando:
docker-compose up --build

Esto desplegará los siguientes servicios:
- Microservicio de Cliente: Disponible en http://localhost:8081
- Microservicio de Cuenta: Disponible en http://localhost:8080
- Microservicio de Reporte: Disponible en http://localhost:8082
- Kafka: Disponible en localhost:9092
- Zookeeper: Disponible en localhost:2181

##  **Ejecución de Pruebas**
### 1.Pruebas Unitarias
Para ejecutar las pruebas unitarias, usa el siguiente comando:
mvn test
### 2. Pruebas de Integración
Las pruebas de integración se ejecutan automáticamente al construir el proyecto con mvn clean install.
También puedes ejecutarlas manualmente con:
mvn verify

## **Endpoints de la Api**
### Microservicio de Cliente (http://localhost:8081)
- GET/api/clientes: Obtener todos los clientes.
- GET/api/clientes/{id}: Obtener un cliente por ID.
- POST/api/clientes: Crear un nuevo cliente.
- PUT/api/clientes/{id}:Actualizar un cliente existente.
- DELETE/api/clientes/{id}: Eliminar un cliente.

### Microservicio de Cuenta (http://localhost:8080)
- GET/api/cuentas: Obtener todas las cuentas.
- GET /api/cuentas/{id}: Obtener una cuenta por ID.
- POST /api/cuentas: Crear una nueva cuenta.
- PUT /api/cuentas/{id}: Actualizar una cuenta existente.
- DELETE /api/cuentas/{id}: Eliminar una cuenta.

### Microservicio de Reporte(http://localhost:8082)
- GET/api/reportes: Genera un reporte de estado de cuenta para un cliente en un rango de fechas.

## **Estructura del proyecto**
El proyecto está dividido en tres microservicios principales:
### 1. microservice-cliente
- Gestiona las entidades Cliente y Persona.
- Expone endpoints para operaciones CRUD.
- Se comunica con el microservicio de Cuenta a través de Kafka.

## 2. microservice-cuenta
- Gestiona las entidades Cuenta y Movimiento.
- Expone endpoints para operaciones CRUD.
- Publica eventos de movimientos en Kafka.

## 3. banco-reporte
- Genera reportes de estado de cuenta.
- Consume eventos de movimientos desde Kafka para actualizar los reportes.

## ** Comunicación entre Microservicios**
La comunicación entre los microservicios se realiza de forma asincrónica utilizando Apache Kafka. 
Los eventos de movimientos se publican en el tópico movimientos, y el microservicio de Reporte los consume para generar reportes actualizados.

## 1. Tipos de Kafka
- movimientos: Para eventos relacionados con movimientos de cuentas.
- clientes: Para eventos relacionados con clientes (opcional, no implementado en esta versión).

## **Consideraciones de Diseño**
- Resiliencia: Se utiliza Resilience4j para manejar fallos en la comunicación entre microservicios.
- Escalabilidad: La arquitectura de microservicios permite escalar horizontalmente cada servicio de forma independiente.
- Pruebas: Se han implementado pruebas unitarias y de integración para garantizar la calidad del código.
- Despliegue: El proyecto está diseñado para ser desplegado en contenedores Docker, lo que facilita su implementación en entornos de producción.

## **Contacto**
Si tienes alguna duda o sugerencia, no dudes en contactarme:
- Nombre: Edgar Jimmy Benavides López
- Email: edgarjimmyb@hotmail.com
- Linkedin: https://www.linkedin.com/in/edgar-benavides-b3972750/

