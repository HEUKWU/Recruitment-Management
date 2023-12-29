package heukwu.recruitmentmanagement.post.service;

import heukwu.recruitmentmanagement.company.repository.Company;
import heukwu.recruitmentmanagement.post.repository.PostEntity;
import lombok.Builder;
import org.springframework.lang.NonNull;

import java.util.List;

@Builder
public record PostWithOtherPosts(
        Long id,
        @NonNull String position,
        @NonNull String skill,
        @NonNull String description,
        boolean deleted,
        Company company,
        List<Post> otherPosts
) {

    static PostWithOtherPosts from(PostEntity postEntity) {
        List<Post> otherPosts = postEntity.getCompany().getPostEntityList().stream()
                .filter(post -> !post.getId().equals(postEntity.getId()))
                .map(Post::from)
                .toList();

        return builder()
                .id(postEntity.getId())
                .position(postEntity.getPosition())
                .skill(postEntity.getSkill())
                .description(postEntity.getDescription())
                .deleted(postEntity.getDeleted())
                .company(postEntity.getCompany())
                .otherPosts(otherPosts)
                .build();
    }
}
