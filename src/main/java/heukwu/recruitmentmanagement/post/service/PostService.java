package heukwu.recruitmentmanagement.post.service;

import heukwu.recruitmentmanagement.company.repository.Company;
import heukwu.recruitmentmanagement.company.repository.CompanyRepository;
import heukwu.recruitmentmanagement.post.repository.PostEntity;
import heukwu.recruitmentmanagement.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final CompanyRepository companyRepository;

    public List<Post> getAllPost() {
        List<PostEntity> postEntityList = postRepository.findAll();

        return postEntityList.stream()
                .map(Post::from)
                .collect(Collectors.toList());
    }

    public PostWithOtherPosts getPost(Long postId) {
        PostEntity postEntity = postRepository.findById(postId).orElseThrow(IllegalArgumentException::new);

        return PostWithOtherPosts.from(postEntity);
    }

    public PostDto.Res createPost(Long companyId, PostDto.Req requestDto) {
        Company company = companyRepository.findById(companyId).orElseThrow(IllegalArgumentException::new);
        PostEntity postEntity = PostEntity.of(company, requestDto);
        postRepository.save(postEntity);

        return PostDto.Res.of(postEntity);
    }

    @Transactional
    public PostDto.Res editPost(Long postId, PostDto.Req editDto) {
        PostEntity postEntity = postRepository.findById(postId).orElseThrow(IllegalArgumentException::new);
        PostEntity editPostEntity = postEntity.edit(editDto);

        return PostDto.Res.of(editPostEntity);
    }

    public void deletePost(Long postId) {
        PostEntity postEntity = postRepository.findById(postId).orElseThrow(IllegalArgumentException::new);
        postRepository.delete(postEntity);
    }
}
