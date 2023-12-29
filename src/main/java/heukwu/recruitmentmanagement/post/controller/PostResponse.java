package heukwu.recruitmentmanagement.post.controller;

import heukwu.recruitmentmanagement.post.service.Post;
import heukwu.recruitmentmanagement.post.service.PostWithOtherPosts;
import lombok.Builder;

import java.util.List;

class PostResponse {

    @Builder
    record Create(
            Long id,
            String companyName,
            String position,
            String skill,
            String description
    ) {
        static Create from(Post post) {
            return Create.builder()
                    .id(post.id())
                    .companyName(post.company().getCompanyName())
                    .position(post.position())
                    .skill(post.skill())
                    .description(post.description())
                    .build();
        }
    }

    @Builder
    record Edit(
            Long id,
            String companyName,
            String position,
            String skill,
            String description
    ) {
        static Edit from(Post post) {
            return Edit.builder()
                    .id(post.id())
                    .companyName(post.company().getCompanyName())
                    .position(post.position())
                    .skill(post.skill())
                    .description(post.description())
                    .build();
        }
    }

    @Builder
    record Get(
            Long id,
            String companyName,
            String position,
            String skill,
            String description,
            List<Long> otherPostIds
    ) {
        static Get from(PostWithOtherPosts post) {
            List<Long> otherPostIds = post.otherPosts().stream().map(Post::id).toList();

            return Get.builder()
                    .id(post.id())
                    .companyName(post.company().getCompanyName())
                    .position(post.position())
                    .skill(post.skill())
                    .description(post.description())
                    .otherPostIds(otherPostIds)
                    .build();
        }
    }

    @Builder
    record GetList(
            Long id,
            String companyName,
            String position,
            String skill
    ) {
        static GetList from(Post post) {
            return GetList.builder()
                    .id(post.id())
                    .companyName(post.company().getCompanyName())
                    .position(post.position())
                    .skill(post.skill())
                    .build();
        }
    }
}
