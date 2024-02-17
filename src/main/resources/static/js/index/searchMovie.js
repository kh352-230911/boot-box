$(document).ready(function() {
    var slideWidth = $('.small-ul li').outerWidth() + 10;
    var currentSlide = 0;

    // Automatic sliding
    var autoSlide = setInterval(function() {
        if (currentSlide < $('.small-ul li').length - 1) {
            currentSlide++;
            moveSlide(currentSlide);
        } else {
            currentSlide = 0;
            moveSlide(0);
        }
    }, 2000);

    $('.container').mouseenter(function() {
        clearInterval(autoSlide);
    });

    $('.container').mouseleave(function() {
        autoSlide = setInterval(function() {
            if (currentSlide < $('.small-ul li').length - 1) {
                currentSlide++;
                moveSlide(currentSlide);
            } else {
                currentSlide = 0;
                moveSlide(0);
            }
        }, 2000);
    });

    // Move slide function
    function moveSlide(index) {
        $('.small-ul').css('transform', 'translateX(' + (-slideWidth * index) + 'px)');
    }

    // Left button click
    $('.left').click(function() {
        if (currentSlide > 0) {
            currentSlide--;
            moveSlide(currentSlide);
        } else {
            currentSlide = $('.small-ul li').length - 1;
            moveSlide(currentSlide);
        }
    });

    // Right button click
    $('.right').click(function() {
        if (currentSlide < $('.small-ul li').length - 1) {
            currentSlide++;
            moveSlide(currentSlide);
        } else {
            currentSlide = 0;
            moveSlide(0);
        }
    });
});