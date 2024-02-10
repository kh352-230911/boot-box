$('.select_location').on('click', function () {
    $("div.location-area li.on").removeClass('on');
    $(this).parent().addClass('on');
});