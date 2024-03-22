package com.example.assignment.advicer;

import com.example.assignment.exception.*;
import com.example.assignment.util.StandardResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.lang.NullPointerException;
import java.sql.SQLException;

/**
 * @author Thrimal Avishka <thrimalavishka99@gmail.com>
 * @since 21-Jul-23
 */
@RestControllerAdvice
public class AppWideExceptionHandler extends ResponseEntityExceptionHandler {

//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<StandardResponse> handleMethodArgumentNotValid(MethodArgumentNotValidException e) {
//        Map<String, String> errorsMap = new HashMap<>();
//        e.getBindingResult().getFieldErrors().forEach(error -> {
//            errorsMap.put(error.getField(), error.getDefaultMessage());
//        });
//        return new ResponseEntity<StandardResponse>(
//                new StandardResponse(400, "Validation Exception...", errorsMap),
//                HttpStatus.BAD_REQUEST);
//    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<StandardResponse> handleIllegalArgumentException(IllegalArgumentException e) {
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(400, "Validation Error...", e.getMessage()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntryDuplicateException.class)
    public ResponseEntity<StandardResponse> handleDuplicateRequestException(EntryDuplicateException e) {
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(409, "Duplicate Entry Error...", e.getMessage()),
                HttpStatus.CONFLICT);
    }

    @ExceptionHandler(EntryNotFoundException.class)
    public ResponseEntity<StandardResponse> handleEntryNotFoundException(EntryNotFoundException e) {
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(404, "Not Found Error...", e.getMessage()),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<StandardResponse> handleNotFoundException(NotFoundException e) {
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(404, "Not Found Error...", e.getMessage()),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserLockedException.class)
    public ResponseEntity<StandardResponse> handleUserLockedException(UserLockedException e) {
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(423, "User is Locked...", e.getMessage()),
                HttpStatus.LOCKED);
    }

    @ExceptionHandler(TokenExpireException.class)
    public ResponseEntity<StandardResponse> handleTokenExpiredException(TokenExpireException e) {
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(401, "Token is expired...", e.getMessage()),
                HttpStatus.GATEWAY_TIMEOUT);
    }

//    @ExceptionHandler(BadCredentialsException.class)
//    public ResponseEntity<StandardResponse> handleBadCredential(BadCredentialsException e){
//        return new ResponseEntity<StandardResponse>(
//                new StandardResponse(401, "Bad Credentials Exception", e.getMessage()),
//                HttpStatus.UNAUTHORIZED);
//    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<StandardResponse> handleResponseStatusException(ResponseStatusException ex) {
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(401, "Status Error...", ex.getMessage()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotVerifiedException.class)
    public ResponseEntity<StandardResponse> handleUserNotVerifiedException(UserNotVerifiedException e) {
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(503, "User is Not Verified...", e.getMessage()),
                HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler(UserNotAcceptableException.class)
    public ResponseEntity<StandardResponse> handleUserNotAcceptableException(UserNotAcceptableException e) {
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(406, "User Not Acceptable...", e.getMessage()),
                HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(ValidationException.class)
    protected ResponseEntity<StandardResponse> handleValidateException(ValidationException e) {
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(400, "Validation Error...", e.getMessage()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RestrictedAreaException.class)
    public ResponseEntity<StandardResponse> handleUserRestrictedException(RestrictedAreaException e) {
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(405, "Restricted Area...", e.getMessage()),
                HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(UnAuthorizedException.class)
    public ResponseEntity<StandardResponse> handleUserUnAuthorizedException(UnAuthorizedException e) {
        System.out.println("ok");
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(401, "User is Unauthorized...", e.getMessage()),
                HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<StandardResponse> handleNullPointerException(NullPointerException e) {
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(401, "Null Pointer...", e.getMessage()),
                HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<StandardResponse> handleUserNotFoundException(UserNotFoundException e) {
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(404, "User is Not Found...", e.getMessage()),
                HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(HttpClientErrorException.Gone.class)
    public ResponseEntity<StandardResponse> handleGoneException(HttpClientErrorException.Gone e) {
        return new ResponseEntity<StandardResponse>(new StandardResponse(410, "Error", e.getMessage()),
                HttpStatus.GONE);
    }

    @ExceptionHandler(SQLException.class)
    public ResponseEntity<StandardResponse> handleSqlException(SQLException ex) {
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(404, "SQL Error", ex.getMessage()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TransactionException.class)
    public ResponseEntity<StandardResponse> handleTransactionException(TransactionException e) {
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(417, "Transaction Exception", e.getMessage()),
                HttpStatus.EXPECTATION_FAILED);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<StandardResponse> handleGlobalException(Exception ex) {
        ex.printStackTrace();
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(500, "Internal Server Error", ex.getMessage()),
                HttpStatus.BAD_REQUEST);
    }
}
