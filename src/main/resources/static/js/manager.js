document.addEventListener('DOMContentLoaded', () => {

    // ================== 메뉴 관리 ==================
    // 메뉴 수정
    document.querySelectorAll('.updateMenuBtn').forEach(button => {
        button.addEventListener('click', (e) => {
            const id = e.target.dataset.id;
            const row = document.getElementById(`menu-${id}`);
            
            const data = {
                name: row.querySelector('.name').value,
                price: row.querySelector('.price').value,
                num: row.querySelector('.num').value,
                soldOut: row.querySelector('.soldOut').checked,
                imgpath: row.querySelector('.imgpath').value
            };

            fetch(`/manager/menu/update/${id}`, {
                method: 'POST',
                headers: {'Content-Type': 'application/x-www-form-urlencoded'},
                body: new URLSearchParams(data)
            }).then(() => {
                alert('메뉴 수정 완료!');
                location.reload();
            }).catch(err => console.error(err));
        });
    });

    // 메뉴 삭제
    document.querySelectorAll('.deleteMenuBtn').forEach(button => {
        button.addEventListener('click', (e) => {
            const id = e.target.dataset.id;

            fetch(`/manager/menu/delete/${id}`, {
                method: 'GET'
            }).then(() => {
                alert('메뉴 삭제 완료!');
                location.reload();
            }).catch(err => console.error(err));
        });
    });

    // ================== 주문 내역 관리 ==================
    // 주문 수정
    document.querySelectorAll('.updateOrderBtn').forEach(button => {
        button.addEventListener('click', (e) => {
            const id = e.target.dataset.id;
            const row = document.getElementById(`order-${id}`);

            const data = {
                quantity: row.querySelector('.quantity').value,
                notes: row.querySelector('.notes').value,
                status: row.querySelector('.status').value
            };

            fetch(`/manager/order/update/${id}`, {
                method: 'POST',
                headers: {'Content-Type': 'application/x-www-form-urlencoded'},
                body: new URLSearchParams(data)
            }).then(() => {
                alert('주문 상태 변경 완료!');
                location.reload();
            }).catch(err => console.error(err));
        });
    });

    // 주문 삭제
    document.querySelectorAll('.deleteOrderBtn').forEach(button => {
        button.addEventListener('click', (e) => {
            const id = e.target.dataset.id;

            fetch(`/manager/order/delete/${id}`, {
                method: 'GET'
            }).then(() => {
                alert('주문 내역 삭제 완료!');
                location.reload();
            }).catch(err => console.error(err));
        });
    });

});
