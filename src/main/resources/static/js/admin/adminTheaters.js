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
    const theaterId = $("#theaterId").val();
    const cinemaId = $("#cinemaId").val();
    const theaterName = $("#theaterName").val();
    const theaterSeat = $("#theaterSeat").val();
    const redirectUrl = $("#adminRegionUrl").attr("href");

    $.ajax({
        url: "createTheater",
        method: "POST",
        headers: {
            [csrfHeaderName] : csrfToken
        },
        data: {
            theaterId, cinemaId, theaterName, theaterSeat
        },
        success() {
            alert("상영관 등록이 완료되었습니다.");
            location.href = redirectUrl;
        },
        error() {
            alert("상영관 등록에 실패했습니다.");
        }
    });
});


$("#deleteSearch").click(function () {
    const deleteId = $("#deleteId").val();
    const redirectUrl = $("#adminRegionUrl").attr("href");
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
                location.href = redirectUrl;
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