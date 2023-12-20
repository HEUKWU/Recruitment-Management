package heukwu.recruitmentmanagement.controller;

import heukwu.recruitmentmanagement.dto.PostDto;
import heukwu.recruitmentmanagement.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/post/{companyId}")
    public PostDto.Res createPost(@PathVariable Long companyId, @RequestBody PostDto.Req requestDto) {
        return postService.createPost(companyId, requestDto);
    }

    @PutMapping("/post/editing/{postId}")
    public PostDto.Res editPost(@PathVariable Long postId, @RequestBody PostDto.Req editDto) {
        return postService.editPost(postId, editDto);
    }

    @DeleteMapping("/post/deleting/{postId}")
    public void deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
    }

    @GetMapping("/post")
    public List<PostDto.Res> getAllPost()  {
        return postService.getAllPost();
    }

    @GetMapping("/post/{postId}")
    public PostDto.Res getPost(@PathVariable Long postId) {
        return postService.getPost(postId);
    }
}
