// 페이지 입장 시 첫번째 지역 자동선택 1회용 함수
(function (){
    $("div.location-area li:first-child").addClass('on')
})();

$('.select_location').on('click', function () {
    $("div.location-area li.on").removeClass('on');
    $(this).parent().addClass('on');
});