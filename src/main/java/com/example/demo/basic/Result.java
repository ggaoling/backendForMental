package com.example.demo.basic;

public class Result
{
    /**
     * 错误内容
     */
    private String error;

    /**
     * 自定义错误码
     */
    private int code;

    private Object result;


    public Result(String error, int code,Object result)
    {
        this.error = error;
        this.code = code;
        this.result=result;
    }

    public String getError()
    {
        return error;
    }

    public void setError(String error)
    {
        this.error = error;
    }

    public int getCode()
    {
        return code;
    }

    public void setCode(int code)
    {
        this.code = code;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public enum ErrorCode{
        /**
         * 用户不存在
         */
        USER_NOT_FOUND(40401),

        /**
         * 用户已存在
         */
        USER_ALREADY_EXIST(40001),
        ;

        private int code;

        public int getCode()
        {
            return code;
        }

        ErrorCode(int code)
        {
            this.code = code;
        }
    }
}
