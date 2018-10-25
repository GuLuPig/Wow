package com.shop.result;

public class Result<T> {
    private int code;
    private String msg;
    private T data;

    public static <T> Result<T> success(T data){
        return new Result<T>(data);
    }
    public static  <T> Result<T> error(CodeMsg cm){
        return new Result<T>(cm);
    }

    public Result(T data) {
        this.code=0;
        this.msg="success";
        this.data = data;
    }

    public Result(CodeMsg cm) {
        if (cm!=null) {
            this.code=cm.getCode();
            this.msg=cm.getMsg();
            this.data=null;
        }

    }

    public Result(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }

}
