// 영화
function movieByAlphabetically() {
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

// 극장
function cinemaAll() {
    document.getElementById('seoulCinema').classList.add('hidden');
    document.getElementById('gyeonggiCinema').classList.add('hidden');
    document.getElementById('incheonCinema').classList.add('hidden');
    document.getElementById('frequentCinema').classList.add('hidden');
}

function cinemaLike() {
    document.getElementById('seoulCinema').classList.add('hidden');
    document.getElementById('gyeonggiCinema').classList.add('hidden');
    document.getElementById('incheonCinema').classList.add('hidden');
    document.getElementById('frequentCinema').classList.remove('hidden');
}

function showRegion(region) {
    document.getElementById('seoulCinema').classList.add('hidden');
    document.getElementById('gyeonggiCinema').classList.add('hidden');
    document.getElementById('incheonCinema').classList.add('hidden');
    document.getElementById('frequentCinema').classList.add('hidden');
    document.getElementById(region + 'Cinema').classList.remove('hidden');
}

// 날짜
document.addEventListener("DOMContentLoaded", function() {
    var ul = document.getElementById("dateUl");
    var startDate = new Date(2024, 2, 10); // 시작 날짜
    var endDate = new Date(2024, 2, 29); // 종료 날짜

    while (startDate <= endDate) {
        var li = document.createElement("li");
        var a = document.createElement("a");
        a.href = "#";
        a.textContent = startDate.getDate() + "일";
        li.appendChild(a);
        ul.appendChild(li);

        // 다음 날짜로 이동
        startDate.setDate(startDate.getDate() + 1);
    }
});
document.addEventListener("DOMContentLoaded", function() {
    var ul = document.getElementById("dateUl1");
    var startDate = new Date(2024, 3, 1); // 시작 날짜
    var endDate = new Date(2024, 3, 20); // 종료 날짜

    while (startDate <= endDate) {
        var li = document.createElement("li");
        var a = document.createElement("a");
        a.href = "#";
        a.textContent = startDate.getDate() + "일";
        li.appendChild(a);
        ul.appendChild(li);

        // 다음 날짜로 이동
        startDate.setDate(startDate.getDate() + 1);
    }
});

// 모든정보
function selectedMovie(movieName) { // 영화
    var selectedMovieDiv = document.getElementById('selectedMovie');
    selectedMovieDiv.innerHTML = movieName;
    var movieSelectionText = document.getElementById('movieSelectionText');
    movieSelectionText.style.display = 'none'; // 영화선택 텍스트 숨기기
}
function selectedCinema(cinemaName) { // 극장
    var selectedMovieDiv = document.getElementById('cinemaSelect');
    selectedMovieDiv.innerHTML = cinemaName;
    var movieSelectionText = document.getElementById('cinemaSelectionText');
    movieSelectionText.style.display = 'none'; // 극장선택 텍스트 숨기기
}

