package heukwu.recruitmentmanagement.post.service;

import heukwu.recruitmentmanagement.post.repository.PostEntity;
import lombok.Builder;
import org.springframework.lang.NonNull;

@Builder
public record Post(
        Long id,
        @NonNull String companyName,
        @NonNull String position,
        @NonNull String skill,
        @NonNull String description,
        boolean deleted,
        long companyId
) {
    static Post from(PostEntity postEntity, String companyName) {
        return builder()
                .id(postEntity.getId())
                .companyName(companyName)
                .position(postEntity.getPosition())
                .skill(postEntity.getSkill())
                .description(postEntity.getDescription())
                .deleted(postEntity.getDeleted())
                .companyId(postEntity.getCompanyId())
                .build();
    }

    public PostEntity toEntity(Long companyId) {
        return PostEntity.builder()
                .position(position)
                .skill(skill)
                .description(description)
                .companyId(companyId)
                .build();
    }
}
