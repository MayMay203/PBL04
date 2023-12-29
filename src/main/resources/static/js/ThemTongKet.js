// Thêm nhiều ảnh cho  tổng kết hoạt động
$('.tongket_insert_attach').click(function() {
    if ($('.list_attach').hasClass('show-btn') === false) {
        $('.list_attach').addClass('show-btn');
    }
    var _lastimg = jQuery('.tongket_attach_view li').last().find('input[type="file"]').val();

    if (_lastimg != '') {
        var d = new Date();
        var _time = d.getTime();
        var _html = '<li id="li_files_' + _time + '" class="li_file_hide">';
        _html += '<div class="img-wrap">';
        _html += '<span class="close" onclick="DelImg(this)">×</span>';
        _html += ' <div class="img-wrap-box"></div>';
        _html += '</div>';
        _html += '<div class="' + _time + '">';
        _html += '<input type="file" class="hidden"  onchange="uploadImg(this)" id="files_' + _time + '"   />';
        _html += '</div>';
        _html += '</li>';
        jQuery('.tongket_attach_view').append(_html);
        jQuery('.tongket_attach_view li').last().find('input[type="file"]').click();
    } else {
        if (_lastimg == '') {
            jQuery('.tongket_attach_view li').last().find('input[type="file"]').click();
        } else {
            if ($('.list_attach').hasClass('show-btn') === true) {
                $('.list_attach').removeClass('show-btn');
            }
        }
    }
});
var imagePaths = [];
function uploadImg(el) {
    var file_data = $(el).prop('files')[0];
    var type = file_data.type;
    var fileToLoad = file_data;

    var filereader = new FileReader();

    filereader.onload = function(fileLoadedEvent) {
        var srcData = fileLoadedEvent.target.result;

        var newImage = document.createElement('img');
        newImage.src = srcData;
        var _li = $(el).closest('li');
        if (_li.hasClass('li_file_hide')) {
            _li.removeClass('li_file_hide');
        }
        _li.find('.img-wrap-box').append(newImage.outerHTML);
        //_li.find('.img-wrap-box').append('<input type="hidden" name="imagePaths[]" value="' + file_data.name + '">');
        //formImage.append('/images/', file_data.name);
       // imagePaths.push('/images/'+file_data.name);

        var imagePath = '/images/' + file_data.name;
        _li.find('.img-wrap-box').append('<input type="hidden" name="imagePaths[]" value="' + imagePath + '">');
        // Thêm đường dẫn vào mảng imagePaths
        imagePaths.push(imagePath);
    }
    filereader.readAsDataURL(fileToLoad);
    console.log(imagePaths);
}

function DelImg(el) {
    // jQuery(el).closest('li').remove();
    var removedImagePath = $(el).closest('li').find('.img-wrap-box input[type="hidden"]').val();

    // Xóa li khỏi giao diện
    $(el).closest('li').remove();

    // Tìm và xóa đường dẫn ảnh khỏi mảng imagePaths
    var index = imagePaths.indexOf(removedImagePath);
    if (index !== -1) {
        imagePaths.splice(index, 1);
    }
    console.log(imagePaths);
}

$(document).ready(function () {
    'use strict'
    var forms = document.querySelectorAll('.needs-validation');

    Array.prototype.slice.call(forms)
      .forEach(function (form) {
        form.addEventListener('submit', function (event) {
          if (!form.checkValidity()) {
            event.preventDefault();
            event.stopPropagation();
          }

          form.classList.add('was-validated');
        }, false);
      });
  });

  function resetFormFields() {
      $("#txt_tongket").val("");
      $("#txt_loiket").val("");
    //document.getElementById("myForm").reset();
    var imgElements = document.querySelectorAll('.tongket_attach_view li');
    imgElements.forEach(function (imgElement) {
        imgElement.remove();
    });

    // Hide all error messages
    var formElements = document.getElementById("myForm").elements;
    for (var i = 0; i < formElements.length; i++) {
      formElements[i].setCustomValidity('');
    }
  }

