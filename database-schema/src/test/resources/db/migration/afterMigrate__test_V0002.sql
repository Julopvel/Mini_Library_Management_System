-- Step 1: Insert a test record

INSERT INTO ${schema}.books (title, author, isbn, availability_status)
VALUES ('The Demon-Haunted World', 'Carl Sagan', '0-345-40946-9', true);

-- Step 2: Update the test record

UPDATE ${schema}.books
SET title = 'The Demon-Haunted World: Science as a Candle in the Dark', availability_status = false
WHERE isbn = '0-345-40946-9';

-- Step 3: Delete the test record

DELETE FROM ${schema}.books WHERE isbn = '0-345-40946-9';
