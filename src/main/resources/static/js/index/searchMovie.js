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

// function changeTab(tabName) {
//     // 모든 탭 컨텐츠를 숨김
//     var i, tabcontent, tablinks;
//     tabcontent = document.getElementsByClassName("tab-content");
//     for (i = 0; i < tabcontent.length; i++) {
//         tabcontent[i].style.display = "none";
//     }
//     // 모든 탭 링크에서 'active' 클래스 제거
//     tablinks = document.getElementsByClassName("tab");
//     for (i = 0; i < tablinks.length; i++) {
//         tablinks[i].className = tablinks[i].className.replace(" active", "");
//     }
//     // 클릭된 탭과 관련된 컨텐츠를 표시
//     document.getElementById(tabName).style.display = "block";
//     document.getElementById(tabName).classList.add("active");
//     // 클릭된 탭에 'active' 클래스 추가
//     document.getElementById(tabName + "Btn").classList.add("active");
// }
// $(document).ready(function() {
//     // 사용자의 선호 장르에 대한 데이터를 가져오는 함수
//     function fetchGenerLikeData() {
//         $.ajax({
//             url: `${contextPath}GenerLike`, // 요청을 보낼 서버의 URL
//             type: 'GET',
//             dataType: 'json', // 서버에서 받을 데이터 형식
//             beforeSend: function(xhr) {
//                 xhr.setRequestHeader(csrfHeaderName, csrfToken);
//             },
//             success: function (data) {
//                 // 데이터 처리 성공 시 호출됩니다.
//                 console.log('Data received:', data);
//                 updateUIWithGenerLikeData(data); // UI를 업데이트하는 함수 호출
//             },
//             error: function (jqXHR, textStatus, errorThrown) {
//                 // 요청 실패 시 호출됩니다.
//                 console.error('AJAX request failed:', textStatus, errorThrown);
//             }
//         });
//     }
// });
// // 페이지 로딩 시 '무비 차트' 탭을 기본적으로 활성화
// document.addEventListener('DOMContentLoaded', (event) => {
//     changeTab('movieChart');
//
// });


// function changeTab(tabName) {
//     var i, tabcontent, tablinks;
//     tabcontent = document.getElementsByClassName("tab-content");
//     for (i = 0; i < tabcontent.length; i++) {
//         tabcontent[i].style.display = "none";
//     }
//     tablinks = document.getElementsByClassName("tab");
//     for (i = 0; i < tablinks.length; i++) {
//         tablinks[i].className = tablinks[i].className.replace(" active", "");
//     }
//     document.getElementById(tabName).style.display = "block";
//     document.getElementById(tabName + "Btn").className += " active";
// }
//
// function updateUIWithGenerLikeData(data) {
//     // 선호 장르 데이터로 UI 업데이트
//     document.getElementById('recommended').innerHTML;
// }
//
// $(document).ready(function() {
//     const memberId = $('.memberId').data('member-id');
//
//     function fetchGenerLikeData() {
//         $.ajax({
//             url: `${contextPath}GenerLike`, // 서버 URL을 여러분의 앱에 맞게 수정하세요.
//             type: 'GET',
//             data: { memberId: memberId},
//             success: function(data) {
//                 console.log('Data received:', data);
//                 updateUIWithGenerLikeData(data);
//             },
//             error: function(jqXHR, textStatus, errorThrown) {
//                 console.error('AJAX request failed:', textStatus, errorThrown);
//             }
//         });
//     }
//
//     $('#recommendedBtn').click(function() {
//         fetchGenerLikeData(); // '선호장르 추천영화' 탭을 클릭했을 때 데이터를 가져옵니다.
//     });
//
//     // 페이지 로드 시 기본적으로 '무비 차트' 탭 활성화
//     changeTab('movieChart');
// });


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
}

// function checkAuthentication() {
//     // 예제에서는 간단히 체크하기 위해 memberId가 존재하는지 확인합니다.
//     // 실제 애플리케이션에서는 서버측에서 인증 상태를 더 정확하게 확인할 수 있는 방법을 사용해야 합니다.
//     const memberId = $('.memberId').data('member-id');
//     if (memberId == undefined) {
//         alert('먼저 로그인을 해주세요.');
//         window.location.href = `${contextPath}login.do`; // 로그인 페이지 경로를 실제 경로로 변경해야 합니다.
//         return false; // 이후 로직을 실행하지 않습니다.
//     }
//     return true; // 인증된 경우, true를 반환합니다.
// }

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

function updateUIWithGenerLikeData(data) {
    const message = `<span style="color: white; margin-left: 10px">"${data.memberName}" </span>
                            <span style="color: grey;">님의 선호 장르는</span>
                            <span style="color: white;">"${data.genreName}" </span>
                            <span style="color: grey;">입니다.</span>
                            <br><span style="color: white; margin-left: 141px; margin-top: 10px;"> 장르에 맞는 추천영화 입니다.</span>`;
    // 메시지를 'recommended' 영역에 표시
    document.getElementById('recommended').innerHTML = message;
    // 'recommended' 영역을 보이게 함
    document.getElementById('recommended').style.display = 'block';
}
function checkAndChangeTab(tabName) {
    if (checkAuthentication()) { // 먼저 로그인 상태를 확인
        changeTab(tabName); // 로그인 상태가 확인되면 탭을 변경
    }
}

$(document).ready(function() {
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
                updateUIWithGenerLikeData(data);
            },
            error: function(jqXHR, textStatus, errorThrown) {
                console.error('AJAX request failed:', textStatus, errorThrown);
            }
        });
    }

    // 페이지 로드 시 기본적으로 '무비 차트' 탭 활성화
    changeTab('movieChart');
});



