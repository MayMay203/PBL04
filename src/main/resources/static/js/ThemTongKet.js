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


    }
    filereader.readAsDataURL(fileToLoad);

}

function DelImg(el) {
    jQuery(el).closest('li').remove();

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
    document.getElementById("myForm").reset();
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