package heukwu.recruitmentmanagement.post.repository;

import lombok.Builder;

@Builder
public record PostEntityUpdatePolicy(String position, String skill, String description) {
}
