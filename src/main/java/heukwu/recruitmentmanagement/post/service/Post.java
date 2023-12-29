package heukwu.recruitmentmanagement.post.service;

import heukwu.recruitmentmanagement.company.repository.Company;
import heukwu.recruitmentmanagement.post.repository.PostEntity;
import lombok.Builder;
import org.springframework.lang.NonNull;

@Builder
public record Post(
        Long id,
        @NonNull String position,
        @NonNull String skill,
        @NonNull String description,
        boolean deleted,
        Company company
) {
    static Post from(PostEntity postEntity) {
        return builder()
                .id(postEntity.getId())
                .position(postEntity.getPosition())
                .skill(postEntity.getSkill())
                .description(postEntity.getDescription())
                .deleted(postEntity.getDeleted())
                .company(postEntity.getCompany())
                .build();
    }
}
