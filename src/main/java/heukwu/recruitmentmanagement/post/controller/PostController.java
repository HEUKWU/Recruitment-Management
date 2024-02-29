package heukwu.recruitmentmanagement.post.controller;

import heukwu.recruitmentmanagement.post.service.Post;
import heukwu.recruitmentmanagement.post.service.PostService;
import heukwu.recruitmentmanagement.post.service.PostWithOtherPosts;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "post", description = "채용 공고")
@RestController
@RequiredArgsConstructor
@Slf4j
public class PostController {

    private final PostService postService;

    @Operation(summary = "채용 공고 검색 조회")
    @GetMapping("/post")
    public List<PostResponse.GetList> getAllPost(PostSearch search, @RequestParam int size, Long cursorId) {
        List<Post> postList = postService.getAllPost(search, size, cursorId);

        return postList.stream()
                .map(PostResponse.GetList::from)
                .collect(Collectors.toList());
    }

    @Operation(summary = "채용 공고 상세 조회")
    @GetMapping("/post/{postId}")
    public PostResponse.Get getPost(@PathVariable Long postId) {
        PostWithOtherPosts post = postService.getPost(postId);

        return PostResponse.Get.from(post);
    }

    @Operation(summary = "채용 공고 생성")
    @PostMapping("/post/{companyId}")
    public PostResponse.Create createPost(
            @PathVariable Long companyId,
            @RequestBody PostRequest.Create request
    ) {
        Post requestDto = request.toDomain();
        Post createdPost = postService.createPost(companyId, requestDto);

        return PostResponse.Create.from(createdPost);
    }

    @Operation(summary = "채용 공고 수정")
    @PutMapping("/post/editing/{postId}")
    public PostResponse.Edit editPost(@PathVariable Long postId, @RequestBody PostRequest.Edit request) {
        Post requestDto = request.toDomain();
        Post responseDto = postService.editPost(postId, requestDto);

        return PostResponse.Edit.from(responseDto);
    }

    @Operation(summary = "채용 공고 삭제")
    @DeleteMapping("/post/deleting/{postId}")
    public void deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
    }
}
