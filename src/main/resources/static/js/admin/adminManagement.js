const approvalBtns = document.querySelectorAll('.approval');

approvalBtns.forEach((approvalBtn) => {
    approvalBtn.addEventListener('click', () => {
        const scheduleId = approvalBtn.getAttribute('data-schedule-id');
        const approvalStatus = approvalBtn.getAttribute('data-approval-status');
        console.log(approvalStatus);
        if (approvalStatus === 'Y') {
            alert("이미 승인된 상영입니다.");
            return;
        }

        const adminConfirm = confirm("상영을 승인하시겠습니까?");
        // console.log(approvalBtn.previousElementSibling.textContent);

        if (adminConfirm) {
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
                    if (response.approved === 'Y') {
                        approvalBtn.textContent = 'Y';
                        approvalBtn.setAttribute('data-approval-status', 'Y');
                    } else {
                        console.log("승인 상태 업데이트에 실패했습니다.");
                    }
                },
                error(error) {
                    console.log("승인 실패 :" + error);
                }
            });
        }
    });
});