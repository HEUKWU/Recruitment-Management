package heukwu.recruitmentmanagement.dto;

import heukwu.recruitmentmanagement.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class PostDto {

    @Getter
    @NoArgsConstructor
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
        private String position;
        private String skill;
        private String description;

        public static Res of(Post post) {
            return Res.builder()
                    .id(post.getId())
                    .position(post.getPosition())
                    .skill(post.getSkill())
                    .description(post.getDescription())
                    .build();
        }
    }
}
