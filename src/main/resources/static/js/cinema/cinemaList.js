$('.select_location').on('click', function () {
    console.log("0214......");
    $("div.location-area li.on").removeClass('on');
    $(this).parent().addClass('on');
});