//--------------- trang cá nhân ---------------
$(document).ready(function() {
    $('#icon-edit').on('click', function() {
        $('#modalChangeInform').modal('show');
    });
});

$(document).ready(function() {
    $('#closeButton').on('click', function() {
        $('#modalChangeInform').modal('hide');
    });
});

$(document).ready(function() {
    $('#btnFilter').on('click', function() {
        $('#modalFilter').modal('show');
    });
});
$(document).ready(function() {
    $('#btn-closeFilter').on('click', function() {
        $('#modalFilter').modal('hide');
    });
});
// $('#modalFilter').on('hidden.bs.modal', function () {
//     // Loại bỏ backdrop khi modal đóng
//     $('.modal-backdrop').remove();
// });
// $('#modalChangeInform').on('hidden.bs.modal', function () {
//     // Loại bỏ backdrop khi modal đóng
//     $('.modal-backdrop').remove();
// });
document.addEventListener('DOMContentLoaded', function() {
    document.getElementById('input-file').addEventListener('change', function (event) {
        var file = event.target.files[0]; // Lấy file từ input
        if (file) {
            var reader = new FileReader();
            reader.onload = function(e) {
                document.getElementById('img-avartar').src = e.target.result; // Cập nhật src của thẻ img
            }
            reader.readAsDataURL(file); // Đọc nội dung file dưới dạng Data URL
        }
    });
});

document.addEventListener('DOMContentLoaded', function() {
    document.getElementById('formChangeInform').addEventListener('submit', function (event) {
        if (!this.checkValidity()) {
            event.preventDefault(); // Ngăn chặn gửi form nếu nó không hợp lệ
            this.classList.add('was-validated'); // Hiển thị thông báo lỗi
        }
    });
});
document.addEventListener('DOMContentLoaded', function() {
    document.getElementById('btnReset').addEventListener('click', function() {
        document.getElementById('formChangeInform').reset(); // Đặt lại form
    });
});

document.addEventListener('DOMContentLoaded', function () {
    var buttons = document.querySelectorAll('.btnActivity');

    buttons.forEach(function (button) {
        button.addEventListener('click', function () {
            // Xoá lớp 'active' từ tất cả các nút
            buttons.forEach(function (btn) {
                btn.classList.remove('active');
            });
            // Thêm lớp 'active' vào nút được nhấp
            this.classList.add('active');
        });
    });
});

//thêm hoạt động
document.addEventListener("DOMContentLoaded", function() {
    // Gán sự kiện click cho txtCreateActivity khi nó được focus
    $('.txtCreateActivity').focus(function() {
        // Mô phỏng sự kiện click trên nút
        $('#btnAddAct').trigger('click');
    });
});
//định dạng ngày
$(document).ready(function () {
    $('.datepicker').datepicker({
        format: 'dd-mm-yyyy',
        autoclose: true,
        todayHighlight: true
    });
});
//sửa thông tin cá nhân:
document.getElementById('btnSave').addEventListener('click', function() {
    // Gọi phương thức submit cho formChangeInform
    document.getElementById('formChangeInform').submit();});