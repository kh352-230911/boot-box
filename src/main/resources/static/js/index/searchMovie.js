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
    let currentSlide = 0;
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

function changeTab(tabName) {
    // 모든 탭 컨텐츠를 숨김
    var i, tabcontent, tablinks;
    tabcontent = document.getElementsByClassName("tab-content");
    for (i = 0; i < tabcontent.length; i++) {
        tabcontent[i].style.display = "none";
    }
    // 모든 탭 링크에서 'active' 클래스 제거
    tablinks = document.getElementsByClassName("tab");
    for (i = 0; i < tablinks.length; i++) {
        tablinks[i].className = tablinks[i].className.replace(" active", "");
    }
    // 클릭된 탭과 관련된 컨텐츠를 표시
    document.getElementById(tabName).style.display = "block";
    document.getElementById(tabName).classList.add("active");
    // 클릭된 탭에 'active' 클래스 추가
    document.getElementById(tabName + "Btn").classList.add("active");
}

// 페이지 로딩 시 '무비 차트' 탭을 기본적으로 활성화
document.addEventListener('DOMContentLoaded', (event) => {
    changeTab('movieChart');
});

