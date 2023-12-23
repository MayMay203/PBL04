// $(document).ready(function() {
//   $('#btnDangNhap').on('click', function() {
//     $('#DangNhapModal').modal('show');
//   });
// });

document.addEventListener("DOMContentLoaded", function () {
  // document.getElementById("imageInputAdd").addEventListener("change", function (event) {
    document.getElementById("imageInput").addEventListener("change", function (event) {
    previewImage(event);
  });

  function previewImage(event) {
    var selectedFile = event.target.files[0];
    var imagePreview = document.getElementById("userImage");

    if (selectedFile) {
      var reader = new FileReader();
      reader.onload = function (e) {
        imagePreview.src = e.target.result;
      };
      reader.readAsDataURL(selectedFile);
    }
  }
});
   

(function () {
      'use strict'
      var forms = document.querySelectorAll('.needs-validation')

      Array.prototype.slice.call(forms)
        .forEach(function (form) {
          form.addEventListener('submit', function (event) {
            if (!form.checkValidity()) {
              event.preventDefault()
              event.stopPropagation()
            }

            form.classList.add('was-validated')
          }, false)
        })
    })()
    function resetFormFields() {
      document.getElementById("myForm").reset();

      // Hide all error messages
      // var formElements = document.getElementById("myForm").elements;
      // for (var i = 0; i < formElements.length; i++) {
      //   formElements[i].setCustomValidity('');
      // }
      document.getElementById("userImage").src = "../assets/images/default.webp";
}
// Thêm hoạt động
// document.addEventListener('DOMContentLoaded', function() {
//   document.getElementById('formLogin').addEventListener('submit', function (e) {
//     console.log('handleLogin called with loginSuccess:');
//     e.preventDefault();
//
//     // Lấy giá trị từ form
//     var tenChuDe = $('#txt_NameTopic').val();
//     var tenHD = $('#txt_NameActi').val();
//
//     // Gọi AJAX đến endpoint xử lý đăng nhập
//     $.ajax({
//       type: 'POST',
//       url: '/check-login',
//
//       data: { 'tenDN': username, 'matKhau': password },
//       success: function (data) {
//         console.log('data:', data);
//         if (!data.error) {
//           // Đăng nhập thành công
//           $('#DangNhapModal').modal('hide');
//           $('#btnLoginModal').hide();
//           $('#btn_userProfile').show();
//           $('body').removeClass('modal-open');
//           $('.modal-backdrop').remove();
//         } else {
//           // Đăng nhập thất bại, hiển thị thông báo lỗi
//           $('#error-message').text(data.error);
//           $('#DangNhapModal').modal('show');
//         }
//       },
//       error: function (error) {
//         console.error('Đã có lỗi xảy ra:', error);
//       }
//     });
//   });
// });
// function handleLogin(loginSuccess) {
//   // Xử lý đăng nhập ở đây. Nếu đăng nhập thành công, ẩn modal và trả về true.
//   // loginSuccess = true;
//   console.log('handleLogin called with loginSuccess:', loginSuccess);
//
//   // return false; // Ngăn chặn gửi form nếu đăng nhập không thành công
// }
// document.addEventListener('DOMContentLoaded', function() {
//   document.getElementById('checkShowPass').addEventListener('click', function (event) {
//     var password = document.getElementById("password");
//     var checkBox = document.getElementById("checkShowPass");
//     password.type = checkBox.checked ? "text" : "password";
//   });
// });
// function userLoggedIn() {
//   // Thay đổi giao diện header khi người dùng đã đăng nhập
//   document.getElementById('btnLoginModal').style.display = 'none';
//   document.getElementById('btn_userProfile').style.display = 'inline-block';
//   // Thêm các thay đổi khác nếu cần
// }
// Lắng nghe sự kiện trước khi modal được hiển thị
$('#ThemHoatDongModal').on('show.bs.modal', function (event) {
    // Thực hiện các hành động trước khi modal được hiển thị
    var header = document.getElementById("myHeader");
    header.classList.remove("sticky");
});


// Thêm hoạt động
document.addEventListener('DOMContentLoaded', function() {
    document.getElementById('formAddActivity').addEventListener('submit', function (e) {
        console.log('Form submission started');

        e.preventDefault();


        // Lấy giá trị từ form
        var tenChuDe = $('#txt_NameTopic option:selected').val();
        var tenHD = $('#txt_NameActi').val();
        var diaDiem = $('#txt_Location').val();
        var thoigianBD = $('#txt_dateBD').val();
        var thoigianKT =$('#txt_dateKT').val();
        var sotnvtt = $('#txt_tnvtt').val();
        var sotnvtd= $('#txt_tnvtd').val();
        var moTa= $('#txt_mota').val();
        var hinhanh =$('#imageInput').val();
        // var userID = parseInt($('.btnSave', this).data('account-id'));

        // var userID = 3;
        // var userID = parseInt($('#userID').val());
        var userID = $('#userID').val();

        // var userID = $('[data-maTK]').data('maTK');
        // var userID = $(this).data('account-id');
        // Lấy giá trị của input file
        // ===============
        var formData = new FormData();
        var formData1 = new FormData();
        var selectedFile = document.getElementById("imageInput").files[0];
        formData.append("imageFile", selectedFile);
        formData.append("tenChuDe", tenChuDe);
        formData.append("tenhd", tenHD);
        formData.append("diaDiem", diaDiem);
        formData.append("thoiGianBD", thoigianBD);
        formData.append("thoiGianKT", thoigianKT);
        formData.append("soTNVToiThieu", sotnvtt);
        formData.append("soTNVToiDa", sotnvtd);
        formData.append("moTa", moTa);
        formData.append("anh", hinhanh);
        formData.append("maTK", userID);
        console.log("Lay du lieu");
        console.log(tenChuDe, tenHD, diaDiem, thoigianBD, thoigianKT, sotnvtd, sotnvtt, moTa, hinhanh, userID);

        // Gọi AJAX đến endpoint xử lý đăng nhập
        $.ajax({
            type: 'POST',
            url: '/addActivity',

            data: formData,
            processData: false,
            contentType: false,
            // enctype: 'multipart/form-data',
            // type: 'POST',
            // url: '/addActivity',
            //
            // data: { 'tenChuDe': tenChuDe, 'tenhd': tenHD, 'diaDiem': diaDiem,
            //     'thoiGianBD': thoigianBD, 'thoiGianKT':thoigianKT, 'soTNVToiThieu': sotnvtt, 'soTNVToiDa':sotnvtd,
            //     'moTa':moTa
            //     ,'anh': hinhanh
            //     ,"imageFile": selectedFile
            //     ,'maTK': userID
            // },
            // contentType: false,
            // processData: false,
            // enctype: 'multipart/form-data',
            success: function (data) {
                console.log('data:', data);
                if (!data.error) {
                    $('#ThemHoatDongModal').modal('hide');
                    $('body').removeClass('modal-open');
                    $('.modal-backdrop').remove();
                    location.reload();
                }
            },
            error: function (error) {
                console.error('Đã có lỗi xảy ra:', error);
            }

        });console.log('Form submission completed');
    });
});