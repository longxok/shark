package com.cloudwalk.shark.config.exception;

/**
 * @author: yuanhao
 * @version: v1.0
 * @description: com.cloudwalk.shark.config.exception
 * @date:2019/6/21
 */
public class RequestLimitException extends RuntimeException{
    private static final long serialVersionUID = 1364225358754654702L;

    public RequestLimitException() {
        super("HTTP请求超出设定的限制");
    }

    public RequestLimitException(String message) {
        super(message);
    }

}
