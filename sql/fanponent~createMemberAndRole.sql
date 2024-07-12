-- 시퀀스 생성 ( 유저 아이디 )
CREATE SEQUENCE user_id_seq
    START WITH 1
    INCREMENT BY 1
    NOCACHE;

--DROP TABLE MEMBER;
--TRUNCATE TABLE MEMBER;
--DROP TABLE ROLE;

-- 유저 테이블 생성
CREATE TABLE MEMBER (
    id NUMBER(10) DEFAULT user_id_seq.NEXTVAL PRIMARY KEY,
    name VARCHAR2(100) NOT NULL,
    email VARCHAR2(255) NOT NULL,
    password VARCHAR2(255) NOT NULL,
    createdAt TIMESTAMP,
    updatedAt TIMESTAMP,
    role NUMBER(10),
    profilePictureUrl VARCHAR2(500)
);

-- MEMBER 테이블에 예제 데이터 삽입
INSERT INTO MEMBER (name, email, password, createdAt, updatedAt, role)
VALUES ('유저3', 'user1@example.com', 'mypassword', SYSTIMESTAMP, SYSTIMESTAMP, 2);

CREATE TABLE ROLE (
    role_id NUMBER(10) PRIMARY KEY,
    role_name VARCHAR2(100) NOT NULL
);

-- MEMBER 테이블에 외래 키 설정
ALTER TABLE MEMBER
ADD CONSTRAINT fk_user_role
FOREIGN KEY (role)
REFERENCES ROLE(role_id);

-- 예시 데이터 추가 (관리자, 사용자)
INSERT INTO ROLE (role_id, role_name) VALUES (1, '관리자');
INSERT INTO ROLE (role_id, role_name) VALUES (2, '사용자');

commit;
