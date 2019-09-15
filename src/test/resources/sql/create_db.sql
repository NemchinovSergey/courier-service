-- Database: courierdb

-- DROP DATABASE courierdb;

CREATE DATABASE courierdb
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'Russian_Russia.1251'
    LC_CTYPE = 'Russian_Russia.1251'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;

CREATE OR REPLACE FUNCTION refresh_updated() RETURNS TRIGGER LANGUAGE PLPGSQL AS
$$
BEGIN
    NEW.updated := now();
    RETURN NEW;
END;
$$;