//--------------------------Chuyen tab------------------------------//
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

//chuyen tab
document.addEventListener("DOMContentLoaded", function () {
    var btnConfirmStage0 = document.getElementById("btnConfirmStage0");
    var btnActivityNeedConfirm = document.getElementById("btnActivityNeedConfirm");
    var btnNewButton = document.getElementById("btnConfirmStage1");
    var btnCancelStage1 = document.getElementById("btnCancelStage1");
    var btnActivityParticipate = document.getElementById("btnActivityParticipate");
    var subTab1 = document.getElementById("subTab1");
    var subTab2 = document.getElementById("subTab2");
    var subTab3 = document.getElementById("subTab3");
    var subTab4 = document.getElementById("subTab4");
    var subTab5 = document.getElementById("subTab5");
    // Thêm lớp 'active' cho nút và subTab mặc định
    btnActivityNeedConfirm.classList.add("active");
    subTab2.classList.add("active");
    if(btnConfirmStage0)
    {
        btnConfirmStage0.addEventListener("click", function() {
            // Xóa lớp 'active' từ tất cả các nút và subTabs
            btnConfirmStage0.classList.add("active");
            btnActivityNeedConfirm.classList.remove("active");
            btnNewButton.classList.remove("active");
           if(btnCancelStage1) btnCancelStage1.classList.remove("active");
            btnActivityParticipate.classList.remove("active");
            if(subTab1) subTab1.style.display = "block";
            if(subTab2) subTab2.style.display = "none";
            if(subTab3) subTab3.style.display = "none";
            if(subTab4) subTab4.style.display = "none";
            if(subTab5) subTab5.style.display = "none";
        });
    }

    // Thêm sự kiện click cho nút "Hoạt động đang chờ xét duyệt"
    btnActivityNeedConfirm.addEventListener("click", function() {
        // Xóa lớp 'active' từ tất cả các nút và subTabs
        if(btnConfirmStage0) btnConfirmStage0.classList.remove("active");
        btnActivityNeedConfirm.classList.add("active");
        btnNewButton.classList.remove("active");
       if(btnCancelStage1) btnCancelStage1.classList.remove("active");
        btnActivityParticipate.classList.remove("active");
       if(subTab2) subTab2.style.display = "block";
       if(subTab1) subTab1.style.display = "none";
       if(subTab3) subTab3.style.display = "none";
        if(subTab4) subTab4.style.display = "none";
        if(subTab5) subTab5.style.display = "none";
    });

    // Thêm sự kiện click cho nút mới
    btnNewButton.addEventListener("click", function() {
        // Xóa lớp 'active' từ tất cả các nút và subTabs
       if(btnConfirmStage0) btnConfirmStage0.classList.remove("active");
        btnActivityNeedConfirm.classList.remove("active");
        btnNewButton.classList.add("active");
       if(btnCancelStage1) btnCancelStage1.classList.remove("active");
        btnActivityParticipate.classList.remove("active");
       if(subTab3) subTab3.style.display = "block";
       if(subTab1) subTab1.style.display = "none";
       if(subTab2) subTab2.style.display = "none";
        if(subTab4) subTab4.style.display = "none";
        if(subTab5) subTab5.style.display = "none";
    });
    if(btnCancelStage1)
    {
        btnCancelStage1.addEventListener("click", function() {
            // Xóa lớp 'active' từ tất cả các nút và subTabs
            if(btnConfirmStage0) btnConfirmStage0.classList.remove("active");
            btnActivityNeedConfirm.classList.remove("active");
            btnNewButton.classList.remove("active");
            if(btnCancelStage1) btnCancelStage1.classList.add("active");
            btnActivityParticipate.classList.remove("active");
            if(subTab4) subTab4.style.display = "block";
            if(subTab1) subTab1.style.display = "none";
            if(subTab2) subTab2.style.display = "none";
            if(subTab3) subTab3.style.display = "none";
            if(subTab5) subTab5.style.display = "none";
        });
    }
    btnActivityParticipate.addEventListener("click", function () {
        // Xóa lớp 'active' từ tất cả các nút và subTabs
        if (btnConfirmStage0) btnConfirmStage0.classList.remove("active");
        btnActivityNeedConfirm.classList.remove("active");
        btnNewButton.classList.remove("active");
        if (btnCancelStage1) btnCancelStage1.classList.remove("active");
        btnActivityParticipate.classList.add("active");

        // Xóa lớp 'active' từ tất cả các subTabs
        if (subTab1) subTab1.style.display = "none";
        if (subTab2) subTab2.style.display = "none";
        if (subTab3) subTab3.style.display = "none";
        if (subTab4) subTab4.style.display = "none";
        if (subTab5) subTab5.style.display = "block";
    });
})

