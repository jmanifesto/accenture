import React from "react";
import useFetchData from "../hooks/useFetchData";


export function UrlView() {
    const { sendRequest, isSending, names } = useFetchData('api/urls');
    sendRequest();
    /*const handleInputChange = event => {
        setUrl(event.target.value);
    };*/

    if(isSending){

    }

    return (
        <>
            {names.map(urls =>
                <div key={urls.id}>
                    {urls.name}
                </div>
            )}
        </>
    );
}
