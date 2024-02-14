// 초기화
document.getElementById("reset-button").addEventListener("click", function() {
    // 선택한 영화 초기화
    document.getElementById("selectedMovie").innerText = "";

    // 선택한 극장 초기화
    document.getElementById("selectedCinema").innerText = "";
    document.getElementById("selectedFrequentCinema").innerText = "";

    // 선택한 날짜 초기화
    document.getElementById("selectedDate").innerText = "";

    // 선택한 시간 초기화
    var timeList = document.getElementById("timeList");
    var timeItems = timeList.querySelectorAll("ul li");
    timeItems.forEach(function(item) {
        item.querySelector("a").innerText = "";
    });

    // 영화 선택 영역 보이기
    document.getElementById("movieSelect").style.display = "block";

    // 극장 선택 영역 보이기
    document.getElementById("cinemaSelect").style.display = "block";
});

// 영화
function movieByAlphabetically() { // 가나다순으로 조회
    var movieList = document.getElementById('movieList');
    var movies = Array.from(movieList.getElementsByTagName('li'));
    movies.sort(function(a, b) {
        var titleA = a.textContent.trim();
        var titleB = b.textContent.trim();
        return titleA.localeCompare(titleB);
    });
    movieList.innerHTML = '';
    movies.forEach(function(movie) {
        movieList.appendChild(movie);
    });
}
function movieByRating() { // 예매율순으로 조회
    var movieList = document.getElementById('movieList');
    var movies = Array.from(movieList.getElementsByTagName('li'));

    // 예매율 정보
    var movieData = [
        { title: '웡카', rating: 10.2 },
        { title: '시민덕희', rating: 1.4 },
        { title: '데드맨', rating: 2.2 },
        { title: '추락의 해부', rating: 1.1 },
        { title: '도그데이즈', rating: 3.6 },
        { title: '듄-파트2', rating: 34.1 },
        { title: '파묘', rating: 7.3 },
        { title: '소풍', rating: 1.7 },
        { title: '아가일', rating: 0.9 },
        { title: '건국전쟁', rating: 5.5 },
        { title: '검은소년', rating: 0.4 },
        { title: '기적의시작', rating: 0.1 }
        // 각 영화와 해당하는 예매율 정보를 포함하는 데이터를 추가해야 합니다.
    ];

    // 예매율순으로 영화 정렬
    movies.sort(function(a, b) {
        var titleA = a.textContent.trim();
        var titleB = b.textContent.trim();

        // 해당 영화의 예매율 정보 가져오기
        var ratingA = movieData.find(movie => movie.title === titleA)?.rating || 0;
        var ratingB = movieData.find(movie => movie.title === titleB)?.rating || 0;

        return ratingB - ratingA; // 내림차순 정렬
    });

    // 정렬된 영화 목록으로 업데이트
    movieList.innerHTML = '';
    movies.forEach(function(movie) {
        movieList.appendChild(movie);
    });
}

// 영화선택하면 색상 변경
// function selectedMovie(movieName) {
//     // 모든 영화 버튼에서 글씨색을 초기화합니다.
//     var movieButtons = document.getElementsByTagName("button");
//     for (var i = 0; i < movieButtons.length; i++) {
//         movieButtons[i].style.color = "black"; // 모든 버튼의 글씨색을 검은색으로 변경합니다.
//     }
//
//     // 선택한 영화 버튼의 글씨색을 변경합니다.
//     var selectedButton = document.querySelector("button");
//     if(selectedButton.innerHTML === movieName){
//         selectedButton.style.color = "forestgreen";
//     }
// }

// 극장
function cinemaAll() {
    document.getElementById('seoulCinema').classList.add('hidden');
    document.getElementById('gyeonggiCinema').classList.add('hidden');
    document.getElementById('incheonCinema').classList.add('hidden');
    document.getElementById('kangwonCinema').classList.add('hidden');
    document.getElementById('daejeonCinema').classList.add('hidden');
    document.getElementById('daeguCinema').classList.add('hidden');
    document.getElementById('busanCinema').classList.add('hidden');
    document.getElementById('gyeongsangCinema').classList.add('hidden');
    document.getElementById('incheonCinema').classList.add('hidden');
    document.getElementById('frequentCinema').classList.add('hidden');
}

function cinemaLike() {
    document.getElementById('seoulCinema').classList.add('hidden');
    document.getElementById('gyeonggiCinema').classList.add('hidden');
    document.getElementById('incheonCinema').classList.add('hidden');
    document.getElementById('kangwonCinema').classList.add('hidden');
    document.getElementById('daejeonCinema').classList.add('hidden');
    document.getElementById('daeguCinema').classList.add('hidden');
    document.getElementById('busanCinema').classList.add('hidden');
    document.getElementById('gyeongsangCinema').classList.add('hidden');
    document.getElementById('gwangjuCinema').classList.add('hidden');
    document.getElementById('frequentCinema').classList.remove('hidden');
}