//-----------------show modal duyet bai dang-------------------------//
var maDuyetBai;
$(document).ready(function () {
    var btnShowModal = document.getElementsByClassName("buttonConfirmPostStage0");
    for(btn of btnShowModal){
        btn.addEventListener("click", async (e) =>{
            maDuyetBai = e.target.dataset.hoatdongId;
            showConfirmModal();
        });
    }


    confirmStage0Modal.addEventListener("click", function () {
        closeConfirmModal();
    });
    function showConfirmModal() {
        // Hiển thị modal
        $('#confirmStage0').modal("show");
    }
    function closeConfirmModal() {
        // Đóng modal
        $('#confirmStage0').modal("hide");
    }
});

//------------------------------------ Duyệt bài đăng--------------------------//
$(document).ready(function () {
    $(".btnConfirmActivityStage0").on("click", function() {
        console.log("maHD:", maDuyetBai);
        confirmPost(maDuyetBai);
    });

    var modalContentUpdated = false;
    function confirmPost(maHD) {

        // Gửi AJAX request đến controller
        $.ajax({
            type: 'POST',
            url: '/Confirm-post',
            data: { 'maHD': maHD },
            success: function (data) {
                console.log('Success:', data);
                // Assuming the submission is successful, update modal content and hide buttons
                updateModalContent("Xét duyệt bài đăng thành công.");
                hideButtons();
                modalContentUpdated =true;
            },
            error: function (error) {
                console.error('Error:', error);
            }
        });
    }

    function updateModalContent(newContent) {
        // Update the modal body content
        $('#modalBodyContent').html(newContent);
    }

    function hideButtons() {
        // Hide the buttons in the modal footer
        $('#confirmStage0 .modal-footer').hide();
    }
    $('.closeModalLogout').on('click', function () {
        $('#confirmStage0').modal('hide');
        if(modalContentUpdated ===true){
            location.reload();
        }
    });
})



