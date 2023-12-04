//
// $(document).ready(function() {
//     $('#btnLoginModal').on('click', function() {
//         $('#DangNhapModal').modal('show');
//     });
// });


const headerLink = "/header"
fetch(headerLink)
    .then(response => response.text())
    .then(data => {
        document.getElementById('iframe-container').innerHTML = data;
    });

// $(document).ready(function() {
//     $('#btnLoginModal').on('click', function() {
//         $('#DangNhapModal').modal('show');
//     });
// });
// document.addEventListener('DOMContentLoaded', function() {
//     document.getElementById('btnLoginModal').addEventListener('click',  $('#DangNhapModal').modal('show'));
// });
// $('#DangNhapModal').on('shown.bs.modal', function () {
//     // Chèn modal vào sau modal backdrop của modal khác
//     $(this).after($('.modal-backdrop'));
// });

