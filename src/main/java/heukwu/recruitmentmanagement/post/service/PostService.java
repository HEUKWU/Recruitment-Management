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

    public Post createPost(Long companyId, Post post) {
        Company company = companyRepository.findById(companyId).orElseThrow(IllegalArgumentException::new);
        PostEntity postEntity = post.toEntity(company);
        postRepository.save(postEntity);

        return Post.from(postEntity);
    }

    @Transactional
    public Post editPost(Long postId, Post post) {
        PostEntity postEntity = postRepository.findById(postId).orElseThrow(IllegalArgumentException::new);
        PostEntity editPostEntity = postEntity.edit(post.position(), post.skill(), post.description());

        return Post.from(editPostEntity);
    }

    public void deletePost(Long postId) {
        PostEntity postEntity = postRepository.findById(postId).orElseThrow(IllegalArgumentException::new);
        postRepository.delete(postEntity);
    }
}
