package com.lucky.shop.mobile.ucenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lucky.shop.admin.system.api.RemoteFileService;
import com.lucky.shop.admin.system.api.RemoteSysCfgService;
import com.lucky.shop.admin.system.api.domain.SysFileInfo;
import com.lucky.shop.common.core.constant.CfgKey;
import com.lucky.shop.common.core.tool.Maps;
import com.lucky.shop.common.core.utils.CryptUtil;
import com.lucky.shop.common.core.utils.HttpUtil;
import com.lucky.shop.common.core.utils.StringUtil;
import com.lucky.shop.common.core.utils.XlsUtils;
import com.lucky.shop.mobile.ucenter.domain.Base64File;
import com.lucky.shop.mobile.ucenter.domain.ShopUser;
import com.lucky.shop.mobile.ucenter.service.ShopUserService;
import lombok.extern.slf4j.Slf4j;
import org.jxls.common.Context;
import org.jxls.expression.JexlExpressionEvaluator;
import org.jxls.transform.Transformer;
import org.jxls.util.JxlsHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @Author Guo Huaijian
 * @Date 2020/6/24 15:17
 */
@Service
@Slf4j
public class FileService {

    @Autowired
    private RemoteSysCfgService cfgService;

    @Autowired
    private RemoteFileService fileService;

    @Autowired
    private ShopUserService userService;

    /**
     * 上传文件
     *
     * @param multipartFile
     * @return
     */
    public Object uploadFile(MultipartFile multipartFile) {
        try {
            SysFileInfo fileInfo = this.upload(multipartFile);
            return fileInfo;
        } catch (Exception e) {
            log.error("上传文件异常", e);
            return "上传文件失败";
        }
    }

    /**
     * 上传文件
     *
     * @param base64File
     * @return
     */
    public Object uploadUploadFileBase64(Base64File base64File) {
        try {
            SysFileInfo fileInfo = this.upload(base64File);
            ShopUser user = this.getCurrentUser();
            user.setAvatar(String.valueOf(fileInfo.getRealFileName()));
            userService.updateById(user);
            return user;
        } catch (Exception e) {
            log.error("上传文件异常", e);
            return "上传文件失败";
        }
    }

    /**
     * 下载文件
     *
     * @param fileName
     */
    public void download(String fileName) {
        SysFileInfo fileInfo = this.getByName(fileName);
        fileName = StringUtil.isEmpty(fileName) ? fileInfo.getOriginalFileName() : fileName;
        HttpServletResponse response = HttpUtil.getResponse();
        response.setContentType("application/x-download");
        try {
            fileName = new String(fileName.getBytes(), "ISO-8859-1");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        byte[] buffer = new byte[1024];
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            File file = new File(fileInfo.getAblatePath());
            os = response.getOutputStream();
            fis = new FileInputStream(file);
            bis = new BufferedInputStream(fis);
            int i = bis.read(buffer);
            while (i != -1) {
                os.write(buffer);
                i = bis.read(buffer);
            }

        } catch (Exception e) {
            log.error("download error", e);
        } finally {
            try {
                bis.close();
                fis.close();
            } catch (IOException e) {
                log.error("close inputstream error", e);
            }
        }
    }

    /**
     * 获取base64图片数据
     *
     * @param fileName
     * @return
     */
    public Object getImgBase64(@RequestParam("idFile") String fileName) {
        SysFileInfo fileInfo = this.getByName(fileName);
        FileInputStream fis = null;
        try {
            File file = new File(fileInfo.getAblatePath());
            byte[] bytes = new byte[(int) file.length()];
            fis = new FileInputStream(file);
            BufferedInputStream bis = new BufferedInputStream(fis);
            bis.read(bytes);
            String base64 = CryptUtil.encodeBASE64(bytes);
            return Maps.newHashMap("imgContent", base64);
        } catch (Exception e) {
            log.error("get img error", e);
            return "获取图片异常";
        } finally {
            try {
                fis.close();
            } catch (Exception e) {
                log.error("close getImgBase64 error", e);
            }
        }
    }

