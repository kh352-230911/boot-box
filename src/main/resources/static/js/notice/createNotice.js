// 엔터누르면 다음줄로 이동해서 작성(제출X)
document.getElementById("content").addEventListener("keyup", function(event) {
    if (event.key === "Enter") {
        event.preventDefault(); // 기본 엔터 행동을 막기

        // 현재 커서 위치와 내용을 가져옴
        var currentCursorPosition = this.selectionStart;
        var currentValue = this.value;

        // 값이 유효한지 확인하고 null 아닌 경우에만 처리
        if (currentValue) {
            // 현재 커서 위치에 줄바꿈 문자를 추가한 후, 나머지 내용을 이어 붙임
            var newValue = currentValue.slice(0, currentCursorPosition) + "\n" + currentValue.slice(currentCursorPosition);

            // textarea 내용을 새로운 값으로 설정하고, 커서를 다음 줄로 이동
            this.value = newValue;
            this.setSelectionRange(currentCursorPosition + 1, currentCursorPosition + 1);
        }
    }
});

// 선택한 공지 유형을 표시하는 함수
function updateSelectedType() {
    var selectedTypeElement = document.getElementById("noticeType");
    var selectedIndex = selectedTypeElement.selectedIndex;
    var selectedValue = selectedTypeElement.options[selectedIndex].value;
    var selectedTypeDisplay = document.getElementById("selectedTypeDisplay");
    if (selectedTypeDisplay) {
        selectedTypeDisplay.innerText = "선택된 Type : " + selectedValue;
    } else {
        console.error("'selectedTypeDisplay' not found.");
    }
}
// 페이지 로드 시 초기화
window.onload = updateSelectedType;