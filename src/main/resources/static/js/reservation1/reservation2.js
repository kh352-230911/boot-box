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



$('.select_location').on('click', function () {
    console.log("0214......2");
    $("table.location-area li.on").removeClass('on');
    $(this).parent().addClass('on');
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
    alert('이전버튼');
});
document.querySelector(".select-seats-next-button").addEventListener('click',function ()
{
    alert('다음버튼');
});


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
            let seatId =alphabet[i] + j;
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
            if (seatId === 'A6' || seatId === 'F1') {
                checkbox.disabled = true;
                //this.parentNode.style.backgroundColor = 'red';
            }

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



function highlightCell(cell) {
    // 클릭된 td의 클래스에 "highlighted" 클래스가 있는지 확인
    console.log(cell.classList);
    var isHighlighted = cell.classList.contains("highlighted");

    // 클래스가 있으면 제거하고, 없으면 추가하여 배경색 변경
    if (isHighlighted) {
        cell.classList.remove("highlighted");
    } else {
        cell.classList.add("highlighted");
    }
}











// 6행 10열의 좌석 생성
createSeats(6, 10);