document.addEventListener('DOMContentLoaded', function() {
    const favoriteIcon = document.getElementById('favorite-icon');
    const likeCountElement = document.getElementById('like-count');
    const postFooter = favoriteIcon.closest('.fan-post-card__footer');
    const postId = postFooter.getAttribute('data-post-id');
    const memberId = postFooter.getAttribute('data-member-id');
    const csrfToken = document.getElementById('csrfToken').value; // CSRF 토큰 가져오기

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
            likeCountElement.textContent = data.likeCount;
            console.log('Like count updated:', data.likeCount); // 디버깅용 로그
        })
        .catch(error => console.error('Error:', error));
    });
});
