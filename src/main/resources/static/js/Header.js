

const headerLink = "/header"
fetch(headerLink)
    .then(response => response.text())
    .then(data => {
        document.getElementById('iframe-container').innerHTML = data;
    });
