DO $$
BEGIN
	IF NOT EXISTS (SELECT 1 FROM pg_namespace WHERE nspname = '${schema}') THEN
		CREATE SCHEMA ${schema};
	ELSE
		RAISE INFO 'Schema "${schema}" already exists';
	END IF;
END $$;
