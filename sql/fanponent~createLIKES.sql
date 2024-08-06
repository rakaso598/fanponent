CREATE TABLE LIKES (
    id NUMBER PRIMARY KEY,
    post_id NUMBER,
    member_id NUMBER,
    FOREIGN KEY (post_id) REFERENCES POST(post_id),
    FOREIGN KEY (member_id) REFERENCES MEMBER(member_id)
);

ALTER TABLE POST ADD like_count NUMBER DEFAULT 0;


