// let seats = document.querySelector(".all-seats");
// console.log("reservation2 분리 - 좌석 60개 배치");
// for (var i = 0; i < 59; i++) {
//     let randint = Math.floor(Math.random() * 2);
//     let booked = randint === 1 ? "booked" : "";
//     seats.insertAdjacentHTML(
//         "beforeend",
//         '<input type="checkbox" name="tickets" id="s' +
//         (i + 2) +
//         '" /><label for="s' +
//         (i + 2) +
//         '" class="seat ' +
//         booked +
//         '"></label>'
//     );
// }
//
// let tickets = seats.querySelectorAll("input");
// tickets.forEach((ticket) => {
//     ticket.addEventListener("change", () => {
//         let amount = document.querySelector(".amount").innerHTML;
//         let count = document.querySelector(".count").innerHTML;
//         amount = Number(amount);
//         count = Number(count);
//
//         if (ticket.checked) {
//             count += 1;
//             amount += 200;
//         } else {
//             count -= 1;
//             amount -= 200;
//         }
//         document.querySelector(".amount").innerHTML = amount;
//         document.querySelector(".count").innerHTML = count;
//     });
// });
//=====================================================
//https://api.iamport.kr/
let currentPage = 1; //jin 초기 페이지 설정
const now = new Date();
console.log("현재 날짜와 시간",now);

window.onload = function()
{
    var movieIdCookie = getCookie('movieIdCookie');
    console.log("쿠키에 저장된 무비 아이디는?:",movieIdCookie);
    //테이블에 출력한 후 , 쿠키를 삭제한다.
    if (movieIdCookie) {
        // 해당 영화 아이디를 가진 행에 CSS를 직접 적용하여 하이라이트 표시
        $(".select-movieData[data-movieData-id='" + movieIdCookie + "']").css('background', 'linear-gradient(to right, darkred, darkred)');
    }
    deleteCookie('movieIdCookie');
    addPlaceholderTextToTable();
}
function addPlaceholderTextToTable() {
    var placeholderText = "영화, 지점, 시간을 선택해주세요";
    var placeholderRow = document.getElementById("placeholder_row");
    placeholderRow.innerHTML = "<td colspan='3'>" + placeholderText + "</td>";
}

function deleteCookie(name) {
    console.log("movieIdCookie 쿠키를삭제합니다.");
    document.cookie = name + '=; expires=Thu, 01 Jan 1970 00:00:01 GMT; path=/;';

}

function getCookie(cookieName) {
    var name = cookieName + '=';
    var decodedCookie = decodeURIComponent(document.cookie);
    var cookieArray = decodedCookie.split(';');
    for (var i = 0; i < cookieArray.length; i++) {
        var cookie = cookieArray[i].trim();
        if (cookie.indexOf(name) == 0) {
            return cookie.substring(name.length, cookie.length);
        }
    }
    return '';
}

//th 영화 제목 클릭시 하단 div에 해당 영화 포스터 이름, 포스터 동적으로 출력
//예매 페이지 첫 진입시 info-seats none 처리
const infoMoviesNone = document.querySelector(".info-movies");
const infoSeatsNone = document.querySelector(".info-seats");
infoSeatsNone.style.display='none';
var cinemaDiv = container.querySelector('.seat-container2-2cinema');
var dateDiv = container.querySelector('.seat-container2-2date');
var theaterDiv = container.querySelector('.seat-container2-2theater');
var peopleDiv = container.querySelector('.seat-container2-2people');
//
var selectSeatDiv = container.querySelector(".seat-container3-2");
var totalPayDiv = container.querySelector(".seat-container3-4");
var totalPay;
//
//0219
let reservatoinPage=1; //기본 1부터 시작.
//0223
var checkedIds = []; // 체크된 체크박스의 아이디를 저장할 배열
var checkedNumbers = []; // 체크된 체크박스의 넘버를 저장할 배열
const Status = {
    CONFIRM: 'CONFIRM',
    PENDING: 'PENDING'
};

//영화 선택 시..0219
var movieId;
$(document).ready(function()
{
    //select-movie에서 select-movieData 수정해봄.0327
    $(".select-movieData").click(function()
    {
        console.log("영화 선택");
        // 모든 행의 선택을 취소하고 선택된 행에만 'selected' 클래스를 추가
        //$(this).css('background', 'linear-gradient(to right, darkred, darkred)');
        // 다른 버튼의 배경색을 원래대로 되돌리기 위해 모든 버튼에 대해 반복
        //$(".select-movieData").not(this).css('background', '');

        $(this).css('background-color', 'darkred');
        $(".select-movieData").not(this).css('background-color', '');


        //선택한 영화의 고유 id (pk)값 가져오기.
        // 선택된 버튼의 ID(영화관 ID) 출력
        //0328 무비쪽 수정되면서 이쪽도 변수명을 바꿔야할 것 같기도.data-movieData-id
        // movieId = $(this).data("movie-id");
        movieId = $(this).attr("data-movieData-id");
        console.log("선택된 영화 고유 ID:", movieId);
    });
});





