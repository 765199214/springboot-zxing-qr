package cn.linkpower.controller;

import java.io.File;
import java.util.UUID;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.linkpower.util.QRCodeUtil1;

@Controller
@Deprecated
public class QrCodeController1 {
	/**
     * 根据 url 生成 普通二维码
     */
    @RequestMapping(value = "/createCommonQRCode")
    public void createCommonQRCode(HttpServletResponse response,HttpServletRequest request) throws Exception {
        ServletOutputStream stream = null;
        try {
            stream = response.getOutputStream();
            String url = request.getParameter("url");
            //使用工具类生成二维码
            QRCodeUtil1.encode(url, stream);
        } catch (Exception e) {
            e.getStackTrace();
        } finally {
            if (stream != null) {
                stream.flush();
                stream.close();
            }
        }
    }
 
    /**
     * 根据 url 生成 带有logo二维码
     */
    @RequestMapping(value = "/createLogoQRCode")
    public void createLogoQRCode(HttpServletResponse response,HttpServletRequest request) throws Exception {
        ServletOutputStream stream = null;
        try {
            stream = response.getOutputStream();
//            String logoPath = Thread.currentThread().getContextClassLoader().getResource("").getPath() 
//                    + "templates" + File.separator +"logo-"+UUID.randomUUID().toString().trim().replaceAll("-", "")+ ".jpg";
            String logoPath = Thread.currentThread().getContextClassLoader().getResource("").getPath() 
                    + "templates" + File.separator +"logo.jpg";
            String url = request.getParameter("url");
            //使用工具类生成二维码
            QRCodeUtil1.encode(url, logoPath, stream, true);
        } catch (Exception e) {
            e.getStackTrace();
        } finally {
            if (stream != null) {
                stream.flush();
                stream.close();
            }
        }
    }
}
