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
$(document).ready(function () {
    var btnSummary = document.getElementsByClassName("buttonSummary");
    for(btn of btnSummary){
        btn.addEventListener("click" ,async (e) =>{
            maHD= e.target.dataset.value;
            maTK= e.target.dataset.account;
            console.log("maHD:",maHD);
            console.log("maTK",maTK);
            $.ajax({
                url: '/get-activity-by-id',
                type: 'GET',
                data: {'maHD' :maHD},
                success: function (data) {
                    console.log(data);
                    // Populate modal with activity information
                    $("#txt_TenChuDe").val(data.maChuDe.tenChuDe);
                    $("#txt_TenHoatDong").val(data.tenHD);
                    $("#txt_tongket").val("");
                    $("#txt_loiket").val("");
                    // Show the modal
                    $('#TongKetModal').modal('show');
                },
                error: function (error) {
                    console.log(error);
                }
            });
        })
    }
})
var check=false;
$(document).ready(function () {
    var btnSave = document.getElementsByClassName("btnSave");
    for(btn of btnSave){
        btn.addEventListener("click", async (e)=> {
            const formData = new FormData();
            const bangTongKet = $('#txt_tongket').val();
            const loiKet = $('#txt_loiket').val();
            formData.append('maHD', maHD);
            formData.append('bangTongKet', bangTongKet);
            formData.append('loiKet', loiKet);
            formData.append('imagesPaths', imagePaths);
            $.ajax({
                type: 'POST',
                url: '/addSummary',
                data: formData,
                processData: false,
                contentType: false,
                success: function (data) {
                    console.log('data:', data);
                    if (data.success) {
                        console.log("them thanh cong");
                        $('#TongKetModal').modal('hide');
                        //location.reload();
                       //window.location.href = '/trang-ca-nhan?id=${maTK}';
                    } else {
                        console.error('Đã có lỗi xảy ra:', data.message);
                    }
                },
                error: function (error) {
                    console.error('Đã có lỗi xảy ra:', error);
                }

            });console.log('Form submission completed');
        })
    }
    function updateModalContent(newContent) {
        // Update the modal body content
        $('.modal-body-themhd').html(newContent);
    }
    $(".btnClose").on("click", function() {
        // Close the modal without saving
        $('#TongKetModal').modal('hide');
        if(check ===true){
            location.reload();
        }
    });
})
