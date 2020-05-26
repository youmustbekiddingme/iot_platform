package com.clh.iot.common.company.ali.oss;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.common.utils.DateUtil;
import com.aliyun.oss.model.*;
import com.clh.iot.common.util.CloudStorage;
import org.apache.commons.lang3.builder.ToStringExclude;
import org.junit.Test;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * OSS云存储 常用接口
 * OSS 云存储和MNS消息服务结合使用
 */
public class OssUtil extends CloudStorage {
    // Endpoint以杭州为例，其它Region请按实际情况填写。
    public static String endpoint="http://oss-cn-shenzhen.aliyuncs.com";
    // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
    public static String accessKeyId = "LTAI4Fzp4qXtBxskJUSdj2CX";
    public static String accessKeySecret = "";
    public static String bucketName = "clh-gf-test";


    public static void main(String[] args) throws  Exception

    {


        //1.测试文件上传
//        String sPath="D://A065473_2020-04-22_11-18-11_Motion Detection.mp4";
//        String dPath="vedio/china/motion/A065473_2020-04-22_11-18-11_Motion Detection.mp4";


        String sPath="D://test111.txt";
        String dPath="vedio/test111.txt";
        OssUtil ossUtil = new OssUtil();
        ossUtil.upLoadFile(sPath,dPath);

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
    public  void upLoadFile(String sPath,String dPath) throws Exception{

        Map<String, String> tags = new HashMap<String, String>();
        tags.put("key0", "value0");


// 在http header中设置标签信息。
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setObjectTagging(tags);

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        // 上传文件流。
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(sPath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        PutObjectResult putObjectResult =ossClient.putObject(bucketName, dPath, inputStream,metadata);
        System.out.println(        putObjectResult.toString()
        );


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

    @Test
    public  void generateRule()throws  Exception{
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        // 创建SetBucketLifecycleRequest。
        SetBucketLifecycleRequest request = new SetBucketLifecycleRequest(bucketName);
        String ruleId0 = "rule0";
        String matchPrefix0 = "vedio/";  //桶名
        Map<String, String> matchTags0 = new HashMap<String, String>();
        matchTags0.put("key0", "value0");

        LifecycleRule rule = new LifecycleRule(ruleId0, matchPrefix0, LifecycleRule.RuleStatus.Enabled, 1);
        rule.setTags(matchTags0);
        request.AddLifecycleRule(rule);
        // 发起设置生命周期规则请求。
        ossClient.setBucketLifecycle(request);

        // 关闭OSSClient。
        ossClient.shutdown();
    }
    @Test
    public  void deleteLifeCircleRule(){
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        ossClient.deleteBucketLifecycle(bucketName);

// 关闭OSSClient。
        ossClient.shutdown();
    }
}
