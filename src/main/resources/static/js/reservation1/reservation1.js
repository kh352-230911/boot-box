const movieSelected = document.getElementById('movie');
const seatsSelected = document.getElementById('seatsSelected');
const totalPrice = document.getElementById('totalPrice');
const container = document.querySelector('.container');

const updatePrice = () => {
    const selectedSeats = document.querySelectorAll(
        '.container .seat.selected:not(.occupied)'
    );
    const selectedSeatsCount = selectedSeats.length;

    totalPrice.innerText = selectedSeatsCount * +movieSelected.value;
    seatsSelected.innerText = selectedSeatsCount;
};

const selectSeat = (seat) => {
    seat.classList.toggle('selected');
    updatePrice();
};

movieSelected.addEventListener('change', () => {
    console.log('change');
    updatePrice();
});

container.addEventListener('click', (e) => {
    e.preventDefault();
    if (
        e.target.classList.contains('seat') &&
        !e.target.classList.contains('occupied')
    ) {
        selectSeat(e.target);
    }
});
