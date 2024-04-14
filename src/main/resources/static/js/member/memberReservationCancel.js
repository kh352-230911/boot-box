//jin 사용자 선택에 따라 예매를 취소하는 함수.
function reservationCancel(reservationId) {
    //alert("취소버튼을누름");
    console.log("취소하려는 예매 ID:", reservationId);
    const result = confirm(reservationId+"번의 예매를 취소하시겠습니까?");
    if (result)
    {
        $.ajax({
            method:"POST",
            url: `${contextPath}reservation/cancelReservation`,
            headers:{
                [csrfHeaderName] : csrfToken
            },
            data:
                {
                    reservationId
                },
            success: function(response) {
                console.log("예매 취소 성공:", response);
                alert("예매가 취소 되었습니다.");
                window.location.reload(); // 페이지 리로드
            },
            error: function(xhr, status, error) {
                console.error("예매 취소 실패:",xhr, status,error);
                // 예매 취소 실패에 대한 처리를 수행할 수 있습니다.
                alert("취소 처리에 실패했습니다. 다시 시도해주세요.");
            }
        });
    }
    //아니오
    else
    {

    }
}