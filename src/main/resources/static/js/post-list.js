//document.addEventListener('DOMContentLoaded', function() {
//    const favoriteIcons = document.querySelectorAll('.favorite-icon'); // 모든 좋아요 아이콘 선택
//
//    favoriteIcons.forEach(favoriteIcon => {
//        const postFooter = favoriteIcon.closest('.fan-post-card__footer');
//        const postId = postFooter.getAttribute('data-post-id');
//        const memberId = postFooter.getAttribute('data-member-id');
//        const csrfTokenElement = postFooter.querySelector('.csrfToken'); // 각 카드의 CSRF 토큰 요소 가져오기
//
//        if (csrfTokenElement) { // 요소가 존재하는지 확인
//            const csrfToken = csrfTokenElement.value;
//
//            favoriteIcon.addEventListener('click', function(event) {
//                event.preventDefault(); // 기본 폼 제출 동작 방지
//                console.log('Favorite icon clicked'); // 디버깅용 로그
//
//                const isLiked = favoriteIcon.getAttribute('data-liked') === 'true';
//                const url = isLiked ? `/likes/unlike/${postId}` : `/likes/like/${postId}`; // 좋아요/좋아요 취소 URL 설정
//
//                fetch(url, {
//                    method: 'POST',
//                    headers: {
//                        'Content-Type': 'application/json',
//                        'X-CSRF-TOKEN': csrfToken // CSRF 토큰 추가
//                    },
//                    body: JSON.stringify({ postId: postId, memberId: memberId }) // postId와 memberId 모두 포함
//                })
//                .then(response => {
//                    if (!response.ok) {
//                        throw new Error('Network response was not ok ' + response.statusText);
//                    }
//                    return response.json();
//                })
//                .then(data => {
//                    // 서버에서 반환된 데이터 형식을 확인하고, likeCount를 올바르게 업데이트
//                    if (data && data.likeCount !== undefined) {
//                        const likeCountElement = postFooter.querySelector('.like-count');
//                        likeCountElement.textContent = data.likeCount;
//                        console.log('Like count updated:', data.likeCount); // 디버깅용 로그
//
//                        // 하트 아이콘 상태 업데이트
//                        if (isLiked) {
//                            favoriteIcon.textContent = 'favorite_border';
//                            favoriteIcon.setAttribute('data-liked', 'false');
//                        } else {
//                            favoriteIcon.textContent = 'favorite';
//                            favoriteIcon.setAttribute('data-liked', 'true');
//                        }
//                    } else {
//                        console.error('Invalid data format:', data);
//                    }
//                })
//                .catch(error => console.error('Error:', error));
//            });
//        } else {
//            console.error('CSRF token element not found');
//        }
//    });
//});
