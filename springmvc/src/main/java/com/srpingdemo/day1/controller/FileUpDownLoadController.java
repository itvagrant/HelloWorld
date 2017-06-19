package com.srpingdemo.day1.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

/**
 * 处理文件上传/下载的控制器
 * @author bwfadmin
 */
@Controller
@RequestMapping("/file")
public class FileUpDownLoadController {
	
	/**
	 * 单文件上传
	 * 注入request是为了通过servletContext获取webapp下目录的绝对路径
	 * MultipartFile---关键的Spring用来完成上传的类
	 */
	@RequestMapping(value="/one-upload",method=RequestMethod.POST)
	public String doUpload(@RequestParam("multipartFile") MultipartFile mtfile,
			HttpServletRequest request){
		String filename = mtfile.getOriginalFilename();
		
		String path = request.getSession().getServletContext().getRealPath("/upload");
		
		try {
			mtfile.transferTo(new File(path,filename));
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "home";
	}
	
	/**
	 * 多文件上传
	 * @param mtfiles 注意，多个file项的name要一样file，才能构建一个MultipartFile数组
	 * @param request
	 * @return 
	 */
	@RequestMapping(value="/many-upload",method=RequestMethod.POST)
	public String doMultipleUpload(@RequestParam("file") MultipartFile[] mtfiles,
			HttpServletRequest request){
		String path = request.getSession().getServletContext().getRealPath("/upload");
		/*
		 * 遍历MultipartFile数组保存即可
		 */
		for (MultipartFile mf : mtfiles) {
			String filename = mf.getOriginalFilename();
			try {
				mf.transferTo(new File(path,filename));
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return "home";
	}
	
	
	public ResponseEntity<byte[]> download(@RequestParam("/download") String filename,
			HttpServletRequest request){
		String path = request.getSession().getServletContext().getRealPath("/upload");
		
		File file = new File(path,filename);
		
		//替代请求头和响应头的总头
		HttpHeaders headers = new HttpHeaders();
		
		try {
			headers.setContentDispositionFormData("attachment", new String(filename.getBytes("utf-8"),"ISO-8859-1"));
		
			/*application/octet-stream这个头，即代表二进制流的传输形式，因为用来传文件，
			 * 不知道文件的格式，所以统一用二进制流，通吃
			 */
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			
			/*将file文件读取为字节数组放入响应实体中，因为上面配置了传输形式是二进制流的形式，状态码返回200
			 * FileUtils是commons-io包中的
			 */
			return new ResponseEntity<>(FileUtils.readFileToByteArray(file),HttpStatus.OK);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	/**
	 * 通过解析request实现文件上传
	 * 了解即可，平时开发，谁自己创建啊
	 * */
	@RequestMapping(value="/otherup",method=RequestMethod.POST)
	public String testOtherFileUp(HttpServletRequest request){
		
		//手动创建CommonsMultipartResolver 解析器  不是使用的SpringIOC容器中的解析器
		CommonsMultipartResolver resolver=new CommonsMultipartResolver(request.getSession().getServletContext());
		
		//检查form  enctype="multipart/form-data"
		if(resolver.isMultipart(request)){
			
			MultipartHttpServletRequest multipartRequset=(MultipartHttpServletRequest)request;
			
			Iterator<String> iter = multipartRequset.getFileNames();
			
			while(iter.hasNext()){
				
				MultipartFile multipartFile=multipartRequset.getFile(iter.next());
				
				String filename=multipartFile.getOriginalFilename();
				
				String uploadpath=request.getSession().getServletContext().getRealPath("/upload");
				
				try {
					multipartFile.transferTo(new File(uploadpath, filename));
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
			
		}
		
		return "home";
	}
}
