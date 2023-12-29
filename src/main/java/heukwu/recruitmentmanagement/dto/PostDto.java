package heukwu.recruitmentmanagement.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import heukwu.recruitmentmanagement.entity.Post;
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
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private String description;
        @JsonInclude(JsonInclude.Include.NON_NULL)

        private List<Long> postList;

        public static Res of(Post post) {
            return Res.builder()
                    .id(post.getId())
                    .companyName(post.getCompany().getCompanyName())
                    .position(post.getPosition())
                    .skill(post.getSkill())
                    .build();
        }

        public static Res getDetailPost(Post post) {
            List<Long> postIdList = post.getCompany().getPostList().stream()
                    .map(Post::getId)
                    .filter(id -> !id.equals(post.getId()))
                    .collect(Collectors.toList());

            return Res.builder()
                    .id(post.getId())
                    .companyName(post.getCompany().getCompanyName())
                    .position(post.getPosition())
                    .skill(post.getSkill())
                    .description(post.getDescription())
                    .postList(postIdList)
                    .build();
        }
    }
}
