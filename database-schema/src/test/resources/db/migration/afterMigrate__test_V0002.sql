-- Step 1: Insert a test record

INSERT INTO ${schema}.books (title, author, isbn, availability_status)
VALUES ('Test Book', 'Test autor 01', 'test-isbn-123', true);

-- Step 2: Update the test record

UPDATE ${schema}.books
SET title = 'Test Book 01', availability_status = false
WHERE isbn = 'test-isbn-123';

-- Step 3: Delete the test record

DELETE FROM ${schema}.books WHERE isbn = 'test-isbn-123';
