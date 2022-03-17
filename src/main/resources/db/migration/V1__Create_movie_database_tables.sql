CREATE TABLE title_basics
(
    tconst          text PRIMARY KEY,
    title_type      text,
    primary_title   text,
    original_title  text,
    is_adult        smallint,
    start_year      int,
    end_year        int,
    runtime_minutes int,
    genres          text[]
);

CREATE TABLE name_basics
(
    nconst             text PRIMARY KEY,
    primary_name       text,
    birth_year         int,
    death_year         int,
    primary_profession text[],
    known_for_titles   text[]
);

CREATE TABLE title_crew
(
    tconst    text PRIMARY KEY REFERENCES title_basics (tconst),
    directors text[],
    writers   text[]
);

CREATE TABLE title_principals
(
    tconst     text REFERENCES title_basics (tconst),
    ordering   int,
    nconst     text,
    category   text,
    job        text,
    characters text[],
    PRIMARY KEY (tconst, ordering)
);

CREATE TABLE title_episode
(
    tconst         text PRIMARY KEY,
    parent_tconst  text REFERENCES title_basics (tconst),
    season_number  int,
    episode_number int
);

CREATE TABLE title_akas
(
    tconst            text REFERENCES title_basics (tconst),
    ordering          int,
    localized_title   text,
    region            text,
    language          text,
    types             text,
    attributes        text,
    is_original_title smallint,
    PRIMARY KEY (tconst, ordering)
);

CREATE TABLE title_ratings
(
    tconst         text PRIMARY KEY REFERENCES title_basics (tconst),
    average_rating numeric(3, 1),
    num_votes      int
);