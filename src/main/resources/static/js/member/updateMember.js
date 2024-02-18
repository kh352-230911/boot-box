document.querySelector("#passwordConfirmation").onblur = (e) => {
    const password = document.querySelector("#memberPwd");
    const passwordConfirmation = e.target;
    if (password.value !== passwordConfirmation.value) {
        alert("패스워드가 일치하지 않습니다.");
        password.select();
    }
};

document.memberCreateFrm.onsubmit = (e) => {
    const frm = e.target;
    const username = frm.memberLoginId;
    const password = frm.memberPwd;
    const idDuplicateCheck = frm.idDuplicateCheck;
    const passwordConfirmation = frm.passwordConfirmation;
    const email = frm.memberEmail;
    const name = frm.memberName;

    if (!/^\w{4,}$/.test(username.value)) {
        alert("아이디는 영문자, 숫자, _ 4자리이상 입력하세요.");
        username.focus();
        return false;
    }
    if(idDuplicateCheck.value == 0) {
        alert("사용 가능 한 아이디를 입력해주세요.")
        memberLoginId.select();
        return false;
    }

    if (password.value !== passwordConfirmation.value) {
        alert("패스워드가 일치하지 않습니다.");
        password.select();
        return false;
    }
    if (!/^[\w가-힣]{2,}$/.test(name.value)) {
        alert("이름을 2글자 이상 입력하세요.");
        name.select();
        return false;
    }
    if (!/^[\w]{4,}@[\w]+(\.[\w]+){1,3}$/.test(email.value)) {
        alert("유효한 이메일을 작성해주세요.");
        email.select();
        return false;
    }
};
