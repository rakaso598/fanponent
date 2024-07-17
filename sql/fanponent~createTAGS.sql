--ALTER TABLE TAGS RENAME TO TAG;

--TRUNCATE TABLE TAG;
-- 태그 테이블 생성
CREATE TABLE TAG (
    TAG_ID NUMBER PRIMARY KEY,
    TAG_NAME VARCHAR2(10) NOT NULL
);
ALTER TABLE TAG MODIFY TAG_NAME VARCHAR2(10);


-- 유니크 제약조건
--ALTER TABLE TAG
--ADD CONSTRAINT TAG_NAME_UNIQUE UNIQUE (TAG_NAME);


-- 게시물과 태그 간의 관계를 맺기 위한 중간 테이블
--CREATE TABLE POST_TAG (
--    POST_ID NUMBER,
--    TAG_ID NUMBER,
--    PRIMARY KEY (POST_ID, TAG_ID),
--    FOREIGN KEY (POST_ID) REFERENCES POST (POST_ID),
--    FOREIGN KEY (TAG_ID) REFERENCES TAGS (TAG_ID)
--);

-- TAG
ALTER TABLE TAG
MODIFY TAG_ID NUMBER GENERATED ALWAYS AS IDENTITY;

ALTER TABLE TAG
DROP COLUMN TAG_ID;

-- ID 시퀀스
CREATE SEQUENCE tag_id_seq
  START WITH 1
  INCREMENT BY 1
  NOCACHE
  NOCYCLE;

-- 시퀀스 적용
ALTER TABLE TAG
MODIFY TAG_ID DEFAULT tag_id_seq.NEXTVAL;

INSERT INTO TAG(TAG_ID,TAG_NAME) VALUES (tag_id_seq.NEXTVAL,'다람쥐');
