package com.exx.dzj.facade.image;

import com.aliyun.oss.OSSClient;
import com.exx.dzj.config.AliyunOSS;
import com.exx.dzj.facade.customer.CustomerSupplierFacade;
import com.exx.dzj.unique.DefaultIdGenerator;
import com.exx.dzj.unique.DefaultIdGeneratorConfig;
import com.exx.dzj.unique.IdGenerator;
import com.exx.dzj.unique.IdGeneratorConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.ws.soap.Addressing;

/**
 * @Author
 * @Date 2019/1/15 0015 14:19
 * @Description 图片上传(在分布式中，上传模块可以单独的拉出来)
 */
@Component
public class ImageUploadFacade {

    private final static Logger LOGGER = LoggerFactory.getLogger(ImageUploadFacade.class);

    @Autowired
    private AliyunOSS aliyunOSS;

    /**
     * 图片上传到 阿里云存储 (方法很粗糙,有待改进)
     * @param file
     * @return
     */
    public String uploadAliOSS(MultipartFile file, String subdir, String prefix){
        if(null == file){
            return null;
        }
        OSSClient ossClient = null;
        // 上传文件流
        String uploadPath = null;
        try {

            String endpoint = aliyunOSS.getEndpoint();
            String accessKeyId = aliyunOSS.getAccessKeyId();
            String accessKeySecret = aliyunOSS.getAccessKeySecret();
            String bucketName = aliyunOSS.getBucketName();
            //String subdir = aliyunOSS.getSubdir();
            String active = aliyunOSS.getActive();

            // https://academy-server-test.oss-cn-shenzhen.aliyuncs.com/academy-server/0000aa61cc1fbdadb1cd1d0c1aa02e30.png
            //获取配置文件对象,通过配置文件获取相关信息
            /**endpoint = "http://oss-cn-shenzhen.aliyuncs.com";
            accessKeyId = "LTAI24Fekt4cpDoy";
            accessKeySecret = "iw4Yp42IqB5wh461RITJJexpCaFhJw";
            bucketName = "academy-server";
            subdir = "academy-server/";*/

            //String prefix = "producet";
            IdGeneratorConfig config = new DefaultIdGeneratorConfig(prefix);
            IdGenerator idGenerator = new DefaultIdGenerator(config);

            final String fileName = idGenerator.next();
            String tempFileName = file.getOriginalFilename();
            String fileType = tempFileName.substring(tempFileName.lastIndexOf(".")+1, tempFileName.length()).toLowerCase();
            fileType="."+fileType;

            // 创建OSSClient实例
            ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
            ossClient.putObject(bucketName, subdir + "/" + active + "/" +fileName+fileType, file.getInputStream());
            uploadPath = new StringBuilder("http://").append(bucketName).append(".oss-cn-shenzhen.aliyuncs.com/").append(subdir).append("/").append(active).append("/").append(fileName).append(fileType).toString();
        } catch (Exception ex) {
            LOGGER.error("异常方法:{}异常信息:{}", ImageUploadFacade.class.getName()+".uploadAliOSS", ex.getMessage());
        } finally {
            // 关闭client
            ossClient.shutdown();
        }
        return uploadPath;
    }
}
