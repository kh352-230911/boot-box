$(document).ready(function() {
    // 답변 작성 폼 제출 시
    $('#answerForm').submit(function(e) {
        // 폼 기본 동작 방지
        e.preventDefault();

        // 폼 데이터 가져오기
        var formData = $(this).serialize();

        // AJAX를 사용하여 답변 저장 요청
        var url = window.location.href;
        var match = url.match(/[?&]id=(\d+)/); // 정규식을 사용하여 id 값 추출
        if (match) {
            var askId = match[1];
        }
        $.ajax({
            url: '/ask/createAnswer.do?id=' + askId,
            type: 'POST',
            data: formData,
            success: function(response) {
                // 성공적으로 저장되면 답변을 화면에 표시
                $('#showAnswer').text(response);
                // 성공 메시지 표시
                alert('답변이 성공적으로 저장되었습니다!');
                // 답변 작성 폼 숨기기
                // $('#answerForm').hide();
                // askList 페이지로 이동
                window.location.href = '/ask/askList.do';
            },
            error: function(xhr, status, error) {
                console.error(xhr.responseText);
            }
        });
    });
});