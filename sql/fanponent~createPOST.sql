
--drop table POST;

CREATE TABLE post (
    post_id NUMBER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    title VARCHAR2(255) NOT NULL,
    MEMBER_ID NUMBER(10),
    content CLOB,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL
);

ALTER TABLE POST DROP COLUMN MEMBER_NAME;


--TRUNCATE TABLE POST;

-- 제약조건 드랍
ALTER TABLE POST
DROP CONSTRAINT FK_TAG_TAG_NAME;

-- 제약조건 syntax
/* 
ALTER TABLE 현재테이블명
ADD CONSTRAINT 외래키이름 FOREIGN KEY (현재테이블의속성)
REFERENCES 다른테이블명 (다른테이블의유니크속성);
*/
ALTER TABLE POST
ADD CONSTRAINT FK_MEMBER_MEMBER_NAME FOREIGN KEY (MEMBER_NAME)
REFERENCES MEMBER (MEMBER_NAME);

ALTER TABLE POST
ADD CONSTRAINT FK_TAG_TAG_NAME FOREIGN KEY (TAG_NAME)
REFERENCES TAG (TAG_NAME);

--COMMIT;

-- 속성명 변경
/* 
ALTER TABLE 테이블명
RENAME COLUMN 기존속성명 TO 새로운속성명;
*/
ALTER TABLE POST
RENAME COLUMN MEMBER_ID TO MEMBER_NAME;

-- 속성 추가
ALTER TABLE POST ADD MEMBER_ID NUMBER(10,0);

-- 외래키
ALTER TABLE post
ADD CONSTRAINT fk_post_member
FOREIGN KEY (member_id)
REFERENCES member(member_id);



-- 속성 타입 변경
/*
ALTER TABLE 테이블명
MODIFY 컬럼명 새로운데이터타입;
*/

-- 칼럼 유형 변경
ALTER TABLE POST
MODIFY POST_TITLE VARCHAR2(100 BYTE);

--TRUNCATE TABLE POST;

--commit;

truncate table post;

INSERT INTO post (post_title, member_name, post_content, created_at, updated_at, tag_name)
VALUES ('제목_3', 'member_3','내용입니다_3' , SYSTIMESTAMP, SYSTIMESTAMP, '고기');

--commit;



--ALTER TABLE POST
--MODIFY UPDATED_AT NULL;

CREATE SEQUENCE post_seq
    START WITH 1
    INCREMENT BY 1
    NOCACHE;


--
ALTER TABLE post
    MODIFY id DEFAULT post_seq.NEXTVAL;


--
DROP SEQUENCE post_seq;

--commit;