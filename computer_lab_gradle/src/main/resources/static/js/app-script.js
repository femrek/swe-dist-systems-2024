
async function getRequest(path) {
    let token = localStorage.getItem('accessToken');
    if (token === null) {
        return;
    }

    let response = await fetch(path, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + token
        }
    });

    if (response.ok) {
       return  await response.json();
    }
    else {
        throw new Error(await response.json());
    }
}

async function getUser() {
    let token = localStorage.getItem('accessToken');
    if (token === null) {
        return;
    }

    let user =  await getRequest('/api/auth/user');
    localStorage.setItem('user', JSON.stringify(user));
    return user;
}
