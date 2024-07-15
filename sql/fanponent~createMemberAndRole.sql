-- ������ ���� ( ���� ���̵� )
CREATE SEQUENCE user_id_seq
    START WITH 1
    INCREMENT BY 1
    NOCACHE;

drop TABLE MEMBER;
--TRUNCATE TABLE MEMBER;
--DROP TABLE ROLE;

-- create member table
CREATE TABLE MEMBER (
    member_id NUMBER(10) DEFAULT user_id_seq.NEXTVAL PRIMARY KEY,
    name VARCHAR2(100) NOT NULL,
    email VARCHAR2(255) NOT NULL,
    password VARCHAR2(255) NOT NULL,
    createdAt TIMESTAMP,
    updatedAt TIMESTAMP,
    role NUMBER(10),
    profilePictureUrl VARCHAR2(500)
);

ALTER TABLE MEMBER
RENAME COLUMN name TO member_name;

ALTER TABLE MEMBER
MODIFY (MEMBER_ID NUMBER(10,0) NOT NULL);

-- unique
ALTER TABLE MEMBER
ADD CONSTRAINT unique_member_name UNIQUE (MEMBER_NAME);


-- MEMBER ���̺� ���� ������ ����
INSERT INTO MEMBER (name, email, password, createdAt, updatedAt, role)
VALUES ('member_3', 'user1@example.com', 'mypassword', SYSTIMESTAMP, SYSTIMESTAMP, 2);

--commit;

CREATE TABLE ROLE (
    role_id NUMBER(10) PRIMARY KEY,
    role_name VARCHAR2(100) NOT NULL
);

-- MEMBER ���̺� �ܷ� Ű ����
ALTER TABLE MEMBER
ADD CONSTRAINT fk_user_role
FOREIGN KEY (role)
REFERENCES ROLE(role_id);

-- ���� ������ �߰� (������, �����)
INSERT INTO ROLE (role_id, role_name) VALUES (1, '������');
INSERT INTO ROLE (role_id, role_name) VALUES (2, '�����');

--commit;