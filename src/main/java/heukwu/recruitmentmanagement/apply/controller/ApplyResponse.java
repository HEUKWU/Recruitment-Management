package heukwu.recruitmentmanagement.apply.controller;

import heukwu.recruitmentmanagement.apply.service.Apply;
import lombok.Builder;

public class ApplyResponse {

    @Builder
    record Create(
            Long id,
            Long userId,
            Long postId
    ) {
        static Create from(Apply apply) {
            return Create.builder()
                    .id(apply.id())
                    .userId(apply.userId())
                    .postId(apply.postId())
                    .build();
        }
    }
}
