package service.hobbyservice.base.exception.code;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum RoutineErrorStatus implements BaseErrorCodeInterface {
    HOBBY_ROUTINE_NOT_FOUND(HttpStatus.BAD_REQUEST, "HOBBY_4001", "존재하지 않는 취미 루틴입니다."),
    HOBBY_ROUTINE_ALREADY_EXIST(HttpStatus.BAD_REQUEST, "HOBBY_4002", "이미 추가한 취미 루틴입니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
    private final boolean isSuccess = false;


    RoutineErrorStatus(HttpStatus httpStatus, String code, String message) {
        this.httpStatus = httpStatus;
        this.code = code;
        this.message = message;
    }

    @Override
    public BaseCodeDto getErrorCode() {
        return BaseCodeDto.builder()
                .httpStatus(httpStatus)
                .isSuccess(isSuccess)
                .code(code)
                .message(message)
                .build();
    }
}