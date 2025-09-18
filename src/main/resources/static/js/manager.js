document.addEventListener('DOMContentLoaded', () => {
    // 메뉴 수정
    document.querySelectorAll('.updateBtn').forEach(button => {
        button.addEventListener('click', (e) => {
            const id = e.target.dataset.id;
            const row = document.getElementById(`row-${id}`);
            
            const data = {
                name: row.querySelector('.name').value,
                price: row.querySelector('.price').value,
                num: row.querySelector('.num').value,
                soldOut: row.querySelector('.soldOut').checked,
                imgpath: row.querySelector('.imgpath').value
            };

            fetch(`/manager/update/${id}`, {
                method: 'POST',
                headers: {'Content-Type': 'application/x-www-form-urlencoded'},
                body: new URLSearchParams(data)
            }).then(() => {
                alert('수정 완료!');
                location.reload();
            }).catch(err => console.error(err));
        });
    });

    // 메뉴 삭제
    document.querySelectorAll('.deleteBtn').forEach(button => {
        button.addEventListener('click', (e) => {
            const id = e.target.dataset.id;

            fetch(`/manager/delete/${id}`, {
                method: 'GET'
            }).then(() => {
                alert('삭제 완료!');
                location.reload();
            }).catch(err => console.error(err));
        });
    });
});
