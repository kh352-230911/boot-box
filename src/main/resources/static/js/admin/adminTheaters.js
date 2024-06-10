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

/*
* 0425 상영 영화 삭제하기.
*
* 삭제하기 전에 해당 지점에 해당영화로 삭제 시점으로부터 미래의 스케쥴이 있는지 확인함.
* 과거 스케쥴은 상관x
* 만약 미래 상영 스케쥴이 1건이라도 존재한다면 삭제하지 않고 알려준다.
*
*
* */
function deleteMovie(id,cinemaId,movieId) {
    console.log(id,cinemaId,movieId);
    // alert('삭제용/현재 지점('+cinemaId+')에서 선택한 영화의 ID는 ' + movieId + '입니다.');
    $.ajax({
        url: "searchMovieSchedule",
        method: "GET",
        data: {
            cinemaId, movieId
        },
        success: function(data) {
            console.log(data);
            if(data.length==0)
            {
                let yes = confirm('해당 영화를 지점 상영 목록에서 삭제 하시겠습니까?');
                if(yes)
                {
                    console.log("예 누름");
                    //삭제 쿼리 진행
                    deleteMovieMyCinema(id,cinemaId,movieId);
                }
                else
                {
                    console.log("아니오 누름");
                }
            }
            else
            {
                alert("영화 아이디 : "+movieId+"는 금일 이후 스케쥴이 존재합니다. 삭제하실 수 없습니다.");
            }
        },
        error() {
            alert("[삭제용]해당 영화 상영스케쥴 조회에 실패하였습니다.");
        }
    });
}
function deleteMovieMyCinema(id,cinemaId,movieId)
{
    const redirectUrl = $("#adminRegionUrl").attr("href");
    $.ajax({
        url:"deleteMovieMyCinema",
        method:"POST",
        headers:{
            [csrfHeaderName] : csrfToken
        },
        data:{
            id
        },
        success(){
            alert("지점 상영 영화 삭제가 완료되었습니다.");
            location.href = redirectUrl;
        },
        error() {
            alert("지점 상영 영화 삭제에 실패했습니다.");
        }
    });
}
// 해당 영화화 지점에 해당하는 일정 조회
function searchMovieSchedule(id,cinemaId,movieId) {
    console.log(id,cinemaId,movieId);
    //alert('조회용/현재 지점('+cinemaId+')에서 선택한 영화의 ID는 ' + movieId + '입니다.');
    $.ajax({
        url: "searchMovieSchedule",
        method: "GET",
        data: {
            cinemaId, movieId
        },
        success: function(data) {
            alert("해당 영화 상영스케쥴 조회가 완료되었습니다.");
            console.log(data);
            //location.href = redirectUrl;
        },
        error() {
            alert("[조회용]해당 영화 상영스케쥴 조회에 실패하였습니다.");
        }
    });
}




$(".back").click(function (){
    $(".createModal").css("display", "none");
    $(".deleteModal").css("display", "none");
    $(".createScheduleModal").css("display", "none");
    //$(".addTheaterMovieModal").css("display", "none");
});

