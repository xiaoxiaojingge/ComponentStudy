package com.itjing.response;

/**
 * @author lijing
 * @date 2021年11月13日 9:33
 * @description
 */
public class InfoMessage {
    /**
     * 失败代码.
     */
    private String returnCode;
    /**
     * 失败信息.
     */
    private String returnMsg;
    /**
     * 异常信息
     */
    private String returnDetailMsg;

    public InfoMessage() {
    }

    public InfoMessage(String code, String msg) {
        this.returnCode = code;
        this.returnMsg = msg;
    }

    public InfoMessage(String code, String msg, String detail) {
        this.returnCode = code;
        this.returnMsg = msg;
        this.returnDetailMsg = msg;
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getReturnMsg() {
        return returnMsg;
    }

    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg;
    }

    public String getReturnDetailMsg() {
        return returnDetailMsg;
    }

    public void setReturnDetailMsg(String returnDetailMsg) {
        this.returnDetailMsg = returnDetailMsg;
    }
}

