package com.itb.tecnostar.api.tecnostar.controller.dtoresponse;

public class ObjectDeleteResponse {

    private int status;
    private String message;
    private Long deleteId;

    public ObjectDeleteResponse(int status, String message, Long deleteId) {
        this.status = status;
        this.message = message;
        this.deleteId = deleteId;
    }

    public int getStatus() { return status; }
    public void setStatus(int status) { this.status = status; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public Long getDeleteId() { return deleteId; }
    public void setDeleteId(Long deleteId) { this.deleteId = deleteId; }
}
