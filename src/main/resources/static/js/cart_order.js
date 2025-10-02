document.addEventListener("DOMContentLoaded", () => {
    const orderBtn = document.getElementById("orderBtn");

    orderBtn.addEventListener("click", () => {
        fetch("/cart/order", {
            method: "POST"
        })
        .then(response => {
            if (!response.ok) throw new Error("주문 실패");
            return response.text();
        })
        .then(() => {
            alert("장바구니 주문이 완료되었습니다!");
            window.location.href = "/menu/list";
        })
        .catch(err => {
            console.error("주문 중 오류:", err);
            alert("주문 중 오류가 발생했습니다.");
        });
    });
});
