import React from 'react'

type Props = {}

const Home = (props: Props) => {
  return (
    <div className='margin-page'>
      <div className='page'>
        <h1 className='h1-centered'>Full stack movie database</h1>
        <p className='p-centered'>Technologies used:</p>
        <div className='image-container'>
          <img src="java-logo.png" alt="java logo" />
          <img src="spring-boot-logo.png" alt="spring boot logo" />
          <img src="postgres-logo.png" alt="postgres logo" />
          <img src="docker-logo.png" alt="docker logo" />
          <img src="react-logo.png" alt="react logo" />
        </div>
        <div className='text-container'>
          <p>The backend is written in Java using the Spring Boot framework and connects to the Postgres database
            using <code>JDBCTemplate</code>. It's containerized in docker with docker-compose using the postgres 
            image for the database and a custom image that runs the app.
          </p>
          <p>The app is packed as a whole in one .war package, containing the backend and the frontend.<br />
          Frontend listens at /<br />Backend listens at /api/v1/</p>
          <p>The frontend is written in React using Typescript.</p>
          <p>The data comes from the publicly available <a href='https://www.imdb.com/interfaces/'>IMDB datasets</a> that, according
          to IMDB, are daily updated. The ETL process is: the app downloads the .gz files, extracts them, transforms 
          and cleans them (removing errors) and upserts them into the database.</p>
          <p>The user can search titles and persons, add titles to watched/watchlist and rate them.</p>
          <p>Searching is implemented using the <code>tsvector</code> and <code>tsquery</code> capabilities
          of Postgres.</p>
        </div>
      </div>
    </div>
  )
}

export default Home