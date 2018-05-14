package com.maxnerva.maxbase.msg;

public class ObjectRestResponse<T>extends BaseResponse {
    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    T data;

    public ObjectRestResponse date(T data)
    {
        this.setData(data);
        return this;
    }

}