//--------------------show modal duyet hoat dong------------------//
var maDuyetHD;
$(document).ready(function () {
    var buttonConfirmPostStage1 = document.getElementsByClassName("buttonConfirmPostStage1");
    for(btn of buttonConfirmPostStage1) {
        btn.addEventListener("click", async (e) => {
            maDuyetHD = e.target.dataset.hoatdongId;
            var response = await fetch(`/check-participant?maHD=${maDuyetHD}`)
            if (!response.ok) {
                console.log("error:", response.status);
            } else {
                var result = await response.json();
                var hoatdong = result.hoatdong;
                var sotnv = result.sotnv;
                var checkParticipant = result.checkParticipant;
                if (checkParticipant) {
                    // document.getElementsByClassName('incorrect-participant').style.display = 'none';
                    var element = document.getElementsByClassName('incorrect-participant')[0];
                    if (element) {
                        element.style.display = 'none';
                    }
                    showConfirmModal();
                } else {
                    document.getElementById('txt_stnvtt').value = hoatdong.soTNVToiThieu;
                    document.getElementById('txt_stnvtd').value = hoatdong.soTNVToiDa;
                    document.getElementById('txt_stnv').value = sotnv;
                    showConfirmModal();
                }
            }
        });
    }

    confirmStage0Modal.addEventListener("click", function () {
        closeConfirmModal();
    });
    function showConfirmModal() {
        // Hiển thị modal
        $('#confirmStage1').modal("show");
    }
    function closeConfirmModal() {
        // Đóng modal
        $('#confirmStage1').modal("hide");
    }

});
//-----------------------Duyet hoat dong--------------------------//
$(document).ready(function () {
    $('.btnConfirmActivityStage1').on('click', function () {
        console.log("maHD:", maDuyetHD);
        confirmPost(maDuyetHD);
    });
    var modalContentUpdated = false;
    function confirmPost(maHD) {

        // Gửi AJAX request đến controller
        $.ajax({
            type: 'POST',
            url: '/Confirm-activity',
            data: { 'maHD': maHD },
            success: function (data) {
                console.log('Success:', data);
                // Assuming the submission is successful, update modal content and hide buttons
                updateModalContent("Xét duyệt hoạt động thành công.");
                hideButtons();
                modalContentUpdated =true;
            },
            error: function (error) {
                console.error('Error:', error);
            }
        });
    }

    function updateModalContent(newContent) {
        // Update the modal body content
        $('#modalBodyContent2').html(newContent);
    }

    function hideButtons() {
        // Hide the buttons in the modal footer
        $('#confirmStage1 .modal-footer').hide();
    }
    $('.closeModalLogout').on('click', function () {
        $('#confirmStage1').modal('hide');
        if(modalContentUpdated ===true){
            location.reload();
        }
    });
})

//----------------------------------Show modal duyet thành vien------------------------//
var maHD;
$(document).ready(function () {
    var btnViewPartici = document.getElementsByClassName('viewParticipant');
    for(const btn of btnViewPartici){
        btn.addEventListener("click", async (e) => {
            console.log('maHD: ', e.target.dataset.value);
            maHD = e.target.dataset.value;
            var response = await fetch(`/get-member-need-confirm?maHD=${maHD}`)
            if(!response.ok){
                console.log("error:", response.status);

            } else{
                var listMember = await response.json();
                console.log(listMember);
                console.log('ok');
                // $('#MemberModal .modal-content').empty();
                $('#MemberModal').modal('show');
                $('#MemberModal .modal-body-member').empty();
                $('#MemberModal .modal-footer').empty();
                // $(targetModal + ' .modal-body-member').empty();
                listMember.forEach(function (thanhvien) {
                    var modalContent = '<div class="modal-member">\
                               <span>\
                                 <img class="modal-member-ava" src="' + thanhvien.anh + '" alt="">\
                                 <span class="modal-member-name">' + thanhvien.hoTen + '</span>\
                               </span>\
                               <span class="modal-member-rate">\
                                 <i class="rate-detail">5</i>\
                                 <i class="bi bi-star-fill icon-star"></i>\
                                 <input type="checkbox" value="'+thanhvien.id+'">\
                               </span>\
                             </div>\
                            </div>';

                    $('#MemberModal .modal-body-member').append(modalContent);
                    // $(targetModal + ' .modal-body-member').append(modalContent);
                });
                var modalFooter = `
                    <div class="modal-footer">
                        <button type="button" class="btn btn-danger" id="btnConfirmPartici">Duyệt</button>
                        <button type="button" class="close closeModalLogout btn btn-secondary" data-dismiss="modal">Hủy</button>
                    </div>`;

                $('#MemberModal .modal-footer').append(modalFooter);

            }
        });
    }



    $('.closeModalLogout').on('click', function () {
        $('#MemberModal').modal('hide');
        // if(modalContentUpdated ===true){
        //     location.reload();
        // }
    });
})
//-----------------------------------Duyet thanh vien-----------------------//
$(document).on('click', '#btnConfirmPartici', function () {
    console.log('Button clicked!');
        var selectedIds = [];
        $('#MemberModal .modal-body-member input[type="checkbox"]:checked').each(function () {
            selectedIds.push($(this).val());
        });
        console.log('maHD:', maHD);
        console.log('ids: ', selectedIds);
        const idsString = selectedIds.join(",");
        const formData = new FormData();
        formData.append('maHD', maHD);
        formData.append('ids', idsString);

        // Gửi danh sách ID tới controller
        $.ajax({
            type: 'POST',
            url: '/Confirm-participants',
            // contentType: 'application/json',
            data:formData,
            processData: false,  // Đặt giá trị này thành false
            contentType: false,
            // data: JSON.stringify({maHD: maHD, ids: selectedIds}),
            success: function (response) {
                // Xử lý phản hồi từ server nếu cần
                console.log(response);
                console.log('Data sent:', formData);
                console.log('xác nhận thaành viên ok');
                $('#MemberModal').modal('hide');
                switchToMemberTabAndReload();
            },
            error: function (error) {
                console.error('Error:', error);
            }
        });
    });
