package com.lucky.shop.admin.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lucky.shop.admin.system.domain.SysFileInfo;
import com.lucky.shop.admin.system.domain.vo.Base64File;
import com.lucky.shop.admin.system.service.SysCfgService;
import com.lucky.shop.admin.system.service.SysFileInfoService;
import com.lucky.shop.common.constant.CfgKey;
import com.lucky.shop.common.utils.XlsUtils;
import lombok.extern.slf4j.Slf4j;
import org.jxls.common.Context;
import org.jxls.expression.JexlExpressionEvaluator;
import org.jxls.transform.Transformer;
import org.jxls.util.JxlsHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @Author Guo Huaijian
 * @Date 2020/6/5 17:15
 */
@Service
@Slf4j
public class FileService {

    @Autowired
    private SysCfgService cfgService;

    @Autowired
    private SysFileInfoService fileInfoService;

    /**
     * 文件上传
     * @param multipartFile
     * @return
     */
    public SysFileInfo upload(MultipartFile multipartFile){
        String uuid = UUID.randomUUID().toString();
        String originalFileName = multipartFile.getOriginalFilename();
        String realFileName =   uuid +"."+ originalFileName.split("\\.")[originalFileName.split("\\.").length-1];
        try {

            File file = new File(cfgService.getCfgValue(CfgKey.SYSTEM_FILE_UPLOAD_PATH) + File.separator+realFileName);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            multipartFile.transferTo(file);
            return save(multipartFile.getOriginalFilename(),file);
        } catch (Exception e) {

            log.error("保存文件异常",e);

        }
        return null;
    }
    /**
     * 文件上传
     * @param base64File
     * @return
     */
    public SysFileInfo upload(Base64File base64File){
        String uuid = UUID.randomUUID().toString();
        String originalFileName = base64File.getName();
        String realFileName =   uuid +"."+ originalFileName.split("\\.")[originalFileName.split("\\.").length-1];
        try {
            File file = new File(cfgService.getCfgValue(CfgKey.SYSTEM_FILE_UPLOAD_PATH) + File.separator+realFileName);
            if(base64ToFile(base64File.getBase64(),file)){
                return save(originalFileName,file);
            }

        } catch (Exception e) {
            log.error("保存文件异常",e);
        }
        return null;
    }

    private boolean base64ToFile(String base64, File file) {
        base64 = base64.substring(base64.indexOf(",")+1);
        BufferedOutputStream bos = null;
        java.io.FileOutputStream fos = null;
        try {

            byte[] bytes = org.apache.commons.codec.binary.Base64.decodeBase64(base64);
            fos = new java.io.FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(bytes);
            return true;
        } catch (Exception e) {
            log.error("base64转视频文件失败", e);
            return false;
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    log.error("关闭文件流bos失败", e);
                    return false;
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    log.error("关闭文件流fos失败", e);
                    return false;
                }
            }
        }
    }
    /**
     * 根据模板创建excel文件
     * @param template excel模板
     * @param fileName 导出的文件名称
     * @param data  excel中填充的数据
     * @return
     */
    public SysFileInfo createExcel(String template, String fileName, Map<String, Object> data){
        FileOutputStream outputStream = null;
        File file = new File(cfgService.getCfgValue(CfgKey.SYSTEM_FILE_UPLOAD_PATH) + File.separator+UUID.randomUUID().toString()+".xlsx");
        try {

            // 定义输出类型
            outputStream =new FileOutputStream(file);

            JxlsHelper jxlsHelper = JxlsHelper.getInstance();
            String templateFile = getClass().getClassLoader().getResource(template).getPath();
            InputStream is = new BufferedInputStream(new FileInputStream(templateFile));

            Transformer transformer = jxlsHelper.createTransformer(is, outputStream);
            Context context = new Context();
            for (Map.Entry<String, Object> entry : data.entrySet()) {
                context.putVar(entry.getKey(), entry.getValue());
            }

            JexlExpressionEvaluator evaluator = (JexlExpressionEvaluator) transformer.getTransformationConfig().getExpressionEvaluator();
            Map<String, Object> funcs = new HashMap<String, Object>(4);
            funcs.put("utils", new XlsUtils());
            evaluator.getJexlEngine().setFunctions(funcs);
            jxlsHelper.processTemplate(context, transformer);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                outputStream.flush();
                outputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return save(fileName,file);
    }
    /**
     * 创建文件
     * @param originalFileName
     * @param file
     * @return
     */
    public SysFileInfo save(String originalFileName,File file){
        try {
            SysFileInfo fileInfo = new SysFileInfo();
            fileInfo.setCreateTime(new Date());
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String account = authentication.getName();
            fileInfo.setCreateBy(Long.parseLong(account));
            fileInfo.setOriginalFileName(originalFileName);
            fileInfo.setRealFileName(file.getName());
            fileInfoService.save(fileInfo);
            return fileInfo;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public SysFileInfo get(Long id){
        SysFileInfo fileInfo = fileInfoService.getById(id);
        fileInfo.setAblatePath(cfgService.getCfgValue(CfgKey.SYSTEM_FILE_UPLOAD_PATH) + File.separator+fileInfo.getRealFileName());
        return fileInfo;
    }

    public SysFileInfo getByName(String fileName) {
        QueryWrapper<SysFileInfo> wrapper = new QueryWrapper<>();
        wrapper.eq(SysFileInfo.COL_REAL_FILE_NAME,fileName);
        SysFileInfo fileInfo = fileInfoService.getOne(wrapper);
        fileInfo.setAblatePath(cfgService.getCfgValue(CfgKey.SYSTEM_FILE_UPLOAD_PATH) + File.separator+fileInfo.getRealFileName());
        return fileInfo;
    }
}