//리스트에서 선택한 영화 포스터를 보여주는 함수
function showPoster(posterUrl,movieTitle) {
    console.log("출력할 포스터:"+posterUrl);
    console.log("출력할 영화명:"+movieTitle);
    // 외부의 <div> 요소를 찾습니다.
    const posterContainer = document.querySelector(".seat-container1");//getElementById("posterContainer");

    // 해당 포스터 이미지를 표시하는 <img> 요소를 생성합니다.
    let imgElement = document.querySelector(".seat-container1-img");

    let textElement = document.querySelector(".seat-container1-title");
    // 이전에 표시된 이미지가 있다면 제거합니다.
    posterContainer.innerHTML = "";
    imgElement.src = 'https://image.tmdb.org/t/p/w200'+posterUrl;
    console.log("출력할 포스터 test ^^:"+posterUrl);
    
    //movieTitle을 전체 출력하되, 제목이 길면 자른다.
    if(movieTitle.length>=10)
    {
        textElement.innerHTML = addLineBreaks(movieTitle,10);
    }
    else
    {
        textElement.innerHTML = movieTitle;
    }
    posterContainer.appendChild(imgElement);
    posterContainer.appendChild(textElement);
}


$(document).ready(function(){
    $(".select_location").click(function()
    {
        //수정한 거
        console.log("지역선택");

        $(this).css('background', 'linear-gradient(to right, red, red)');
        // 다른 버튼의 배경색을 원래대로 되돌리기 위해 모든 버튼에 대해 반복
        $(".select_location").not(this).css('background', '');
        console.log("지역선택2");
        var localName = $(this).text();
        console.log("선택된 지역:", localName);

        // 선택된 버튼의 ID(영화관 ID) 출력
        var localId = $(this).data("local-id");
        console.log("선택된 지역 ID:", localId);
    });
});



//지점 선택시
var cinemaId;
$(document).ready(function(){
    $(".select_location2").click(function()
    {
        //0419
        $(this).css('background-color', 'darkred');
        $(".select_location2").not(this).css('background-color', '');
        //수정한 거
        // $(this).css('background', 'linear-gradient(to right, grey, grey)');
        // // 다른 버튼의 배경색을 원래대로 되돌리기 위해 모든 버튼에 대해 반복
        // $(".select_location2").not(this).css('background', '');
        console.log("지점선택2");
        var cinemaName = $(this).text();
        console.log("선택된 영화관:", cinemaName);

        cinemaDiv.innerText = cinemaName;



        // 선택된 버튼의 ID(영화관 ID) 출력
        cinemaId = $(this).data("cinema-id");
        console.log("선택된 영화관 ID:", cinemaId);
    });
});


//0220 임시 - 날짜를 선택해야 비동기로 상영 스케줄을 가져오도록 함.
$(document).ready(function(){
    $(".select_date").click(function()
    {
        //수정한 거
        console.log("날짜 선택");
        // $(this).css('background', 'linear-gradient(to right, grey, grey)');
        // // 다른 버튼의 배경색을 원래대로 되돌리기 위해 모든 버튼에 대해 반복
        // $(".select_date").not(this).css('background', '');

        $(this).css('background-color', 'darkred');
        $(".select_date").not(this).css('background-color', '');

        dateDiv.innerText = $(this).text();

        // 선택된 버튼의 ID(영화관 ID) 출력
        let dateId = $(this).data("date-id");
        console.log("선택된 date ID:",dateId);

        //현재 선택된 극장,상영관,날짜 한 번 더 체크하기.
        console.log("현재 선택한 영화,상영관,날짜");
        console.log(movieId,cinemaId,dateId);

        /*
        * test1.날짜선택시 스케쥴 출력
        *
        *
        * */

        $.ajax({
            url: `${contextPath}reservation/findSchedules`,
            type: 'get',
            data:{
                movieId, //영화
                cinemaId, //샹영관
                dateId //날짜
            },
            success(organizedSchedules){

                makeNewSchedule(true,organizedSchedules);

            },
            //requests:  요청 객체입니다. 보통 HTTP 요청 정보를 포함하며, 요청한 클라이언트의 정보와 요청된 리소스에 대한 정보 등을 포함합니다.
            // status: HTTP 상태 코드입니다. 실패한 요청의 상태 코드를 나타냅니다.
            // error: 서버에서 반환된 오류 메시지입니다.
            error(request, status, error) {
                //console.error('~~~~Ajax request failed~~~~:', error);
                console.log('~~~~Error response responseText~~~~:', request.responseText);
                console.log('~~~~Error response status~~~~:', request.status);
                if(request.status==500)
                {
                    alert(`에러로 인해 메인페이지로 이동합니다. 이용에 불편을 끼쳐드려 죄송합니다.`)
                    window.location.href = `${contextPath}bootbox/`; // 리다이렉트할 URL을 지정합니다.
                }
                else if(request.status==401) //인증 관련 에러 잠시 주석처리..
                {
                    alert(`예매는 로그인 후 이용하실 수 있습니다.`)
                    window.location.href = `${contextPath}`+request.responseText; // 리다이렉트할 URL을 지정합니다.
                }
                else if(request.status==404)
                {
                    //204는 delete 했을 때 경우라, select 조회 결과가 없는 경우 404로 처리
                    makeNewSchedule(false,"해당 상영스케쥴이 존재하지 않습니다");
                }

            },

        });

    });
});

