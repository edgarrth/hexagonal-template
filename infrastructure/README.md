# Local infrastructure

This folder provisions the external dependencies required by the application.
For this template, the only mandatory dependency is MySQL.

## Start MySQL

From the project root:

```bash
docker compose -f infrastructure/compose.yaml up -d
```

Check that it is ready:

```bash
docker compose -f infrastructure/compose.yaml ps
```

The `mysql` service must appear as `healthy` before starting the Java application.

Then run the application from the project root:

```bash
mvn spring-boot:run
```

The application defaults match the infrastructure defaults:

- host: `localhost`
- port: `3306`
- database: `payment_management`
- user: `payment_user`
- password: `payment_password`

## Custom configuration

Copy the example file and change any value you need:

```bash
cp infrastructure/.env.example infrastructure/.env
```

Then use it explicitly:

```bash
docker compose --env-file infrastructure/.env -f infrastructure/compose.yaml up -d
```

Use the same values as environment variables when starting the application.

## Stop

```bash
docker compose -f infrastructure/compose.yaml down
```

To also delete the local database volume:

```bash
docker compose -f infrastructure/compose.yaml down -v
```
