### Migration Versioning

The version specified in the POM file must match the version of the latest migration 
to ensure alignment between the database schema and the project artifact.

For example, if the latest migration file is named `V0099__<migration_description>.sql`, 
then the corresponding POM version should be `<version>0099</version>`.

This alignment ensures that artifact release versions and migration versions are consistent, 
providing traceability between schema changes and artifact releases.
