package com.clh.iot.netty;

/**
 * 摄像头网络延迟测试UDP通信协议
 *
 */
public class Protocol {
    private byte cmd; //命令码
    private byte flag;//收发标志位
    private byte seq; //帧序号
    private byte length;//数据包长度
    private byte[] data;//数据体
    private byte crcL;//crc低位
    private byte crcH;//crc高位

    /**
     * 解析上行16进制字符串
     * 01010107015858178589705197
     * @param hexstring
     * @return
     */
    public static Protocol parse(String hexstring){

        return null;
    }

    public byte getCmd() {
        return cmd;
    }

    public void setCmd(byte cmd) {
        this.cmd = cmd;
    }

    public byte getFlag() {
        return flag;
    }

    public void setFlag(byte flag) {
        this.flag = flag;
    }

    public byte getSeq() {
        return seq;
    }

    public void setSeq(byte seq) {
        this.seq = seq;
    }

    public byte getLength() {
        return length;
    }

    public void setLength(byte length) {
        this.length = length;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public byte getCrcL() {
        return crcL;
    }

    public void setCrcL(byte crcL) {
        this.crcL = crcL;
    }

    public byte getCrcH() {
        return crcH;
    }

    public void setCrcH(byte crcH) {
        this.crcH = crcH;
    }
}
