fetch('../templates/Header.html')
    .then(response => response.text())
    .then(data => {
        document.getElementById('iframe-container').innerHTML = data;
    });