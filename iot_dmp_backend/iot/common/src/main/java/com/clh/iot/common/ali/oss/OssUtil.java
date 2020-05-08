package com.clh.iot.common.ali.oss;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.GetObjectRequest;
import com.aliyun.oss.model.OSSObjectSummary;
import com.aliyun.oss.model.ObjectListing;

import java.io.*;
import java.util.List;

/**
 * OSS云存储 常用接口
 */
public class OssUtil {
    // Endpoint以杭州为例，其它Region请按实际情况填写。
    private static String endpoint="http://oss-cn-shenzhen.aliyuncs.com";
    // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
    private static String accessKeyId = "LTAI4Fzp4qXtBxskJUSdj2CX";
    private static String accessKeySecret = "jGSPIcYlvjAtb6LgjW9XfScfGeVdSO";
    private static String bucketName = "clh-gf-test";


    public static void main(String[] args) {
        //1.测试文件上传
        String sPath="D://A065473_2020-04-22_11-18-11_Motion Detection.mp4";
        String dPath="vedio/china/motion/200108_093054_100054_M.mp4";
        OssUtil.upLoadFile(sPath,dPath);

        //2.测试文件下载
//        String objectName= "vedio/china/motion/200108_093054_100054_M.mp4";
//        OssUtil.downLoadFile(objectName,"c://xxx.mp4");

        //3.查询文件
//        String KeyPrefix = "vedio/america/";
//        OssUtil.listFile(KeyPrefix);

        //4.删除文件
//        String objectName="vedio/china/motion/200108_093054_100054_M.mp4";
//        OssUtil.deleteFile(objectName);

    }

    /**
     * 文件上传
     * @param sPath  源文件地址
     * @param dPath  目标存储桶地址
     */
    public static void upLoadFile(String sPath,String dPath){
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        // 上传文件流。
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(sPath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ossClient.putObject(bucketName, dPath, inputStream);
        // 关闭OSSClient。
        ossClient.shutdown();
    }

    /**
     * 文件下载
     * @param objectName  下载的文件名
     */
    public static void downLoadFile(String objectName,String dPath){
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        // 下载OSS文件到本地文件。如果指定的本地文件存在会覆盖，不存在则新建。
        ossClient.getObject(new GetObjectRequest(bucketName, objectName), new File(dPath));
        // 关闭OSSClient。
        ossClient.shutdown();

    }

    /**
     * 3.查询桶里包含某个前缀下的所有文件
     * @param KeyPrefix
     */
    public static void listFile(String KeyPrefix){
// 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

// 列举文件。 如果不设置KeyPrefix，则列举存储空间下所有的文件。KeyPrefix，则列举包含指定前缀的文件。
        ObjectListing objectListing = ossClient.listObjects(bucketName,KeyPrefix);
        List<OSSObjectSummary> sums = objectListing.getObjectSummaries();
        for (OSSObjectSummary s : sums) {
            System.out.println("\t" + s.getKey());
        }

// 关闭OSSClient。
        ossClient.shutdown();
    }

    /**
     * 4.删除文件
     * @param objectName   全路径文件名
     */
    public  static void deleteFile(String objectName){

// 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

// 删除文件。如需删除文件夹，请将ObjectName设置为对应的文件夹名称。如果文件夹非空，则需要将文件夹下的所有object删除后才能删除该文件夹。
        ossClient.deleteObject(bucketName, objectName);

// 关闭OSSClient。
        ossClient.shutdown();


    }
}
