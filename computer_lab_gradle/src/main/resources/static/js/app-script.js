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
        return await response.json();
    } else {
        throw new Error(await response.json());
    }
}

async function postRequest(path, data) {
    let token = localStorage.getItem('accessToken');
    if (token === null) {
        return;
    }

    let response = await fetch(path, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + token
        },
        body: JSON.stringify(data)
    });

    if (response.ok) {
        return await response.json();
    } else {
        throw new Error(await response.json());
    }
}

async function putRequest(path, data) {
    let token = localStorage.getItem('accessToken');
    if (token === null) {
        return;
    }

    let response = await fetch(path, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + token
        },
        body: JSON.stringify(data)
    });

    if (response.ok) {
        return await response.json();
    } else {
        throw new Error(await response.json());
    }
}

async function deleteRequest(path) {
    let token = localStorage.getItem('accessToken');
    if (token === null) {
        return;
    }

    let response = await fetch(path, {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + token
        }
    });

    if (response.ok) {
        return await response.json();
    } else {
        throw new Error(await response.json());
    }

}

async function getUser() {
    let token = localStorage.getItem('accessToken');
    if (token === null) {
        return;
    }

    let user = await getRequest('/api/auth/user');

    localStorage.setItem('user', JSON.stringify(user));
    hideAdminOnlyElements();

    return user;
}

function hideAdminOnlyElements() {
    let user = localStorage.getItem('user');
    if (user !== null && user !== undefined && user !== '') {
        let roles = JSON.parse(user).roles;
        if (roles.includes('ADMIN')) {
            // show if it is admin
            let buttons = document.getElementsByClassName('admin-only-element');
            for (let i = 0; i < buttons.length; i++) {
                buttons[i].style.display = 'inline-block';
            }

            return;
        }
    }

    // hide if it is not admin or it is null
    let buttons = document.getElementsByClassName('admin-only-element');
    for (let i = 0; i < buttons.length; i++) {
        buttons[i].style.display = 'none';
    }
}

hideAdminOnlyElements();
