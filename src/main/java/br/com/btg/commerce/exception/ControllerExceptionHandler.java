package br.com.btg.commerce.exception;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.NoSuchMessageException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestControllerAdvice
public class ControllerExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ControllerExceptionHandler.class);

    @Autowired
    private MessageService messageService;

    protected ExceptionMessage addContent(final ExceptionMessage message) {
        // Keeps the original values if already has been set.
        String actualContent = message.getContent();

        if (StringUtils.isBlank(actualContent)) {
            try {
                actualContent = this.messageService.getMessageFromProperty(message.getCode(), message.getParameters());
            }
            catch (final NoSuchMessageException exception) {
                ControllerExceptionHandler.LOGGER.debug("Message content could not be added for code: '" + message.getCode() + "'.", exception);
            }
            message.setContent(actualContent);
        }

        return message;
    }


    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Object> processBusinessException(final BusinessException businessException) {
        // Loop through exception messages to add content.
        businessException.getMessages().forEach(this::addContent);
        return new ResponseEntity<>(businessException.getMessages(), businessException.getHttpStatus());
    }

}