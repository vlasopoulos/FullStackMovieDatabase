import React, { useEffect, useState } from 'react'
import { NamesFromTconsts} from '../Interfaces';

type Props = {
    tconst: string;
    nameData: NamesFromTconsts[];
    setPage: React.Dispatch<React.SetStateAction<string>>;
}




const NameFromTconst = (props: Props) => {

    const [name, setName] = useState<string>("")

    useEffect(() => {
        for (let namePair of props.nameData) {
            if (props.tconst == namePair.tconst) setName(namePair.primary_title)
        }
    },[]);


    return (
        <span className='clickable-link' onClick={(e)=>props.setPage("title" + props.tconst)}>{name}</span>
    )
    
    }

export default NameFromTconst