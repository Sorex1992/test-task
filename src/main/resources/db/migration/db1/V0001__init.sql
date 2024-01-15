CREATE TABLE IF NOT EXISTS public.users (
    user_id UUID PRIMARY KEY,
    login VARCHAR(255),
    first_name VARCHAR(255),
    last_name VARCHAR(255)
);
