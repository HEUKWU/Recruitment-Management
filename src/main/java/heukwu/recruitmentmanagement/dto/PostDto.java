package heukwu.recruitmentmanagement.dto;

import heukwu.recruitmentmanagement.entity.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class PostDto {

    @NoArgsConstructor
    @Getter
    public static class Req {
        private String position;
        private String skill;
        private String description;

        @Builder
        public Req(String position, String skill, String description) {
            this.position = position;
            this.skill = skill;
            this.description = description;
        }

        public static Req of(Post post) {
            return Req.builder()
                    .position(post.getPosition())
                    .skill(post.getSkill())
                    .description(post.getDescription())
                    .build();
        }
    }

    @NoArgsConstructor
    @Getter
    public static class Res {
        private String position;
        private String skill;
        private String description;

        @Builder
        public Res(String position, String skill, String description) {
            this.position = position;
            this.skill = skill;
            this.description = description;
        }

        public static Res of(Post post) {
            return Res.builder()
                    .position(post.getPosition())
                    .skill(post.getSkill())
                    .description(post.getDescription())
                    .build();
        }
    }
}
