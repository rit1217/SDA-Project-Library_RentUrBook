
export const getUser = async () => {
    const userUrl = "http://<yourip>:8080/api/v1/user"; //remove ip everytime you commit for security
    
    try {
        const response = await fetch(userUrl);
        const json = await response.json();
        console.log(json);
        return json;
    } 
    catch (error) {
        console.error(error);
    }

}
