package com.clh.iot.netty;

import com.clh.iot.util.ClhUtils;

import java.util.Arrays;

/**
 * 摄像头网络延迟测试UDP通信协议
 *
 */
public class Protocol {
    private byte cmd; //命令码
    private byte flag;//收发标志位
    private byte seq; //帧序号
    private byte length;//1
    private byte[] data;//数据体仅上传时间，目前固定数据包长度7
    private byte crcL;//crc低位
    private byte crcH;//crc高位

    public static void main(String[] args) {
        String xx="01010107015858178589705197"; //51 97
        String yy=  xx.substring(0,2);
        System.out.println(yy);
        Protocol    protocol=    Protocol.parse("01010107015858178589705197");
        System.out.println("time:"+ ClhUtils.Bytes2Hexstring(protocol.data));
        System.out.println(protocol.toString());
    }
    /**
     * 解析上行16进制字符串
     * 01010107015858178589705197
     * @param hexstring
     * @return
     */
    public  static Protocol parse(String hexstring){
        Protocol protocol = new Protocol();
        protocol.cmd=ClhUtils.Hexstring2Bytes(hexstring.substring(0,2))[0];
        protocol.flag=ClhUtils.Hexstring2Bytes(hexstring.substring(2,4))[0];
        protocol.seq=ClhUtils.Hexstring2Bytes(hexstring.substring(4,6))[0];
        protocol.length=ClhUtils.Hexstring2Bytes(hexstring.substring(6,8))[0];
        protocol.data=ClhUtils.Hexstring2Bytes(hexstring.substring(8,22));
        protocol.crcL=ClhUtils.Hexstring2Bytes(hexstring.substring(22,24))[0];
        protocol.crcH=ClhUtils.Hexstring2Bytes(hexstring.substring(24,26))[0];
        return protocol;
    }

    @Override
    public String toString() {
        return "Protocol{" +
                "cmd=" + cmd +
                ", flag=" + flag +
                ", seq=" + seq +
                ", length=" + length +
                ", data=" + Arrays.toString(data) +
                ", crcL=" + crcL +
                ", crcH=" + crcH +
                '}';
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
