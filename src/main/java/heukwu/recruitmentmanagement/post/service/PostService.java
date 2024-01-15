package heukwu.recruitmentmanagement.post.service;

import heukwu.recruitmentmanagement.company.repository.Company;
import heukwu.recruitmentmanagement.company.repository.CompanyRepository;
import heukwu.recruitmentmanagement.exception.NotFoundCompanyException;
import heukwu.recruitmentmanagement.exception.NotFoundPostException;
import heukwu.recruitmentmanagement.post.repository.PostEntity;
import heukwu.recruitmentmanagement.post.repository.PostEntityUpdatePolicy;
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
                .map(i -> Post.from(i, companyRepository.findById(i.getCompanyId()).orElseThrow(NotFoundCompanyException::new).getCompanyName()))
                .toList();
    }

    public PostWithOtherPosts getPost(Long postId) {
        PostEntity postEntity = postRepository.findById(postId).orElseThrow(NotFoundPostException::new);
        Company company = companyRepository.findById(postEntity.getCompanyId()).orElseThrow(NotFoundCompanyException::new);
        List<PostEntity> postEntities = postRepository.findAllByCompanyId(postEntity.getCompanyId());

        List<Long> otherPostIds = postEntities.stream()
                .map(PostEntity::getId)
                .filter(id -> !id.equals(postEntity.getId()))
                .collect(Collectors.toList());

        return PostWithOtherPosts.from(postEntity, otherPostIds, company);
    }

    @Transactional
    public Post createPost(Long companyId, Post post) {
        Company company = companyRepository.findById(companyId).orElseThrow(NotFoundCompanyException::new);
        PostEntity postEntity = post.toEntity(companyId);
        postRepository.save(postEntity);

        return Post.from(postEntity, company.getCompanyName());
    }

    @Transactional
    public Post editPost(Long postId, Post post) {
        PostEntity postEntity = postRepository.findById(postId).orElseThrow(NotFoundPostException::new);
        Company company = companyRepository.findById(postEntity.getCompanyId()).orElseThrow(NotFoundCompanyException::new);
        PostEntityUpdatePolicy updatePolicy = PostEntityUpdatePolicy.builder()
                .position(post.position())
                .skill(post.skill())
                .description(post.description())
                .build();
        PostEntity editPostEntity = postEntity.edit(updatePolicy);

        return Post.from(editPostEntity, company.getCompanyName());
    }

    @Transactional
    public void deletePost(Long postId) {
        PostEntity postEntity = postRepository.findById(postId).orElseThrow(NotFoundPostException::new);
        postRepository.delete(postEntity);
    }
}
