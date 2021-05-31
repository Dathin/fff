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
- Sonarq
- Unit test
- USER e-mail, multiplas accounts
- USER GET
- ACCOUNT GET
- ENVIRONMENT
- FF
- Client
- Web

swagger, spring secutiry, redis, unit test, default error handling

ACCOUNT
id
name
// POST - create account

USER
id
name
password_hash
salt
(bcrypt)
// POST - create user
//PUT - update user
//POST - login (token com user_id e account_id)
//DELETE - user
//- verify user

ENVIRONMENT
id
name
account_id
user_id
//GET - environment
//POST - create_account_env
//PUT - update env

FEATURE_FLAG
id
name - index
environment_id  - index
value
default_value
//GET - feature flag (id, name)
//POST - create feature_flag
//PUT - update feature_flag
//DELETE - delete feature_flag