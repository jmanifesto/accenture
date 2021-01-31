import { useState, useCallback } from "react";

const useFetchData = url => {
    let [names, setNames] = useState([]);
    let [isSending, setIsSending] = useState(false);

    const sendRequest = useCallback(() => {
        // don't send again while we are sending
        if (isSending) return;
        // send the actual request
        fetch(url)
            .then(response => response.json())
            .then(data => {
                setNames(data);
            })
            .catch(err => {
                console.error(err);
            })
            .finally(() => {
                setIsSending(false);
            });

        setIsSending(true);
    }, [isSending, url]);

    return {
        names,
        isSending,
        sendRequest,
    };
};

export default useFetchData;
