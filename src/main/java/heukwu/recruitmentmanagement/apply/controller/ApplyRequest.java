package heukwu.recruitmentmanagement.apply.controller;

import heukwu.recruitmentmanagement.apply.service.Apply;

public class ApplyRequest {

    record Create(
            long postId,
            long userId
    ) {
        Apply toDomain() {
            return Apply.builder()
                    .postId(postId)
                    .userId(userId)
                    .build();
        }
    }
}
