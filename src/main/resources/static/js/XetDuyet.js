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
var mataikhoan ;
function getOrganizator(maHD)
{
    $.ajax({
        type: 'POST',
        url: '/get-organizator',
        data: {  'maHD': maHD },
        success: function (data) {
            console.log('Success:', data);
            mataikhoan= parseInt(data);
            console.log("thanh cong");
            console.log("mataikhoan:",mataikhoan);
        },
        error: function (error) {
            console.error('Error:', error);
        }
    });
}
//------------------------------------ Duyệt bài đăng--------------------------//
$(document).ready(function () {
    $(".btnConfirmActivityStage0").on("click", function() {
        console.log("maHD:", maDuyetBai);
        getOrganizator(maDuyetBai);
        confirmPost(maDuyetBai);
    });

    function sendNotiConfirmPost(maHD)
    {
        var noidung ="Bài đăng của bạn đã được xét duyệt";
        console.log("maOR:",mataikhoan);
        $.ajax({
            type: 'POST',
            url: '/them-thong-bao',
            data: {  'maTK': mataikhoan ,'noiDung': noidung, 'loaiTB':1, 'ma':maHD,},
            success: function (data) {
                console.log('Success:', data);
                console.log("thanh cong");
            },
            error: function (error) {
                console.error('Error:', error);
            }
        });
    }

    var modalContentUpdated = false;
    function confirmPost(maHD) {

        // Gửi AJAX request đến controller
        $.ajax({
            type: 'POST',
            url: '/Confirm-post',
            data: { 'maHD': maHD },
            success: function (data) {
                console.log('Success:', data);
                sendNotiConfirmPost(maHD);
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
var listMember =[];
$(document).ready(function () {

    function getMemberJoinActivity(maHD)
    {
        $.ajax({
            type: 'POST',
            url: '/get-member',
            data: {  'maHD': maHD },
            success: function (data) {
                console.log('Success:', data);
                listMember = data.map(function (id) {
                    return parseInt(id);
                });
                // mataikhoan= parseInt(data);
                console.log("thanh cong");
                console.log("mataikhoan:",mataikhoan);
            },
            error: function (error) {
                console.error('Error:', error);
            }
        });
    }

    $('.btnConfirmActivityStage1').on('click', function () {
        console.log("maHD:", maDuyetHD);
        getMemberJoinActivity(maDuyetHD);
        confirmPost(maDuyetHD);
    });
    function sendNotiConfirmActivity(maHD)
    {
        var noidung ="Hoạt động mà bạn tham gia đã được xét duyệt";
        for (var i = 0; i < listMember.length; i++) {
            var id = listMember[i];
            $.ajax({
                type: 'POST',
                url: '/them-thong-bao',
                data: {'maTK': id, 'noiDung': noidung, 'loaiTB': 1, 'ma': maHD,},
                success: function (data) {
                    console.log('Success:', data);
                    console.log("thanh cong");
                },
                error: function (error) {
                    console.error('Error:', error);
                }
            });
        }
    }

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
                sendNotiConfirmActivity(maHD);
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
var matkhoan;
$(document).ready(function () {
    var btnViewPartici = document.getElementsByClassName('viewParticipant');
    for(const btn of btnViewPartici){
        btn.addEventListener("click", async (e) => {
            console.log('maHD: ', e.target.dataset.value);
            maHD = e.target.dataset.value;
            // getOrganizator(maHD);
            // console.log('mataikhoan:',matkhoan);
            var response = await fetch(`/get-member-need-confirm?maHD=${maHD}`)
            if(!response.ok){
                console.log("error:", response.status);

            } else{
                var responseData = await response.json();
                var listMember = responseData.listMemberNeedConfirm;
                var pointList = responseData.pointList;
                console.log(listMember);
                console.log('ok');

                // $('#MemberModal .modal-content').empty();
                $('#MemberModal').modal('show');
                $('#MemberModal .modal-body-member').empty();
                $('#MemberModal .modal-footer').empty();
                // $(targetModal + ' .modal-body-member').empty();
                listMember.forEach(function (thanhvien,index) {
                    var modalContent = '<div class="modal-member">\
                               <span>\
                                 <img class="modal-member-ava" src="' + thanhvien.anh + '" alt="">\
                                 <span class="modal-member-name">' + thanhvien.hoTen + '</span>\
                               </span>\
                               <span class="modal-member-rate">\
                                 <i class="rate-detail" >' + pointList[index] + '</i>\
                                 <i class="bi bi-star-fill icon-star"></i>\
                                 <input type="checkbox" value="'+thanhvien.maTK.id+'">\
                                    \<i class="bi bi-info-circle mr--0_5 show-member-detail" data-member-id="' + thanhvien.maTK.id + '"></i>\
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
$(document).ready(function () {
    function sendNotiMemberNeedConfirm(maHD,idString)
    {
        var noidung ="Yêu cầu tham gia hoạt động của bạn đã được xét duyệt";
        for (var i = 0; i < idString.length; i++) {
            var id = idString[i];
            $.ajax({
                type: 'POST',
                url: '/them-thong-bao',
                data: {  'maTK': id ,'noiDung': noidung, 'loaiTB':1, 'ma':maHD,},
                success: function (data) {
                    console.log('Success:', data);
                    console.log("thanh cong");
                },
                error: function (error) {
                    console.error('Error:', error);
                }
            });
        }
        // console.log("maOR:",mataikhoan);

    }

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
            data: formData,
            processData: false,  // Đặt giá trị này thành false
            contentType: false,
            // data: JSON.stringify({maHD: maHD, ids: selectedIds}),
            success: function (response) {
                // Xử lý phản hồi từ server nếu cần
                console.log(response);
                console.log('Data sent:', formData);
                sendNotiMemberNeedConfirm(maHD,idsString);
                console.log('xác nhận thành viên ok');
                $('#MemberModal').modal('hide');
                switchToMemberTabAndReload();
            },
            error: function (error) {
                console.error('Error:', error);
            }
        });
    });
})

function switchToMemberTabAndReload() {
    // location.reload(true);
    $('.tabs .tab-item').removeClass('active');
    $('.tabs .tab-item:nth-child(2)').addClass('active');
    $('.tab-content .tab-pane').removeClass('active');
    $('#subTab3').addClass('active');
    setTimeout(function () {
        location.reload(true);
    }, 500);
}
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
                var responseData = await response.json();
                var listMember = responseData.listMemberConfirmed;
                var pointList = responseData.pointList;
                console.log(listMember);
                console.log('ok');
                $('#MemberModal').modal('show');
                $('#MemberModal .modal-footer').empty();
                $('#MemberModal .modal-body-member').empty();
                // $(targetModal + ' .modal-body-member').empty();
                listMember.forEach(function (thanhvien,index) {
                    var modalContent = '<div class="modal-member">\
                               <span>\
                                 <img class="modal-member-ava" src="' + thanhvien.anh + '" alt="">\
                                 <span class="modal-member-name">' + thanhvien.hoTen + '</span>\
                               </span>\
                               <span class="modal-member-rate">\
                                 <i class="rate-detail">' + pointList[index] + '</i>\
                                 <i class="bi bi-star-fill icon-star"></i>\
                                 <input class="mb--0_2" type="checkbox" value="'+thanhvien.maTK.id+'">\
                                    \<i class="bi bi-info-circle mr--0_5 show-member-detail" data-member-id="' + thanhvien.maTK.id + '"></i>\
                               </span>\
                             </div>';
                    $('#MemberModal .modal-body-member').append(modalContent);
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
$(document).ready(function () {

    $('#MemberModal .modal-body-member').on('click', '.show-member-detail', function (e) {
        $('#MemberModal').modal('hide');
        var maThanhVien = $(this).data('memberId');
        console.log("mathanhvien:", maThanhVien);
        console.log("Showing modal");
        $.ajax({
            url: '/get-member-by-id',
            type: 'GET',
            data: {'maTK' :maThanhVien},
            success: function (data) {
                console.log(data);
                $("#anhDaiDien").attr('src', data.maTK.anhDaiDien);
                $("#name").val(data.hoTen);
                $("#birthday").val(data.ngaySinh);
                $("#address").val(data.diaChi);
                $("#phone").val(data.soDienThoai);
                $("#email").val(data.email);
                $("input[name='gioiTinh']").filter(`[value='${data.gioiTinh}']`).prop('checked', true);
                // $('.form-check-gender').prop('disabled', true);
                // Show the modal
                $('#modelMemberDetail').modal('show');
            },
            error: function (error) {
                console.log(error);
            }
        });

    });
});
$(document).ready(function () {
    $('#modelMemberDetail #closeButton').click(function () {
        console.log('Button close clicked');
        $('#modelMemberDetail').modal('hide');
        $('#MemberModal').modal('show');

    });
});

//-----------------------------xoa duyet thanh vien---------------------//
$(document).ready(function () {
    function sendNotiMemberConfirm(maHD, idString) {
        var noidung = "Yêu cầu tham gia hoạt động của bạn đã bị hủy bỏ";
        for (var i = 0; i < idString.length; i++) {
            var id = idString[i];
            $.ajax({
                type: 'POST',
                url: '/them-thong-bao',
                data: {'maTK': id, 'noiDung': noidung, 'loaiTB': 1, 'ma': maHD,},
                success: function (data) {
                    console.log('Success:', data);
                    console.log("thanh cong");
                },
                error: function (error) {
                    console.error('Error:', error);
                }
            });
        }

    }

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

        $.ajax({
            type: 'POST',
            url: '/Cancel-participants',
            data: formData,
            processData: false,
            contentType: false,
            success: function (response) {
                console.log(response);
                sendNotiMemberConfirm(maHD,idsString);
                console.log('Hủy duyệt thành viên ok');
                $('#MemberModal').modal('hide');
                switchToMemberTabAndReload();
            },
            error: function (error) {
                console.error('Error:', error);
            }
        });
    });
})
//--------------------------Show modal Hủy bài viết - hoạt động----------------------//
var idHD;
var ids=[];
$(document).ready(function() {
    function getMember(maHD)
    {
        $.ajax({
            type: 'POST',
            url: '/get-member',
            data: {  'maHD': maHD },
            success: function (data) {
                console.log('Success:', data);
                ids = data.map(function (id) {
                    return parseInt(id);
                });
                // mataikhoan= parseInt(data);
                console.log("thanh cong");
                console.log("mataikhoan:",mataikhoan);
            },
            error: function (error) {
                console.error('Error:', error);
            }
        });
    }
    const activityCancelButtons = document.querySelectorAll('.btnCancelPost, .btnCancelActivity');
    const handleCancelClick = async (e) => {
        console.log("button click");
         idHD =  e.target.dataset.value;
        console.log('mahd:', idHD);
        $.ajax({
            url: '/get-activity-by-id',
            type: 'GET',
            data: {'maHD' :idHD},
            success: function (data) {
                console.log(data);
                // Populate modal with activity information
                $("#txt_TenChuDe").val(data.maChuDe.tenChuDe);
                $("#txt_TenHoatDong").val(data.tenHD);
                $("#txt_LydoHuy").val("");
                // Show the modal
                $('#HuyHoatDongModal').modal('show');
                getOrganizator(idHD);
                getMember(idHD);
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
    function sendNotiCancelActivity(maHD)
    {
        var noidung ="Hoạt động mà bạn tham gia đã bị hủy";
        for (var i = 0; i < ids.length; i++) {
            var id = ids[i];
            $.ajax({
                type: 'POST',
                url: '/them-thong-bao',
                data: {'maTK': id, 'noiDung': noidung, 'loaiTB': 1, 'ma': maHD,},
                success: function (data) {
                    console.log('Success:', data);
                    console.log("thanh cong");
                },
                error: function (error) {
                    console.error('Error:', error);
                }
            });
        }
    }
    $(".btnSave").on("click", function() {
        // Get the value from the txt_mota textarea
        var txtMotaValue = $("#txt_LydoHuy").val();
        var txt_mota = $("#txt_LydoHuy");

        if (txtMotaValue.trim() === '') {
            txt_mota.addClass("is-invalid");
            txt_mota.siblings(".invalid-feedback").text("Vui lòng nhập lý do hủy hoạt động");
            $('#HuyHoatDongModal').modal('show');
        } else {
            $.ajax({
                url: '/cancel-post',
                type: 'POST',
                data: {
                    'maHD': idHD, txtHuy: txtMotaValue,
                },
                success: function (response) {
                    console.log(response);
                    updateModalContent("Hủy bài đăng thành công");
                    check = true;
                    hideButtons();
                    sendNotiCancelActivity(idHD);
                    console.log("Huy bai dang thanh cong");
                    location.reload();
                },
                error: function (error) {
                    console.log(error);
                }
            });
        }
    });

    function updateModalContent(newContent) {
        $('.modal-body-themhd').html(newContent);
    }
    function hideButtons() {
        $('#HuyHoatDongModal .modal-footer').hide();
    }
    $(".btnCancel").on("click", function() {
        // Close the modal without saving
        $('#HuyHoatDongModal').modal('hide');
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
    var btnViewActivityConfirm =document.getElementsByClassName("btnViewActivityConfirm");
    for(btn of btnViewActivityConfirm){
        btn.addEventListener("click",async (e)=>{
            var id= e.target.dataset.value;
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
    }


    function displayModal(hoatdong) {
        console.log(" view hoat dong");
        $('#ViewHoaDongModal').modal("show");

        document.getElementById('txt_Chude').value = hoatdong.maChuDe.tenChuDe;
        document.getElementById('txt_TenHD').value = hoatdong.tenhd;
        document.getElementById('txt_DiaDiem').value = hoatdong.diaDiem;
        document.getElementById('txt_TGBD').value = hoatdong.thoiGianBD;
        document.getElementById('txt_TGKT').value = hoatdong.thoiGianKT;
        document.getElementById('txt_toithieu').value = hoatdong.soTNVToiThieu;
        document.getElementById('txt_toida').value = hoatdong.soTNVToiDa;
        document.getElementById('txt_MotaHD').value = hoatdong.moTa;
        // Set the image preview if there is an image
        var userImage = document.getElementById('userImageView');
        if (hoatdong.anh) {
            userImage.src = hoatdong.anh;
        } else {
            userImage.src = '/images/default.webp';
        }
    }
    var closeButton = document.querySelector('.btnClose');
    closeButton.onclick = function() {
        var modal = document.getElementById('ViewHoaDongModal');
        modal.style.display = 'none';
    };

})
//Duyệt đề xuất
function reloadSugg(suggList){
    const suggDiv = document.querySelector(".suggestion")
    if(suggDiv!=null){
        suggDiv.innerHTML = ''
        suggList.forEach(sugg=>{
            const suggTime = new Intl.DateTimeFormat("vi-VN",{ day: '2-digit', month: '2-digit', year: 'numeric' }).format(new Date(sugg.thoiGianDeXuat))
            suggDiv.innerHTML+=
                `
                    <div class="container d-flex align-items-center p-3 border_bottom-solid">
                        <div class="container flex-5 col-9">
                            <h3 class="green-color fs-4">${sugg.maChuDe.tenChuDe}</h3>
                            <p class="mb-0 fs-6">${sugg.moTa}</p>
                            <p class="fs-7 fst-italic my-1"><i class="bi bi-geo-alt-fill green"></i>${sugg.viTri}</p>
                            <p class="fs-7 fst-italic my-1">
                                <i class="bi bi-alarm-fill green"></i>
                                Thời gian đề xuất ${suggTime}
                            </p>
                        </div>
                        <div class="container col-3 display-flex p-0 justify-content-center ml-3">
                            <button class="btn white-color p-2 bg-btn me-3" id="btn-confirm-sugg" data-id="${sugg.id}">Duyệt đề xuất</button>
                            <button class="btn btn-danger" id="btn-cancel-sugg" data-id="${sugg.id}">Hủy</button>
                        </div>
                    </div>
           `
        })
    }
}
async function confirmSugg(){
    try{
            idSugg = $(this).data("id");
        const response =  await fetch(`/confirm-suggestion?idSugg=${idSugg}`,{
            method: 'POST'
        })
        if(!response.ok){
            console.log("Lỗi HTTP. Trạng thái " + response.status);
            return;
        }
        //Thêm thông báo xét duyệt đề xuất
        const confirmedSugg = await response.json();
        const maTK = confirmedSugg.maTK.id;
        const noiDung = "Đề xuất của bạn đã được duyệt thành công."
        const loaiTB = 0;
        const ma = confirmedSugg.id;
        const addURL = await fetch(`/them-thong-bao?maTK=${maTK}&noiDung=${noiDung}&loaiTB=${loaiTB}&ma=${ma}`, {
            method: 'POST'
        });
        if(!addURL.ok){
            console.log("Lỗi thêm thông báo đề xuất. Trạng thái " + addURL.status);
        }
        function customAlert(message){
            Swal.fire({
                icon: 'success',
                text: message,
                confirmButtonText: 'OK',
                customClass:{
                    popup: 'custom-alert-popup'
                }
            })
        }
        customAlert("Duyệt đề xuất thành công!")
        $('.swal2-confirm').on('click', function (e) {
            e.stopPropagation();
        });
        //Load lại đề xuất chưa duyệt
        const res = await fetch(`de-xuat-chua-duyet`)
        if(!res.ok){
            console.log("Lỗi HTTP. Trạng thái " + res.status)
        }
        else{
            const suggList = await res.json();
            reloadSugg(suggList);
        }
    }catch(error){
        console.error(error)
    }
}

$(document).on('click', '#btn-confirm-sugg',confirmSugg)

//Huy de xuat
async function cancelSugg (){
    try {
        const idSugg = $(this).data("id");
        const response = await fetch(`/cancel-suggestion?idSugg=${idSugg}`, {
            method: 'POST'
        });
        if(!response.ok){
            console.log("Lỗi HTTP. Trạng thái " + response.status);
            return;
        }
        //Thêm thông báo xét duyệt đề xuất
        const canceledSugg = await response.json();
        const maTK = canceledSugg.maTK.id;
        const noiDung = "Đề xuất của bạn đã bị hủy."
        const loaiTB = 0;
        const ma = canceledSugg.id;
        const addURL = await fetch(`/them-thong-bao?maTK=${maTK}&noiDung=${noiDung}&loaiTB=${loaiTB}&ma=${ma}`, {
            method: 'POST'
        });
        if(!addURL.ok){
            console.log("Lỗi thêm thông báo đề xuất. Trạng thái " + addURL.status);
        }
        function customAlert(message) {
            Swal.fire({
                icon: 'success',
                text: message,
                confirmButtonText: 'OK',
                customClass: {
                    popup: 'custom-alert-popup'
                }
            });
        }
        customAlert("Hủy đề xuất thành công!");
        $('.swal2-confirm').on('click', function (e) {
            e.stopPropagation();
        });
        const res = await fetch(`de-xuat-chua-duyet`)
        if(!res.ok){
            console.log("Lỗi HTTP. Trạng thái " + res.status)
        }
        else{
            const suggList = await res.json();
            reloadSugg(suggList);
        }
    } catch (error) {
        console.error(error);
    }
}
$(document).on('click', '#btn-cancel-sugg',cancelSugg);