//0221 영화-극장-원하는날짜로 새 상영스케쥴을 받아온 후 가공하는 함수
function makeNewSchedule(able,organizedSchedules)
{
    const scheduleTable = document.getElementById('scheduleTable');
    const initialTableHeight = scheduleTable.offsetHeight; // 테이블의 초기 높이 저장

    //다른 테이블들 높이도 체크..
    const cinemaTable = document.querySelector('.cinema-area');
    const initialTableHeight2 = cinemaTable.offsetHeight; // 테이블의 초기 높이 저장
    console.log("테이블 초기 높이:",initialTableHeight);
    console.log("테이블 초기 높이2:",initialTableHeight2);
// 기존의 행 삭제
    while (scheduleTable.rows.length > 1) {
        scheduleTable.deleteRow(1); // 헤더를 제외한 행을 삭제합니다.
    }
// 새로운 데이터 추가
    const [{ totalDuration, schedules, title }] = organizedSchedules;
    console.log("총 상영 시간:", totalDuration);
    console.log("제목:", title);

    const tbody = document.getElementById('tbody_schedule');
    tbody.innerHTML = ''; // tbody 내용을 비움

// 선택된 행 추적을 위한 변수
    let selectedRow = null;



    if(able)
    {
    //insertRow(long):섹션 내에서 지정된 위치 앞에 새로운 <tr> 요소를 추가한다
    //추가할  요소가 위치할 기준이 될 인덱스를 지정하며 지정된 인덱스 바로 앞에 추가된다. 생략하거나 -1을 지정한 경우에는 섹션내 끝에 추가한다.
    schedules.forEach(({ times, theater }) => {
        times.forEach(({ schId, time, seatsAvailable }) => {
            const row = tbody.insertRow();
            //const schIdCell = row.insertCell();
            const timeCell = row.insertCell();
            const theaterCell = row.insertCell();
            const seatsAvailableCell = row.insertCell();
            //상영일정id도 추가
            //schIdCell.textContent = schId;
            timeCell.textContent = time;
            theaterCell.textContent = theater;
            seatsAvailableCell.textContent = seatsAvailable + '석';

            // 행에 클릭 이벤트 리스너 추가
            row.addEventListener('click', () => {
                // 이전에 선택된 행이 존재한다면 배경색을 원래대로 되돌리기
                if (selectedRow) {
                    selectedRow.style.backgroundColor = ''; // 이전에 선택된 행의 배경색을 지움
                }
                // 선택된 행을 현재 클릭한 행으로 설정하고 배경색을 검은색으로 변경
                selectedRow = row;
                selectedRow.style.backgroundColor = 'darkred';
                // 클릭된 행에 대한 동작을 여기에 추가
                console.log('클릭된 행:',time, theater, seatsAvailable);
                selectedSheduleId = schId;
                theaterDiv.innerHTML=theater;
            });
        });
    });

    //insertRow(long):섹션 내에서 지정된 위치 앞에 새로운 <tr> 요소를 추가한다
    //추가할  요소가 위치할 기준이 될 인덱스를 지정하며 지정된 인덱스 바로 앞에 추가된다. 생략하거나 -1을 지정한 경우에는 섹션내 끝에 추가한다.

    // 새로운 tbody를 추가
    //scheduleTable.appendChild(tbody);
    }
    else
    {
        console.log("해당 영화,지점,날짜에 맞는 상영 일정이 없다..");
        const row = tbody.insertRow();
        const noSch = row.insertCell();
        //상영일정id도 추가
        //schIdCell.textContent = schId;
        noSch.textContent = '해당하는 상영 일정이 없습니다.';
    }
}






