package heukwu.recruitmentmanagement.exception;

import lombok.Getter;

@Getter
public enum ErrorMessage {
    NOT_FOUND_COMPANY("회사를 찾을 수 없습니다."),
    NOT_FOUND_POST("공고를 찾을 수 없습니다."),
    NOT_FOUND_USER("사용자를 찾을 수 없습니다."),
    DUPLICATE_APPLY("이미 지원한 공고입니다."),
    ;

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }
}
