package heukwu.recruitmentmanagement.post.service;

import heukwu.recruitmentmanagement.company.repository.Company;
import heukwu.recruitmentmanagement.post.repository.Post;
import heukwu.recruitmentmanagement.company.repository.CompanyRepository;
import heukwu.recruitmentmanagement.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final CompanyRepository companyRepository;

    public List<PostDto.Res> getAllPost() {
        List<Post> postList = postRepository.findAll();

        List<PostDto.Res> postDtoList = new ArrayList<>();
        postList.forEach(post -> postDtoList.add(PostDto.Res.of(post)));

        return postDtoList;
    }

    public PostDto.Res getPost(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(IllegalArgumentException::new);

        return PostDto.Res.getDetailPost(post);
    }

    public PostDto.Res createPost(Long companyId, PostDto.Req requestDto) {
        Company company = companyRepository.findById(companyId).orElseThrow(IllegalArgumentException::new);
        Post post = Post.of(company, requestDto);
        postRepository.save(post);

        return PostDto.Res.of(post);
    }

    @Transactional
    public PostDto.Res editPost(Long postId, PostDto.Req editDto) {
        Post post = postRepository.findById(postId).orElseThrow(IllegalArgumentException::new);
        Post editPost = post.edit(editDto);

        return PostDto.Res.of(editPost);
    }

    public void deletePost(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(IllegalArgumentException::new);
        postRepository.delete(post);
    }
}
