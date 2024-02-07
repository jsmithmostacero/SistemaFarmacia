let baseUrl = 'http://localhost:8080/';
export default baseUrl;

export const sleep = (time: number) =>
    new Promise((res) => setTimeout(res, time));
