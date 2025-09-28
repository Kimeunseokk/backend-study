document.addEventListener('DOMContentLoaded', () => {
    const logoutBtn = document.getElementById('logoutBtn');
    if(logoutBtn) {
        logoutBtn.addEventListener('click', () => {
            fetch('/login/logout', { method: 'GET' })
            .then(response => {
                if(response.ok) {
                    window.location.reload(); // 로그아웃 후 새로고침
                } else {
                    alert('로그아웃 실패');
                }
            })
            .catch(err => {
                console.error(err);
                alert('서버 오류');
            });
        });
    }
});
