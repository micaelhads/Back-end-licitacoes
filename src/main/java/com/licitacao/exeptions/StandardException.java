package com.licitacao.exeptions;

public class StandardException {
    private Long timeStamp;
    private String menssage;
    private Integer status;

    public StandardException(String menssage, Long timeStamp, Integer status){
        super();
        this.menssage = menssage;
        this.timeStamp = timeStamp;
        this.status = status;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getMenssage() {
        return menssage;
    }

    public void setMenssage(String menssage) {
        this.menssage = menssage;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
