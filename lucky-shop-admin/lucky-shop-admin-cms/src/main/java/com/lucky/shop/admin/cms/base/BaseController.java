package com.lucky.shop.admin.cms.base;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lucky.shop.common.core.utils.HttpUtil;
import com.lucky.shop.common.core.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLDecoder;

/**
 * 基础controller
 *
 * @Author Guo Huaijian
 * @Date 2020/6/15 14:13
 */
public class BaseController {

    protected static Logger logger = LoggerFactory.getLogger(BaseController.class);
//
//    public Long getIdUser( ) {
//        String token = HttpUtil.getRequest().getHeader("Authorization");
//
//        Long idUser = JwtUtil.getUserId(token);
//        if (idUser == null) {
//            throw new RuntimeException("用户不存在");
//        }
//        return idUser;
//    }
    /**
     * 根据token获取用户id，如果不存在则抛出异常
     *
     * @param request
     * @return
     */
//    public Long getIdUser(HttpServletRequest request) {
//        String token = request.getHeader("Authorization");
//
//        Long idUser = JwtUtil.getUserId(token);
//        if (idUser == null) {
//            throw new RuntimeException("用户不存在");
//        }
//        return idUser;
//    }

    /**
     * 获取客户端token
     *
     * @param request
     * @return
     */
    public String getToken(HttpServletRequest request) {
        return request.getHeader("Authorization");
    }

    public String getToken() {
        return HttpUtil.getRequest().getHeader("Authorization");
    }

    /**
     * 获取客户端ip
     *
     * @param req
     * @return
     */
//    public String getRealIp(HttpServletRequest req) {
//        String ip = req.getHeader("x-forwarded-for");
//
//        if (ip == null || ip.length() == 0 || ApiConstants.IP_UNKNOW.equalsIgnoreCase(ip)) {
//            ip = req.getHeader("Proxy-Client-IP");
//        }
//        if (ip == null || ip.length() == 0 || ApiConstants.IP_UNKNOW.equalsIgnoreCase(ip)) {
//            ip = req.getHeader("WL-Proxy-Client-IP");
//        }
//        if (ip == null || ip.length() == 0 || ApiConstants.IP_UNKNOW.equalsIgnoreCase(ip)) {
//            ip = req.getRemoteAddr();
//        }
//        if (ip == null || ip.length() == 0 || ApiConstants.IPV6_LOCALHOST.equals(ip)) {
//            ip =ApiConstants.IPV4_LOCALHOST;
//        }
//        return ip;
//    }

    /**
     * 获取前端传递过来的json字符串<br>
     * 如果前端使用axios的data方式传参则使用改方法接收参数
     *
     * @return
     */
    public String getjsonReq() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(HttpUtil.getRequest().getInputStream()));
            String line = null;
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {
                sb.append(line);

            }
            br.close();
            if (sb.length() < 1) {
                return "";
            }
            String reqBody = URLDecoder.decode(sb.toString(), "UTF-8");
            reqBody = reqBody.substring(reqBody.indexOf("{"));
            return reqBody;

        } catch (IOException e) {

            logger.error("获取json参数错误！{}", e.getMessage());

            return "";

        }

    }

    public <T> T getFromJson(Class<T> klass) {
        String jsonStr = getjsonReq();
        if (StringUtil.isEmpty(jsonStr)) {
            return null;
        }
        JSONObject json = JSONObject.parseObject(jsonStr);
        return JSON.toJavaObject(json, klass);
    }

//    public String detectOS(HttpServletRequest req) {
//        if (req != null) {
//            UserAgent userAgent = UserAgent.parseUserAgentString(req.getHeader("User-Agent"));
//            OperatingSystem os = userAgent.getOperatingSystem();
//            return os.getName();
//        }
//        return null;
//    }
}