var maHD;var maTK;
// var idList;
$(document).ready(function () {
    var btnSummary = document.getElementsByClassName("btn_Summary");
    // function getMember(maHD)
    // {
    //     $.ajax({
    //         type: 'POST',
    //         url: '/get-member',
    //         data: {  'maHD': maHD },
    //         success: function (data) {
    //             console.log('Success:', data);
    //             idList = data.map(function (id) {
    //                 return parseInt(id);
    //             });
    //             // mataikhoan= parseInt(data);
    //             console.log("thanh cong");
    //             console.log("mataikhoan:",mataikhoan);
    //         },
    //         error: function (error) {
    //             console.error('Error:', error);
    //         }
    //     });
    // }

    for(btn of btnSummary){
        btn.addEventListener("click" ,async (e) =>{
            var mahoatdong= e.target.dataset.value;
            maTK= e.target.dataset.account;
            console.log("maHD:",mahoatdong);
            console.log("maTK",maTK);
            maHD= mahoatdong;
            try {
                const data = await checkSummary(mahoatdong);
                if (data.summaryExists) {
                    window.location.href = '/View-Summary?id=' + mahoatdong;
                } else {
                    // getMember(maHD);
                    await showModalSummary();
                }
            } catch (error) {
                console.error('Error:', error);
            }

        })
    }

})
function checkSummary(mahoatdong) {
    return new Promise((resolve, reject) => {
        $.ajax({
            type: 'GET',
            url: '/Check-Summary',
            data: { 'maHD': mahoatdong },
            success: resolve,
            error: reject
        });
    });
}
function getActivityById(maHD) {
    return new Promise((resolve, reject) => {
        $.ajax({
            url: '/get-activity-by-id',
            type: 'GET',
            data: { 'maHD': maHD },
            success: resolve,
            error: reject
        });
    });
}

async function showModalSummary() {
    try {
        const data = await getActivityById(maHD);
        console.log(data);
        // Populate modal with activity information
        $("#txt_TenChuDe").val(data.maChuDe.tenChuDe);
        $("#txt_TenHoatDong").val(data.tenHD);
        $("#txt_tongket").val("");
        $("#txt_loiket").val("");
        // Show the modal
        $('#TongKetModal').modal('show');
    } catch (error) {
        console.log(error);
    }
}


$(document).ready(function () {
    // function sendNotiSummary(maHD)
    // {
    //     var noidung ="Một bài viết tổng kết về hoạt động bạn tham gia vừa được đăng. Hãy kiểm tra ngay để cập nhật thông tin chi tiết và chia sẻ cảm nhận của bạn";
    //     for (var i = 0; i < idList.length; i++) {
    //         var id = idList[i];
    //         $.ajax({
    //             type: 'POST',
    //             url: '/them-thong-bao',
    //             data: {'maTK': id, 'noiDung': noidung, 'loaiTB': 1, 'ma': maHD,},
    //             success: function (data) {
    //                 console.log('Success:', data);
    //                 console.log("thanh cong");
    //             },
    //             error: function (error) {
    //                 console.error('Error:', error);
    //             }
    //         });
    //     }
    // }
    var btnSave = document.getElementsByClassName("btnSave");
    for(btn of btnSave){
        btn.addEventListener("click", async (e)=> {
            const formData = new FormData();
            const bangTongKet = $('#txt_tongket').val();
            const loiKet = $('#txt_loiket').val();
            if (!bangTongKet || !loiKet) {
                 console.error('Vui lòng nhập đầy đủ thông tin.');
                return;
            }
            formData.append('maHD', maHD);
            formData.append('bangTongKet', bangTongKet);
            formData.append('loiKet', loiKet);
            if (imagePaths.length === 0) {
                formData.append('imagesPaths',imagePaths ); // hoặc có thể không thêm gì cả
            } else {
                for (var i = 0; i < imagePaths.length; i++) {
                    formData.append('imagesPaths', imagePaths[i]);
                }
            }
            $.ajax({
                type: 'POST',
                url: '/addSummary',
                data: formData,
                processData: false,
                contentType: false,
                success: function (response) {
                    if(response.success)
                    {
                        // sendNotiSummary(maHD);
                        console.log(response);
                        location.reload();
                    }else{
                        alert(response.message)
                    }
                },
                error: function (error) {
                    console.error('AJAX Error:', error);
                    console.error('Đã có lỗi xảy ra:', error);
                }

            });console.log('Form submission completed');
        })
    }
    // function updateModalContent(newContent) {
    //     // Update the modal body content
    //     $('.modal-body-themhd').html(newContent);
    // }
    $(".btnClose").on("click", function() {
        // Close the modal without saving
        $('#TongKetModal').modal('hide');
        if(check ===true){
            location.reload();
        }
    });
})
