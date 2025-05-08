-- Step 1: Insert a test record

INSERT INTO ${schema}.users (name, email, unique_library_id)
VALUES ('Valeria Fajardo', 'valeria@example.com', 'id0001');

-- Step 2: Update the test record

UPDATE ${schema}.users
SET name = 'Valeria Velasquez'
WHERE unique_library_id = 'id0001';

-- Step 3: Delete the test record

DELETE FROM ${schema}.users WHERE unique_library_id = 'id0001';
