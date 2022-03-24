import axios from 'axios'
import React, { ReactEventHandler, useState } from 'react'

type Props = {
    tconst: string,
    userRating: number
}

const UserRating = (props: Props) => {
  const [userRating,setUserRating] = useState<number>(props.userRating)

  const handleChange = (e:React.ChangeEvent<HTMLSelectElement>) => {
    setUserRating(parseInt(e.target.value));
    const newRating = parseInt(e.target.value);
    if (userRating === 0){
      if (newRating != 0) {
      //send insert
      const body = { tconst : props.tconst, rating: newRating };
      axios.post("http://localhost:8080/api/v1/user/rating",body);
      }
    } else {
      if (newRating === 0) {
        //send delete
        axios.delete("http://localhost:8080/api/v1/user/rating/" + props.tconst)
      } else {
        //send update
        const body = { tconst : props.tconst, rating: newRating };
        axios.put("http://localhost:8080/api/v1/user/rating",body);
      }
    }
  }

  return (
    <select defaultValue={userRating} name="user-rating" className='user-rating clickable' onChange={handleChange}>
        <option value="0">N/A</option>
        <option value="1">1</option>
        <option value="2">2</option>
        <option value="3">3</option>
        <option value="4">4</option>
        <option value="5">5</option>
        <option value="6">6</option>
        <option value="7">7</option>
        <option value="8">8</option>
        <option value="9">9</option>
        <option value="10">10</option>
    </select>
  )
}

export default UserRating