package fr.dawan.demoapirest.interceptors;

import fr.dawan.demoapirest.dtos.LogDto;
import fr.dawan.demoapirest.exceptions.AuthentificationException;
import jakarta.servlet.ServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class MyGlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private static Logger rootLogger = LoggerFactory.getLogger(MyGlobalExceptionHandler.class);

    /*
    Définir une méthode pour chaque exception possible
     */

    @ExceptionHandler(AuthentificationException.class)
    public ResponseEntity<?> handleAuthentificationException(Exception ex, WebRequest request){

        LogDto logDto = new LogDto();
        logDto.setCode(403);
        logDto.setMessage(ex.getMessage());
        logDto.setLevel(LogDto.LogLevel.ERROR);
        logDto.setLogType(LogDto.LogType.ACCESS);
        logDto.setPath(((ServletWebRequest) request).getRequest().getRequestURI());

        rootLogger.error(ex.getMessage());

        return handleExceptionInternal(ex, logDto, new HttpHeaders(), HttpStatus.FORBIDDEN, request);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGenericException(Exception ex, WebRequest request){

        LogDto logDto = new LogDto();
        logDto.setCode(500);
        logDto.setMessage(ex.getMessage());
        logDto.setLevel(LogDto.LogLevel.ERROR);
        logDto.setLogType(LogDto.LogType.EXCEPTION);
        logDto.setPath(((ServletWebRequest) request).getRequest().getRequestURI());

        rootLogger.error(ex.getMessage());

        return handleExceptionInternal(ex, logDto, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }
}
