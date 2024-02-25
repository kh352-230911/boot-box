function confirmAndDelete() {
    // 삭제 여부 확인
    if (confirm("정말로 공지사항을 삭제하시겠습니까?")) {
        // 해당 공지 id
        var url = window.location.href;
        var match = url.match(/[?&]id=(\d+)/); // 정규식을 사용하여 id 값 추출
        if (match) {
            var noticeId = match[1];
            // 숨겨진 필드의 값을 설정
            document.getElementById('noticeId').value = noticeId;
            // form submit
            document.getElementById('deleteForm').submit();
        }
    }
}