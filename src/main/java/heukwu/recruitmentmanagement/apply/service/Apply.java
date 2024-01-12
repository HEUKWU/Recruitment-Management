package heukwu.recruitmentmanagement.apply.service;

import heukwu.recruitmentmanagement.apply.repository.ApplyEntity;
import lombok.Builder;

@Builder
public record Apply(
        Long id,
        long postId,
        long userId
) {

    public static Apply from(ApplyEntity applyEntity) {
        return Apply.builder()
                .id(applyEntity.getId())
                .postId(applyEntity.getPostId())
                .userId(applyEntity.getUserId())
                .build();

    }

    public ApplyEntity toEntity() {
        return ApplyEntity.builder()
                .postId(postId)
                .userId(userId)
                .build();
    }
}
