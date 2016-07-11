package com.lianjia.sh.kanban.bean;

/**
 * @author ouyang
 * @since 2016-07-08 11:40
 */
public enum  MessageCodeEnum {

    //90000 参数错误
    日期格式错误(9000010011l),
    //90001 登录错误
    账号或者密码错误(9000110000l),
    登录异常(9000110009l),
    //99999 初始化失败
    初始化失败(9999900001l);

    long code;

    MessageCodeEnum(long code) {
        this.code = code;
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }
}
