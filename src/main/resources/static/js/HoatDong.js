
// Thêm hoạt động
// document.addEventListener('DOMContentLoaded', function() {
//     document.getElementById('formAddActivity').addEventListener('submit', function (e) {
//         console.log('Form submission started');
//
//         e.preventDefault();
//
//
//         // Lấy giá trị từ form
//         var tenChuDe = $('#txt_NameTopic option:selected').val();
//         var tenHD = $('#txt_NameActi').val();
//         var diaDiem = $('#txt_Location').val();
//         var thoigianBD = $('#txt_dateBD').val();
//         var thoigianKT =$('#txt_dateKT').val();
//         var sotnvtt = $('#txt_tnvtt').val();
//         var sotnvtd= $('#txt_tnvtd').val();
//         var moTa= $('#txt_mota').val();
//         var hinhanh =$('#imageInput').val();
//         var userID = parseInt($('.btnSave', this).data('account-id'));
//
//         // var userID = 3;
//         // var userID =parseInt($('#userID').val());
//
//
//         // var userID = $('[data-maTK]').data('maTK');
//         // var userID = $(this).data('account-id');
//         // Lấy giá trị của input file
//         var formData = new FormData();
//         var selectedFile = document.getElementById("imageInput").files[0];
//         formData.append("imageFile", selectedFile);
//         formData.append("tenChuDe", tenChuDe);
//         formData.append("tenHD", tenHD);
//         formData.append("diaDiem", diaDiem);
//         formData.append("thoigianBD", thoigianBD);
//         formData.append("thoigianKT", thoigianKT);
//         formData.append("sotnvtt", sotnvtt);
//         formData.append("sotnvtd", sotnvtd);
//         formData.append("moTa", moTa);
//         formData.append("anh", hinhanh);
//         formData.append("maTK", userID);
//         console.log("Lay du lieu");
//         console.log(tenChuDe, tenHD, diaDiem, thoigianBD, thoigianKT, sotnvtd, sotnvtt, moTa, hinhanh, userID);
//
//         // Gọi AJAX đến endpoint xử lý đăng nhập
//         $.ajax({
//             type: 'POST',
//             url: '/addActivity',
//
//             data: formData,
//             processData: false,
//             contentType: false,
//             success: function (data) {
//                 console.log('data:', data);
//                 if (!data.error) {
//                     $('#ThemHoatDongModal').modal('hide');
//                     $('body').removeClass('modal-open');
//                     $('.modal-backdrop').remove();
//                     location.reload();
//                 }
//             },
//             error: function (error) {
//                 console.error('Đã có lỗi xảy ra:', error);
//             }
//
//         });console.log('Form submission completed');
//     });
// });
