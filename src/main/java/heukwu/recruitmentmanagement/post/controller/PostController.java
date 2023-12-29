package heukwu.recruitmentmanagement.post.controller;

import heukwu.recruitmentmanagement.post.service.PostDto;
import heukwu.recruitmentmanagement.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/post")
    public List<PostResponse.Get> getAllPost() {
        List<PostDto.Res> postList = postService.getAllPost();

        return postList.stream()
                .map(PostResponse.Get::from)
                .collect(Collectors.toList());
    }

    @GetMapping("/post/{postId}")
    public PostResponse.Get getPost(@PathVariable Long postId) {
        PostDto.Res dto = postService.getPost(postId);

        return PostResponse.Get.from(dto);
    }

    @PostMapping("/post/{companyId}")
    public PostResponse.Create createPost(
            @PathVariable Long companyId,
            @RequestBody PostRequest.Create request
    ) {
        PostDto.Req requestDto = request.toDomain();
        PostDto.Res responseDto = postService.createPost(companyId, requestDto);

        return PostResponse.Create.from(responseDto);
    }

    @PutMapping("/post/editing/{postId}")
    public PostResponse.Edit editPost(@PathVariable Long postId, @RequestBody PostRequest.Edit request) {
        PostDto.Req requestDto = request.toDomain();
        PostDto.Res responseDto = postService.editPost(postId, requestDto);

        return PostResponse.Edit.from(responseDto);
    }

    @DeleteMapping("/post/deleting/{postId}")
    public void deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
    }
}
