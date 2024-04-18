// let priceText = document.querySelector('.order-price').textContent;
// console.log(priceText);
// let amount = parseInt(priceText.replace(/[^0-9]/g, ''));
// console.log(amount);

// // 페이지 로드 완료 후 실행됩니다.
// document.addEventListener('DOMContentLoaded', function() {
//     // '구매하기' 버튼에 해당하는 클래스 이름으로 모든 버튼을 선택합니다.
//     const purchaseButtons = document.querySelectorAll('.purchase-button');
//
//     // 각 '구매하기' 버튼에 대해 함수를 실행합니다.
//     purchaseButtons.forEach(function(currentButton) { // 여기서 currentButton이 각 버튼을 의미합니다.
//         currentButton.addEventListener('click', function(event) {
//             // 현재 클릭된 버튼(currentButton)과 관련된 .order-price 요소를 찾습니다.
//             const priceElement = currentButton.closest('.flex.flex-col').querySelector('.order-price');
//             const priceText = priceElement.textContent.replace(/[^0-9]/g, '');
//             const price = parseInt(priceText, 10); // 문자열을 숫자로 변환
//             console.log(price);
//
//             // 여기에서 id, name, phone을 얻어와야 하는데, 이는 currentButton의 데이터 속성에서 가져올 수 있습니다.
//             const id = currentButton.getAttribute('data-memberId');
//             const name = currentButton.getAttribute('data-username');
//             const phone = currentButton.getAttribute('data-memberPhone');
//
//             // requestPay 함수를 호출하고, 필요한 모든 데이터를 인자로 넘깁니다.
//             // requestPay(id, name, phone, price);
//         });
//     });
// });
const Status = {
    CONFIRM: 'confirm',
    CANCEL: 'cancel'
};

IMP.init("imp32105587"); // 가맹점코드 - 고정값

const buttons = document.querySelectorAll('.purchase-button');

buttons.forEach(function (currentButton) {
    currentButton.addEventListener('click', function (event) {
        IMP.init("imp32105587"); // 가맹점코드 - 고정값
        // 버튼에 가장 가까운 .flex.flex-col 요소를 찾고, 그 내부에서 .order-price를 찾습니다.
        let priceElement = currentButton.closest('.merchandise').querySelector('.order-price');
        let priceText = priceElement.textContent;
        let amount = parseInt(priceText.replace(/[^0-9]/g, ''), 10);

        const id = currentButton.getAttribute('data-memberId');
        const name = currentButton.getAttribute('data-username');
        const phone = currentButton.getAttribute('data-memberPhone');
        const storeId = currentButton.getAttribute('data-storeId');

        // requestPay 함수를 호출하고, 필요한 모든 데이터를 인자로 넘깁니다.
        requestPay(id, name, phone, amount, storeId);
    });
});

const requestPay_notworking = () => {
    console.log("로그인을 하세용");
};

const requestPay = (id, name, phone, amount, storeId) => {
    console.log("구매하기")
    console.log("회원 정보: ", id, name, phone, amount, storeId);

    let pay_uid = new Date().getTime().toString();
    let boxId = "box" + new Date().getTime().toString().substring(8);
    IMP.request_pay({
        pg: "html5_inicis", // PG사코드 - 고정값
        pay_method: "card", // 결제방식 - 고정값
        merchant_uid: "order" + pay_uid, // UTC , 결제 API 주문번호 고유값
        name: "Boot-Box 상품 구매", // 고정값[상품명]
        amount: amount, // 결제 금액
        buyer_name: name, //회원명
        buyer_tel: phone, // 회원연락처
    },
    function (res) {
        console.log("res::::",res);
        // console.log("Payment success!");
        console.log("Payment ID : " + res.imp_uid);
        console.log("Order ID : " + res.merchant_uid);
        console.log("Payment Amount : " + res.paid_amount);
        let boxId = "box" + new Date().getTime().toString().substring(8);
        let orderId = "order" + new Date().getTime().toString();
        console.log("생성된 box id:",boxId);

        if (res.success) {
            console.log("결제가 성공적으로 요청되었습니다!", res.success);

            //0224 결제관련 정보 객체 order_pay에 저장할..
            let sendData03 = {
                id: res.merchant_uid,//orderId,
                memberId: id,
                imp: res.imp_uid,
                inicis: "html5_inicis",
                amount: res.pay_method, //결제 방식
                price: amount,
                count: 1,
                payTime: new Date(),
                payStatus: Status.CONFIRM

            };

            //0224 객체 합치기
            let combinedData = {
                productOrderPayDto: sendData03,
                storeId: storeId
            };

            $.ajax({
                headers: {
                    [csrfHeaderName]: csrfToken
                },
                type: "POST",
                url: `${contextPath}store/storePayment`, // 전송할 URL
                contentType: "application/json", // 전송하는 데이터의 타입
                data: JSON.stringify(combinedData), // JSON 데이터로 변환하여 전송

                success: function (response) {
                    // 요청이 성공했을 때 처리할 로직
                    console.log("응답:", response);

                    //window.location.href = `${contextPath}bootbox/`;
                    window.location.href = `${contextPath}`; // 리다이렉트할 URL을 지정합니다.
                    //member/memberReservation.do?id=1
                },
                error: function (xhr, status, error) {
                    // 요청이 실패했을 때 처리할 로직
                    // alert("에러가 발생했습니다.:", status + "/" + error);
                    console.error("에러가 발생했습니다.:", status + "/" + error);
                }
            });
        } else {
            // alert(res.error_msg);
            console.error(res.error_msg);
        }
    });
}