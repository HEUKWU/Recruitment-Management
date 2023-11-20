package heukwu.recruitmentmanagement.controller;

import heukwu.recruitmentmanagement.dto.PostDto;
import heukwu.recruitmentmanagement.entity.Post;
import heukwu.recruitmentmanagement.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/post/{companyId}")
    public ResponseEntity<?> createPost(@PathVariable Long companyId, @RequestBody PostDto.Req requestDto) {
        postService.createPost(companyId, requestDto);

        return ResponseEntity.ok("성공");
    }

    @PutMapping("/post/editing/{postId}")
    public ResponseEntity<PostDto.Res> editPost(@PathVariable Long postId, @RequestBody PostDto.Req editDto) {
        PostDto.Res response = postService.editPost(postId, editDto);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/post/deleting/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);

        return ResponseEntity.ok("삭제 성공");
    }

    @GetMapping("/post")
    public ResponseEntity<List<PostDto.Res>> getAllPost() {
        List<PostDto.Res> allPost = postService.getAllPost();

        return ResponseEntity.ok(allPost);
    }

}