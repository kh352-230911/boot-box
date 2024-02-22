// document.getElementById("askForm").addEventListener("submit", function(event) {
//     // 폼 제출을 방지하여 기본 동작을 막음
//     event.preventDefault();
//
//     // 답변 입력란의 값을 가져옴
//     var answer = document.getElementById("answer").value;
//
//     // 만약 답변이 비어 있지 않다면
//     if (answer.trim() !== "") {
//         // 폼을 제출하여 저장
//         this.submit();
//     } else {
//         // 사용자에게 알림을 표시
//         alert("답변을 입력해주세요.");
//         console.log(answer+ "들었나")
//         return "redirect:/ask/askList.do";
//     }
// });
//
//
// // 서버로부터 답변을 가져오는 함수
// function fetchAnswer() {
//     // 서버에서 데이터를 가져오는 fetch API 사용
//     fetch('/api/answerByAskId/{answerId}') // 123은 askId에 해당하는 값입니다. 실제로는 사용자가 선택한 askId 값이여야 합니다.
//         .then(response => response.json()) // 응답을 JSON 형식으로 파싱
//         .then(data => {
//             // 가져온 데이터를 변수에 할당
//             var answerFromServer = data.answer;
//
//             // 가져온 답변을 텍스트 영역에 표시
//             var answerContainer = document.getElementById('answerContainer');
//             answerContainer.innerHTML = '<textarea id="answer" placeholder="답변을 남겨주세요." disabled>' + answerFromServer + '</textarea>';
//
//             // 만약 답변이 없으면 새로운 답변을 작성할 수 있는 텍스트 영역 생성
//             if (!answerFromServer) {
//                 answerContainer.innerHTML += '<textarea id="answer" placeholder="답변을 남겨주세요." ></textarea>';
//             }
//         })
//         .catch(error => console.error('Error:', error));
// }

// Get the modal
var modal = document.getElementById("myModal");

// Get the button that opens the modal
var btn = document.getElementById("openModalBtn");

// Get the <span> element that closes the modal
var span = document.getElementsByClassName("close")[0];

// When the user clicks the button, open the modal
btn.onclick = function() {
    modal.style.display = "block";
}

// When the user clicks on <span> (x), close the modal
span.onclick = function() {
    modal.style.display = "none";
}

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
}