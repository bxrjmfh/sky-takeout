package com.sky.controller.admin;

import com.sky.constant.MessageConstant;
import com.sky.result.Result;
import com.sky.utils.AliOssUtil;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping("/admin/common")
@Api(tags = "通用接口")
@Slf4j
public class CommonController {
    @Autowired
    private AliOssUtil aliOssUtil;

    /**
     * 文件上床
     * @param file
     * @return
     */
    @PostMapping(value = "/upload", consumes = "multipart/form-data")
    @ApiOperation(value = "文件上传", notes = "单个文件上传")
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "file",           // 必须和方法参数名一致
                    value = "要上传的文件",
                    required = true,
                    dataType = "file",       // 关键！写 "file" 或 "__file" 都可以，但 "file" 最常用
                    paramType = "form"       // 关键！必须是 form
            )
    })
    public Result<String> upload(@RequestParam("file") MultipartFile file){
        log.info("文件上传：{}",file);
        try{
            String originalFilename = file.getOriginalFilename();
            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            String objectName = UUID.randomUUID().toString() + extension;
            String filePath = aliOssUtil.upload(file.getBytes(), objectName);
            return Result.success(filePath);
        } catch (Exception e){
            log.error("文件上传失败：",e);
        }
        return Result.error(MessageConstant.UPLOAD_FAILED);
    }
}
