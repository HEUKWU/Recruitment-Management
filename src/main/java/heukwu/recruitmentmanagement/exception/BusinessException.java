package heukwu.recruitmentmanagement.exception;

public class BusinessException extends RuntimeException {

    public BusinessException(ErrorMessage message) {
        super(message.getMessage());
    }
}
