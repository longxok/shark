package com.cloudwalk.shark.config.exception.handler;

import com.cloudwalk.shark.common.em.GlobalCodeEnum;
import com.cloudwalk.shark.common.utils.ResponseData;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;


/**
 * 全局异常捕获
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseData handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        StringBuilder errorInfo = new StringBuilder();
        BindingResult bindingResult = exception.getBindingResult();
        for(int i = 0; i < bindingResult.getFieldErrors().size(); i++){
            if(i > 0){
                errorInfo.append(",");
            }
            FieldError fieldError = bindingResult.getFieldErrors().get(i);
            errorInfo.append(fieldError.getField()).append(" :").append(fieldError.getDefaultMessage());
        }

        //返回BaseResponse
        ResponseData response = new ResponseData();
        response.setMessage(errorInfo.toString());
        response.setCode(GlobalCodeEnum.FAIL.getErrorCode());
        return response;
    }


    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseData<String> handleConstraintViolationException(ConstraintViolationException exception) {
        StringBuilder errorInfo = new StringBuilder();
        String errorMessage ;

        Set<ConstraintViolation<?>> violations = exception.getConstraintViolations();
        for (ConstraintViolation<?> item : violations) {
            errorInfo.append(item.getMessage()).append(",");
        }
        errorMessage = errorInfo.toString().substring(0, errorInfo.toString().length()-1);

        ResponseData<String> response = new ResponseData<String>();
        response.setMessage(errorMessage);
        response.setCode(GlobalCodeEnum.FAIL.getErrorCode());
        return response;
    }



    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseData<String> handleDefaultException(Exception exception) {

        ResponseData<String> response = new ResponseData<>();
        response.setMessage("其他错误:"+exception.getMessage());
        response.setCode(GlobalCodeEnum.FAIL.getErrorCode());
        return response;
    }
}
