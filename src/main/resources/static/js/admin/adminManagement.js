const approvalBtns = document.querySelectorAll('.approval');

approvalBtns.forEach((approvalBtn) => {
    approvalBtn.addEventListener('click', () => {
        const adminConfirm = confirm("상영을 승인하시겠습니까?");
        // console.log(approvalBtn.previousElementSibling.textContent);

        if (adminConfirm) {
            const scheduleId = approvalBtn.getAttribute('data-schedule-id');

            console.log(scheduleId);
            $.ajax({
                headers: {
                    [csrfHeaderName]: csrfToken
                },
                type: "POST",
                url: `${contextPath}admin/scheduleApprove`, // 전송할 URL
                data:  {
                    scheduleId: scheduleId, // 서버로 전송할 데이터
                    approve: true
                },
                success(response) {
                    approvalBtn.textContent = 'Y';
                },
                error(error) {
                    console.log("승인 실패 :" + error);
                }
            });
        }
    });
});