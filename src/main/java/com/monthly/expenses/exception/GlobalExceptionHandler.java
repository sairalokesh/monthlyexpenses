package com.monthly.expenses.exception;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.monthly.expenses.constant.MessageKey;
import com.monthly.expenses.model.Response;
import com.monthly.expenses.service.impl.MessageLocaleService;

@ControllerAdvice(annotations = RestController.class)
@Order(Ordered.HIGHEST_PRECEDENCE + 5)
public class GlobalExceptionHandler {

    @Autowired
    private MessageLocaleService messageLocaleService;
    private static Map<String, String> constraintCodeMap = new HashMap<String, String>() {
        private static final long serialVersionUID = 786849357576621684L;
        {
            put("user_email_unique", MessageKey.EXCEPTION_USER_DUPLICATE_EMAIL);
        }
    };

    @ResponseStatus(value = HttpStatus.CONFLICT)
    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseBody
    public ResponseEntity<Response> conflict(HttpServletRequest req, final DataIntegrityViolationException e) {
        ConstraintViolationException se = (ConstraintViolationException) e.getCause();
        String rootMsg = se.getSQLException().getMessage();
        String msg = rootMsg;
        if (rootMsg != null) {
            Optional<Map.Entry<String, String>> entry = constraintCodeMap.entrySet().stream()
                    .filter((it) -> rootMsg.contains(it.getKey())).findAny();
            if (entry.isPresent()) {
                msg = messageLocaleService.getMessage(entry.get().getValue());
            }
        }
        return new ResponseEntity<Response>(
                Response.error(msg, HttpStatus.CONFLICT.value(), DataIntegrityViolationException.class.getName()),
                HttpStatus.CONFLICT);
    }

    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    @ExceptionHandler(UnauthorizedException.class)
    @ResponseBody
    public ResponseEntity<Response> forbidden(HttpServletRequest req, UnauthorizedException e) {
        String rootMsg = e.getMessage();
        return new ResponseEntity<Response>(
                Response.error(rootMsg, HttpStatus.FORBIDDEN.value(), AccessDeniedException.class.getName()),
                HttpStatus.FORBIDDEN);
    }
}