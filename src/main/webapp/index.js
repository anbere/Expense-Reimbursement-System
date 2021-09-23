function nospaces(t) {
    if (t.value.match(/\s/g)) {
        t.value = t.value.replace(/\s/g, '');
    }
}

function checkCredentials() {

    let userInfo = {
        username: document.getElementById('floatingUsername').value,
        password: document.getElementById('floatingPassword').value
    }

    fetch("LoginServlet", {
            method: "POST",
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(userInfo)
        })
        .then((response) => window.location = response.url
		)

}