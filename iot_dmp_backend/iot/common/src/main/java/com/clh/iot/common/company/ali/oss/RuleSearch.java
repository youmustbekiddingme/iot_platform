package com.clh.iot.common.company.ali.oss;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.LifecycleRule;

import java.util.List;

public class RuleSearch {
    public static void main(String[] args) {
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = "http://oss-cn-shenzhen.aliyuncs.com";
// 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录RAM控制台创建RAM账号。
        String accessKeyId = "LTAI4Fzp4qXtBxskJUSdj2CX";
        String accessKeySecret = "jGSPIcYlvjAtb6LgjW9XfScfGeVdSO";
        String bucketName = "clh-gf-test";

// 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

// 获取生命周期规则。
        List<LifecycleRule> rules = ossClient.getBucketLifecycle(bucketName);

// 查看生命周期规则。
        for (LifecycleRule r : rules) {
            System.out.println("================");

            // 查看规则id。
            System.out.println("rule id: " + r.getId());

            // 查看规则状态。
            System.out.println("rule status: " + r.getStatus());

            // 查看规则前缀。
            System.out.println("rule prefix: " + r.getPrefix());

            // 查看规则标签。
            if (r.hasTags()) {
                System.out.println("rule tagging: "+ r.getTags().toString());
            }

            // 查看过期天数规则。
            if (r.hasExpirationDays()) {
                System.out.println("rule expiration days: " + r.getExpirationDays());
            }

            // 查看过期日期规则。
            if (r.hasCreatedBeforeDate()) {
                System.out.println("rule expiration create before days: " + r.getCreatedBeforeDate());
            }

            // 查看过期分片规则。
            if(r.hasAbortMultipartUpload()) {
                if(r.getAbortMultipartUpload().hasExpirationDays()) {
                    System.out.println("rule abort uppart days: " + r.getAbortMultipartUpload().getExpirationDays());
                }

                if (r.getAbortMultipartUpload().hasCreatedBeforeDate()) {
                    System.out.println("rule abort uppart create before date: " + r.getAbortMultipartUpload().getCreatedBeforeDate());
                }
            }

            // 查看存储类型转换规则。
            if (r.getStorageTransition().size() > 0) {
                for (LifecycleRule.StorageTransition transition : r.getStorageTransition()) {
                    if (transition.hasExpirationDays()) {
                        System.out.println("rule storage trans days: " + transition.getExpirationDays() +
                                " trans storage class: " + transition.getStorageClass());
                    }

                    if (transition.hasCreatedBeforeDate()) {
                        System.out.println("rule storage trans before create date: " + transition.getCreatedBeforeDate());
                    }
                }
            }

            // 查看是否自动删除过期删除标记。
            if (r.hasExpiredDeleteMarker()) {
                System.out.println("rule expired delete marker: " + r.getExpiredDeleteMarker());
            }

            // 查看非当前版本object存储类型转换规则。
            if (r.hasNoncurrentVersionStorageTransitions()) {
                for (LifecycleRule.NoncurrentVersionStorageTransition transition : r.getNoncurrentVersionStorageTransitions()) {
                    System.out.println("rule noncurrent versions trans days:" + transition.getNoncurrentDays() +
                            " trans storage class: " + transition.getStorageClass());
                }
            }

            // 查看非当前版本object过期规则。
            if (r.hasNoncurrentVersionExpiration()) {
                System.out.println("rule noncurrent versions expiration days:" + r.getNoncurrentVersionExpiration().getNoncurrentDays());
            }
        }

// 关闭OSSClient。
        ossClient.shutdown();
    }
}
