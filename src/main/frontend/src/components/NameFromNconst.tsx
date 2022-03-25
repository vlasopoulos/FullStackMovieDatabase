import React, { useEffect, useState } from 'react'
import { NamesFromNconsts} from '../Interfaces';

type Props = {
    nconst: string;
    nameData: NamesFromNconsts[];
    setPage: React.Dispatch<React.SetStateAction<string>>;
}




const NameFromNconst = (props: Props) => {

    const [name, setName] = useState<string>("")

    useEffect(() => {
        for (let namePair of props.nameData) {
            if (props.nconst == namePair.nconst) setName(namePair.primary_name)
        }
    },[]);


    return (
        <span className='clickable-link' onClick={(e)=>props.setPage("person" + props.nconst)}>{name}</span>
    )
    
    }

export default NameFromNconst