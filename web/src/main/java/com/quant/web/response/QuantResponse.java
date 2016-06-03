package com.quant.web.response;

import java.io.Serializable;

public class QuantResponse<T> implements Serializable {

    private static final long serialVersionUID = 6513865987379000722L;

    private T result;

    public void setResult(T result) {
        this.result = result;
    }

    public T getResult() {
        return result;
    }
}
