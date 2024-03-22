$(document).ready(() => {
    let page = 0; // 초기 페이지 번호
    const size = 12; // 페이지 당 영화 수

    $('#loadMoreMovies').click(() => {
        page++; // 다음 페이지 번호 증가
        loadMovies(page, size);
    });

    function loadMovies(page, size) {
        // AJAX 요청을 통해 서버로부터 JSON 데이터 받아오기
        $.ajax({
            url: `${contextPath}movie/movieList.do`,
            type: 'GET',
            dataType: 'json',
            data: {
                page: page,
                size: size
            },
            success: function(response) {
                // 영화 데이터를 화면에 카드 형태로 렌더링
                response.movies.forEach(movie => {
                    const movieCardHtml = `
                        <div class="movie-card">
                            <img src="${movie.poster}" alt="${movie.title}">
                            <div class="movie-info">
                                <h3>${movie.title}</h3>
                                <p>예매율: ${movie.advanceReservation}%</p>
                                <p>개봉일: ${movie.releaseDate}</p>
                                <button>예매하기</button>
                            </div>
                        </div>`;
                    $('#moviesContainer').append(movieCardHtml);
                });

                if (page >= response.totalPages - 1) {
                    $('#loadMoreMovies').hide(); // 마지막 페이지에 도달하면 더보기 버튼 숨김
                }
            },
            error: function(xhr, status, error) {
                console.error('Error fetching movies:', error);
            }
        });
    }

    // 초기 로드 시 첫 번째 페이지의 영화를 불러오기
    loadMovies(page, size);
});