package cn.linkpower.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.zxing.Result;

import cn.linkpower.util.QRCodeUtil2;

@Controller
public class QrCodeController2 {
	private Logger log = LoggerFactory.getLogger(QrCodeController2.class);
	/**
	 * 获取普通二维码
	 * @param request
	 * @param response
	 */
	@RequestMapping("/getNormalQR")
	public void getNormalQR(HttpServletRequest request,HttpServletResponse response){
		String content = request.getParameter("text");
		ServletOutputStream stream;
		try {
			stream = response.getOutputStream();
			//图片存放再指定的   项目全路径\target\classes\ 下
			String path = Thread.currentThread().getContextClassLoader().getResource("").getPath()       
					+ "static" + File.separator +"photo"+ File.separator;
			
			//String size = request.getParameter("");
			QRCodeUtil2.zxingCodeCreate(content, path, 500, null,stream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取带logo的二维码
	 * @param request
	 * @param response
	 */
	@RequestMapping("/getLogoQR")
	public void getLogoQR(HttpServletRequest request,HttpServletResponse response){
		String content = request.getParameter("text");
		ServletOutputStream stream;
		try {
			stream = response.getOutputStream();
			//图片存放再指定的   项目全路径\target\classes\templates 下
			String path = Thread.currentThread().getContextClassLoader().getResource("").getPath()       
					+ "static" + File.separator +"photo"+ File.separator;
			//logo是在resources目录下，项目编译后打包部署，是在   项目地址\target\classes\static 下
			String logoPath = Thread.currentThread().getContextClassLoader().getResource("").getPath()
					+"static"+File.separator+"logo.jpg";
			QRCodeUtil2.zxingCodeCreate(content, path, 500, logoPath,stream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 解析 图片信息并打印
	 * @param request
	 * @return
	 */
	@RequestMapping("/analysiscode")
	@ResponseBody
	public Result analysiscode(HttpServletRequest request){
		String name = request.getParameter("name");
		String path = Thread.currentThread().getContextClassLoader().getResource("").getPath()       
				+ "static" + File.separator +"photo"+ File.separator+name;
		log.info("File.separator---->"+File.separator);
		log.info("图片路径---->"+path);
		Result result = QRCodeUtil2.zxingCodeAnalyze(path);
		
		return result;
	}
}
