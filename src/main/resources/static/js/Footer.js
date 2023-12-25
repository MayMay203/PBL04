$(document).ready(function() {
    $('.btn-join-now').on('click', function() {
        var header = document.getElementById("myHeader");
        header.classList.remove("sticky");
        $('#DangNhapModal').modal('show');
    });
});