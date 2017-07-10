package com.basic.rabbitmq.constant;

/**
 * Created by sdc on 2017/7/7.
 */
public enum WebStatusEnum {


    /**
     * 定义接口返回状态码
     *
     * 通用部分范围 5000 +
     * 业务使用范围 2000 至 4000
     */

    SUCCESS("5000", "成功"),
    FAILED("7000", "失败"),


    PARAM_ERROR("7001", "参数错误"),
    PARAM_NOT_NULL("7002", "参数不能为空");


    /**
     * 系统码
     */
    private String code;

    /**
     * 描述
     */
    private String desc;

    WebStatusEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static WebStatusEnum getWebStatusEnumByKey(String key){
        for(WebStatusEnum bt : values()){
            if(bt.getCode().equals(key) )
                return bt;
        }
        return null;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
