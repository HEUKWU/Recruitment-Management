package heukwu.recruitmentmanagement.controller;

import heukwu.recruitmentmanagement.dto.PostDto;
import heukwu.recruitmentmanagement.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/post/{companyId}")
    public ResponseEntity<?> createPost(@PathVariable Long companyId, PostDto.Req requestDto) {
        postService.createPost(companyId, requestDto);

        return ResponseEntity.ok().build();
    }
}
