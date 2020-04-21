package com.clh.iot.common.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Caolh on 2018/5/21.
 * 定义返回APP前段 统一结果值
 */
public class ResultBase implements Serializable{
    /**
     * 结果码：负数-1，服务器端异常
     * 正数：代表非服务器异常的不同逻辑值
     */
    private int code=0;
    /**
     *服务器端异常描述信息：为""，没有异常
     */
    private String msg="";

    /**
     * 存储 一个 list 数组,分页信息
     */
    private List list = new ArrayList();

    /**
     * 成功，有返回参数
     * @param list
     * @return
     */
    public static ResultBase success(int code,String msg,List list){
        ResultBase resultBase  =new ResultBase();
        resultBase.code=code;
        resultBase.list=list;
        resultBase.msg=msg;
        return resultBase;
    }

    /**
     * 没有返回参数
     * @param code
     * @return
     */
    public static ResultBase success(int code,String msg){
        //默认为空
        ResultBase resultBase  =new ResultBase();
        resultBase.code=code;
        resultBase.msg=msg;
        return resultBase;
    }

    /**
     * 没有返回参数
     * @param code
     * @return
     */
    public static ResultBase success(int code){
        //默认为空
        ResultBase resultBase  =new ResultBase();
        resultBase.code=code;
        return resultBase;
    }

    /**
     * 失败
     * @param code   错误代码
     * @param msg   错误信息
     * @return
     */
    public static ResultBase error(int code,String msg){
        ResultBase resultBase  =new ResultBase();
        resultBase.code=code;
        resultBase.msg=msg;
        return resultBase;
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

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }
}
