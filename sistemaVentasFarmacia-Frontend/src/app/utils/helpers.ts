let baseUrl = 'http://localhost:8090/';
export default baseUrl;

export const sleep = (time: number) =>
    new Promise((res) => setTimeout(res, time));
