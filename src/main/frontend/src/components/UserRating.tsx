import React from 'react'

type Props = {
    tconst: string,
    userRating: number
}

const UserRating = (props: Props) => {
  return (
    <select defaultValue={props.userRating} name="user-rating" className='user-rating'>
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