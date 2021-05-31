[![QualityGate](https://sonarcloud.io/api/project_badges/measure?project=Dathin_fff&metric=alert_status)](https://sonarcloud.io/dashboard?id=Dathin_fff)
[![Duplications](https://sonarcloud.io/api/project_badges/measure?project=Dathin_fff&metric=duplicated_lines_density)](https://sonarcloud.io/dashboard?id=fff)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=Dathin_fff&metric=coverage)](https://sonarcloud.io/dashboard?id=fff)

### Description
TODO

### How To Docker Compose
```bash
docker-compose up -d
```

### How To Run Migrations
```bash
mvn liquibase:update
```

### How To Clean Changelog
```postgresql
drop table databasechangelog;
drop table databasechangeloglock;
```
