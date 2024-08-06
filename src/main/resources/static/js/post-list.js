// post-list.js

document.addEventListener('DOMContentLoaded', function() {
    const favoriteIcon = document.getElementById('favorite-icon');
    const likeCountElement = document.getElementById('like-count');
    const postFooter = favoriteIcon.closest('.fan-post-card__footer');
    const postId = postFooter.getAttribute('data-post-id');
    const memberId = postFooter.getAttribute('data-member-id');

    favoriteIcon.addEventListener('click', function() {
        fetch('/likes/like', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ postId: postId, memberId: memberId })
        })
        .then(response => response.json())
        .then(data => {
            // 좋아요 수 업데이트
            likeCountElement.textContent = data.likeCount;
        })
        .catch(error => console.error('Error:', error));
    });
});
