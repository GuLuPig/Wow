package com.shop.result;

public class CodeMsg {
    private int code;
    private String msg;

    //通用异常
    public static CodeMsg SERVER_ERROR=new CodeMsg(500100,"服务器异常");

    //业务异常
    public static CodeMsg STOCK_ERROR=new CodeMsg(500200,"库存不足");
    //登陆
    public static CodeMsg LOGIN_ERROR=new CodeMsg(500200,"用户未登陆");

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public static CodeMsg getServerError() {
        return SERVER_ERROR;
    }

    public CodeMsg(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
