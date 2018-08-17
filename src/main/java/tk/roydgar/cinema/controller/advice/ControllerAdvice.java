package tk.roydgar.cinema.controller.advice;

import org.slf4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import static tk.roydgar.cinema.util.constants.HeaderMessages.ERROR;
import static tk.roydgar.cinema.util.constants.HeaderMessages.INTERNAL_ERROR;
import static tk.roydgar.cinema.util.constants.HeaderMessages.INVALID_ID;


@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {

    @Resource
    private Logger logger;

    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<?> handleNumberCastException(HttpServletRequest request, Exception ex){
        logger.error(ERROR, ex);
        return ResponseEntity.badRequest().header(ERROR, INVALID_ID).build();
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<?> handleGeneralException(HttpServletRequest request, Exception ex) {
        logger.error(ERROR, ex);
        return ResponseEntity.badRequest().header(ERROR, INTERNAL_ERROR).build();
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handleValidationException(HttpServletRequest request, Exception ex) {
        logger.error(ERROR, ex);
        return ResponseEntity.badRequest().header(ERROR, ex.getMessage()).build();
    }

}