//10글자씩 잘라서 br 처리 (장문의 영화 타이틀 처리)
function addLineBreaks(str, charsPerLine) {
    let result = '';
    for (let i = 0; i < str.length; i++) {
        result += str[i];
        if ((i + 1) % charsPerLine === 0) {
            result += '<br>';
        }
    }
    return result;
}

// URL에서 쿼리 파라미터를 가져오는 함수입니다.
function getQueryParams() {
    const params = new URLSearchParams(window.location.search);
    return {
        movieId: params.get('movieId'),
        cinemaId: params.get('cinemaId'),
        schId: params.get('schId'),
        schDate: params.get('schDate')
    };
}

// 페이지 로드 시 선택된 영화, 극장, 스케줄을 설정하는 함수입니다.
function setSelectedOptions() {
    const { movieId, cinemaId, schId , schDate} = getQueryParams();

    if (movieId && cinemaId && schId) {
        // 영화 선택
        const movieOption = document.querySelector(`.select-movie[data-movie-id="${movieId}"]`);
        if (movieOption) {
            movieOption.click();
        }

        // 극장 선택
        const cinemaOption = document.querySelector(`.select_location2[data-cinema-id="${cinemaId}"]`);
        if (cinemaOption) {
            cinemaOption.click();
        }

        // 스케줄 선택
        const scheduleOption = document.querySelector(`.select_date[data-date-id="${schDate}"]`);
        if (scheduleOption) {
            scheduleOption.click();
        }
    } else {
        console.error('파라미터를 읽을 수 없습니다.');
    }
}

// 페이지가 로드될 때 자동으로 선택하는 로직을 실행합니다.
document.addEventListener('DOMContentLoaded', setSelectedOptions);
document.addEventListener('DOMContentLoaded', updateButtonVisibility);
function updateButtonVisibility() {
    console.log("이전/다음 버튼 표시 함수,현재 currentPage:"+currentPage);
    const prevButton = document.querySelector('.select-seats-prev-button');
    const nextButton = document.querySelector('.select-seats-next-button');
    //.select-requestPay-button
    const payButton = document.querySelector('.select-requestPay-button');

    // 현재 페이지가 1인 경우 이전 버튼,결제버튼을 숨김 , 다음 버튼은 보여줌
    if (currentPage === 1) {
        prevButton.style.visibility = "hidden";
        nextButton.style.visibility = "visible";
        payButton.style.display = "none"; // display 속성을 변경합니다.
    }
// 다음 페이지(좌석 선택)
    else {
        prevButton.style.visibility = "visible";
        nextButton.style.visibility = "hidden";
        payButton.style.display = "block"; // display 속성을 변경합니다.
    }
    // 현재 페이지가 2 인 경우 이전 버튼 보이고, 다음 버튼 숨김.
    // if (currentPage >= 2) {
    //     nextButton.style.display = "inline"; // 또는 "block"
    // } else {
    //     // nextButton.style.display = "none";
    // }
}


//======================================================================================
document.querySelector(".select-seats-prev-button").addEventListener('click',function ()
{
    currentPage--;
    updateButtonVisibility();
    //alert('이전버튼');
    console.log("...이전 버튼...");


    var cookieValue = getCookie("myCookie");
    $('#test-area').html("");

    if (cookieValue !== null) {
        // 쿠키에서 가져온 JSON 형식의 문자열을 배열로 변환
        var myArray = JSON.parse(cookieValue);

        // 배열 값을 사용하여 필요한 작업 수행
        console.log(myArray); // 배열 값 콘솔 출력
    } else {
        console.log("쿠키를 찾을 수 없습니다.");
    }

    if (infoMoviesNone.style.display === 'none') {
        infoMoviesNone.style.display = 'block'; //보이고
        infoMoviesNone.style.display = 'flex'; //flex속성
        infoSeatsNone.style.display='none';
        toClear();//인원,좌석 초기화
    } else {
        // div.style.display = 'none';
    }
});





