// $(document).ready(function() {
//     var slideWidth = $('.small-ul li').outerWidth() + 10;
//     var currentSlide = 0;
//
//     // Automatic sliding
//     var autoSlide = setInterval(function() {
//         if (currentSlide < $('.small-ul li').length - 1) {
//             currentSlide++;
//             moveSlide(currentSlide);
//         } else {
//             currentSlide = 0;
//             moveSlide(0);
//         }
//     }, 2000);
//
//     $('.container').mouseenter(function() {
//         clearInterval(autoSlide);
//     });
//
//     $('.container').mouseleave(function() {
//         autoSlide = setInterval(function() {
//             if (currentSlide < $('.small-ul li').length - 1) {
//                 currentSlide++;
//                 moveSlide(currentSlide);
//             } else {
//                 currentSlide = 0;
//                 moveSlide(0);
//             }
//         }, 2000);
//     });
//
//     // Move slide function
//     function moveSlide(index) {
//         $('.small-ul').css('transform', 'translateX(' + (-slideWidth * index) + 'px)');
//     }
//
//     // Left button click
//     $('.left').click(function() {
//         if (currentSlide > 0) {
//             currentSlide--;
//             moveSlide(currentSlide);
//         } else {
//             currentSlide = $('.small-ul li').length - 1;
//             moveSlide(currentSlide);
//         }
//     });
//
//     // Right button click
//     $('.right').click(function() {
//         if (currentSlide < $('.small-ul li').length - 1) {
//             currentSlide++;
//             moveSlide(currentSlide);
//         } else {
//             currentSlide = 0;
//             moveSlide(0);
//         }
//     });
// });
let currentSlide = 0; // 변수를 올바르게 초기화
$(document).ready(() => {
    // 각 포스터의 너비를 계산합니다 (외부 여백 포함).
    const posterWidth = $('.small-ul li').outerWidth(true);

    // 가시적으로 보여줄 포스터의 수를 설정합니다.
    const visiblePosters = 6;

    // 컨테이너의 너비를 계산합니다 (가시적인 포스터 수에 따라).
    const containerWidth = posterWidth * visiblePosters;

    // 컨테이너의 너비를 설정합니다.
    // $('.small').css('width', containerWidth);


    const slideWidth = $('.small-ul li').outerWidth() + 10;
    const slideCount = $('.small-ul li').length;

    const moveSlide = (index) => {
        console.log(`Move to slide: ${index}`);
        $('.small-ul').css('transform', `translateX(${-slideWidth * index}px)`);
    };

    let autoSlide = setInterval(() => {
        currentSlide = (currentSlide + 1) % slideCount;
        moveSlide(currentSlide);
    }, 4000);

    const stopAutoSlide = () => {
        console.log('Stop automatic slide');
        clearInterval(autoSlide);
    };

    const startAutoSlide = () => {
        console.log('Start automatic slide');
        autoSlide = setInterval(() => {
            currentSlide = (currentSlide + 1) % slideCount;
            moveSlide(currentSlide);
        }, 4000);
    };

    $('.left').click(() => {
        stopAutoSlide();
        currentSlide = (currentSlide - 1 + slideCount) % slideCount;
        moveSlide(currentSlide);
    });

    $('.right').click(() => {
        stopAutoSlide();
        currentSlide = (currentSlide + 1) % slideCount;
        moveSlide(currentSlide);
    });

    $('.small').mouseenter(stopAutoSlide);

    $('.small').mouseleave(startAutoSlide);
});



// 선호장르 영역
function changeTab(tabName) {
    var i, tabcontent, tablinks;
    tabcontent = document.getElementsByClassName("tab-content");
    for (i = 0; i < tabcontent.length; i++) {
        tabcontent[i].style.display = "none";
    }
    tablinks = document.getElementsByClassName("tab");
    for (i = 0; i < tablinks.length; i++) {
        tablinks[i].className = tablinks[i].className.replace(" active", "");
    }
    document.getElementById(tabName).style.display = "block";
    document.getElementById(tabName + "Btn").className += " active";

    var moviesContainer = document.getElementById('movies');
    if (moviesContainer && tabName !== 'recommended') {
        moviesContainer.innerHTML = ''; // 다른 탭을 열 때 'movies' 컨테이너를 비웁니다.
    }
}

function checkAuthentication() {
    const memberIdElement = document.querySelector('.memberId');
    const memberId = memberIdElement ? memberIdElement.getAttribute('data-member-id') : undefined;

    if (!memberId) {
        alert('먼저 로그인을 해주세요.');
        window.location.href = `${contextPath}login`;
        return false;
    }
    return true;
}

