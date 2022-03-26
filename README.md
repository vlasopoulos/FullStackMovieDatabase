# FullStackMovieDatabase
A dockerized full stack Movie Database using Java Spring Boot, Postgres, Flyway and React using the IMDB datasets.

---
To run outside of docker you will have to change the database url in the application.properties file from

`spring.datasource.jdbc-url=jdbc:postgresql://postgresmoviedatabase:5432/movie_database`

to

`spring.datasource.jdbc-url=jdbc:postgresql://localhost:5432/movie_database`

---
The backend is written in Java using the Spring Boot framework and connects to the Postgres database using JDBCTemplate. It's containerized in docker with docker-compose using the postgres image for the database and a custom image that runs the app.

The app is packed as a whole in one .war package, containing the backend and the frontend.
Frontend listens at /
Backend listens at /api/v1/

The frontend is written in React using Typescript.

The data comes from the publicly available [IMDB datasets](https://www.imdb.com/interfaces/) that, according to IMDB, are daily updated. The ETL process is: the app downloads the .gz files, extracts them, transforms and cleans them (removing errors) and upserts them into the database.

The user can search titles and persons, add titles to watched/watchlist and rate them.

Searching is implemented using the tsvector and tsquery capabilities of Postgres.