function showRegion(region) {
    document.getElementById('seoulCinema').classList.add('hidden');
    document.getElementById('gyeonggiCinema').classList.add('hidden');
    document.getElementById('incheonCinema').classList.add('hidden');
    document.getElementById('kangwonCinema').classList.add('hidden');
    document.getElementById('daejeonCinema').classList.add('hidden');
    document.getElementById('daeguCinema').classList.add('hidden');
    document.getElementById('busanCinema').classList.add('hidden');
    document.getElementById('gyeongsangCinema').classList.add('hidden');
    document.getElementById('gwangjuCinema').classList.add('hidden');
    document.getElementById('frequentCinema').classList.add('hidden');
    document.getElementById(region + 'Cinema').classList.remove('hidden');
}

// 날짜
document.addEventListener("DOMContentLoaded", function() {
    var ulFeb = document.getElementById("dateUl");
    var ulMar = document.getElementById("dateUl1");

    var today = new Date();
    today.setDate(13); // 오늘 날짜를 오늘의 첫째 날로 설정

    var startDateFeb = new Date(2024, 1, 1); // 2월 시작 날짜 -> javascript는 0월부터 시작
    var endDateFeb = new Date(2024, 1, 29); // 2월 종료 날짜
    var startDateMar = new Date(2024, 2, 1); // 3월 시작 날짜
    var endDateMar = new Date(2024, 2, 31); // 3월 종료 날짜

    var weekdays = ['토', '일', '월', '화', '수', '목', '금']; // 요일 목록

    // 2월 날짜 생성 (오늘부터)
    while (startDateFeb <= endDateFeb) {
        if (startDateFeb >= today && startDateFeb <= endDateFeb) {
            var li = document.createElement("li");
            var a = document.createElement("a");
            a.href = "#";
            var dateText = startDateFeb.getDate() + "일 " + weekdays[startDateFeb.getDay()];
            a.textContent = dateText;
            a.onclick = function () {
                selectedDate(this.textContent); // 날짜 클릭 시 selectedDate 함수 호출
            };
            // 토요일은 파란색으로 설정
            if (weekdays[startDateFeb.getDay()] === '토') {
                a.classList.add('blue-text');
            } else if (weekdays[startDateFeb.getDay()] === '일') { // 일요일은 빨간색으로 설정
                a.classList.add('red-text');
            } else { // 토요일과 일요일이 아닌 경우 검정색으로 설정
                a.classList.add('black-text');
            }
            li.appendChild(a);
            ulFeb.appendChild(li);
        }

        // 다음 날짜로 이동
        startDateFeb.setDate(startDateFeb.getDate() + 1);
    }

    // 3월 날짜 생성
    while (startDateMar <= endDateMar) {
        if (startDateMar >= today) { // 오늘 이후의 날짜만 생성
            var li = document.createElement("li");
            var a = document.createElement("a");
            a.href = "#";
            var dateText = startDateMar.getDate() + "일 " + weekdays[startDateMar.getDay()];
            a.textContent = dateText;
            a.onclick = function () {
                selectedDate(this.textContent); // 날짜 클릭 시 selectedDate 함수 호출
            };
            // 토요일은 파란색으로 설정
            if (weekdays[startDateMar.getDay()] === '토') {
                a.classList.add('blue-text');
            } else if (weekdays[startDateMar.getDay()] === '일') { // 일요일은 빨간색으로 설정
                a.classList.add('red-text');
            } else { // 토요일과 일요일이 아닌 경우 검정색으로 설정
                a.classList.add('black-text');
            }

            li.appendChild(a);
            ulMar.appendChild(li);
        }

        // 다음 날짜로 이동
        startDateMar.setDate(startDateMar.getDate() + 1);
    }
});
// 선택하면 색상 변경
document.addEventListener("DOMContentLoaded", function() {
    // 2월 날짜 요소 선택
    var dateUl = document.getElementById("dateUl");
    var febDates = dateUl.getElementsByTagName("a");

    // 3월 날짜 요소 선택
    var dateUl1 = document.getElementById("dateUl1"); // 3월 요소 선택
    var marDates = dateUl1.getElementsByTagName("a");

    // 모든 날짜 요소에 대한 이벤트 리스너 추가
    var allDates = [].concat(Array.from(febDates), Array.from(marDates));
    for (var i = 0; i < allDates.length; i++) {
        allDates[i].addEventListener("click", function() {
            // 모든 날짜 요소의 클래스 초기화
            for (var j = 0; j < allDates.length; j++) {
                allDates[j].classList.remove("selected-date");
            }

            // 선택된 요소의 클래스 변경
            this.classList.add("selected-date");
        });
    }
});

