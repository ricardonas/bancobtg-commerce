package br.com.btg.commerce.exception;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.With;

import java.io.Serial;
import java.io.Serializable;

@Getter @Setter @With @AllArgsConstructor @NoArgsConstructor
public class ExceptionMessage implements Serializable {

    @Serial
    private static final long serialVersionUID = -984895280469276677L;

    private String code;

    private String content;

    @JsonIgnore
    private Object[] parameters;

    public ExceptionMessage(String code) {
        this.code = code;
    }

    public ExceptionMessage(final String code, final Object... parameters) {
        this.code = code;
        this.parameters = parameters;
    }

}