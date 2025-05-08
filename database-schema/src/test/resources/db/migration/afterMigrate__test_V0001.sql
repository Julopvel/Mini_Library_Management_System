DO
$$
    BEGIN
        IF NOT EXISTS (SELECT 1 FROM pg_namespace WHERE nspname = '${schema}') THEN
            RAISE EXCEPTION 'Schema "${schema}" does not exist';
        END IF;
    END
$$;
