document.addEventListener("DOMContentLoaded", () => {
  const orderBtn = document.getElementById("orderBtn");
  const cartBtn = document.getElementById("cartBtn");

  // ✅ 주문하기
  orderBtn.addEventListener("click", () => {
    const menuId = document.getElementById("menuId").value;
    const quantity = document.getElementById("quantity").value;
    const notes = document.getElementById("notes").value;

    fetch("/order/add", {
      method: "POST",
      headers: { "Content-Type": "application/x-www-form-urlencoded" },
      body: new URLSearchParams({ menuId, quantity, notes })
    })
      .then((response) => {
        if (!response.ok) {
          return response.text().then((text) => {
            throw new Error(`주문 실패: ${text}`);
          });
        }
        return response.text();
      })
      .then(() => {
        alert("✅ 주문이 완료되었습니다!");
        window.location.href = "/menu/list"; // 주문 후 메뉴 목록 이동
      })
      .catch((err) => {
        console.error(err);
        alert("❌ 주문 처리 중 오류 발생!");
      });
  });

  // 🛒 장바구니 추가
  cartBtn.addEventListener("click", () => {
    const menuId = document.getElementById("menuId").value;
    const quantity = document.getElementById("quantity").value;
    const notes = document.getElementById("notes").value;

    // 📌 cartId는 나중에 세션/유저 기반으로 변경해야 함
    const cartId = 1;

    fetch("/order/cartadd", {
      method: "POST",
      headers: { "Content-Type": "application/x-www-form-urlencoded" },
      body: new URLSearchParams({ menuId, cartId, quantity, notes })
    })
      .then((response) => {
        if (!response.ok) {
          return response.text().then((text) => {
            throw new Error(`장바구니 추가 실패: ${text}`);
          });
        }
        return response.text();
      })
      .then((msg) => {
        alert(msg || "🛒 장바구니에 추가되었습니다!");
        window.location.href = "/menu/list"; // ✅ 장바구니 추가 후 메뉴 목록으로 이동
      })
      .catch((err) => {
        console.error(err);
        alert("❌ 장바구니 추가 중 오류 발생!");
      });
  });
});