let selectedSheduleId;
//0219 다음 버튼을 눌렀을 때, 영화-극장-시간-상영시간 모두 선택되어야 한다. 한개라도 선택 안되어있다면 넘어가지 못함.
document.querySelector(".select-seats-next-button").addEventListener('click',function ()
{
    currentPage++;
    updateButtonVisibility();
    console.log("...다음 버튼 클릭...");
    //0219 영화,극장,날짜,상영시간표를 모두 선택하지 않으면 다음으로 넘어갈 수 없음.
    // if(!checkButtonAllSelect()) {
    //     alert('모든 버튼을 누르셔야 다음으로 이동하실 수 있습니다.');
    //     return;
    // }

//     const nextButton = document.querySelector('.select-seats-next-button');
//     const requestPayButton = document.querySelector('.select-requestPay-button');
// // nextButton의 display 속성을 조작하여 보이거나 숨깁니다.
//     if (requestPayButton.style.display === 'none') {
//         requestPayButton.style.display = 'block'; // 보이게 설정
//         nextButton.style.display='none';
//     } else {
//         requestPayButton.style.display = 'none'; // 숨김 설정
//     }


    //비동기 test
    $.ajax({
        url: `${contextPath}reservation/detailSchedule`,
        type: 'get',
        data:{
            scheduleId:selectedSheduleId
        },
        success(response)
        {
            console.log("success : 선택한 상영일정의 예약된 좌석 값 가져오기",response);
            makeSeat(response);
        },
        //requests:  요청 객체입니다. 보통 HTTP 요청 정보를 포함하며, 요청한 클라이언트의 정보와 요청된 리소스에 대한 정보 등을 포함합니다.
        // status: HTTP 상태 코드입니다. 실패한 요청의 상태 코드를 나타냅니다.
        // error: 서버에서 반환된 오류 메시지입니다.
        error(request, status, error) {
            //console.error('~~~~Ajax request failed~~~~:', error);
            // console.log('~~~~Error response responseText~~~~:', request.responseText);
            // console.log('~~~~Error response status~~~~:', request.status);
            if(request.status==500)
            {
                alert(`에러로 인해 메인페이지로 이동합니다. 이용에 불편을 끼쳐드려 죄송합니다.`+request.status)
                window.location.href = `${contextPath}bootbox/`; // 리다이렉트할 URL을 지정합니다.
            }
            else if(request.status==401) //인증 관련 에러 잠시 주석처리..
            {
                //주석처리
                alert(`예매는 로그인 후 이용하실 수 있습니다.`)
                 window.location.href = `${contextPath}`+request.responseText; // 리다이렉트할 URL을 지정합니다.
            }
            else if(request.status==400)//잘못된 클라이언트 요청
            {
                alert(`에러로 인해 메인페이지로 이동합니다. 이용에 불편을 끼쳐드려 죄송합니다.`+request.status)
                //window.location.href = `${contextPath}bootbox/`; // 리다이렉트할 URL을 지정합니다.
            }

        }
    });
});

//0219 다음 버튼 외에 영화,극장,시간 버튼 눌렀을 때에도 비동기로 쿼리를 전송하는 작업을 실행해야함..ㅎ..=>상영스케줄을 알기 위해서..
//하나라도 false면 다음으로 넘어갈 수 없음.
function  checkButtonAllSelect()
{

}


function makeSeat(response)
{
    console.log("ajax에서 함수 불러오기.");
    ////[SeatDto(id=23, name=C03), SeatDto(id=30, name=C10)]
    // const [SeatDto] = response;
    // console.log("testttttttttttttttttttttttttt", SeatDto);


    //이미 예약된 자리 배열
    const disabledSeat = [];
    response.forEach((SeatDto)=>
    {
        console.log("testttttttttttttttttttttttttt", SeatDto);
        const {id,name}=SeatDto;

        console.log("id:", id);
        console.log("name:", name);
        disabledSeat.push(name);
    });

    reservatoinPage=2;
    let testArray = ['Aa', 'Bb', 'Cc', 'Dd', 'Ee', 'Ff'];
    setCookieForList("myCookie", testArray, 1); //1일
    if (infoSeatsNone.style.display === 'none') {
        infoSeatsNone.style.display = 'block';
        infoSeatsNone.style.display = 'flex';

        infoMoviesNone.style.display='none';
    } else {
        // div.style.display = 'none';
    }

    // 6행 10열의 좌석 생성
    createSeats(6, 10,disabledSeat);

}

