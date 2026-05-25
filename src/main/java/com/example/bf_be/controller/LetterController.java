package com.example.bf_be.controller;

import com.example.bf_be.dto.LetterDTO;
import com.example.bf_be.service.LetterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/letters")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class LetterController {

    private final LetterService letterService;

    // 1. 편지 전체 조회
    @GetMapping
    public ResponseEntity<List<LetterDTO.Response>> getAllLetters() {
        return ResponseEntity.ok(letterService.findAll());
    }

    // 2. 편지 상세 조회
    @GetMapping("/{id}")
    public ResponseEntity<LetterDTO.Response> getLetterById(@PathVariable Long id) {
        return ResponseEntity.ok(letterService.findById(id));
    }

    // 3. 편지 작성
    @PostMapping
    public ResponseEntity<LetterDTO.Response> createLetter(@RequestBody LetterDTO.CreateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(letterService.save(request));
    }

    // 4. 편지 수정
    @PutMapping("/{id}")
    public ResponseEntity<LetterDTO.Response> updateLetter(
            @PathVariable Long id,
            @RequestBody LetterDTO.UpdateRequest request) {
        return ResponseEntity.ok(letterService.update(id, request));
    }

    // 5. 편지 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLetter(@PathVariable Long id) {
        letterService.delete(id);
        return ResponseEntity.noContent().build();
    }
}