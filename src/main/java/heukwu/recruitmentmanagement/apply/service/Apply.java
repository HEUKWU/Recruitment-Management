package heukwu.recruitmentmanagement.apply.service;

import heukwu.recruitmentmanagement.apply.repository.ApplyEntity;
import lombok.Builder;

@Builder
public record Apply(
        Long id,
        Long postId,
        Long userId
) {

    public static Apply from(ApplyEntity applyEntity) {
        return Apply.builder()
                .id(applyEntity.getId())
                .postId(applyEntity.getPostId())
                .userId(applyEntity.getUserId())
                .build();

    }

    public static ApplyEntity toEntity(Long postId, Long userId) {
        return ApplyEntity.builder()
                .postId(postId)
                .userId(userId)
                .build();
    }
}