//============================================================================================================================
// 알파벳 배열(좌석행)
let alphabet = ['A', 'B', 'C', 'D', 'E', 'F'];
let checkbox;
function createSeats(rows, cols,disabledSeat) {
    console.log("createSeats :)");

    const seatTableBody = document.getElementById('seatTableBody');
// seatTableBody에 있는 모든 자식 요소를 제거
    while (seatTableBody.firstChild) {
        seatTableBody.removeChild(seatTableBody.firstChild);
    }

    var seatNumber = 1; // 좌석 번호 초기값


    for (var i = 0; i < rows; i++) {
        var row = document.createElement('tr');

        // 알파벳 표시하는 첫 번째 셀 생성
        var col = document.createElement('td');
        var label = document.createElement('label');
        var label_row = document.createElement('label_row');
        label.className = 'seat';
        label_row.textContent = alphabet[i]; // 알파벳 표시
        col.appendChild(label);
        col.appendChild(label_row);
        row.appendChild(col);

        // 좌석 생성하는 나머지 셀 생성
        for (var j = 1; j <= cols; j++)
        {
            //좌석 번호에서 알파벳+번호로 처리 a1 b5 이런식으로.
            //0216 한자리 수 인 경우 앞에 0 추가 1->01
            let seatId =alphabet[i] +  (j < 10 ? '0' + j : j);
            // 좌석 생성
            checkbox = document.createElement('input');
            // 좌석 번호 계산 0224 순수 숫자값으로. 1~60
            //var seatId = i * cols + j;
            checkbox.type = 'checkbox';
            checkbox.id = 's' + seatId;
            checkbox.name = 'tickets';
            checkbox.className = 'custom-checkbox'; // 사용자 정의 체크박스 클래스 추가



            checkbox.setAttribute('data-seat-id', seatId); // 좌석 아이디를 데이터 속성으로 추가
            checkbox.setAttribute('data-seat-number', seatNumber);
            checkbox.value=seatNumber;

            checkbox.addEventListener('click', function()
            {
                console.log('Clicked seat ID:', this.getAttribute('data-seat-id'));
                console.log('Clicked seat NUMBER:', this.getAttribute('data-seat-number'));

                //클릭시 관람인원이 0명인 경우 인원을 먼저 선택하라고 알려준다.
                if(numberOfPeople==0)
                {
                    alert('관람인원을 선택해주세요.(현재 0명)');
                    this.checked = false;
                }
                else
                {
                    checkedCount = 0;
                    // 페이지 내의 모든 체크박스를 반복하여 상태 확인
                    var checkboxes = document.querySelectorAll('input[type="checkbox"][name="tickets"]');

                    checkboxes.forEach(function(checkbox)
                    {
                        if (checkbox.checked) {
                            checkedCount++;
                        }
                    });
                    console.log("현재 체크된 체크박스:",checkedCount);
                    if(numberOfPeople<checkedCount)
                    {
                        alert("관람인원 이상 좌석을 선택하실 수 없습니다.");
                        this.checked = false;
                        checkedCount--; //증가된 것을 하나 차감한다.
                    }
                    //모든 체크박스들을 순회하여
                    checkedIds = []; // 체크된 체크박스의 아이디를 저장할 배열
                    checkedNumbers = []; // 체크된 체크박스의 숫자를 저장할 배열
                    checkboxes.forEach(function(checkbox) {
                        if (checkbox.checked) {
                            //0224 좌석네임이 아닌 id값을 넣어줘야 할 것 같음.

                            checkedIds.push(checkbox.id.replace('s','')); // 체크된 체크박스의 아이디를 배열에 추가
                            checkedNumbers.push(checkbox.value);
                        }
                    });
                    // 체크된 체크박스의 아이디를 출력
                    selectSeatDiv.innerText = checkedIds;
                    console.log("체크된 체크박스의 아이디: ", checkedIds);
                    console.log("체크된 체크박스의 넘버즈: ", checkedNumbers);
                    console.log("체크카운트:", checkedCount);
                    totalPay = 100* checkedCount;
                    totalPayDiv.innerText = "100 * "+ checkedCount +" = "+totalPay +"원";
                    if(checkedIds.length==0) {
                        totalPayDiv.innerText = "-";
                        selectSeatDiv.innerText = "-";
                    }
                }
            });

            var label = document.createElement('label');
            label.htmlFor = 's' + seatId;
            label.className = 'seat';
            label.textContent = j; // 열 번호 추가

            disabledSeat.forEach(function(seat) {
                if(seat===seatId)
                    checkbox.disabled = true;
            });

            // 테이블에 추가
            var col = document.createElement('td');
            col.appendChild(checkbox);
            col.appendChild(label);
            row.appendChild(col);


            seatNumber++;
        }
        seatTableBody.appendChild(row);
    }
}

//선택한 좌석값 및 정보들을 초기화 하는 함수
function toClear()
{
    var checkboxes = document.querySelectorAll('input[type="checkbox"][name="tickets"]');
    // 각 체크박스의 checked 속성을 false로 설정하여 초기화
    checkboxes.forEach(function(checkbox) {
        checkbox.checked = false; //모든 체크박스 false
    });
    numberOfPeople = 0; //지정한 인원수도 초기화
    peopleDiv.innerText = numberOfPeople +"명";
    resultElement.innerText = numberOfPeople;
    selectSeatDiv.innerText = "-";
    totalPayDiv.innerText = "-";
}

document.getElementById('btn-clear').addEventListener('click', function() {
    toClear();
});
// 결과를 표시할 element
const resultElement = document.getElementById('peopleResult');

