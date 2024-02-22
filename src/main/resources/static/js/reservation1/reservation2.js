
    // let seats = document.querySelector(".all-seats");
    // console.log("랜덤 좌석을 만듭니다.");
    // for (var i = 0; i < 59; i++) {
    //     let randint = Math.floor(Math.random() * 2);
    //     let booked = randint === 1 ? "booked" : "";
    //     seats.insertAdjacentHTML(
    //         "beforeend",
    //         '<input type="checkbox" name="tickets" id="s' +
    //         (i + 2) +
    //         '" /><label for="s' +
    //         (i + 2) +
    //         '" class="seat ' +
    //         booked +
    //         '"></label>'
    //     );
    // }

    let seats = document.querySelector(".all-seats");
    console.log("랜덤 좌석을 만듭니다.");
    for (var i = 0; i < 59; i++) {
    let randint = Math.floor(Math.random() * 2);
    let booked = randint === 1 ? "booked" : "";
    let seatElement = document.createElement('input');
    seatElement.type = 'checkbox';
    seatElement.name = 'tickets';
    seatElement.id = 's' + (i + 2);
    seatElement.disabled = booked === 'booked'; // 예약된 좌석은 비활성화
    let labelElement = document.createElement('label');
    labelElement.htmlFor = 's' + (i + 2);
    labelElement.className = 'seat ' + booked;
    seats.appendChild(seatElement);
    seats.appendChild(labelElement);
}

    let tickets = seats.querySelectorAll("input");
    tickets.forEach((ticket) => {
    ticket.addEventListener("change", () => {
    let amount = document.querySelector(".amount").innerHTML;
    let count = document.querySelector(".count").innerHTML;
    amount = Number(amount);
    count = Number(count);

    if (ticket.checked) {
    console.log("좌석을 클릭했습니다~");
    count += 1;
    amount += 200;

} else {
    console.log("좌석을 클릭을 해제 했습니다~");
    count -= 1;
    amount -= 200;
}
    document.querySelector(".amount").innerHTML = amount;
    document.querySelector(".count").innerHTML = count;
});
});
