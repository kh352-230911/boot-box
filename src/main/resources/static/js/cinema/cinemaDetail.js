// 현재 영화 상영작
// const arrows = document.querySelectorAll("i.arrow");
// const movieList = document.querySelectorAll(".movie-list");
//
// arrows.forEach((arrow, i) => {
//     const movieItemsLength = movieList[i].querySelectorAll("img").length;
//     let clickCounter = 0;
//     arrow.addEventListener("click", () => {
//         clickCounter++;
//         let ratio = window.innerWidth / 270;
//         if (window.innerWidth <= 765) {
//             let valueOfX = movieList[i].computedStyleMap().get("transform")[0].x
//                 .value;
//             if (movieItemsLength - (5 + clickCounter) + (5 - ratio) >= 0) {
//                 movieList[i].style.transform = `translateX(${valueOfX - 290}px)`;
//             } else {
//                 movieList[i].style.transform = "translateX(0)";
//                 clickCounter = 0;
//             }
//         } else {
//             let valueOfX = movieList[i].computedStyleMap().get("transform")[0].x
//                 .value;
//             if (movieItemsLength - (5 + clickCounter) >= 0) {
//                 movieList[i].style.transform = `translateX(${valueOfX - 290}px)`;
//             } else {
//                 movieList[i].style.transform = "translateX(0)";
//                 clickCounter = 0;
//             }
//         }
//         // let valueOfX = movieList[i].computedStyleMap().get("transform")[0].x.value;
//         // if (movieItemsLength - (5 + clickCounter) >= 0) {
//         //   movieList[i].style.transform = `translateX(${valueOfX - 290}px)`;
//         // } else {
//         //   movieList[i].style.transform = "translateX(0)";
//         //   clickCounter = 0;
//         // }
//     });
// });

// 마지막에서 한 번에 움직임...
// const leftArrow = document.querySelector(".left");
// const rightArrow = document.querySelector(".right");
// const movieList = document.querySelector(".movie-list");
// const movieItems = document.querySelectorAll(".movie-list-item");
// const itemWidth = movieItems[0].clientWidth; // 영화 포스터 하나의 너비
// const gap = parseInt(window.getComputedStyle(movieItems[0]).marginRight); // CSS에서 설정한 margin-right 값
// const step = itemWidth + gap; // 이동할 단계 길이
// const maxVisibleItems = 6; // 화면에 보여줄 영화의 최대 개수
// const maxIndex = movieItems.length - maxVisibleItems; // 최대 인덱스 계산
//
// let index = 0; // 현재 위치
//
// rightArrow.addEventListener("click", () => {
//     if (index < maxIndex) {
//         index++;
//         movieList.style.transform = `translateX(${-step * index}px)`;
//     } else if (index === maxIndex) {
//         // 마지막 슬라이드의 경우, 남은 영화 포스터 수에 따라 이동 거리를 조정합니다.
//         const remainingItems = movieItems.length % maxVisibleItems;
//         const lastStep = remainingItems * (itemWidth + gap); // 남은 아이템에 대한 이동 거리
//         movieList.style.transform = `translateX(${-step * index - lastStep}px)`;
//         index++; // 마지막 슬라이드 이후에는 더 이상 이동하지 않도록 index를 증가시킵니다.
//     }
// });
//
// leftArrow.addEventListener("click", () => {
//     // 왼쪽 화살표 클릭: index 감소
//     if (index > 0) {
//         index--;
//         movieList.style.transform = `translateX(${-step * index}px)`;
//     }
// });

// arrows.forEach((arrow, i) => {
//     const movieListContainer = document.querySelector('.movie-list-wrapper'); // 포스터 목록 컨테이너
//     const movieList = movieListContainer.querySelector('.movie-list'); // 포스터 목록
//
//     arrow.addEventListener('click', () => {
//         const containerWidth = movieListContainer.offsetWidth; // 컨테이너 너비
//         const itemWidth = movieList.querySelector('.movie-list-item').offsetWidth; // 각 포스터 아이템 너비
//         const itemsToShow = Math.floor(containerWidth / itemWidth); // 한 번에 표시할 아이템 수
//         const totalItems = movieList.querySelectorAll('.movie-list-item').length; // 전체 아이템 수
//         const maxScrollCount = totalItems - itemsToShow; // 최대 스크롤 가능 횟수
//
//         if (arrow.classList.contains('fa-chevron-right')) {
//             // 오른쪽 화살표 클릭
//             if (clickCounter < maxScrollCount) {
//                 movieList.style.transform = `translateX(-${++clickCounter * itemWidth}px)`;
//             }
//         } else if (arrow.classList.contains('fa-chevron-left')) {
//             // 왼쪽 화살표 클릭
//             if (clickCounter > 0) {
//                 movieList.style.transform = `translateX(-${--clickCounter * itemWidth}px)`;
//             }
//         }
//     });
// });

