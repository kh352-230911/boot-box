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