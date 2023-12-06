// $(document).ready(function() {
//   $('#btnDangNhap').on('click', function() {
//     $('#DangNhapModal').modal('show');
//   });
// });

document.addEventListener("DOMContentLoaded", function () {
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
      // }th:src="@{/images/default.webp}"
      document.getElementById("userImage").src = "@{/images/default.webp}";
    }

