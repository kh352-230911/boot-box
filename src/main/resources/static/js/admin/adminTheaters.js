$("#createTheater").click(function () {
    $(".createModal").css("display","block");
});

$("#deleteTheater").click(function () {
    $(".deleteModal").css("display","block");
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

$(".theaters").click(function () {
    const theaterId = $(this).find(".theaterId").val();
    $.ajax({
        url:"insertScheduleList",
        data:{
            theaterId
        },
        success(){
            alert("일정 불러오기 성공");
        },
        error() {
            alert("일정을 불러오는데 실패했습니다.");
        }
    });
});

//0424 상영 영화 추가

//0424 상영 영화 삭제
function deleteMovie(movieId) {
    alert('선택한 영화의 ID는 ' + movieId + '입니다.');
}



//0423 상영스케쥴 추가
$("#createScheule").click(function () {
    $(".createScheduleModal").css("display","block");
});

$(".back").click(function (){
    $(".createModal").css("display", "none");
    $(".deleteModal").css("display", "none");
    $(".createScheduleModal").css("display", "none");
    $(".addTheaterMovieModal").css("display", "none");
});

$("#sch_insert").click(function () {
    const sch_theaterId = $("#sch_theaterId").val();
    const sch_movieId = $("#sch_movieId").val();
    const sch_date = $("#sch_date").val();
    const sch_startTime = $("#sch_date").val()+" "+$("#sch_startTime").val();
    const redirectUrl = $("#adminRegionUrl").attr("href");
    console.log("상영일정 신청:",sch_theaterId,"/",sch_movieId,"/",sch_date,"/",sch_startTime);
    $.ajax({
        url: "createSchedule",
        method: "POST",
        headers: {
            [csrfHeaderName] : csrfToken
        },
        data: {
            sch_theaterId, sch_movieId, sch_date, sch_startTime
        },
        success() {
            alert("상영스케쥴 신청이 완료되었습니다.");
            location.href = redirectUrl;
        },
        error() {
            alert("상영스케쥴 신청에 실패했습니다.");
        }
    });
});

$(function() {
    // datepicker를 sch_date input 요소에 연결
    $("#sch_date").datepicker({
        dateFormat: "yy-mm-dd", // 날짜 형식 설정
        changeMonth: true, // 월 변경 가능 여부
        changeYear: true, // 연도 변경 가능 여부
        showButtonPanel: true, // 버튼 패널 표시 여부
        monthNames: ["1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월"], // 월 한글로 표시
        dayNamesMin: ["일", "월", "화", "수", "목", "금", "토"],// 요일 한글로 표시
        minDate:0 //오늘날짜 이 전은 선택할 수 없다!
    });
});

// timepicker를 sch_startTime input 요소에 연결 https://timepicker.co/
$("#sch_startTime").timepicker({
    timeFormat: 'HH:mm',
    interval: 10,
    minTime: '10',
    maxTime: '23:00', // 6시간까지
    // defaultTime: '11',
    startTime: '10:00',
    dynamic: false,
    dropdown: true,
    scrollbar: true
});

//==========아래부턴 해당 지점 상영 영화 관련 코드======================
//0424 상영 영화 추가할 때 팝업되는 모달  - 그리고 현 지점에서 상영중인 영화를 제외한 전체 영화를 db에서 조회한다..

function addTheaterMovie(cinemaId) {
    //alert('현재 지점의 넘버는 ' + cinemaId + '입니다.');
    $(".addTheaterMovieModal").css("display","block");

    $.ajax({
        url:"findOtherMovie",
        data:{
            cinemaId
        },
        success(){
            alert("영화 목록을 불러왔습니다.");
        },
        error() {
            alert("영화목록을 불러오는데 실패했습니다.");
        }
    });

}



