package org.example.common.util.web;

/**
 * @Description: TODO
 * @Author: liyu
 * @Date: 2020/2/29
 */
public class ResponseResult<T> {

    public static final int SUCCESS_CODE = 0;
    public static final int FAILED_CODE = 1;
    public static final int EXCEPTION_CODE = -1;
    public static final int PARAM_VALID_FAILED_CODE = -2;
    public static final String SUCCESS_MSG = "请求成功";
    public static final String FAILED_MSG = "请求失败";
    public static final String EXCEPTION_MSG = "程序异常";

    private int code;
    private String msg;
    private T data;
    private Exception ex;

    public static  ResponseResult success(Object data) {
        return new ResponseResult<>(SUCCESS_CODE, SUCCESS_MSG, data);
    }

    public static ResponseResult success(){
        return new ResponseResult<>(SUCCESS_CODE, SUCCESS_MSG, null);
    }

    public static ResponseResult success(String message, Object data) {
        return new ResponseResult<>(SUCCESS_CODE, message, data);
    }

    public static ResponseResult failed() {
        return new ResponseResult<>(FAILED_CODE, FAILED_MSG, "");
    }

    public static ResponseResult failed(String msg) {
        return new ResponseResult<>(FAILED_CODE, msg, "");
    }

    public static ResponseResult failed(Object data){
        return new ResponseResult<>(FAILED_CODE, FAILED_MSG, data);
    }

    public static ResponseResult failed(String msg, Object data) {
        return new ResponseResult<>(FAILED_CODE, msg, data);
    }

    public static ResponseResult exception(Exception e){
        return new ResponseResult<>(EXCEPTION_CODE, EXCEPTION_MSG, e.getStackTrace());
    }

    public static ResponseResult exception(String exceptionMsg){
        return new ResponseResult(EXCEPTION_CODE, exceptionMsg);
    }

    public ResponseResult() {
    }

    public ResponseResult(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResponseResult(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ResponseResult(int code, String msg, T data, Exception ex) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.ex = ex;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Exception getEx() {
        return ex;
    }

    public void setEx(Exception ex) {
        this.ex = ex;
    }
}
