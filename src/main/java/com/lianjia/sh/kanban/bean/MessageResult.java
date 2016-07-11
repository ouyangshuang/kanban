package com.lianjia.sh.kanban.bean;

import java.io.Serializable;

/**
 * @author ouyang
 * @since 2016-07-06 16:43
 */
public class MessageResult<T> implements Serializable {

    private String status;
    private String message;
    private String desc;
    private Long code;
    private T body;

    public MessageResult(MessageResultStatusEnum status, T body) {
        this.status = status.name();
        this.body = body;
    }

    public MessageResult(MessageResultStatusEnum status, T body, MessageCodeEnum messageCodeEnum, String desc) {
        this(status,body);
        this.code = messageCodeEnum.getCode();
        this.message = messageCodeEnum.name();
        this.desc = desc;
    }

    public static <T> MessageResult<T> success(T body){
        return new MessageResult(MessageResultStatusEnum.success, body);
    }

    public static <T> MessageResult<T> success(T body, MessageCodeEnum messageCodeEnum){
        return new MessageResult(MessageResultStatusEnum.success, body, messageCodeEnum, null);
    }

    public static <T> MessageResult<T> success(T body, MessageCodeEnum messageCodeEnum, String desc){
        return new MessageResult(MessageResultStatusEnum.success, body, messageCodeEnum, desc);
    }

    public static <T> MessageResult<T> fail(T body){
        return new MessageResult(MessageResultStatusEnum.fail, body);
    }

    public static <T> MessageResult<T> fail(T body, MessageCodeEnum messageCodeEnum){
        return new MessageResult(MessageResultStatusEnum.fail, body, messageCodeEnum, null);
    }

    public static <T> MessageResult<T> fail(T body, MessageCodeEnum messageCodeEnum, String desc){
        return new MessageResult(MessageResultStatusEnum.fail, body, messageCodeEnum, desc);
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
