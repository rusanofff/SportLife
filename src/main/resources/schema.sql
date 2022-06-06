CREATE TABLE IF NOT EXISTS Activity
(
    id integer NOT NULL,
    name character varying COLLATE pg_catalog."default" NOT NULL,
    activity_type character varying COLLATE pg_catalog."default" NOT NULL,
    event_type_opportunities character varying COLLATE pg_catalog."default",
    CONSTRAINT "Activity_pkey" PRIMARY KEY (id)
    );

CREATE TABLE IF NOT EXISTS "user"
(
    id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    password character varying COLLATE pg_catalog."default" NOT NULL,
    email character varying COLLATE pg_catalog."default" NOT NULL,
    name character varying COLLATE pg_catalog."default" NOT NULL,
    surname character varying COLLATE pg_catalog."default" NOT NULL,
    description character varying COLLATE pg_catalog."default",
    phone character varying COLLATE pg_catalog."default",
    town character varying COLLATE pg_catalog."default" NOT NULL,
    date_of_birth date,
    image bytea[],
    CONSTRAINT "Profile_pkey" PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS SportEvent
(
    id integer NOT NULL,
    name character varying COLLATE pg_catalog."default" NOT NULL,
    event_type character varying COLLATE pg_catalog."default" NOT NULL,
    start_date timestamp without time zone NOT NULL,
    description character varying COLLATE pg_catalog."default",
    user_id integer NOT NULL,
    town character varying COLLATE pg_catalog."default" NOT NULL,
    adress character varying COLLATE pg_catalog."default" NOT NULL,
    activity_id integer NOT NULL,
    min_count integer,
    max_count integer NOT NULL,
    min_count_team integer,
    max_count_team integer,
    CONSTRAINT "Event_pkey" PRIMARY KEY (id),
    CONSTRAINT account_id FOREIGN KEY (user_id)
        REFERENCES "user" (id),
    CONSTRAINT activity_id FOREIGN KEY (activity_id)
        REFERENCES Activity (id)
);

CREATE TABLE IF NOT EXISTS Team
(
    id integer NOT NULL,
    name character varying COLLATE pg_catalog."default" NOT NULL,
    event_id integer NOT NULL,
    count integer NOT NULL,
    CONSTRAINT "Team_pkey" PRIMARY KEY (id),
    CONSTRAINT event_id FOREIGN KEY (event_id)
        REFERENCES SportEvent (id)
);

CREATE TABLE IF NOT EXISTS UserEvent
(
    user_id integer NOT NULL,
    event_id integer NOT NULL,
    id integer NOT NULL,
    CONSTRAINT "User_event_pkey" PRIMARY KEY (id),
    CONSTRAINT account_id FOREIGN KEY (user_id)
        REFERENCES "user" (id),
    CONSTRAINT event_id FOREIGN KEY (event_id)
        REFERENCES SportEvent (id)
);

CREATE TABLE IF NOT EXISTS UserTeam
(
    id integer NOT NULL,
    user_id integer NOT NULL,
    team_id integer NOT NULL,
    CONSTRAINT "ProfileTeam_pkey" PRIMARY KEY (id),
    CONSTRAINT account_id FOREIGN KEY (user_id)
        REFERENCES "user" (id),
    CONSTRAINT team_id FOREIGN KEY (team_id)
        REFERENCES Team (id)
);