// 영화, 극장, 날짜 선택완료하면 시간 보여주기
// document.addEventListener("DOMContentLoaded", function() {
//     var time = document.getElementById("timeList");
//
//     function showTime() {
//         var movie = document.getElementById("selectedMovie").value;
//         var cinema = document.getElementById("selectedCinema").value;
//         var date = document.getElementById("selectedDate").value;
//
//         if (movie && cinema && date) {
//             time.style.display = "block"; // 모든 변수가 선택되었을 때 시간 요소 보이기
//         } else {
//             time.style.display = "none"; // 아직 모든 변수가 선택되지 않았을 때 시간 요소 숨기기
//         }
//         console.log(movie)
//         console.log(cinema)
//         console.log(date)
//     }
//     // 페이지가 로드될 때와 변수가 변경될 때마다 showTime 함수 호출
//     showTime();
//     // movie, cinema, date 변수가 변경되었을 때 showTime 함수를 호출
//     movie.addEventListener("change", showTime);
//     cinema.addEventListener("change", showTime);
//     date.addEventListener("change", showTime);
// });

// 모든정보
function selectedMovie(pickMovie) { // 영화
    var selectedMovieDiv = document.getElementById('selectedMovie');
    selectedMovieDiv.innerHTML = pickMovie;

    // 영화선택 텍스트 숨기기
    var movieSelectionText = document.getElementById('movieSelectionText');
    movieSelectionText.style.display = 'none';
}
function posterAndMovieAge(movieTitle) { // 포스터, 관람나이
    var poster = document.getElementById("poster");
    var movieAge = document.getElementById("movieAge");

    // 영화에 따라 포스터 이미지와 관람 등급 설정
    switch (movieTitle) {
        case "웡카":
            poster.innerHTML = "<img th:src='${movie.poster}' alt='웡카 포스터' width='200'>";
            movieAge.textContent = "15세 관람가";
            break;
        case "시민덕희":
            poster.innerHTML = "<img src='시민덕희.jpg' alt='시민덕희 포스터' width='200'>";
            movieAge.textContent = "12세 관람가";
            break;
        case "추락의 해부":
            poster.innerHTML = "<img src='추락의 해부.jpg' alt='추락의 해부 포스터' width='200'>";
            movieAge.textContent = "15세 관람가";
            break;
        // 다른 영화들에 대한 정보도 추가
    }
}
function selectedCinema(pickCinema) {
    var selectedCinemaDiv = document.getElementById('selectedCinema');
    selectedCinemaDiv.textContent = "극장 : " + pickCinema;
    selectedCinemaDiv.style.display = 'block'; // 선택된 극장 보여주기
    // 극장선택 텍스트 숨기기
    var cinemaSelectionText = document.getElementById('cinemaSelectionText');
    cinemaSelectionText.style.display = 'none';
}

function selectedDate(pickDate) {
    var selectedDateDiv = document.getElementById('selectedDate');
    selectedDateDiv.textContent = "관람일 : " + pickDate;
    selectedDateDiv.style.display = 'block'; // 선택된 날짜 보여주기
}

function selectedTime(pickTime) {
    var selectedTimeDiv = document.getElementById('selectedTime');
    selectedTimeDiv.textContent = "시간 : " + pickTime;
    selectedTimeDiv.style.display = 'block'; // 선택된 시간 보여주기
}

// 좌석선택 전 모두 선택됐는지 확인
document.getElementById("seatSelectionText").addEventListener("click", function() {
    var selectedMovie = document.getElementById("selectedMovie").innerText.trim();
    var selectedCinema = document.getElementById("selectedCinema").innerText.trim();
    var selectedDate = document.getElementById("selectedDate").innerText.trim();
    var selectedTime = document.getElementById("selectedTime").innerText.trim();

    // 전부 선택 완료됐는지 확인
    if (selectedMovie === "") {
        alert("영화를 선택해주세요.");
    } else if (selectedCinema === "" ) {
        alert("극장을 선택해주세요.");
    } else if (selectedDate === "") {
        alert("날짜를 선택해주세요.");
    } else if (selectedTime === "") {
        alert("시간을 선택해주세요.");
    } else {
        alert("좌석페이지 로딩중 ") // 되는지 확인하려고 작성
    }
});