import axios from 'axios';
import React, { useEffect, useState } from 'react'

type Props = {}

const About = (props: Props) => {
  const [canUpdate, setCanUpdate] = useState<boolean>(true);
  const [loading, setLoading] = useState<boolean>(true);
  
  useEffect(() => {
      const fetchData = async () =>{
        setLoading(true);
        try {
          const response = await axios.get("http://vlasopoulos.ddns.net:8080/api/v1/update");
          setCanUpdate(response.data);
        } catch (error) {
          console.error("Error!");
        }
        setLoading(false);
      }
      fetchData();
    }, []);

  const update = () => {
    const response = axios.post("http://vlasopoulos.ddns.net:8080/api/v1/", "update-database", {headers: {'Content-Type': 'text/plain'}});
    setCanUpdate(false);
  };

  return (
    <div className='margin-page'>
      <div className='page'>
        <p>Project:</p>
        <ul>
          <li><a href="https://github.com/vlasopoulos/FullStackMovieDatabase">Project Github repository</a></li>
          <li><a href="https://hub.docker.com/r/vlasopoulos/fullstackmoviedatabase">Docker hub image</a></li>
          <li><a href="https://vlasopoulos.github.io/full-stack-movie-database/">Project page</a></li>
          <li><a href="https://vlasopoulos.github.io/full-stack-movie-database-blog/">Project blog post</a></li>
        </ul>
        <p>Vasilis Vlasopoulos:</p>
        <ul>
          <li><a href="https://vlasopoulos.github.io">Page</a></li>
          <li><a href="https://github.com/vlasopoulos">Github profile</a></li>
          <li><a href="mailto:vlasopoulos.v@gmail.com">e-mail: vlasopoulos.v@gmail.com</a></li>
        </ul>
        {canUpdate? <button onClick={update}>Update Database</button> : <button disabled={true}>Database currently updating</button>}
      </div>
    </div>
  )
}

export default About