function switchToMemberTabAndReload() {
    // location.reload(true);
    $('.tabs .tab-item').removeClass('active');
    $('.tabs .tab-item:nth-child(2)').addClass('active');
    $('.tab-content .tab-pane').removeClass('active');
    $('#subTab3').addClass('active');
     // True để force reload từ server
    setTimeout(function () {
       location.reload(true); // True để force reload từ server
    }, 500);
}
// window.location.reload();
// // Chuyển về tab "Xét duyệt thành viên"
// $('.tabs .tab-item').removeClass('active');
// $('.tabs .tab-item:nth-child(2)').addClass('active');
// $('.tab-content .tab-pane').removeClass('active');
// $('#subTab3').addClass('active');
//-------------------------------show modal thanh vien da duoc duyetj----------------//
$(document).ready(function () {

    var btnViewMember = document.getElementsByClassName('viewParticipanted');
    for(btn of btnViewMember){
        btn.addEventListener("click", async (e) => {
            console.log('maHD: ', e.target.dataset.value);
            maHD = e.target.dataset.value;
            // var response = await fetch(`/get-member-need-confirm?maHD=${maHD}`, {
            //     method: 'GET',
            // });
            var response = await fetch(`/get-member-confirmed?maHD=${maHD}`)
            if(!response.ok){
                console.log("error:", response.status);

            } else{
                var listMember = await response.json();
                console.log(listMember);
                console.log('ok');
                $('#MemberModal').modal('show');
                $('#MemberModal .modal-footer').empty();
                $('#MemberModal .modal-body-member').empty();
                // $(targetModal + ' .modal-body-member').empty();
                listMember.forEach(function (thanhvien) {
                    var modalContent = '<div class="modal-member">\
                               <span>\
                                 <img class="modal-member-ava" src="' + thanhvien.anh + '" alt="">\
                                 <span class="modal-member-name">' + thanhvien.hoTen + '</span>\
                               </span>\
                               <span class="modal-member-rate">\
                                 <i class="rate-detail">5</i>\
                                 <i class="bi bi-star-fill icon-star"></i>\
                                 <input type="checkbox" value="'+thanhvien.id+'">\
                               </span>\
                             </div>';
                    $('#MemberModal .modal-body-member').append(modalContent);
                    // $(targetModal + ' .modal-body-member').append(modalContent);
                });

                var modalFooter = `
                    <div class="modal-footer">
                        <button type="button" class="btn btn-danger" id="btnCancelPartici">Hủy duyệt</button>
                        <button type="button" class="close closeModalLogout btn btn-secondary" data-dismiss="modal">Hủy</button>
                    </div>`;

                $('#MemberModal .modal-footer').append(modalFooter);
            }
        });
    }
})
//-----------------------------xoa duyet thanh vien---------------------//
$(document).on('click', '#btnCancelPartici', function () {
    var selectedIdCancel = [];
    $('#MemberModal .modal-body-member input[type="checkbox"]:checked').each(function () {
        selectedIdCancel.push($(this).val());
    });
    console.log('maHD:', maHD);
    console.log('ids: ', selectedIdCancel);
    const idsString = selectedIdCancel.join(",");
    const formData = new FormData();
    formData.append('maHD', maHD);
    formData.append('ids', idsString);

    // Gửi danh sách ID tới controller
    $.ajax({
        type: 'POST',
        url: '/Cancel-participants',
        // contentType: 'application/json',
        data:formData,
        processData: false,  // Đặt giá trị này thành false
        contentType: false,
        // data: JSON.stringify({maHD: maHD, ids: selectedIds}),
        success: function (response) {
            // Xử lý phản hồi từ server nếu cần
            console.log(response);
            console.log('Hủy duyệt thành viên ok');
            $('#MemberModal').modal('hide');
            switchToMemberTabAndReload();
        },
        error: function (error) {
            console.error('Error:', error);
        }
    });
});
//--------------------------Show modal Hủy bài viết - hoạt động----------------------//
var idHD;
$(document).ready(function() {
    const activityCancelButtons = document.querySelectorAll('.btnCancelPost, .btnCancelActivity');
    const handleCancelClick = async (e) => {
        console.log("button click");
         idHD =  e.target.dataset.value;
        console.log('mahd:', idHD);
        // Make an AJAX request to the controller to get activity information
        $.ajax({
            url: '/get-activity-by-id',
            type: 'GET',
            data: {'maHD' :idHD},
            success: function (data) {
                console.log(data);
                // Populate modal with activity information
                $("#txt_NameTopic").val(data.maChuDe.tenChuDe); // Replace 'data.txt_NameTopic' with the actual field from your controller
                $("#txt_NameActi").val(data.tenHD); // Replace 'data.txt_NameActi' with the actual field from your controller
                $("#txt_mota").val(""); // Leave txt_mota empty for the user to input
                // Show the modal
                $('#ThemHoatDongModal').modal('show');
            },
            error: function (error) {
                console.log(error);
            }
        });
   // });
    }
    activityCancelButtons.forEach(button => button.addEventListener('click', handleCancelClick));
})

