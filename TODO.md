- USER GET
- ACCOUNT GET
- ENVIRONMENT
- FF
- Client
- Web
- Migrations
- USER e-mail, multiplas accounts
- Spring format

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
