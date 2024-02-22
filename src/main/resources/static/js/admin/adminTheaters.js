$("#createTheater").click(function () {
    $(".createModal").css("display","block");
});

$("#deleteTheater").click(function () {
    $(".deleteModal").css("display","block");
});

$(".back").click(function (){
   $(".createModal").css("display", "none");
   $(".deleteModal").css("display", "none");
});