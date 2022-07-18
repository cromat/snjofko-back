CREATE TABLE public.app_user
(
    id        bigserial,
    full_name varchar(50),
    username  varchar(50),
    email     varchar(50),
    phone     varchar(50),
    password  varchar(50),
    PRIMARY KEY (id)
);

CREATE TABLE public.item
(
    id        bigserial,
    name      varchar(50) NOT NULL,
    category  varchar(50),
    description  varchar(50),
    created   timestamp WITH TIME ZONE,
    modified  timestamp WITH TIME ZONE,
    is_active boolean,
    user_id bigint not null,
    PRIMARY KEY (id),
    foreign key (user_id) references app_user (id)
);