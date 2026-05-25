package com.example.bf_be.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Letter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sender;
    private String receiver;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Enumerated(EnumType.STRING)
    private Genre genre;

    public Letter(String sender, String receiver, String content, Genre genre) {
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
        this.genre = genre;
    }

    public void update(String content) {
        this.content = content;
    }
}