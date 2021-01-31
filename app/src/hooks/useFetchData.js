import { useState, useCallback } from "react";

const useFetchData = (
    url,
    options= {}) => {
    let [data, setData] = useState([]);
    let [isSending, setIsSending] = useState(false);

    const defaultOptions = {
        headers: { 'Content-Type': 'application/json' },
        method: 'POST',
        'Accept': 'application/json',
    };

    const sendRequest = useCallback(requestOptions => {


        if (isSending){
            return;
        }
        fetch(url, requestOptions)
            .then(response => response.json())
            .then(resData => {
                setData(resData);
            })
            .catch(err => {
                console.error(err);
            })
            .finally(() => {
                setIsSending(false);
            });
        setIsSending(true);
    }, [isSending, url, options.body]);

    return {
        data,
        isSending,
        sendRequest,
    };
};

export default useFetchData;
