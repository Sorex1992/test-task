CREATE TABLE IF NOT EXISTS public.user_table (
    ldap_id UUID PRIMARY KEY,
    ldap_login VARCHAR(255),
    name VARCHAR(255),
    surname VARCHAR(255)
);
