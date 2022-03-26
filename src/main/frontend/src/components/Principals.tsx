import axios from 'axios';
import React, { useEffect, useState } from 'react'
import { NamesFromNconsts, PrincipalsRootObject } from '../Interfaces';
import NameFromNconst from './NameFromNconst';

type Props = {
    tconst: string;
    setPage: React.Dispatch<React.SetStateAction<string>>;
}

const Principals = (props: Props) => {

    const [principalData, setPrincipalData] = useState<PrincipalsRootObject[]>();
    const [loading, setLoading] = useState(true);
    const [nameData, setNameData] = useState<NamesFromNconsts[]>([{nconst:"",primary_name:""}]);

    const fetchURL: string = "http://localhost:8080/api/v1/principals/" + props.tconst;

    useEffect(() => {
    const fetchAllData = async () => {
        setLoading(true);
        try {
            const fetchData = await axios.get(fetchURL);
            const localData: PrincipalsRootObject[] = fetchData.data;
            setPrincipalData(localData);
            let fetchNamesURL: string = "http://localhost:8080/api/v1/person-names?";
            localData.forEach(element => fetchNamesURL += "nconst=" + element.nconst + "&");
            
            const fetchNameData = await axios.get(fetchNamesURL.slice(0,-1));
            const localNameData: NamesFromNconsts[] = fetchNameData.data;
            setNameData(localNameData);
        } catch (error) {
            console.error("Error fetching names!");
        }
        setLoading(false);
    }
    fetchAllData();
    }, [fetchURL]);

    if (loading) return <div className='no-results'><p>Loading...</p></div>;
    
    return (
        <div className='principals'>
            <table >
                <tr>
                    <th>Name</th>
                    <th>Job</th>
                    <th>Characters</th>
                </tr>
                {principalData!.map((content) => {
                return (
                <tr>
                    <td><NameFromNconst nconst={content.nconst} nameData={nameData} setPage = {props.setPage} /></td>
                    <td>{content.category}</td>
                    <td>{(content.characters.toString() == "N") ? "N/A" : content.characters.toString().replaceAll(",",", ")}</td>
                </tr>
                );
                })}
            </table>
        </div>
    )
}

export default Principals