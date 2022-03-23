CREATE TABLE user_watched (
    tconst text PRIMARY KEY REFERENCES title_basics(tconst)
);

CREATE TABLE user_watchlist (
    tconst text PRIMARY KEY REFERENCES title_basics(tconst)
);

CREATE TABLE user_ratings (
    tconst text PRIMARY KEY REFERENCES title_basics(tconst),
    user_rating int
);