// 현재 화면에 표시된 인원수 값
let numberOfPeople = resultElement.innerText;
function count(type)  {

    // 더하기/빼기 numberOfPeople = 선택한 인원
    if(type === 'plus' && numberOfPeople<8 )
    {
        numberOfPeople = parseInt(numberOfPeople) + 1;
    }else if(type === 'minus' && numberOfPeople>0)
    {
        console.log("체크된 좌석 갯수:",checkedCount);
        numberOfPeople = parseInt(numberOfPeople) - 1;
        if(numberOfPeople<checkedCount)
        {
            console.log("numberOfPeople..............:",numberOfPeople);
            let yes = confirm('설정한 인원보다 더 많은 좌석을 선택하실 수 없습니다. 초기화 하시겠습니까?');
            if(yes)
            {
                console.log("예 누름");
                toClear();
            }
            else
            {
                console.log("아니오 누름");
                numberOfPeople = parseInt(numberOfPeople) + 1;
            }
        }
    }
    // 결과 출력
    resultElement.innerText = numberOfPeople;
    peopleDiv.innerText = numberOfPeople +"명";
}

//================================================================
//체크된 체크박수 갯수 확인하는 메소드
var checkedCount = 0;

// function highlightCell(cell)
// {
//     // 클릭된 td의 클래스에 "highlighted" 클래스가 있는지 확인
//     console.log(cell.classList);
//     var isHighlighted = cell.classList.contains("highlighted");
//
//     // 클래스가 있으면 제거하고, 없으면 추가하여 배경색 변경(on,off)
//     if (isHighlighted) {
//         cell.classList.remove("highlighted");
//     } else {
//         cell.classList.add("highlighted");
//     }
// }



//0216 내가 선택한 값 저장할 용도로 쓰일 쿠키 test
//쿠키설정
function setCookieForList(name, value, days) {
    var expires = "";
    if (days) {
        var date = new Date();
        date.setTime(date.getTime() + (days * 24 * 60 * 60 * 1000)); //기간 1일
        expires = "; expires=" + date.toUTCString();
    }
    // 배열을 JSON 문자열로 변환하여 저장
    var jsonValue = JSON.stringify(value);
    document.cookie = name + "=" + (jsonValue || "") + expires + "; path=/";
}

//쿠키가져오기.
function getCookie(name) {
    var nameEQ = name + "=";
    var cookies = document.cookie.split(';');
    for(var i = 0; i < cookies.length; i++) {
        var cookie = cookies[i];
        while (cookie.charAt(0) === ' ') {
            cookie = cookie.substring(1, cookie.length);
        }
        if (cookie.indexOf(nameEQ) === 0) {
            return cookie.substring(nameEQ.length, cookie.length);
        }
    }
    return null;
}

//0216 로그인한 회원만 보이는 선호극장을 클릭시 실행하는 이벤트
function select_member_like_cinema()
{
    alert('선호극장 클릭!');
    //선호극장이 등록되어있든 안되어있든 이 부분을 누르면 지점쪽은 초기화가 되어있어야 함.

}


// 결제 연결
// document.querySelector(".btn_pay").addEventListener('click', function ()
// {
//     // alert("결제");
// });
function check_before_requestPay()
{
    if(numberOfPeople!=checkedCount)
    {
        alert('관람인원과 선택된 좌석갯수가 맞지 않습니다.');
        return false;
    }
    else
    {
        return true;
    }
}
IMP.init("imp32105587"); // 가맹점코드 - 고정값

function requestPay_notworking()
{
    concole.log("비로그인자 거름용");
}


