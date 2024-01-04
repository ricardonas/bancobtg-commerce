package br.com.btg.commerce.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.With;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

@Getter @Setter @With @AllArgsConstructor @NoArgsConstructor
public class BusinessException extends RuntimeException {

    private HttpStatus httpStatus;

    @Getter(AccessLevel.NONE)
    private Collection<ExceptionMessage> messages;

    public BusinessException(final HttpStatus httpStatus, final Collection<ExceptionMessage> messages, final Throwable cause) {
        super(cause);
        this.messages = messages;
        this.httpStatus = httpStatus;
    }

    public BusinessException(final Collection<ExceptionMessage> messages) {
        this(HttpStatus.BAD_REQUEST, messages, null);
    }

    public BusinessException(final ExceptionMessage exceptionMessage) {
        this(Collections.singleton(exceptionMessage));
    }

    public Collection<ExceptionMessage> getMessages() {
        return CollectionUtils.isEmpty(this.messages) ? new ArrayList<>() : this.messages;
    }

    public ExceptionMessage getExceptionMessage() {
        return this.getMessages().iterator().next();
    }

    @Override
    public String getMessage() {
        return this.getExceptionMessage().getCode();
    }
}