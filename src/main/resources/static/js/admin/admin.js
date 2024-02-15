document.querySelectorAll("tr[data-admin-id]").forEach((tr) => {
    tr.addEventListener('click', (e) => {
        const td = e.target;
        const tr = td.parentElement;
        const {adminId} = tr.dataset;
        location.href = `${contextPath}admin/memberList.do?id=${adminId}`;
    });
});