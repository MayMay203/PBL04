$(document).ready(function () {
    // Add a click event listener to the "Tham gia" button
    $('#thamgiaButton').on('click', function () {
        // Kiểm tra xem tài khoản có đăng nhập hay không
        var isUserLoggedIn =  $(this).data('account-id');/* Kiểm tra xem tài khoản có đăng nhập hay không, có thể sử dụng một biến toàn cục hoặc kiểm tra session */;

        if (isUserLoggedIn!=null) {
            // Nếu đã đăng nhập, hiển thị modal "Tham gia"
            $('#joinModal').modal('show');
        } else {
            // Nếu chưa đăng nhập, hiển thị notification
            $('#notification').css('display', 'block');
            // Thêm code để ẩn notification sau một khoảng thời gian nếu cần
            setTimeout(function () {
                $('#notification').css('display', 'none');
            },30000000 ); // 3000 milliseconds (3 seconds) - có thể điều chỉnh thời gian theo ý muốn
        }
    });
    $('#cancel').on('click', function () {
        // Ẩn thẻ notification
        $('#notification').hide();

    });
    $('#login').on('click', function () {
        // Ẩn thẻ notification
        $('#DangNhapModal').modal('show');
    });


    $('#btnJoin').on('click', function () {
        var maHD = $(this).data('hoatdong-id');
        var maTK = $(this).data('account-id');
        console.log("maHD:", maHD);
        console.log("maTK:", maTK);
        joinActivity(maHD, maTK);
    });
    var modalContentUpdated = false;
    function joinActivity(maHD, maTK) {

        // Gửi AJAX request đến controller
        $.ajax({
            type: 'POST',
            url: '/Regis-activity',
            data: { 'maHD': maHD, 'maTK': maTK },
            success: function (data) {
                console.log('Success:', data);

                // Assuming the submission is successful, update modal content and hide buttons
                updateModalContent("Yêu cầu của bạn đang được xét duyệt.");
                hideButtons();
                modalContentUpdated =true;
                // location.reload();
            },
            error: function (error) {
                console.error('Error:', error);
            }
        });
    }

    function updateModalContent(newContent) {
        // Update the modal body content
        $('#modalBodyContent').html(newContent);
    }

    function hideButtons() {
        // Hide the buttons in the modal footer
        $('#joinModal .modal-footer').hide();

    }

    // Add a click event listener to the "Đóng" button inside the modal header
    $('.closeModalLogout').on('click', function () {
        $('#joinModal').modal('hide');
        if(modalContentUpdated ===true){
            location.reload();
        }
    });

});

$(document).ready(function () {

    $('#btnSummary').on('click', function () {
        var maHD = $(this).data('hoatdong-id');
        console.log("maHD:",maHD);
        // Gửi AJAX request để kiểm tra tổng kết hoạt động
        $.ajax({
            type: 'GET',
            url: '/Check-Summary',
            data: { 'maHD': maHD},

            success: function (data) {
                if (data.summaryExists) {
                    window.location.href = '/View-Summary?id=' +maHD;
                } else {
                    $('#noSummaryModal').modal('show');
                }
            },

            error: function (error) {
                console.error('Error:', error);
            }
        });
    });
    $('.closeModalLogout').on('click', function () {
        $('#noSummaryModal').modal('hide');
    });
});
document.addEventListener('DOMContentLoaded', function () {
    const notificationButton = document.getElementById('notificationButton');
    const notification = document.getElementById('notification');

    notificationButton.addEventListener('click', function () {
        // Hiển thị notification khi button được nhấn
        notification.style.display = 'block';

        // Ẩn notification sau một khoảng thời gian (ví dụ: 3 giây)
        setTimeout(function () {
            notification.style.display = 'none';
        }, 3000);
    });
});
