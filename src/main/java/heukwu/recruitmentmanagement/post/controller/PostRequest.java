package heukwu.recruitmentmanagement.post.controller;

import heukwu.recruitmentmanagement.post.service.Post;
import heukwu.recruitmentmanagement.post.service.PostDto;

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
        PostDto.Req toDomain() {
            return PostDto.Req.builder()
                    .position(position)
                    .skill(skill)
                    .description(description)
                    .build();
        }
    }
}
