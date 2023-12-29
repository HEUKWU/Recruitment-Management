package heukwu.recruitmentmanagement.post.controller;

import heukwu.recruitmentmanagement.post.service.Post;

class PostRequest {

    record Create(
            String position,
            String skill,
            String description
    ) {
        Post toDomain() {
            return Post.builder()
                    .position(position)
                    .skill(skill)
                    .description(description)
                    .build();
        }
    }

    record Edit(
            String position,
            String skill,
            String description
    ) {
        Post toDomain() {
            return Post.builder()
                    .position(position)
                    .skill(skill)
                    .description(description)
                    .build();
        }
    }
}
