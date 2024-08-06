document.addEventListener('DOMContentLoaded', function() {
    const favoriteIcons = document.querySelectorAll('.favorite-icon'); // 모든 좋아요 아이콘 선택

    favoriteIcons.forEach(favoriteIcon => {
        const postFooter = favoriteIcon.closest('.fan-post-card__footer');
        const postId = postFooter.getAttribute('data-post-id');
        const memberId = postFooter.getAttribute('data-member-id');
        const csrfTokenElement = postFooter.querySelector('.csrfToken'); // 각 카드의 CSRF 토큰 요소 가져오기

        if (csrfTokenElement) { // 요소가 존재하는지 확인
            const csrfToken = csrfTokenElement.value;

            favoriteIcon.addEventListener('click', function(event) {
                event.preventDefault(); // 기본 폼 제출 동작 방지
                console.log('Favorite icon clicked'); // 디버깅용 로그

                fetch(`/likes/${postId}`, {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                        'X-CSRF-TOKEN': csrfToken // CSRF 토큰 추가
                    },
                    body: JSON.stringify({ memberId: memberId })
                })
                .then(response => {
                    if (!response.ok) {
                        throw new Error('Network response was not ok ' + response.statusText);
                    }
                    return response.json();
                })
                .then(data => {
                    // 좋아요 수 업데이트
                    const likeCountElement = postFooter.querySelector('.like-count');
                    likeCountElement.textContent = data.likeCount;
                    console.log('Like count updated:', data.likeCount); // 디버깅용 로그
                })
                .catch(error => console.error('Error:', error));
            });
        } else {
            console.error('CSRF token element not found');
        }
    });
});
