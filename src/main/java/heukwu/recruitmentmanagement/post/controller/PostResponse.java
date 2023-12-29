package heukwu.recruitmentmanagement.post.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import heukwu.recruitmentmanagement.post.service.Post;
import heukwu.recruitmentmanagement.post.service.PostDto;
import lombok.Builder;

import java.util.List;

class PostResponse {

    @Builder
    record Create(
            Long id,
            String companyName,
            String position,
            String skill,
            @JsonInclude(JsonInclude.Include.NON_NULL)
            String description,
            @JsonInclude(JsonInclude.Include.NON_NULL)
            List<Long> postList
    ) {
        static Create from(PostDto.Res dto) {
            return Create.builder()
                    .id(dto.getId())
                    .companyName(dto.getCompanyName())
                    .position(dto.getPosition())
                    .skill(dto.getSkill())
                    .description(dto.getDescription())
                    .postList(dto.getPostList())
                    .build();
        }
    }

    @Builder
    record Edit(
            Long id,
            String companyName,
            String position,
            String skill,
            @JsonInclude(JsonInclude.Include.NON_NULL)
            String description,
            @JsonInclude(JsonInclude.Include.NON_NULL)
            List<Long> postList
    ) {
        static Edit from(PostDto.Res dto) {
            return Edit.builder()
                    .id(dto.getId())
                    .companyName(dto.getCompanyName())
                    .position(dto.getPosition())
                    .skill(dto.getSkill())
                    .description(dto.getDescription())
                    .postList(dto.getPostList())
                    .build();
        }
    }

    @Builder
    record Get(
            Long id,
            String companyName,
            String position,
            String skill,
            @JsonInclude(JsonInclude.Include.NON_NULL)
            String description,
            @JsonInclude(JsonInclude.Include.NON_NULL)
            List<Long> postList
    ) {
        static Get from(Post post) {
            return Get.builder()
                    .id(post.id())
                    .companyName(post.company().getCompanyName())
                    .position(post.position())
                    .skill(post.skill())
                    .description(post.description())
                    .build();
        }
    }
}
