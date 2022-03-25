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
        <p>Frontend listens at /<br />Backend listens at /api/v1/</p>
        <p></p>
      </div>
    </div>
  )
}

export default Home