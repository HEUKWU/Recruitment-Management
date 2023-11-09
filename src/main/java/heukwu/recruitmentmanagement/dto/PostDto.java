package heukwu.recruitmentmanagement.dto;

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
    }
}