function abbreviateTitle(title, maxLength) {
    if (title.length <= maxLength) {
        return title;
    } else {
        return title.substring(0, maxLength) + '...';
    }
}
function updateUIWithGenerLikeData(data) {

    const message = `<span style="color: white; margin-left: 10px">"${data.memberLikeGenre.memberName}" </span>
                            <span style="color: grey;">님의 선호 장르는</span>
                            <span style="color: white;">"${data.memberLikeGenre.genreName}" </span>
                            <span style="color: grey;">입니다.</span>
                            <br><span style="color: white; margin-left: 141px; margin-top: 10px;"> 장르에 맞는 추천영화 입니다.</span>`;
    document.getElementById('genreMessage').innerHTML = message; // 변경된 부분

    let moviesHtml = '<div style="display:flex;">'; // 영화 목록을 표시할 컨테이너
    data.movies.forEach(movie => {
        if (movie.posterUrl === null) {
            // 포스터 URL이 null이면 이 영화는 목록에 추가되지 않음
            return; // 다음 영화
        }
        let posterSrc;
        if (movie.posterUrl.startsWith('http://file.koreafilm.or.kr')) {
            posterSrc = movie.posterUrl;
        } else {
            posterSrc = `https://image.tmdb.org/t/p/w200${movie.posterUrl}`;
        }

        let displayTitle = abbreviateTitle(movie.title, 10);

        const ratingIcon = RatingIcon(movie.filmRatings);

        moviesHtml += `
          <div class="movie-container">
                <div class="movie-box">
                    <a href="/bootbox/movie/movieDetail.do?id=${movie.id}">
                        <img src="${posterSrc}" alt="${displayTitle}" loading="lazy" style="border-radius: 10px;">
                        <div class="section-1-2-btn">
                            <div class="section-1-2-btn-bg"></div>
                            <div class="section-1-2-btn-de">상세보기</div>
                        </div>
                    </a>
                </div>
            
                <div style="display: flex; flex-direction: column; align-items: center; justify-content: center; margin: 5px">
                    <div style="display: flex; align-items: center; justify-content: center; gap: 5px; width: 100%; margin-bottom: 5px">
                        <div style="flex-shrink: 0;">${ratingIcon}</div>
                        <h3 style="margin: 0; text-align: center;">${displayTitle}</h3>
                    </div>
                    <div style="display: flex; text-align: center;">${movie.voteAverage} ${Stars(movie.voteAverage)}</div>
                </div>
            </div>`;
    });
    moviesHtml += '</div>';

    // 생성된 영화 목록 HTML을 'movies' div에 추가
    document.getElementById('movies').innerHTML = moviesHtml;
    document.getElementById('recommended').style.display = 'block'; // 추천영화 영역
}

function Stars(voteAverage) {
    let starsHtml = '';
    for (let i = 1; i <= 5; i++) {
        if (voteAverage / 2 >= i) {
            // Full star
            starsHtml += `<svg class="movie_star rated w-4 h-4 ms-1 text-yellow-500" fill="currentColor" viewBox="0 0 16 16">
                             <path d="M3.612 15.443c-.386.198-.824-.149-.746-.592l.83-4.73L.173 6.765c-.329-.314-.158-.888.283-.95l4.898-.696L7.538.792c.197-.39.73-.39.927 0l2.184 4.327 4.898.696c.441.062.612.636.282.95l-3.522 3.356.83 4.73c.078.443-.36.79-.746.592L8 13.187l-4.389 2.256z" />
                          </svg>`;
        } else if (voteAverage / 2 >= i - 0.5) {
            // Half star
            starsHtml += `<svg class="movie_star rated w-4 h-4 ms-1 text-yellow-500" fill="currentColor" viewBox="0 0 16 16">
                              <path d="M5.354 5.119 7.538.792A.516.516 0 0 1 8 .5c.183 0 .366.097.465.292l2.184 4.327 4.898.696A.537.537 0 0 1 16 6.32a.548.548 0 0 1-.17.445l-3.523 3.356.83 4.73c.078.443-.36.79-.746.592L8 13.187l-4.389 2.256a.52.52 0 0 1-.146.05c-.342.06-.668-.254-.6-.642l.83-4.73L.173 6.765a.55.55 0 0 1-.172-.403.58.58 0 0 1 .085-.302.513.513 0 0 1 .37-.245l4.898-.696zM8 12.027a.5.5 0 0 1 .232.056l3.686 1.894-.694-3.957a.565.565 0 0 1 .162-.505l2.907-2.77-4.052-.576a.525.525 0 0 1-.393-.288L8.001 2.223 8 2.226v9.8z" />
                          </svg>`;
        } else {
            // Empty star
            starsHtml += `<svg class="movie_star rated w-4 h-4 ms-1 text-yellow-500" fill="none" stroke="currentColor" viewBox="0 0 16 16">
                             <path d="M2.866 14.85c-.078.444.36.791.746.593l4.39-2.256 4.389 2.256c.386.198.824-.149.746-.592l-.83-4.73 3.522-3.356c.33-.314.16-.888-.282-.95l-4.898-.696L8.465.792a.513.513 0 0 0-.927 0L5.354 5.12l-4.898.696c-.441.062-.612.636-.283.95l3.523 3.356-.83 4.73zm4.905-2.767-3.686 1.894.694-3.957a.565.565 0 0 0-.163-.505L1.71 6.745l4.052-.576a.525.525 0 0 0 .393-.288L8 2.223l1.847 3.658a.525.525 0 0 0 .393.288l4.052.575-2.906 2.77a.565.565 0 0 0-.163.506l.694 3.957-3.686-1.894a.503.503 0 0 0-.461 0z" />
                          </svg>`;
        }
    }
    return starsHtml;
}

