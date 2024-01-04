package br.com.btg.commerce.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class MessageService {

    @Autowired
    private MessageSource messageSource;

    public String getMessageFromProperty(final String code, final Object... parameters) {
        return this.messageSource.getMessage(code, parameters, Locale.getDefault());
    }

}