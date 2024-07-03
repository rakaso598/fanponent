package com.example.fanponent.dto;

import java.time.LocalDateTime;

public class PostDtoImpl implements PostDto {

    Long id = null; // ID는 NUMBER 타입이므로 Long으로 선언
    String title = null; // TITLE은 VARCHAR2 타입이므로 String으로 선언
    String content = null; // CONTENT는 CLOB 타입이므로 String으로 선언
    LocalDateTime createdAt = null; // CREATED_AT은 DATE 타입이므로 LocalDateTime으로 선언
    LocalDateTime updatedAt = null; // UPDATED_AT은 DATE 타입이므로 LocalDateTime으로 선언

}
