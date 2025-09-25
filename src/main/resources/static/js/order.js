document.addEventListener("DOMContentLoaded", () => {
  const orderBtn = document.getElementById("orderBtn");

  orderBtn.addEventListener("click", () => {
    const menuId = document.getElementById("menuId").value;
    const quantity = document.getElementById("quantity").value;
    const notes = document.getElementById("notes").value;

    const data = new URLSearchParams();
    data.append("menu.id", menuId);
    data.append("quantity", quantity);
    data.append("notes", notes);

    fetch('/order/add', {
    method: 'POST',
    headers: {'Content-Type': 'application/x-www-form-urlencoded'},
    body: new URLSearchParams({ menuId, quantity, notes })
})
      .then((response) => {
        if (!response.ok) {
          return response.text().then((text) => {
            throw new Error(`HTTP ${response.status}: ${text}`);
          });
        }
        return response.text();
      })
      .then(() => {
        alert("주문 완료!");
        window.location.href = "/menu/list"; // 주문 후 메뉴 목록으로 이동
      })
      .catch((err) => {
        console.error("주문 실패:", err);
        alert("주문 중 오류가 발생했습니다. 콘솔 로그를 확인하세요.");
      });
  });
});
