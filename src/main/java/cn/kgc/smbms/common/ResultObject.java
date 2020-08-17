package cn.kgc.smbms.common;

/**
 * @author:liao
 * @Description:TODO
 * @date:2020/8/3
 */
public class ResultObject {
    private int code;
    private String msg;
    private Object data;
    private boolean success;

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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    private ResultObject(int code,String msg,boolean success){
        this.code = code;
        this.msg = msg;
        this.success = success;
    };

    private ResultObject(int code,String msg,Object data,boolean success){
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.success = success;
    }

    /**
     * success
     * @param code
     * @param msg
     * @return
     */
    public static ResultObject resultBySuccess(int code,String msg){
        return new ResultObject(code,msg,true);
    }

    /**
     * success data
     * @param code
     * @param msg
     * @param data
     * @return
     */
    public static ResultObject resultBySuccessData(int code,String msg,Object data){
        return new ResultObject(code,msg,data,true);
    }

    /**
     * error
     * @param code
     * @param msg
     * @return
     */
    public static ResultObject resultByErrorMsg(int code,String msg){
        return new ResultObject(code,msg,false);
    }
}
