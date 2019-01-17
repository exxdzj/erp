package com.exx.dzj.controller.image;

import com.exx.dzj.facade.image.ImageUploadFacade;
import com.exx.dzj.result.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author
 * @Date 2019/1/15 0015 13:52
 * @Description 图片上传
 */
@RestController
@RequestMapping("image/")
public class ImageUploadController {

    @Autowired
    private ImageUploadFacade imageFacade;

    /**
     * 图片上传
     * @param request
     * @param response
     * @return
     */
    @PostMapping("imgUpload")
    public Result imgUpload(HttpServletRequest request, HttpServletResponse response, MultipartHttpServletRequest multiRequest){
        Result result = Result.responseSuccess();
        MultipartFile file = multiRequest.getFile("file");
        if(null == file){
            result.setCode(400);
            result.setMsg("未获取到上传的图片!");
            return result;
        }
        String filePath = imageFacade.uploadAliOSS(file);
        if(!StringUtils.isNotBlank(filePath)){
            result.setCode(400);
            result.setMsg("图片上传失败!");
            return result;
        }
        result.setData(filePath);
        return result;
    }
}
