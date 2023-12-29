//-----sửa format ngày -------------
// var dateformat = date.toLocaleDateString("vi-VN", {
//             day: '2-digit',
//             month: '2-digit',
//             year: 'numeric',
//         });
//
//         var dateformatshow = `${dateformat}`;
//         document.querySelector(".date-show").innerText = dateformatshow;
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
                document.getElementById('anhDaiDien').src = e.target.result; // Cập nhật src của thẻ img
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
// document.getElementById('btnSave').addEventListener('click', function() {
//     // Gọi phương thức submit cho formChangeInform
//     document.getElementById('formChangeInform').submit();});
//


//-----------------------------sửa thông tin cá nhân---------------------------
document.addEventListener("DOMContentLoaded", function () {
    // Sử dụng Array.from để chuyển đổi HTMLCollection thành mảng
    var dateInputs = Array.from(document.getElementsByClassName("formatDate"));

    // Lặp qua mảng các phần tử có class "formatDate"
    dateInputs.forEach(function (dateInput) {
        // Chuyển đổi định dạng từ 'yyyy-MM-dd' sang 'dd-MM-yyyy'
        dateInput.value = reverseDateFormat(dateInput.value);
    });

    // Hàm chuyển đổi định dạng ngày
    function reverseDateFormat(dateString) {
        var parts = dateString.split("-");
        return parts.reverse().join("-");
    }
});
// document.addEventListener("DOMContentLoaded", function () {
// document.getElementById('formUpdateFood').addEventListener('submit', function (e) {
//     e.preventDefault();
//     console.log("Lay du lieu");
//     var formData = new FormData();
//     var selectedFile = document.getElementById("input-file").files[0];
//     formData.append("imageInput", selectedFile);
//     $.ajax({
//         type: 'POST',
//         url: '/sua-thong-tin-ca-nhan',
//         data: formData,
//         processData: false,
//         contentType: false,
//         success: function (data) {}
//     });console.log('Form submission completed');
// });
// });

$(document).ready(function() {
    $('#formChangeInform').submit(function(e) {
        e.preventDefault();

        $.ajax({
            type: 'POST',
            url: '/sua-thong-tin-ca-nhan',
            data: new FormData(this),
            contentType: false,
            processData: false,
            success: function(response) {
                if (response.success) {
                    console.log("-------------------có chạy nề-----------------------")
                    // Thành công, load lại trang hiện tại
                    // if(location.pathname=="/trang-ca-nhan"){
                    //     location.href="/trang-chu";
                    // }
                    // else
                    // page.pathname=="/header";
                    //     page.reload();
                    location.reload();
                } else {
                    // Hiển thị thông báo lỗi
                    alert(response);
                }
            },
            error: function(error) {
                console.error('Error updating profile:', error);
            }
        });
    });
});

$(document).ready(function() {
    $('#formAddActivity').submit(function(e) {
        e.preventDefault();

        $.ajax({
            type: 'POST',
            url: '/addActivity',
            data: new FormData(this),
            contentType: false,
            processData: false,
            enctype: 'multipart/form-data',
            success: function(response) {
                if (response.success) {
                    // Thành công, load lại trang hiện tại
                    // if(location.pathname=="/trang-ca-nhan"){
                    //     location.href="/trang-chu";
                    // }
                    // else
                    // page.pathname=="/header";
                    //     page.reload();
                    location.reload();
                } else {
                    // Hiển thị thông báo lỗi
                    alert(response.message);
                }
            },
            error: function(error) {
                console.error('Error updating profile:', error);
            }
        });
    });
});
// --tổng kết hoạt dộng
// document.addEventListener("DOMContentLoaded", function () {
//     $('#btnSummary').on('click', function () {
//
//         $('#confirmAddSummaryModal').modal("show");
//     });
    $('#confirmAddSummaryModal').on('shown.bs.modal', function () {
        var header = document.getElementById("myHeader");
        header.classList.remove("sticky");
    });
    $('#confirmAddSummaryModal').on('show.bs.modal', function () {
    //
    //     // Tăng biến đếm mỗi khi modal sắp hiển thị
        backdropCount++;

        // Nếu có nhiều hơn một backdrop, xóa đi các backdrop thừa
        if (backdropCount > 1) {
            $('.modal-backdrop').not(':last').remove();
        }
    });
    $('#confirmAddSummaryModal').on('hidden.bs.modal', function () {
        backdropCount--;
        //mỗi khi modal được đóng
        // var header = document.getElementById("myHeader");
        // header.classList.add("sticky");
    });
// });
//---------- hiển thị hđ tổ chức hay tham gia------------
document.addEventListener("DOMContentLoaded", function () {
    $('#btnCreateAct').on('click', function () {
        $('.sectionActIsHost').removeClass("no-display");
        $('.sectionActIsJoin').addClass("no-display");
    });

    $('#btnJoinAct').on('click', function () {
        $('.sectionActIsHost').addClass("no-display");
        $('.sectionActIsJoin').removeClass("no-display");
    });
});
