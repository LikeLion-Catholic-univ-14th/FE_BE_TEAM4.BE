package com.example.bf_be.dto;

import com.example.bf_be.entity.Letter;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class LetterDTO {

    @Getter
    @NoArgsConstructor
    public static class CreateRequest {
        private String sender;
        private String receiver;
        private String content;
        private String genre;
    }

    @Getter
    @NoArgsConstructor
    public static class UpdateRequest {
        private String content;
    }

    @Getter
    public static class Response {
        private Long id;
        private String sender;
        private String receiver;
        private String content;
        private String genre;

        public Response(Letter letter) {
            this.id = letter.getId();
            this.sender = letter.getSender();
            this.receiver = letter.getReceiver();
            this.content = letter.getContent();
            this.genre = letter.getGenre() != null ? letter.getGenre().name() : null;
        }
    }
}