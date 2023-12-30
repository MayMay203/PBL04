// function getContentBodyHeight() {
//   return document.querySelector(".content_detail_body").scrollHeight;
// }
//
// document.querySelector(".content_detail").style.height = getContentBodyHeight() + "px";



document.addEventListener("DOMContentLoaded", function () {
  const $ = document.querySelector.bind(document);
  const $$ = document.querySelectorAll.bind(document);

  const tabs = $$(".tab-item");
  const panes = $$(".tab-pane");

  const tabActive = $(".tab-item.active");
  const line = $(".tabs .line");

  line.style.left = tabActive.offsetLeft + "px";
  line.style.width = tabActive.offsetWidth + "px";

  tabs.forEach((tab, index) => {
    const pane = panes[index];

    tab.onclick = function () {
      $(".tab-item.active").classList.remove("active");
      $(".tab-pane.active").classList.remove("active");

      line.style.left = this.offsetLeft + "px";
      line.style.width = this.offsetWidth + "px";

      this.classList.add("active");
      pane.classList.add("active");
    };
  });
});

//Thêm Ảnh cho hoạt động mới 
// var maTK ;
// $(document).ready(function () {
//
// })
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
  // console.log("maTK:",maTK);
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
var maTK;
var mataikhoan;
var manguoidung;
$(document).ready(function () {

  $(".imgdf1").on("click", function() {
    console.log("Image clicked");
    $('#InsertImage').modal('show');
  });
  $("#btnInsertImg").on("click", function() {
    maTK =  $(this).data("summary-id");
    mataikhoan=  $(this).data("account-id");
    manguoidung=  $(this).data("mauser-id");
    console.log("maTK:",maTK);
    console.log("mataikhoan:",mataikhoan);
    const formData = new FormData();
    formData.append('maTK', maTK);
    for (var i = 0; i < imagePaths.length; i++) {
      formData.append('imagesPaths', imagePaths[i]);
    }

    console.log(formData);
    console.log("maTK:",maTK);
    $.ajax({
      type:'POST',
      url: '/addImgSummary',
      data:formData,
      processData: false,
      contentType: false,
      success: function (response) {
        if(response.success)
        {
          if(mataikhoan!==manguoidung){
            sendNotiSummary(maTK,mataikhoan);
          }
          console.log(response);
          // location.reload();
          updateModalContent("Thêm ảnh tổng kết thành công.");
          hideButtons();
        }else{

          alert(response.message)
        }
      },
      error: function (error) {
        console.error('AJAX Error:', error);
        console.error('Đã có lỗi xảy ra:', error);
      }

    });
    }
    )
  function updateModalContent(newContent) {
    // Update the modal body content
    $('.tongket-reviews').html(newContent);
  }
  $('.btnCloseModal').click(function () {
    location.reload();
  });
  function hideButtons() {
    // Hide the buttons in the modal footer
    $('#InsertImage .modal-footer').hide();
  }
  $('.closeModalLogout').click(function () {
    var imgElements = document.querySelectorAll('.tongket_attach_view li');
    imgElements.forEach(function (imgElement) {
      imgElement.remove();
    });
  });
  function sendNotiSummary(maTK,mataikhoan)
  {
    var noidung ="Một thành viên đã thêm ảnh vào mục Tổng kết hoạt động";
      $.ajax({
        type: 'POST',
        url: '/them-thong-bao',
        data: {'maTK': mataikhoan, 'noiDung': noidung, 'loaiTB': 4, 'ma': maTK,},
        success: function (data) {
          console.log('Success:', data);
          console.log("thanh cong");
        },
        error: function (error) {
          console.error('Error:', error);
        }
      });
    }

})