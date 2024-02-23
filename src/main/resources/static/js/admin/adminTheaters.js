$("#createTheater").click(function () {
    $(".createModal").css("display","block");
});

$("#deleteTheater").click(function () {
    $(".deleteModal").css("display","block");
});

$(".back").click(function (){
   $(".createModal").css("display", "none");
   $(".deleteModal").css("display", "none");
});

$("#insert").click(function () {
    alert("상영관 등록이 완료되었습니다.");
});


$("#deleteSearch").click(function () {
    const deleteId = $("#deleteId").val();
    const confirmText = confirm("정말 삭제하시겠습니까?");
    if (confirmText) {
        $.ajax({
            url:"deleteTheater",
            method:"POST",
            headers:{
                [csrfHeaderName] : csrfToken
            },
            data:{
                deleteId
            },
            success(){
                alert("삭제가 완료되었습니다.");
            },
            error() {
                alert("삭제에 실패했습니다.");
            }
        });
    }
    else {
        alert("삭제를 취소했습니다.")
    }
});