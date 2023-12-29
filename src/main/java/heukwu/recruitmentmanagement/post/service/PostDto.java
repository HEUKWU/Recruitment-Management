package heukwu.recruitmentmanagement.post.service;

import heukwu.recruitmentmanagement.post.repository.PostEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

public class PostDto {

    @Getter
    @NoArgsConstructor
    @Builder
    @AllArgsConstructor
    public static class Req {
        private String position;
        private String skill;
        private String description;
    }

    @Getter
    @NoArgsConstructor
    @Builder
    @AllArgsConstructor
    public static class Res {
        private Long id;
        private String companyName;
        private String position;
        private String skill;
        private String description;

        private List<Long> postList;

        public static Res of(PostEntity postEntity) {
            return Res.builder()
                    .id(postEntity.getId())
                    .companyName(postEntity.getCompany().getCompanyName())
                    .position(postEntity.getPosition())
                    .skill(postEntity.getSkill())
                    .build();
        }

        public static Res getDetailPost(PostEntity postEntity) {
            List<Long> postIdList = postEntity.getCompany().getPostEntityList().stream()
                    .map(PostEntity::getId)
                    .filter(id -> !id.equals(postEntity.getId()))
                    .collect(Collectors.toList());

            return Res.builder()
                    .id(postEntity.getId())
                    .companyName(postEntity.getCompany().getCompanyName())
                    .position(postEntity.getPosition())
                    .skill(postEntity.getSkill())
                    .description(postEntity.getDescription())
                    .postList(postIdList)
                    .build();
        }
    }
}
