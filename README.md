# Hexagonal Template - Java 25

Plantilla de microservicio con arquitectura hexagonal (ports & adapters), Java 25, Spring Boot 4.1 y persistencia JPA/MySQL.

## Stack

- Java 25
- Spring Boot 4.1.0
- Spring Web MVC
- Spring Data JPA
- Jakarta Validation
- MySQL Connector/J
- H2 para tests
- Maven 3.9+

## Arquitectura

```text
adapters/in/api/rest
        |
        v
application/ports/in
        |
        v
application/service
        |
        v
application/ports/out
        |
        v
adapters/out/mysql/springdata
        |
        v
domain
```

El dominio y los puertos no dependen de Spring. El wiring de implementaciones está centralizado en `BeansConfiguration`.

## Requisitos

- JDK 25
- Maven 3.9+
- MySQL 8.x para ejecución normal
- Docker/Docker Compose opcional

## Build y tests

```bash
mvn clean verify
```

Los tests usan H2 en memoria con perfil `test`, por lo que **no necesitan MySQL externo**. Incluyen:

- carga del contexto Spring;
- creación de un pago (`201`);
- consulta de pagos (`200`);
- consulta vacía (`204`);
- rechazo de request inválido (`400`).

## Ejecutar localmente

La infraestructura local está en `infrastructure/`. Para levantar únicamente MySQL:

```bash
docker compose -f infrastructure/compose.yaml up -d
```

Verifica que MySQL esté listo:

```bash
docker compose -f infrastructure/compose.yaml ps
```

El servicio `mysql` debe aparecer como `healthy`. Luego ejecuta:

```bash
mvn spring-boot:run
```

No es necesario configurar variables para el caso local: los valores por defecto de Spring coinciden con los de `infrastructure/compose.yaml`.

Configuración por variables de entorno:

- `MYSQL_HOST` (default `localhost`)
- `MYSQL_PORT` (default `3306`)
- `MYSQL_DATABASE` (default `payment_management`)
- `MYSQL_USER` (default `payment_user`)
- `MYSQL_PASSWORD` (default `payment_password`)
- `JPA_DDL_AUTO` (default `update`)

## API

Base path:

```text
/payment-management/v1/payments
```

### Crear pago

```bash
curl -X POST "http://localhost:8080/payment-management/v1/payments" \
  -H "Content-Type: application/json" \
  -d '{"idTransaction":"abc-123","nombre":"Juan Perez","monto":123.45}'
```

Respuesta esperada: HTTP `201 Created`.

### Listar pagos

```bash
curl "http://localhost:8080/payment-management/v1/payments"
```

Devuelve `200 OK` con datos o `204 No Content` si no existen pagos.

## Infraestructura

La carpeta `infrastructure/` contiene:

```text
infrastructure/
├── .env.example
├── compose.yaml
└── README.md
```

`infrastructure/compose.yaml` aprovisiona MySQL con:

- base `payment_management`;
- usuario de aplicación `payment_user`;
- volumen persistente;
- healthcheck;
- red Docker dedicada;
- creación automática de la base y del usuario mediante las variables soportadas por la imagen oficial de MySQL.

Para personalizar credenciales, copia `infrastructure/.env.example` a `infrastructure/.env` y usa `--env-file infrastructure/.env`.

## Docker full stack

El `compose.yaml` de la raíz permite levantar aplicación + MySQL. Primero genera el JAR:

```bash
mvn clean package
```

Luego:

```bash
docker compose up --build
```

La aplicación espera a que MySQL pase su healthcheck antes de arrancar.

## Cambios respecto al template original

- Java 17 -> Java 25.
- Spring Boot 3.2.1 -> Spring Boot 4.1.0.
- MySQL driver migrado de `mysql:mysql-connector-java` a `com.mysql:mysql-connector-j`.
- Eliminación de Lombok para reducir acoplamiento de compilación y annotation processing.
- DTOs y response wrapper modernizados con `record`.
- Separación de `PaymentRequestDTO` / `PaymentResponseDTO` para no exponer el dominio directamente por REST.
- Tests desacoplados de MySQL mediante H2.
- El controlador REST depende de puertos de entrada, no de la implementación `PaymentService`.
- El `POST` ahora devuelve `201 Created` en lugar de `null`.
- Persistencia corregida para conservar `idTransaction` y el ID generado por la base de datos.
- `monto` migrado de `Double` a `BigDecimal` con precisión/escala en JPA.
- `GenerationType.IDENTITY` para el ID de MySQL.
- Validaciones de request agregadas.
- Docker runtime actualizado a Java 25 y usuario no-root.
- Nueva carpeta `infrastructure/` para aprovisionar MySQL local de forma reproducible.
- Credenciales locales alineadas entre Spring y Docker, usando un usuario de aplicación no-root.
- Healthcheck de MySQL para evitar que la aplicación arranque antes de que la base esté disponible.
