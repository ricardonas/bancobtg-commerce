package br.com.btg.commerce.model;

import br.com.btg.commerce.exception.BusinessException;
import br.com.btg.commerce.exception.ExceptionMessage;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

@AllArgsConstructor @Getter
public enum State {

    PENDING(1, "Pendente"),

    APPROVED(2, "Aprovado"),

    SENT(3, "Enviado"),

    FINISHED(4, "Finalizado"),

    CANCELLED(5, "Cancelado");

    @JsonValue
    private final Integer code;

    private final String description;

    @JsonCreator
    public static State fromCode(final Integer code) {
        final Optional<State> enumeration = Arrays.stream(values()).filter(e -> e.getCode().equals(code)).findFirst();
        if (enumeration.isEmpty()) throw new BusinessException(new ExceptionMessage("invalid-enumeration", "state", code));
        return enumeration.get();
    }

}