$(".addMovieBack").click(function()
{
    document.getElementById("addMovieId").selectedIndex = 0;
    $(".addTheaterMovieModal").css("display", "none");
    selectInit();
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

function addTheaterMovie(cinemaId)
{
    let selectBox = document.getElementById("addMovieId");
    let posterImage = document.querySelector("#posterImage");
    posterImage.src = "https://t4.ftcdn.net/jpg/03/08/67/51/360_F_308675145_Ye70fJFVPntNVnmxjtVgMy5P8MDEmusB.jpg";
    $(".addTheaterMovieModal").css("display","block");
    $.ajax({
        url:"findOtherMovie",
        type: 'GET',
        data:{
            cinemaId
        },
        success: function(movies) { // 성공적으로 요청을 받았을 때의 콜백 함수
            console.log('Received data:', movies);


            movies.forEach(function(movie) {
                var option = document.createElement("option");
                option.value = movie.id; // 옵션의 값으로 영화 id 설정
                option.text = movie.title; // 옵션의 텍스트로 영화 제목 설정

                option.dataset.posterUrl = movie.posterUrl; // 옵션의 데이터로 포스터 URL 추가
                option.dataset.title = movie.title; // 옵션의 데이터로 포스터 URL 추가
                option.dataset.id = movie.id; // 옵션의 데이터로 포스터 URL 추가
                option.dataset.filmRatings = movie.filmRatings;
                option.dataset.releaseDate = movie.releaseDate;
                option.dataset.runtime = movie.runtime;


                selectBox.appendChild(option); // selectbox에 옵션 추가
            });


        },
        error: function(xhr, status, error) { // 요청이 실패했을 때의 콜백 함수
            console.error('Error:', error);

        }
    });

}

//상영 영화 추가 초기화, 취소로 닫을 때 초기화.
function selectInit()
{
    let posterImage = document.querySelector("#posterImage");
    let movieTitle = document.querySelector("#movieTitle");
    let movieId = document.querySelector("#movieId");

    posterImage.innerHTML = ""; // 포스터 이미지 초기화
    posterImage.src="";
    movieTitle.innerHTML = ""; // 영화 제목 등 정보 초기화
    movieId.innerHTML = ""; // 영화 ID 초기화
}
document.getElementById("addMovieId").addEventListener("change", function() {
    showPoster(); // 옵션 선택 시 포스터 표시 함수 호출
});
function showPoster() {
    var selectBox = document.getElementById("addMovieId");
    var selectedOption = selectBox.options[selectBox.selectedIndex];
    var posterUrl = selectedOption.dataset.posterUrl; // 수정된 부분: dataset.postUrl을 가져옴
    var title = selectedOption.dataset.title;
    var id = selectedOption.dataset.id;
    var runtime = selectedOption.dataset.runtime;
    var releaseDate = selectedOption.dataset.releaseDate;
    var filmRatings = selectedOption.dataset.filmRatings;

    var divShowPoster = document.querySelector(".div-showPoster");
    // divShowPoster.innerHTML = ""; // 기존 내용 지우기

    let posterImage = document.querySelector("#posterImage");
    let movieTitle = document.querySelector("#movieTitle");
    let movieId = document.querySelector("#movieId");

    console.log("movieTitle",movieTitle);
    console.log("movieId",movieId);
    console.log("posterUrl:",posterUrl);
    if (posterUrl == null) {
        // posterUrl이 null일 때 기본 이미지 출력
        var img = document.createElement("img");
        img.loading = "lazy";
        posterImage.loading="lazy";
        // img.className = "movie-list-item-img";
        posterImage.src = "https://t4.ftcdn.net/jpg/03/08/67/51/360_F_308675145_Ye70fJFVPntNVnmxjtVgMy5P8MDEmusB.jpg";
        img.src = "https://t4.ftcdn.net/jpg/03/08/67/51/360_F_308675145_Ye70fJFVPntNVnmxjtVgMy5P8MDEmusB.jpg";
        //divShowPoster.appendChild(img);
    } else if (posterUrl.startsWith('http://file.koreafilm.or.kr')) {
        // posterUrl이 'http://file.koreafilm.or.kr'로 시작할 때 해당 URL로 이미지 출력
        var img = document.createElement("img");
        img.loading = "lazy";
        posterImage.loading="lazy";
        // img.className = "movie-list-item-img";
        posterImage.src=posterUrl;
        img.src = posterUrl;
        img.alt = selectedOption.text;
        //divShowPoster.appendChild(img);
    } else {
        // 그 외의 경우에는 TMDB의 URL을 사용하여 이미지 출력
        var img = document.createElement("img");
        img.loading = "lazy";
        posterImage.loading="lazy";
        // img.className = "movie-list-item-img";
        posterImage.src="https://image.tmdb.org/t/p/w200"+posterUrl;
        img.src = "https://image.tmdb.org/t/p/w200" + posterUrl;
        img.alt = selectedOption.text;
        //divShowPoster.appendChild(img);
    }
    movieId.textContent="영화 ID: "+id;
    //textContent는 html인식못해서 <br>사용x
    //innerHTML은 가능, 하지만 xss에 취약하므로 간단한 정보만 출력
    movieTitle.innerHTML = "영화명: " + title + "<br>" + "관람등급: " + filmRatings + "<br>" + "상영시간: " + runtime +"분"+ "<br>" + "개봉일: " + releaseDate;
}


//본인 지점에 상영 영화 추가하기 버튼
function addNewMovie(cinemaId) {
    //selectbox에서 현재 영화 id를 제대로 갖고왔나 확인하기.
    const redirectUrl = $("#adminRegionUrl").attr("href");
    var selectBox = document.getElementById("addMovieId");
    var selectedOption = selectBox.options[selectBox.selectedIndex];
    var movieId = selectedOption.dataset.id;
    console.log("시네마아이디:", cinemaId);
    //selectbox를 선택안하고 영화선택인 상태로 둘 경우 값이 undefined이다. 이 경우를 제외하고 작업을 실행해야한다.
    if (movieId != undefined) {
        $.ajax({
            url: "addNewMovie",
            method: "POST",
            headers: {
                [csrfHeaderName]: csrfToken
            },
            data: {
                cinemaId, movieId
            },
            success() {
                alert("상영 영화 추가가 완료되었습니다.");
                location.href = redirectUrl;
            },
            error() {
                alert("상영 영화 추가에 실패했습니다.");
            }
        });
    }
}

//0423 상영스케쥴 추가 버튼을 눌렀을 때 모달을띄워주는 함수
//본인 지점에 상영일정 신청 시 , 상영영화목록은 실시간으로 반영되어야 해서 ajax 사용
function createScheule(cinemaId) {
    //selectbox에서 현재 영화 id를 제대로 갖고왔나 확인하기.

    console.log("상영 일정 신청하기 click ,지점 : ", cinemaId);
    $(".createScheduleModal").css("display","block");

    let selectMovieBox = document.getElementById("sch_movieId");
    $.ajax({
        url: "findMyCinemaMovie",
        method: "GET",
        data: {
            cinemaId
        },
        success: function(movies) {
            console.log(movies);

            movies.forEach(function(movie) {
                var option = document.createElement("option");
                option.value = movie.id; // 옵션의 값으로 영화 id 설정
                option.text = movie.title; // 옵션의 텍스트로 영화 제목 설정
                option.dataset.title = movie.title;
                option.dataset.id = movie.id;
                selectMovieBox.appendChild(option); // selectbox에 옵션 추가
            });
        },
        error() {
            alert("[신청용]지점 영화 조회에 실패하였습니다.");
        }
    });
}
// $("#createScheule").click(function () {
//     $(".createScheduleModal").css("display","block");
// });

