package com.zimo.personal.entity;

import java.io.Serializable;

public class AesEntity implements Serializable {

    private String originBody;

    private String transId;

    public String getOriginBody() {
        return originBody;
    }

    public String getTransId() {
        return transId;
    }

    public void setOriginBody(String originBody) {
        this.originBody = originBody;
    }

    public void setTransId(String transId) {
        this.transId = transId;
    }

    public AesEntity(String originBody, String transId) {
        this.originBody = originBody;
        this.transId = transId;
    }
    public AesEntity(){
        
    }
}
