function lockButt(){
    let lb = document.getElementById('lockButt');
    let ub = document.getElementById('unlockButt');

    let id = 1;
    let newValue = true;

    let requestOptions = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json'
        },
        body: JSON.stringify({
            id: id,
            newValue: newValue
        })
    }

    fetch("http://localhost:8080/users/locking", requestOptions)
        .then(function(response) {
            if (response.ok) {
                lb.style.display = 'none';
                ub.style.display = 'block';
                return response.text();
            } else {
                throw new Error('Ошибка AJAX запроса');
            }
        })
        .then(function(data) {
            // Обработка успешного ответа от сервера
            console.log(data);
        })
        .catch(function(error) {
            // Обработка ошибки
            console.error(error);
        });


}

function unlockButt(){
    let lb = document.getElementById('lockButt');
    let ub = document.getElementById('unlockButt');

    let id = 1;
    let newValue = false;

    let requestOptions = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json'
        },
        body: JSON.stringify({
            id: id,
            newValue: newValue
        })
    }

    fetch("http://localhost:8080/users/locking", requestOptions)
        .then(function(response) {
            if (response.ok) {
                lb.style.display = 'block';
                ub.style.display = 'none';
                return response.text();
            } else {
                throw new Error('Ошибка AJAX запроса');
            }
        })
        .then(function(data) {
            // Обработка успешного ответа от сервера
            console.log(data);
        })
        .catch(function(error) {
            // Обработка ошибки
            console.error(error);
        });
}
function openButt(){
    let ob = document.getElementById('openButt');
    let cb = document.getElementById('closeButt');

    let id = 2;
    let newValue = true;

    let requestOptions = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json'
        },
        body: JSON.stringify({
            id: id,
            newValue: newValue
        })
    }

    fetch("http://localhost:8080/users/open", requestOptions)
        .then(function(response) {
            if (response.ok) {
                ob.style.display = 'none';
                cb.style.display = 'block';
                return response.text();
            } else {
                throw new Error('Ошибка AJAX запроса');
            }
        })
        .then(function(data) {
            // Обработка успешного ответа от сервера
            console.log(data);
        })
        .catch(function(error) {
            // Обработка ошибки
            console.error(error);
        });


}

function closeButt(){
    let ob = document.getElementById('openButt');
    let cb = document.getElementById('closeButt');

    let id = 2;
    let newValue = false;

    let requestOptions = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json'
        },
        body: JSON.stringify({
            id: id,
            newValue: newValue
        })
    }

    fetch("http://localhost:8080/users/open", requestOptions)
        .then(function(response) {
            if (response.ok) {
                ob.style.display = 'block';
                cb.style.display = 'none';
                return response.text();
            } else {
                throw new Error('Ошибка AJAX запроса');
            }
        })
        .then(function(data) {
            // Обработка успешного ответа от сервера
            console.log(data);
        })
        .catch(function(error) {
            // Обработка ошибки
            console.error(error);
        });
}