// 포스터 화살표 동작
const leftArrow = document.querySelector(".left");
const rightArrow = document.querySelector(".right");
const movieList = document.querySelector(".movie-list");
const movieItems = document.querySelectorAll(".movie-list-item");

// gap을 기본값으로 미리 선언합니다.
let gap = 0;

// movieItems 배열이 존재하고, 적어도 하나의 요소가 있는지 확인합니다.
if (movieItems.length > 0) {
    gap = parseInt(window.getComputedStyle(movieItems[0]).marginRight);
}

// 여기에서 'itemWidth'와 'maxIndex'를 사용하기 전에 'gap'이 정의되었는지 확인합니다.
const itemWidth = movieItems[0] ? movieItems[0].clientWidth + gap : 0; // 영화 포스터 하나의 너비 + gap
const maxIndex = movieItems.length - 1; // 슬라이드를 넘길 수 있는 마지막 아이템 인덱스

let index = 0; // 현재 위치

if (rightArrow) {
    rightArrow.addEventListener("click", () => {
        if (index < maxIndex) {
            index++; // 다음 아이템으로 이동
        } else {
            index = 0; // 처음으로 되돌아감
        }
        movieList.style.transform = `translateX(${-itemWidth * index}px)`; // 슬라이드 이동
    });
}

