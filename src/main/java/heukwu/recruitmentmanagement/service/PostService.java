package heukwu.recruitmentmanagement.service;

import heukwu.recruitmentmanagement.dto.PostDto;
import heukwu.recruitmentmanagement.entity.Company;
import heukwu.recruitmentmanagement.entity.Post;
import heukwu.recruitmentmanagement.repository.CompanyRepository;
import heukwu.recruitmentmanagement.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final CompanyRepository companyRepository;

    public void createPost(Long companyId, PostDto.Req requestDto) {
        Company company = companyRepository.findById(companyId).orElseThrow(IllegalArgumentException::new);
        Post post = Post.of(company, requestDto);
        postRepository.save(post);
    }

    @Transactional
    public PostDto.Res editPost(Long postId, PostDto.Req editDto) {
        Post post = postRepository.findById(postId).orElseThrow(IllegalArgumentException::new);
        Post editPost = post.edit(editDto);

        return PostDto.Res.of(post);
    }
}
