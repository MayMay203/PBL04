$(document).ready(function () {
    // Add a click event listener to the "Tham gia" button
    $('#thamgiaButton').on('click', function () {
        // Show the modal when the button is clicked
        $('#joinModal').modal('show');
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