//-----------------------Huy bai viet- Hoat dong---------------------------//
var check=false;
$(document).ready(function() {
    $(".btnSave").on("click", function() {
        // Get the value from the txt_mota textarea
        var txtMotaValue = $("#txt_mota").val();
        var txt_mota = $("#txt_mota");

        if (txtMotaValue.trim() === '') {
            txt_mota.addClass("is-invalid");
            txt_mota.siblings(".invalid-feedback").text("Vui lòng nhập lý do hủy hoạt động");
            $('#ThemHoatDongModal').modal('show');
        } else {
            $.ajax({
                url: '/cancel-post',
                type: 'POST',
                data: {
                    'maHD': idHD, txtHuy: txtMotaValue,
                },
                success: function (response) {
                    // Handle success
                    console.log(response);
                    updateModalContent("Hủy bài đăng thành công");
                    check = true;
                    hideButtons();
                    console.log("Huy bai dang thanh cong");
                },
                error: function (error) {
                    console.log(error);
                }
            });
        }
    });



    // $("#ThemHoatDongModal").submit(function (e) {
    //     e.preventDefault();
    //
    //     // Manually trigger the form validation
    //     if (this.checkValidity()) {
    //         var txtMotaValue = $("#txt_mota").val();
    //
    //         // Make an AJAX request to update the activity information
    //         $.ajax({
    //             url: '/cancel-post',
    //             type: 'POST',
    //             data: {
    //                 'maHD': idHD,
    //                 'txtHuy': txtMotaValue,
    //             },
    //             success: function (response) {
    //                 // Handle success
    //                 console.log(response);
    //                 updateModalContent("Hủy bài đăng thành công");
    //                 check = true;
    //                 hideButtons();
    //                 console.log("Huy bai dang thanh cong");
    //             },
    //             error: function (error) {
    //                 console.log(error);
    //             }
    //         });
    //     } else {
    //         console.log("Form validation failed");
    //     }
    // });
    function updateModalContent(newContent) {
        // Update the modal body content
        $('.modal-body-themhd').html(newContent);
    }
    function hideButtons() {
        // Hide the buttons in the modal footer
        $('#ThemHoatDongModal .modal-footer').hide();
    }
    // Add an event listener for the Cancel button inside the modal
    $(".btnCancel").on("click", function() {
        // Close the modal without saving
        $('#ThemHoatDongModal').modal('hide');
        if(check ===true){
            location.reload();
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
})
//-----------------------------Xem preview hoat dong(de duyet)-----------------------//
var mahoatdong;
$(document).ready(function () {
    var btnViewDetailActivity = document.getElementsByClassName('viewDetailActivity');

    for(btn of btnViewDetailActivity){
        btn.addEventListener("click", async (e) => {
            console.log('maHD: ', e.target.dataset.value);
            mahoatdong = e.target.dataset.value;
            var response = await fetch(`/check-activity?maHD=${mahoatdong}`)
            if(!response.ok){
                console.log("error:", response.status);

            } else{
                var result = await response.json();
                var hoatdong = result.hoatdong;
                var isCondition = result.isConditionMet;
                console.log("Hoatdong:", hoatdong);
                console.log("isCondition:", isCondition);
                if(isCondition)
                {
                     window.location.href = '/Join?id=' + mahoatdong  ;
                }else{
                    console.log("mo modal nho");
                    displayModal(hoatdong);
                    document.getElementById('txt_sotnv').style.display = 'none';
                    document.getElementById('labeltnv').style.display = 'none';
                }

            }
        });
    }
    var btnViewActivityConfirm =document.getElementById("btnViewActivityConfirm");
    btnViewActivityConfirm.addEventListener("click",async (e)=>{
        var id = btnViewActivityConfirm.getAttribute('data-value');
        //var id = $(this).data('value');
        console.log("id:",id);
        var response = await fetch(`/check-activity?maHD=${id}`)
        if(!response.ok){
            console.log("error:", response.status);
        } else {
            var result = await response.json();
            var hoatdong = result.hoatdong;
            var sotnv= result.sotnv;
            displayModal(hoatdong);
            document.getElementById('txt_sotnv').style.display = 'block';
            document.getElementById('labeltnv').style.display = 'block';
            document.getElementById('txt_sotnv').value =sotnv;

        }
    })

    function displayModal(hoatdong) {
        // Get the modal element by ID
        //var modal = document.getElementById('ViewHoaDongModal');
        console.log(" view hoat dong");
        // Show the modal
        $('#ViewHoaDongModal').modal("show");

        // Populate modal fields with hoatdong information
        // Assuming there are elements with IDs like 'txt_NameTopic', 'txt_NameActi', etc.
        document.getElementById('txt_Chude').value = hoatdong.maChuDe.tenChuDe;
        document.getElementById('txt_TenHD').value = hoatdong.tenhd;
        document.getElementById('txt_DiaDiem').value = hoatdong.diaDiem;
        document.getElementById('txt_TGBD').value = hoatdong.thoiGianBD;
        document.getElementById('txt_TGKT').value = hoatdong.thoiGianKT;
        document.getElementById('txt_tnvtt').value = hoatdong.soTNVToiThieu;
        document.getElementById('txt_tnvtd').value = hoatdong.soTNVToiDa;
        document.getElementById('txt_MotaHD').value = hoatdong.moTa;

        // Set the image preview if there is an image
        var userImage = document.getElementById('userImage');
        if (hoatdong.anh) {
            userImage.src = hoatdong.anh;
        } else {
            userImage.src = '/images/default.webp'; // Default image if no image provided
        }
    }
    var closeButton = document.querySelector('.btnClose');
    closeButton.onclick = function() {
        var modal = document.getElementById('ViewHoaDongModal');
        modal.style.display = 'none';
    };

})
