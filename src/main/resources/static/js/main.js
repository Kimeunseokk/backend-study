document.addEventListener("DOMContentLoaded", () => {
    const orderBtn = document.getElementById("orderBtn");
    orderBtn.addEventListener("click", () => {
        // 주문 버튼 클릭 시 메뉴 페이지로 이동
        window.location.href = "/menu";
    });
});
