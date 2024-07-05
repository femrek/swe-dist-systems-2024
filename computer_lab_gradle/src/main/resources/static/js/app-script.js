async function getRequest(path, json = true) {
    let token = localStorage.getItem('accessToken');
    if (token === null) {
        return;
    }

    let headers = json ? {
        'Content-Type': 'application/json',
        'Authorization': 'Bearer ' + token
    } : {
        'Authorization': 'Bearer ' + token
    }

    let response = await fetch(path, {
        method: 'GET',
        headers: headers
    });

    if (response.ok) {
        return await response.json();
    } else {
        let resBody = await response.text();
        throw new Error(resBody);
    }
}

async function postRequest(path, data = null, json = true) {
    let token = localStorage.getItem('accessToken');
    if (token === null) {
        return;
    }

    let headers = json ? {
        'Content-Type': 'application/json',
        'Authorization': 'Bearer ' + token
    } : {
        'Authorization': 'Bearer ' + token
    };

    let response = await fetch(path, {
        method: 'POST',
        headers: headers,
        body: data == null ? null : json ? JSON.stringify(data) : data
    });

    if (response.ok) {
        return await response.json();
    } else {
        let resBody = await response.text();
        throw new Error(resBody);
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
        let resBody = await response.text();
        throw new Error(resBody);
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
        let resBody = await response.text();
        throw new Error(resBody);
    }

}

async function getUser() {
    let token = localStorage.getItem('accessToken');
    if (token === null) {
        return;
    }

    let user = await getRequest('/api/auth/user');

    localStorage.setItem('user', JSON.stringify(user));
    setElementsVisibility()

    return user;
}

function setAdminOnlyElementsVisibility() {
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

function setAuthOnlyElementsVisibility() {
    let user = localStorage.getItem('user');
    if (user === null || user === undefined || user === '') {
        // hide if it is not authenticated
        let buttons = document.getElementsByClassName('auth-only-element');
        for (let i = 0; i < buttons.length; i++) {
            buttons[i].style.display = 'none';
        }
    } else {
        // show if it is authenticated
        let buttons = document.getElementsByClassName('auth-only-element');
        for (let i = 0; i < buttons.length; i++) {
            buttons[i].style.display = 'inline-block';
        }
    }
}

function setElementsVisibility() {
    setAdminOnlyElementsVisibility();
    setAuthOnlyElementsVisibility();
}

setElementsVisibility();
