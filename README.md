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

TODO
- Mapstruct
- Folder refacotr
- Elsewhere endpoints
- Client