if (leftArrow) {
    leftArrow.addEventListener("click", () => {
        if (index > 0) {
            index--; // 이전 아이템으로 이동
        } else {
            index = maxIndex; // 마지막으로 이동
        }
        movieList.style.transform = `translateX(${-itemWidth * index}px)`; // 슬라이드 이동
    });
}

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
// 이전에 선택된 날짜를 추적하기 위한 변수
let selectedDate;

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

        if (day.toDateString() === today.toDateString()) {
            dayElement.classList.add('today');
            dayElement.classList.add('clickable');
            dayElement.addEventListener('click', function() {
                if (selectedDate) {
                    selectedDate.classList.remove('selected');
                }

                const todayElement = calendarContainer.querySelector('.today');
                if (todayElement) {
                    todayElement.classList.remove('back-color');
                    todayElement.classList.add('selected');
                }

                dayElement.classList.add('selected');

                selectedDate = dayElement;

                selectedDateElement.textContent = formatDate(day);
                updateMonthElement(day);
                scheduleManager();
            });
        }

        if (isDateInRange(day)) {
            dayElement.classList.add('clickable');
            dayElement.addEventListener('click', function() {
                // 이전에 선택한 날짜의 스타일을 제거합니다.
                if (selectedDate) {
                    selectedDate.classList.remove('selected');
                }

                // 오늘 날짜의 스타일을 기본 상태로 되돌립니다.
                const todayElement = calendarContainer.querySelector('.today');
                if (todayElement) {
                    todayElement.classList.add('back-color');
                }

                // 클릭한 요소에 'selected' 클래스를 추가합니다.
                dayElement.classList.add('selected');

                // 선택된 날짜를 업데이트합니다.
                selectedDate = dayElement;

                selectedDateElement.textContent = formatDate(day);
                updateMonthElement(day); // Update the month display
                scheduleManager();
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
    scheduleManager();
});


document.addEventListener('DOMContentLoaded', () => {
    renderCalendar(currentDate);
    selectedDateElement.textContent = formatDate(today); // 페이지 로드 시 오늘 날짜를 선택하도록 설정
    scheduleManager();
    // 오늘 날짜 요소를 찾아 'selected' 클래스를 추가합니다.
    const todayElement = calendarContainer.querySelector('.today');
    todayElement.classList.remove('disabled');
    if (todayElement) {
        todayElement.classList.add('selected');
        selectedDate = todayElement;
    }
});

// 위도, 경도로 네이버 지도에서 극장 실시간 위치 찾기
function openMap(event, element) {
    event.preventDefault();
    const locationLo = element.getAttribute('data-location-lo');
    const locationLa = element.getAttribute('data-location-la');

    const mapUrl = `https://m.map.naver.com/map.naver?lng=${locationLo}&lat=${locationLa}&level=2`;
    window.open(mapUrl, '_blank');
}

// 상영 일정 관리자
const scheduleManager = () => {
    const id = $('.active:eq(1)').data('cinema-id'); // 극장 ID
    const selectedDate = $('#selectedDate').text(); // 선택된 날짜
    console.log(id);
    console.log(selectedDate);

    $.ajax({
        url: `${contextPath}cinema/scheduleByDate`, // 경로는 실제 서버의 엔드포인트에 맞추어야 함
        type: 'GET',
        data: {
            id: id,
            selectedDate: selectedDate
        },
        success: function(response) {
            console.log(response);
            renderSchedule(response); // 응답으로 받은 데이터를 사용하여 스케줄 렌더링
        },
        error: function(xhr, status, error) {
            console.error("An error occurred: " + status + " " + error);
            $('#movieSchedule').html('<p>스케줄을 불러오는데 문제가 발생했습니다. 나중에 다시 시도해주세요.</p>');
        }
    });
};

// 서버에서 받은 JSON 데이터를 기반으로 HTML 렌더링
function renderSchedule(scheduleData) {
    $('#movieSchedule').empty(); // 기존 내용을 지웁니다.

    // 현재 시간을 구하기.
    const now = new Date();

    scheduleData.forEach(movie => {
        const movieElement = $('<div>').addClass('schedule-container');

        // 관람 등급에 따른 스팬 태그 생성
        const ratingSpan = $('<span>').css('font-size', '0.8em').addClass('grade me-2 px-2.5 py-0.5 rounded font-bold');
        // console.log(movie.filmRatings);
        switch(movie.filmRatings) {
            case '전체관람가':
                ratingSpan.addClass('bg-green-100 text-green-800 dark:bg-gray-700 dark:text-green-400 border border-green-400').text('ALL');
                break;
            case '12세관람가':
                ratingSpan.addClass('bg-yellow-100 text-yellow-800 dark:bg-gray-700 dark:text-yellow-300 border border-yellow-300').text('12');
                break;
            case '15세관람가':
                ratingSpan.addClass('bg-purple-100 text-purple-800 dark:bg-gray-700 dark:text-purple-400 border border-purple-400').text('15');
                break;
            case '18세관람가(청소년관람불가)':
                ratingSpan.addClass('bg-red-100 text-red-800 dark:bg-gray-700 dark:text-red-400 border border-red-400').text('18');
                break;
            case '정보 없음':
                ratingSpan.addClass('bg-gray-100 text-gray-800 dark:bg-gray-700 dark:text-gray-400 border border-gray-500').text('미정');
                break;
            // default:
            //     ratingSpan.hide(); // 만약 정의되지 않은 등급이라면 숨김 처리
        }

        const movieTitle = $('<div>').addClass('movie-title').append(ratingSpan).append(`[${movie.title}] 상영시간: ${movie.totalDuration}분`);
        movieElement.append(movieTitle);

        movie.schedules.forEach(schedule => {
            const theaterInfo = $('<div>').addClass('theater-info').text(`-------------------- ${schedule.theater} --------------------`);
            movieElement.append(theaterInfo);

            const timeSlots = $('<div>').addClass('time-slots');
            schedule.times.forEach(time => {
                const startTimeText = time.time;

                // 상영 시간을 Date 객체로 변환.
                const [hours, minutes] = startTimeText.split(':').map(Number);
                const movieStartDate = new Date(now.getFullYear(), now.getMonth(), now.getDate(), hours, minutes, 0, 0);
                const timeDifference = (movieStartDate - now) / 60000; // 분 단위로 차이를 계산합니다.
                // console.log(movieStartDate);
                // console.log(timeDifference);

                let seatsAvailable;
                let bookingText;
                // 현재시간과 비교하여 상영 시작이 10분 이내라면 '마감'으로 표시.
                if (timeDifference <= 10 && timeDifference >= 0) {
                    seatsAvailable = $('<div>').addClass('seats-available').text('남은좌석: 마감');
                    bookingText = $('<span>').addClass('booking-text').text('마감');
                // 아니라면 남은 좌석 표시
                } else {
                    seatsAvailable = $('<div>').addClass('seats-available').text(`남은좌석: ${time.seatsAvailable}석`);
                    bookingText = $('<span>').addClass('booking-text').text('예매');
                }


                // 시간 슬롯 클릭시 예약 페이지로 이동
                const timeSlot = $('<a>').addClass('time-slot').attr('href', time.bookingUrl)
                                    .append($('<span>').text(startTimeText), seatsAvailable, bookingText);
                if (timeDifference <= 10 && timeDifference >= 0) {
                    timeSlot.click(function(e) {
                        e.preventDefault(); // 마감된 시간은 슬롯 클릭 방지하여 예약 페이지 이동 불가
                    });
                }
                timeSlots.append(timeSlot);
            });

            movieElement.append(timeSlots);
        });

        $('#movieSchedule').append(movieElement);
    });
}

// 페이지가 로드될 때 상영 시간표를 렌더링
$(document).ready(() => {
    scheduleManager();
});


// // 영화관사진 랜덤하게 로딩
// function changeImage() {
//     const images = [
//         'static/images/영화관사진1.jpg',
//         'static/images/영화관사진2.jpg',
//         'static/images/영화관사진3.jpg',
//         'static/images/영화관사진4.jpg'
//     ];
//
//     const index = Math.floor(Math.random() * images.length);
//     const selectedImage = images[index];
//
//     const imgElement = document.getElementById('randomImage');
//     imgElement.src = selectedImage;
//
//     console.log('Random image selected:', selectedImage);
// }
//
// // Call the function on page load
// window.onload = changeImage;




///////////// 초임 영역 //////////////////

document.addEventListener('DOMContentLoaded', (event) => {
    // 'bookmark' 아이디를 가진 요소를 찾아 변수에 저장.
    const bookmarkIcon = document.querySelector('.bookmark');
    const cinemaId = $('.bookmark').data('cinema-id');
    const memberId = $('.memberId').data('member-id');
    // console.log(bookmarkIcon);

    //로그인 상태를 확인하고 적절한 동작을 수행하는 함수.
    function checkAuthentication() {
        if (!isAuthenticated) {
            alert('회원만 즐겨찾기가 가능합니다. 먼저 로그인을 해주세요.😊');
            location.href = `${contextPath}auth/login.do`;
        } else {
            // 북마크가 체크가 안되었을때
            if(bookmarkIcon.classList.contains("fa-regular")){
                $.ajax({
                    url: `${contextPath}cinema/cinemaLike`,
                    type: "post",
                    data: {
                        cinemaId : cinemaId,
                        memberId : memberId
                    },
                    beforeSend: function(xhr) {
                        xhr.setRequestHeader(csrfHeaderName, csrfToken);
                    },
                    success:function (data) {
                        console.log('어떤 데이터?', data);
                        // 북마크 최대 3개 이상으로 등록시
                        if(data >= 3) {
                            alert('최대 3개의 극장만 추가 가능합니다.😓');
                            return;
                        }
                         alert('자주가는 극장이 추가되었습니다.😊');

                        // fa-regular 삭제
                        bookmarkIcon.classList.remove('fa-regular');
                        bookmarkIcon.classList.remove('fa-beat-fade');
                        // fa-solid 추가
                        bookmarkIcon.classList.add('fa-solid');
                    }
                })
            } else {
                // 북마크 체크되었을때
                $.ajax({
                    url: `${contextPath}cinema/cinemaNoLike`,
                    type: "post",
                    data: {
                        cinemaId : cinemaId,
                        memberId : memberId
                    },
                    beforeSend: function(xhr) {
                        xhr.setRequestHeader(csrfHeaderName, csrfToken);
                    },
                    success:function (data) {
                        alert('자주가는 극장이 삭제되었습니다.😂');
                            // fa-solid 삭제
                            bookmarkIcon.classList.remove('fa-solid');
                            // fa-regular 추가
                            bookmarkIcon.classList.add('fa-regular');
                            bookmarkIcon.classList.add('fa-beat-fade');
                    }
                })
            }

        }
    }
    // 북마크 아이콘에 클릭 이벤트 리스너를 추가.
    bookmarkIcon.addEventListener('click', checkAuthentication);
});
