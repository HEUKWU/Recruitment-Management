package heukwu.recruitmentmanagement.exception;

import lombok.Builder;

@Builder
public record ErrorResponse(
        int code,
        String message
) {
}
