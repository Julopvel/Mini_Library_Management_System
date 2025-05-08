### Migration Testing

For each migration script located in `src/main/resources/db/migration/*.sql`, there 
must be 
a corresponding test script in `src/test/resources/db/migration/afterMigrate__test_*.sql`.

The test script should validate that the migration has been successfully applied 
by checking the expected database state. 

For example, if the migration file is named `V0123__<migration_description>.sql`, then 
the corresponding test file should be named `afterMigrate__test_V0123.sql`.

### Reference Documentation

For more information on Flyway's callback system and integration with migration testing, 
refer to the official [Flyway Callback Concept](https://documentation.red-gate.com/flyway/flyway-concepts/callbacks).
