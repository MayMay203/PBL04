$(document).ready(function () {
    $('#btnJoin').on('click', function () {
        var maHD = $(this).data('hoatdong-id');
        var maTK = $(this).data('account-id');
        console.log("maHD:", maHD);
        console.log("maTK:", maTK);
        joinActivity(maHD,maTK);
    });
});

function joinActivity(maHD, maTK) {
    // Gửi AJAX request đến controller
    $.ajax({
        type: 'POST',
        url: '/Regis-activity',
        data: {'maHD': maHD, 'maTK': maTK},
        success: function (data) {
            console.log('Success:', data);
        },
        error: function (error) {
            console.error('Error:', error);
        }
    });
}