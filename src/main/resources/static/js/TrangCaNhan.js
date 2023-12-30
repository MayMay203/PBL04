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
    if ($('#btnCreateAct').hasClass('active')) {
        $('#selEvaluateActi').addClass("no-display");
        $('#selEvaluateMember').removeClass("no-display");
    }
    $('#btnJoinAct').on('click', function () {
        $('.sectionActIsHost').addClass("no-display");
        $('.sectionActIsJoin').removeClass("no-display");
    });
    if ($('#btnJoinAct').hasClass('active')) {
        $('#selEvaluateMember').addClass("no-display");
        $('#selEvaluateActi').removeClass("no-display");
    }
});
// ========bộ lọc
// Hàm để đặt giá trị "Tất cả" cho các select khác
var selectedValue = -3;
function setAllOptionsToDefault(selectedSelect) {
    // Lấy danh sách tất cả các select trong form
    var allSelects = document.querySelectorAll('#formFilter select');

    // Lặp qua từng select
    allSelects.forEach(function(select) {
        // Nếu không phải là select được chọn, đặt giá trị thành "Tất cả"
        if (select !== selectedSelect) {
            select.value = '-3';
        }
        else{
            selectedValue = select.value;
        }
    });
}

// Gọi hàm khi select thay đổi giá trị
document.querySelectorAll('#formFilter select').forEach(function(select) {
    select.addEventListener('change', function() {
        setAllOptionsToDefault(this);
    });
});

// Gọi hàm khi nút "Lưu thay đổi" được nhấn
document.getElementById('btnSaveFilter').addEventListener('click', function() {
    // Lấy giá trị của các select
    var optStateValue =  $('#optState option:selected').val();
    var optSummaryValue =  $('#optSummary option:selected').val();
    var optEvaluateValue = $('#optEvaluate option:selected').val();

    // Gọi hàm để thực hiện lọc nội dung
    // filterContent({
    //     optState: optStateValue,
    //     optSummary: optSummaryValue,
    //     optEvaluate: optEvaluateValue
    // });
});

// document.addEventListener("DOMContentLoaded", function () {
//     document.getElementById('optState').addEventListener('change', function() {
//         var selectedValue = this.value;
//         var activityContainers = document.getElementsByClassName('activity-container');
//
//         for (var i = 0; i < activityContainers.length; i++) {
//             var activityContainer = activityContainers[i];
//             var activity = /*[[${actListIsHost.get(i)}]]*/ {}; // Thay đổi cú pháp Thymeleaf thành cú pháp JavaScript
//
//             if (selectedValue == -3) {
//                 activityContainer.classList.remove('hidden');
//             }
//             // Thêm điều kiện th:if vào mỗi div dựa trên giá trị được chọn
//             else if (selectedValue == -2 && activity.getTinhTrangHD == 0 && activity.getTinhTrangDuyet == 0) {
//                 activityContainer.classList.remove('hidden');
//             }
//             else if (selectedValue == -1 && activity.getTinhTrangHD == -1 ) {
//                 activityContainer.classList.remove('hidden');
//             }
//             else if (selectedValue == 0 && activity.getTinhTrangHD == 0 && activity.getTinhTrangDuyet >= 1) {
//                 activityContainer.classList.remove('hidden');
//             } else if (selectedValue == 1 && activity.trangThaiHoatDong == 1) {
//                 activityContainer.classList.remove('hidden');
//             }
//             else if (selectedValue == 2 && activity.trangThaiHoatDong == 2) {
//                 activityContainer.classList.remove('hidden');
//             }
//             else {
//                 activityContainer.classList.add('hidden');
//             }
//         }
//     });
// });
// Hàm xử lý khi form được submit hoặc trang được load
function handleAction() {
    // Lấy giá trị từ form
    // var selectedValue = parseInt($('#optState').val());
    // var selEvaValue = parseInt($('#optEvaluate').val());
    // var selSumValue = parseInt($('#optSummary').val());
    var accountID = parseInt($('#accountID').val());

    console.log("Value select: " + selectedValue);
    console.log("Value accountID: " + accountID);

    // Gọi AJAX đến endpoint xử lý đăng nhập
    $.ajax({
        type: 'POST',
        url: '/bo-loc-hoat-dong',
        data: {
            'selectedValue': selectedValue,
            // 'selEvaValue': selEvaValue,
            // 'selSumValue': selSumValue,
            'accountID': accountID
        },
        success: function (data) {
            if (data.reloadPage) {
                location.reload();
            }
            if (data.success) {
                // location.reload();
                // $('#modalFilter').modal("hide");
                // $('#activity-container').reload();
            } else {
                // Xử lý trường hợp không thành công
            }
        },
        error: function (error) {
            console.error('Đã có lỗi xảy ra:', error);
        }
    });
}
document.addEventListener('DOMContentLoaded', function() {
// Sự kiện khi modal được submit
$('#formFilter').on('submit', function (e) {
    e.preventDefault();
    // Gọi hàm xử lý khi modal được đóng (submit)
    var accountID = parseInt($('#accountID').val());

    console.log("Value select: " + selectedValue);
    console.log("Value accountID: " + accountID);

    // Gọi AJAX đến endpoint xử lý đăng nhập
    $.ajax({
        type: 'POST',
        url: '/bo-loc-hoat-dong',
        data: {
            'selectedValue': selectedValue,
            // 'selEvaValue': selEvaValue,
            // 'selSumValue': selSumValue,
            'accountID': accountID
        },
        success: function (data) {
            if (data.reloadPage) {
                location.reload();
            }
            if (data.success) {
                // location.reload();
                $('#modalFilter').modal("hide");
                // $('#activity-container').reload();
            } else {
                // Xử lý trường hợp không thành công
            }
        },
        error: function (error) {
            console.error('Đã có lỗi xảy ra:', error);
        }
    });
});});
// $('#btn-view-personal-page').on('click', function (e) {
//     e.preventDefault();
//     // Gọi hàm xử lý khi modal được đóng (submit)
//     // handleAction();
// });

// document.addEventListener('DOMContentLoaded', function() {
//     document.getElementById('formFilter').addEventListener('submit', function (e) {
//         e.preventDefault();
//         // Lấy giá trị từ form
//         var selectedValue = parseInt($('#optState').val());
//         var selEvaValue = parseInt($('#optEvaluate').val());
//         var selSumValue = parseInt($('#optSummary').val());
//         var accountID = parseInt($('#accountID').val());
//
//         console.log("Value select: " + selectedValue);
//         console.log("Value accountID: " + accountID);
//         // Gọi AJAX đến endpoint xử lý đăng nhập
//         $.ajax({
//             type: 'POST',
//             url: '/bo-loc-hoat-dong',
//
//             data: { 'selectedValue': selectedValue,
//                 'selEvaValue': selEvaValue,
//                 'selSumValue': selSumValue,
//                     'accountID': accountID},
//             success: function (data) {
//                 if (data.reloadPage) {
//                     location.reload();
//                 }
//                 if (data.success) {
//                     location.reload();
//                     // $('#modalFilter').modal("hide");
//                 } else {
//
//                 }
//             },
//             error: function (error) {
//                 console.error('Đã có lỗi xảy ra:', error);
//             }
//         });
//     });
// });