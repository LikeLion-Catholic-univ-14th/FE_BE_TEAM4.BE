package com.example.bf_be.service;

import com.example.bf_be.dto.LetterDTO;
import com.example.bf_be.entity.Letter;
import com.example.bf_be.entity.Genre;
import com.example.bf_be.repository.LetterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LetterService {

    private final LetterRepository letterRepository;

    // 1. 편지 전체 조회
    public List<LetterDTO.Response> findAll() {
        return letterRepository.findAll().stream()
                .map(LetterDTO.Response::new)
                .collect(Collectors.toList());
    }

    // 2. 편지 상세 조회
    public LetterDTO.Response findById(Long id) {
        Letter letter = letterRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("편지가 존재하지 않습니다. id=" + id));
        return new LetterDTO.Response(letter);
    }

    // 3. 편지 작성
    @Transactional
    public LetterDTO.Response save(LetterDTO.CreateRequest request) {
        Genre genreEnum = Genre.valueOf(request.getGenre());

        Letter letter = new Letter(
                request.getSender(),
                request.getReceiver(),
                request.getContent(),
                genreEnum
        );
        Letter savedLetter = letterRepository.save(letter);
        return new LetterDTO.Response(savedLetter);
    }

    // 4. 편지 수정
    @Transactional
    public LetterDTO.Response update(Long id, LetterDTO.UpdateRequest request) {
        Letter letter = letterRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("편지가 존재하지 않습니다. id=" + id));

        letter.update(request.getContent());
        return new LetterDTO.Response(letter);
    }

    // 5. 편지 삭제
    @Transactional
    public void delete(Long id) {
        Letter letter = letterRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("편지가 존재하지 않습니다. id=" + id));
        letterRepository.delete(letter);
    }
}