//jin 0413 test용으로 rhgPwls,1,핸드폰번호를 고정했었음. 이제 로그인한 회원의 id,phone등을 변수로 지정해야 함!
function requestPay(id,name,phone)
{
    console.log("결제 버튼을 클릭");
    console.log("0413 멤버 정보 테스트:",id+"/"+name+"/"+phone);
    //0224 테스트 하다가 중요한 것을 잊었다. 인원수=체크박스갯수 확인해야함! ex)인원은 2명인데 1개만 선택한 경우를 발견함.
    if(!check_before_requestPay())
        return;
    console.log("pass check_before_requestPay");
    let pay_uid = new Date().getTime().toString();
    let boxId = "box"+new Date().getTime().toString().substring(8);

    IMP.request_pay({
        pg: "html5_inicis", // PG사코드 - 고정값
        pay_method: "card", // 결제방식 - 고정값
        merchant_uid: "order" + pay_uid, // UTC , 결제 API 주문번호 고유값
        name: "Boot-Box 영화 예매", // 고정값[상품명]
        amount: totalPay, // 결제 금액
        buyer_name: name,//회원명
        buyer_tel: phone, // 회원연락처
    },

        function(res) //결제창 띄우고 닫았을 때 여기까지 뜨고
        {
            console.log("res::::",res);
            // console.log("Payment success!");
            console.log("Payment ID : " + res.imp_uid);
            console.log("Order ID : " + res.merchant_uid);
            console.log("Payment Amount : " + res.paid_amount);
            let boxId = "box"+new Date().getTime().toString().substring(8);
            let orderId = "order"+new Date().getTime().toString();
            console.log("생성된 box id:",boxId);

            //결제응답정보를 db에 저장하기 위해 비동기를 사용하는 구간
            if (res.success) //실제로 결제가 성공되면 이 구간으로 넘어오게 된다.
            {
                console.log("결제가 성공적으로 요청되었습니다!",res.success);
                const now = new Date();
                console.log("결제 완료 날짜 시간 :) ",now);
                let sendData01 = {
                    id: boxId,//예매id (랜덤조합)
                    memberId: id,//회원 아이디 long
                    scheduleId: selectedSheduleId, //상영스케쥴 번호
                    status : Status.CONFIRM, //enum 화
                    reservationTime : now
                };
                //0224 seat_id는 A01 이 아니라 1 이런식으로 넘겨줘야함! checkedNumbers는 내가 선택 좌석의 순수 숫자값을 저장한 배열이다.
                let sendData02 = {
                    resId: boxId,
                    seatId : checkedNumbers
                };

                //0224 결제관련 정보 객체 order_pay에 저장할..
                let sendData03={
                    id :res.merchant_uid,//orderId,
                    reservationId : boxId,
                    memberId : 0,
                    imp : res.imp_uid,
                    inicis  : "html5_inicis",
                    reservationAmount : res.pay_method, //결제 방식
                    price : totalPay,
                    phone : phone,
                    status : Status.CONFIRM

                };

                //0224 객체 합치기
                let combinedData = {
                    reservationDto: sendData01,
                    reservationSeatDto: sendData02,
                    orderPayDto : sendData03
                };

                $.ajax({
                    headers : {
                        [csrfHeaderName] : csrfToken
                    },
                    type: "POST",
                    url: `${contextPath}reservation/reservationStart`, // 전송할 URL
                    contentType: "application/json", // 전송하는 데이터의 타입
                    data: JSON.stringify(combinedData), // JSON 데이터로 변환하여 전송

                    success: function(response) {
                        // 요청이 성공했을 때 처리할 로직
                        console.log("응답:", response);

                        //window.location.href = `${contextPath}bootbox/`;
                        window.location.href = `${contextPath}reservation/reservationComplete`; // 리다이렉트할 URL을 지정합니다.
                        //member/memberReservation.do?id=1
                    },
                    error: function(xhr, status, error) {
                        // 요청이 실패했을 때 처리할 로직
                        alert("에러가 발생했습니다.:", status+"/"+error);
                        console.error("에러가 발생했습니다.:", status+"/"+error);
                    }
                });

            } else {
                alert(res.error_msg);
                console.error(res.error_msg);
            }
        });

}





//0413 - test용이라 무시해도됨. 사용되지 않습니다. 추후 삭제예정.
function dtoTest()
{
    console.log("dtoTest");
    let boxId = "box"+new Date().getTime().toString().substring(8);
    console.log("생성된 box id:",boxId);
    let sendData01 = {
        id: boxId,//예매id (랜덤조합)
        memberId: 1,//회원 아이디 long
        scheduleId: selectedSheduleId, //상영스케쥴 번호
        status : Status.CONFIRM //enum 화
    };
    //0224 seat_id는 A01 이 아니라 1 이런식으로 넘겨줘야함!
    //쓰이는 dto와 필드명이 일치해야한다...

    let sendData02 = {
        resId: boxId,
        seatId : checkedNumbers
    };

    //0224 객체 합치기
    let combinedData = {
        reservationDto: sendData01,
        reservationSeatDto: sendData02
    };
    console.log("combinedData:",combinedData);
        $.ajax({
            headers : {
                [csrfHeaderName] : csrfToken
            },
            type: "POST",
            url: `${contextPath}reservation/reservationStart`, // 전송할 URL
            contentType: "application/json", // 전송하는 데이터의 타입
            data: JSON.stringify(combinedData), // JSON 데이터로 변환하여 전송

            success: function(response) {
                // 요청이 성공했을 때 처리할 로직
                console.log("응답:", response);
                // 응답 데이터의 정보들

            },
            error: function(xhr, status, error) {
                // 요청이 실패했을 때 처리할 로직
                alert("에러가 발생했습니다.:", status+"/"+error);
                console.error("에러가 발생했습니다.:", status+"/"+error);
            }
        });

}
