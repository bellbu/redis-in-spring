package com.example.redisinspring;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Table(name = "boards") // boards 테이블에 매핑
public class Board {

    @Id // 기본키 설정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // id 자동 생성
    private Long id;

    private String title;

    private String content;

    @CreatedDate // 엔티티가 생성될 때 자동으로 현재 날짜와 시간을 저장
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") // 객체를 JSON으로 변환할 때 날짜와 시간을 표현하는 방식을 지정
    @JsonSerialize(using = LocalDateTimeSerializer.class) // LocalDateTime을 JSON 형식으로 직렬화
    @JsonDeserialize(using = LocalDateTimeDeserializer.class) // LocalDateTime을 Java 객체로 역직렬화
    private LocalDateTime createdAt;

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
