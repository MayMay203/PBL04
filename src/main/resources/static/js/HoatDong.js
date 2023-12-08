
// Thêm hoạt động
document.addEventListener('DOMContentLoaded', function() {
    document.getElementById('myFormActivity').addEventListener('submit', function (e) {
        console.log('Form submission started');

        e.preventDefault();

        // Lấy giá trị từ form
        var tenChuDe = $('#txt_NameTopic').val();
        var tenHD = $('#txt_NameActi').val();
        var diaDiem = $('#txt_Location').val();
        var thoigianBD = $('#txt_dateBD').val();
        var thoigianKT =$('#txt_dateKT').val();
        var sotnvtt = $('#txt_tnvtt').val();
        var sotnvtd= $('#txt_tnvtd').val();
        var moTa= $('#txt_mota').val();
        var hinhanh =$('#imageInput').val();
        console.log("Lay du lieu");
        // Gọi AJAX đến endpoint xử lý đăng nhập
        $.ajax({
            type: 'POST',
            url: '/addActivity',

            data: { 'tenChuDe': tenChuDe, 'tenHD': tenHD, 'diaDiem': diaDiem,
                'thoiGianBD': thoigianBD, 'thoiGianKT':thoigianKT, 'sotnvtt': sotnvtt, 'sotnvtd':sotnvtd,
                'moTa':moTa,'anh': hinhanh  },
            success: function (data) {
                console.log('data:', data);
                if (!data.error) {
                    $('#ThemHoatDongModal').modal('hide');
                    $('body').removeClass('modal-open');
                    $('.modal-backdrop').remove();
                }
            },
            error: function (error) {
                console.error('Đã có lỗi xảy ra:', error);
            }

        });console.log('Form submission completed');
    });
});