function RatingIcon(filmRatings) {
    const baseClass = "grade bg-gradient-to-r text-m font-bold me-2 px-2.5 py-0.5 rounded dark:text-gray-700 border";
    let ratingClass, ratingText;

    switch (filmRatings) {
        case '전체관람가':
            ratingClass = "bg-green-100 text-green-800 border-green-400";
            ratingText = 'ALL';
            break;
        case '12세관람가':
            ratingClass = "bg-yellow-100 text-yellow-800 border-yellow-300";
            ratingText = '12';
            break;
        case '15세관람가':
            ratingClass = "bg-purple-100 text-purple-800 border-purple-400";
            ratingText = '15';
            break;
        case '18세관람가(청소년관람불가)':
            ratingClass = "bg-red-100 text-red-800 border-red-400";
            ratingText = '18';
            break;
        case '정보 없음':
            ratingClass = "bg-gray-100 text-gray-800 border-gray-500";
            ratingText = '미정';
            break;
        default:
            ratingClass = "bg-gray-100 text-gray-800 border-gray-500";
            ratingText = '미정';
    }

    return `<span style="font-size: 0.4em" class="${baseClass} ${ratingClass}">${ratingText}</span>`;
}

// 슬라이더를 이동하는 함수
let slideIndex = 0;
function moveSlide(direction) {
    const moviesList = document.getElementById('movies');
    const slideWidth = moviesList.getElementsByClassName('movie-container')[0].offsetWidth;
    const maxSlides = moviesList.getElementsByClassName('movie-container').length;
    const sliderVisibleWidth = moviesList.offsetWidth;

    const maxIndex = Math.ceil(maxSlides - sliderVisibleWidth / slideWidth);
    if (direction === 'right' && slideIndex < maxIndex) {
        slideIndex++;
    } else if (direction === 'left' && slideIndex > 0) {
        slideIndex--;
    }

    moviesList.style.transform = `translateX(${-slideWidth * slideIndex}px)`;
}
function checkAndChangeTab(tabName) {
    if (checkAuthentication()) { // 먼저 로그인 상태를 확인
        changeTab(tabName); // 로그인 상태가 확인되면 탭을 변경
    }
}

$(document).ready(function() {
    $('#movies').css('flex-wrap', 'nowrap');
    $('#recommendedBtn').click(function() {
        if (!checkAuthentication()) {
            return; // 사용자가 로그인하지 않았으면 여기서 중단
        }
        fetchGenerLikeData(); // 사용자가 로그인했다면 선호장르 데이터를 가져옴
    });

    function fetchGenerLikeData() {
        var memberId = $('.memberId').data('member-id');
        $.ajax({
            url: `${contextPath}GenerLike`,
            type: 'GET',
            data: { memberId: memberId},
            success: function(data) {
                console.log('Data received:', data);
                if (document.getElementById('recommended').style.display === 'block') {
                    updateUIWithGenerLikeData(data);
                    resetSlidePosition(); // 슬라이드 위치 초기화
                }
            },
            error: function(jqXHR, textStatus, errorThrown) {
                console.error('AJAX request failed:', textStatus, errorThrown);
            }
        });
    }
    function resetSlidePosition() {
        currentSlide = 0;
        moveSlide(currentSlide);
    }
    // $('#recommendedBtn').click(function() {
    //     // ... 기존 코드 ...
    //     slideIndex = 0; // 슬라이더 위치 초기화
    //     $('#movies').css('transform', 'translateX(0)'); // 슬라이더 위치 리셋
    // });

    // 페이지 로드 시 기본적으로 '무비 차트' 탭 활성화
    changeTab('movieChart');
});


