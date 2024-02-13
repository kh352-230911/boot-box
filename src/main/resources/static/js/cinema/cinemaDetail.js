// 현재 영화 상영작
const arrows = document.querySelectorAll("i.arrow");
const movieList = document.querySelectorAll(".movie-list");

arrows.forEach((arrow, i) => {
    const movieItemsLength = movieList[i].querySelectorAll("img").length;
    let clickCounter = 0;
    arrow.addEventListener("click", () => {
        clickCounter++;
        let ratio = window.innerWidth / 270;
        if (window.innerWidth <= 765) {
            let valueOfX = movieList[i].computedStyleMap().get("transform")[0].x
                .value;
            if (movieItemsLength - (5 + clickCounter) + (5 - ratio) >= 0) {
                movieList[i].style.transform = `translateX(${valueOfX - 290}px)`;
            } else {
                movieList[i].style.transform = "translateX(0)";
                clickCounter = 0;
            }
        } else {
            let valueOfX = movieList[i].computedStyleMap().get("transform")[0].x
                .value;
            if (movieItemsLength - (5 + clickCounter) >= 0) {
                movieList[i].style.transform = `translateX(${valueOfX - 290}px)`;
            } else {
                movieList[i].style.transform = "translateX(0)";
                clickCounter = 0;
            }
        }
        // let valueOfX = movieList[i].computedStyleMap().get("transform")[0].x.value;
        // if (movieItemsLength - (5 + clickCounter) >= 0) {
        //   movieList[i].style.transform = `translateX(${valueOfX - 290}px)`;
        // } else {
        //   movieList[i].style.transform = "translateX(0)";
        //   clickCounter = 0;
        // }
    });
});

// // 다크 모드 해제
// const toggleBall = document.querySelector(".toggle");
// const toToggleItems = document.querySelectorAll(
//     ".toggle, .toggle-ball, h2, .sidebar, .navbar-container, .container, footer"
// );
// toggleBall.addEventListener("click", () => {
//     toToggleItems.forEach((item) => {
//         item.classList.toggle("active");
//     });
// });


// 상영 시간표 데이터 - 실제로는 서버에서 가져오는 데이터를 사용.
const scheduleData = [
    {
        title: "데드맨",
        totalDuration: 120,
        schedules: [
            {
                theater: "1관",
                times: [
                    { time: "19:20", seatsAvailable: 50},
                    { time: "21:50", seatsAvailable: 43},
                ],

            },
            {
                theater: "2관",
                times: [{ time: "19:40", seatsAvailable: 58 }],
            }
        ]
    },
    {
        title: "시민덕희",
        totalDuration: 120,
        schedules: [
            {
                theater: "5관",
                times: [{ time: "19:20", seatsAvailable: 50 }],
            },
            {
                theater: "6관",
                times: [{ time: "19:40", seatsAvailable: 58 }],
            }
        ]
    },
    // ...기타 영화 및 상영 시간 데이터 추가..
];

function renderSchedule(scheduleData) {
    const scheduleContainer = document.getElementById('movieSchedule');
    scheduleContainer.innerHTML = ''; // 이전 내용을 지웁니다.

    scheduleData.forEach((movie) => {
        const movieScheduleElement = document.createElement('div');
        movieScheduleElement.classList.add('movie-schedule');

        const movieTitle = document.createElement('div');
        movieTitle.classList.add('movie-title');
        movieTitle.textContent = `${movie.title} 상영시간 ${movie.totalDuration}분`;
        movieScheduleElement.appendChild(movieTitle);

        movie.schedules.forEach((schedule) => {
            const theaterInfo = document.createElement('div');
            theaterInfo.classList.add('theater-info');
            theaterInfo.textContent = `-------------------- ${schedule.theater} -------------------- `;
            movieScheduleElement.appendChild(theaterInfo);

            const timeSlots = document.createElement('div');
            timeSlots.classList.add('time-slots');

            schedule.times.forEach((time) => {
                const timeSlot = document.createElement('div');
                timeSlot.classList.add('time-slot');
                timeSlot.textContent = time.time;

                const seatsAvailable = document.createElement('div');
                seatsAvailable.classList.add('seats-available');
                seatsAvailable.textContent = `남은 좌석 : ${time.seatsAvailable}석`;

                timeSlot.appendChild(seatsAvailable);
                timeSlots.appendChild(timeSlot);
            });

            movieScheduleElement.appendChild(timeSlots);
        });

        scheduleContainer.appendChild(movieScheduleElement);
    });
}

// 페이지가 로드될 때 상영 시간표를 렌더링.
document.addEventListener('DOMContentLoaded', () => renderSchedule(scheduleData));


// 한 줄 달력
const monthElement = document.getElementById('month');
const calendarContainer = document.getElementById('calendar');
const prevArrow = document.getElementById('prev');
const nextArrow = document.getElementById('next');
const todayButton = document.getElementById('today');
const selectedDateElement = document.getElementById('selectedDate');
const weekDays = ['일', '월', '화', '수', '목', '금', '토'];
const months = ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'];
let currentDate = new Date();
const today = new Date();
const twoWeeksFromNow = new Date(today.getFullYear(), today.getMonth(), today.getDate() + 14);

function zeroPad(num, places) {
    return String(num).padStart(places, '0');
}

function formatDate(date) {
    return date.getFullYear() + '/' + zeroPad(date.getMonth() + 1, 2) + '/' + zeroPad(date.getDate(), 2);
}

function updateMonthElement(date) {
    monthElement.textContent = date.getFullYear() + '년 ' + months[date.getMonth()];
}

function isDateInRange(date) {
    return date >= today && date <= twoWeeksFromNow;
}

function renderCalendar(date) {
    updateMonthElement(date);
    calendarContainer.innerHTML = '';
    for (let i = 0; i < 7; i++) {
        const day = new Date(date.getFullYear(), date.getMonth(), date.getDate() - date.getDay() + i);
        const dayElement = document.createElement('li');
        dayElement.textContent = weekDays[day.getDay()] + ' ' + zeroPad(day.getDate(), 2);

        if (isDateInRange(day)) {
            dayElement.addEventListener('click', function() {
                selectedDateElement.textContent = formatDate(day);
                updateMonthElement(day); // Update the month display
            });
        } else {
            dayElement.classList.add('disabled');
        }

        if (day.toDateString() === today.toDateString()) {
            dayElement.classList.add('today');
        }

        calendarContainer.appendChild(dayElement);
    }
}

prevArrow.addEventListener('click', () => {
    currentDate.setDate(currentDate.getDate() - 7);
    renderCalendar(currentDate);
});

nextArrow.addEventListener('click', () => {
    currentDate.setDate(currentDate.getDate() + 7);
    renderCalendar(currentDate);
});

todayButton.addEventListener('click', () => {
    currentDate = new Date();
    renderCalendar(currentDate);
    selectedDateElement.textContent = formatDate(currentDate);
});

renderCalendar(currentDate);