    /**
     * 获取图片流
     *
     * @param response
     * @param fileName
     */
    public void getImgStream(HttpServletResponse response,
                             @RequestParam("idFile") String fileName) {
        SysFileInfo fileInfo = this.getByName(fileName);
        FileInputStream fis = null;
        response.setContentType("image/" + fileInfo.getRealFileName().split("\\.")[1]);
        try {
            OutputStream out = response.getOutputStream();
            File file = new File(fileInfo.getAblatePath());
            fis = new FileInputStream(file);
            byte[] b = new byte[fis.available()];
            fis.read(b);
            out.write(b);
            out.flush();
        } catch (Exception e) {
            log.error("getImgStream error", e);
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    log.error("close getImgStream error", e);
                }
            }
        }
    }


    /**
     * 文件上传
     *
     * @param multipartFile
     * @return
     */
    public SysFileInfo upload(MultipartFile multipartFile) {
        String uuid = UUID.randomUUID().toString();
        String originalFileName = multipartFile.getOriginalFilename();
        String realFileName = uuid + "." + originalFileName.split("\\.")[originalFileName.split("\\.").length - 1];
        try {
            File file = new File(cfgService.getCfgValue(CfgKey.SYSTEM_FILE_UPLOAD_PATH) + File.separator + realFileName);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            multipartFile.transferTo(file);
            return save(multipartFile.getOriginalFilename(), file);
        } catch (Exception e) {
            log.error("保存文件异常", e);
        }
        return null;
    }

    /**
     * 文件上传
     *
     * @param base64File
     * @return
     */
    public SysFileInfo upload(Base64File base64File) {
        String uuid = UUID.randomUUID().toString();
        String originalFileName = base64File.getName();
        String realFileName = uuid + "." + originalFileName.split("\\.")[originalFileName.split("\\.").length - 1];
        try {
            File file = new File(cfgService.getCfgValue(CfgKey.SYSTEM_FILE_UPLOAD_PATH) + File.separator + realFileName);
            if (base64ToFile(base64File.getBase64(), file)) {
                return save(originalFileName, file);
            }
        } catch (Exception e) {
            log.error("保存文件异常", e);
        }
        return null;
    }

    private boolean base64ToFile(String base64, File file) {
        base64 = base64.substring(base64.indexOf(",") + 1);
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
     *
     * @param template excel模板
     * @param fileName 导出的文件名称
     * @param data     excel中填充的数据
     * @return
     */
    public SysFileInfo createExcel(String template, String fileName, Map<String, Object> data) {
        FileOutputStream outputStream = null;
        File file = new File(cfgService.getCfgValue(CfgKey.SYSTEM_FILE_UPLOAD_PATH) + File.separator + UUID.randomUUID().toString() + ".xlsx");
        try {
            // 定义输出类型
            outputStream = new FileOutputStream(file);

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
        return save(fileName, file);
    }

    /**
     * 创建文件
     *
     * @param originalFileName
     * @param file
     * @return
     */
    public SysFileInfo save(String originalFileName, File file) {
        try {
            SysFileInfo fileInfo = new SysFileInfo();
            fileInfo.setCreateTime(new Date());
            fileInfo.setCreateBy(getCurrentUser().getId());
            fileInfo.setOriginalFileName(originalFileName);
            fileInfo.setRealFileName(file.getName());
            fileService.save(fileInfo);
            return fileInfo;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public SysFileInfo get(Long id) {
        SysFileInfo fileInfo = fileService.getOne(id);
        fileInfo.setAblatePath(cfgService.getCfgValue(CfgKey.SYSTEM_FILE_UPLOAD_PATH) + File.separator + fileInfo.getRealFileName());
        return fileInfo;
    }

    public SysFileInfo getByName(String fileName) {
        SysFileInfo fileInfo = fileService.getByFileName(fileName);
        fileInfo.setAblatePath(cfgService.getCfgValue(CfgKey.SYSTEM_FILE_UPLOAD_PATH) + File.separator + fileInfo.getRealFileName());
        return fileInfo;
    }

    private ShopUser getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String account = authentication.getName();
        QueryWrapper<ShopUser> wrapper = new QueryWrapper<>();
        wrapper.eq(ShopUser.COL_MOBILE, account);
        ShopUser user = userService.getOne(wrapper);
        return user;
    }
}
