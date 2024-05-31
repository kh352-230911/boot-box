function setMovieCookieAndRedirect_detail(cookieName, movieId) {
    // 쿠키 저장
    console.log("setMovieCookieAndRedirect_detail cookieName",cookieName);
    console.log("setMovieCookieAndRedirect_detail movieId",movieId);
    document.cookie = cookieName + '=' + movieId + '; path=/';
    // 페이지 이동
    window.location.href = `${contextPath}reservation/reservationBooking.do`;
}