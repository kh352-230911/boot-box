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

//th 영화 제목 클릭시 하단 div에 해당 영화 포스터 이름, 포스터 동적으로 출력
//예매 페이지 첫 진입시 info-seats none 처리
const infoMoviesNone = document.querySelector(".info-movies");
const infoSeatsNone = document.querySelector(".info-seats");
 infoSeatsNone.style.display='none';

//영화 선택 시..
$(document).ready(function(){
    $(".movie-area tbody tr").click(function() {
        console.log("영화 선택1");
        // 모든 행의 선택을 취소하고 선택된 행에만 'selected' 클래스를 추가
        $(this).siblings().removeClass("highlighted");
        $(this).addClass("highlighted");
    });
});

//지역 선택 시 ..1
// $(document).ready(function(){
//     $(".location-area tbody tr").click(function() {
//         console.log("지역 선택");
//         // 모든 행의 선택을 취소하고 선택된 행에만 'selected' 클래스를 추가
//         $(this).siblings().removeClass("highlighted");
//         $(this).addClass("highlighted");
//         $(".cinema-area tbody tr").remove();
//     });
// });

//지역 선택시 .. 2
$(document).ready(function(){
    $(".select_location").click(function()
    {
        //수정한 거
        console.log("지역선택");


        
        
        
        
        
        //기존 
        // $(".select_location").eq(index).css("background-color", "yellow");
        // // 선택된 행의 인덱스 가져오기
        // var index = $(this).closest("tr").index();
        // console.log("선택한 지역의 인덱스:",index);
        // // 모든 행의 지점 목록 숨기기
        // $(".theater-list").hide();
        //
        // // 현재 선택된 행의 지점 목록 표시
        // $(".theater-list").eq(index).show();
        // //$(".theater-list").eq(index).css("background-color", "yellow");
    });
});



const seatContainer1Div = document.querySelector(".seat-container1");
// seatContainer1Div.style.backgroundColor = 'red';

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
    imgElement.src = posterUrl;
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

function addLineBreaks(str, charsPerLine) {
    var result = '';
    for (var i = 0; i < str.length; i++) {
        result += str[i];
        if ((i + 1) % charsPerLine === 0) {
            result += '<br>';
        }
    }
    return result;
}
//======================================================================================
document.querySelector(".select-seats-prev-button").addEventListener('click',function ()
{
    //alert('이전버튼');
    console.log("...이전 버튼...");
    var cookieValue = getCookie("myCookie");

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
    } else {
        // div.style.display = 'none';
    }
});
document.querySelector(".select-seats-next-button").addEventListener('click',function ()
{
    /*
    * 0216
    * //1페이지에서 2페이지로 넘어가는 경우
    *
    *
    *
    * */
    console.log("...다음 버튼...");
    let testArray = ['Aa', 'Bb', 'Cc', 'Dd', 'Ee', 'Ff'];
    setCookieForList("myCookie", testArray, 1); //1일
    if (infoSeatsNone.style.display === 'none') {
        infoSeatsNone.style.display = 'block';
        infoSeatsNone.style.display = 'flex';

        infoMoviesNone.style.display='none';
    } else {
        // div.style.display = 'none';
    }

    //비동기 test
    $.ajax({
        url: `${contextPath}reservation/scheduleTest`,
        type: 'get',
        data:{
            scheduleId:41
        },
        success(fragment){
            console.log("~~~~success~~~~",fragment);
            hohohoho(fragment);
        },
        error(error) {
            console.error('~~~~Ajax request failed~~~~:', error);
        }
    });
});
function hohohoho(fragment)
{
    console.log("ajax에서 함수 불러오기~~~~~~~~~~~~~~~~~");
    //기존 : 제이쿼리로 화면단 일부 갱신
    $('#test').html(fragment);
}

//============================================================================================================================
// 알파벳 배열(좌석행)
let alphabet = ['A', 'B', 'C', 'D', 'E', 'F'];
function createSeats(rows, cols) {
    const seatTableBody = document.getElementById('seatTableBody');

    // 행 반복
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
            // 좌석 번호 계산
            //var seatId = i * cols + j;

            //좌석 번호에서 알파벳+번호로 처리 a1 b5 이런식으로.
            //0216 한자리 수 인 경우 앞에 0 추가 1->01
            let seatId =alphabet[i] +  (j < 10 ? '0' + j : j);
            // 좌석 생성
            var checkbox = document.createElement('input');
            checkbox.type = 'checkbox';
            checkbox.id = 's' + seatId;
            checkbox.name = 'tickets';
            checkbox.className = 'custom-checkbox'; // 사용자 정의 체크박스 클래스 추가

            checkbox.setAttribute('data-seat-id', seatId); // 좌석 아이디를 데이터 속성으로 추가

            checkbox.addEventListener('click', function() {
                console.log('Clicked seat ID:', this.getAttribute('data-seat-id'));
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
                    }
                }
            });

            var label = document.createElement('label');
            label.htmlFor = 's' + seatId;
            label.className = 'seat';
            label.textContent = j; // 열 번호 추가

            // 4번과 20번 체크박스는 disabled 처리
            // if (seatId === 'A6' || seatId === 'F1') {
            //     checkbox.disabled = true;
            //     //this.parentNode.style.backgroundColor = 'red';
            // }
            //0215 isreservationSeat 배열에 예약된 좌석값을 넣은 후 반복문처리.
            let isReservationSeat = [];
            isReservationSeat.push('A06');
            isReservationSeat.push('B03');
            isReservationSeat.push('B04');
            isReservationSeat.push('E10');
            isReservationSeat.push('F01');

            isReservationSeat.forEach(function(seat) {
                if(seat===seatId)
                    checkbox.disabled = true;
            });


            // 테이블에 추가
            var col = document.createElement('td');
            col.appendChild(checkbox);
            col.appendChild(label);
            row.appendChild(col);
        }
        seatTableBody.appendChild(row);
    }
}

function toClear()
{
    var checkboxes = document.querySelectorAll('input[type="checkbox"][name="tickets"]');
    // 각 체크박스의 checked 속성을 false로 설정하여 초기화
    checkboxes.forEach(function(checkbox) {
        checkbox.checked = false; //모든 체크박스 false
    });
    numberOfPeople = 0; //지정한 인원수도 초기화
    resultElement.innerText = numberOfPeople;
}

document.getElementById('btn-clear').addEventListener('click', function() {
    // 페이지 내의 모든 체크박스를 선택
    toClear();
});
// 결과를 표시할 element
const resultElement = document.getElementById('result');

// 현재 화면에 표시된 값
let numberOfPeople = resultElement.innerText;
function count(type)  {

    // 더하기/빼기
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
}

//================================================================

function showAlert(element) {
    let date = element.innerText;
    alert('선택한 날짜: ' + date);

    // var infoSeats = document.querySelectorAll(".info-seats");
    // infoSeats.forEach(function(seat) {
    //     seat.style.display = "none";
    // });
}

//체크된 체크박수 갯수 확인하는 메소드
var checkedCount = 0;
// 페이지 내의 모든 체크박스를 반복하여 상태 확인



function highlightCell(cell)
{
    // 클릭된 td의 클래스에 "highlighted" 클래스가 있는지 확인
    console.log(cell.classList);
    var isHighlighted = cell.classList.contains("highlighted");

    // 클래스가 있으면 제거하고, 없으면 추가하여 배경색 변경(on,off)
    if (isHighlighted) {
        cell.classList.remove("highlighted");
    } else {
        cell.classList.add("highlighted");
    }
}











// 6행 10열의 좌석 생성
createSeats(6, 10);


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