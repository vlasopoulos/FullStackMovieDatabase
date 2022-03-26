import React from 'react'

type Props = {}

const About = (props: Props) => {
  return (
    <div className='margin-page'>
      <div className='page'>
        <p>Project:</p>
        <ul>
          <li><a href="https://github.com/F3V3R/FullStackMovieDatabase">Project Github repository</a></li>
          <li><a href="https://f3v3r.github.io/full-stack-movie-database-blog/">Project page</a></li>
          <li><a href="https://f3v3r.github.io/full-stack-movie-database-blog/">Project blog post</a></li>
        </ul>
        <p>Vasilis Vlasopoulos:</p>
        <ul>
          <li><a href="https://f3v3r.github.io">Page</a></li>
          <li><a href="https://github.com/F3V3R">Github profile</a></li>
          <li><a href="mailto:vlasopoulos.v@gmail.com">E-mail</a></li>
        </ul>
      </div>
    </div>
  )
}

export default About