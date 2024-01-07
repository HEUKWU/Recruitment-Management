package heukwu.recruitmentmanagement.post.service;

import heukwu.recruitmentmanagement.company.repository.Company;
import heukwu.recruitmentmanagement.post.repository.PostEntity;
import lombok.Builder;
import org.springframework.lang.NonNull;

import java.util.List;

@Builder
public record PostWithOtherPosts(
        Long id,
        @NonNull String companyName,
        @NonNull String position,
        @NonNull String skill,
        @NonNull String description,
        boolean deleted,
        Long companyId,
        List<Long> otherPostIds
) {

    static PostWithOtherPosts from(PostEntity postEntity, List<Long> otherPostIds, Company company) {
        return builder()
                .id(postEntity.getId())
                .companyName(company.getCompanyName())
                .position(postEntity.getPosition())
                .skill(postEntity.getSkill())
                .description(postEntity.getDescription())
                .deleted(postEntity.getDeleted())
                .companyId(postEntity.getCompanyId())
                .otherPostIds(otherPostIds)
                .build();
    }
}
