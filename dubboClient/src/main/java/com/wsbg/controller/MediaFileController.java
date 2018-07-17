package com.wsbg.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wsbg.entity.MediaFile;
import com.wsbg.service.MediaFileService;

import net.sf.json.JSONArray;

/**
 * @desc 获取文件上传资料图片信息 
 *@author wsbg
 * 
 * **/
@Controller
@RequestMapping("/media")
public class MediaFileController extends BaseAction{
     
	 private static final Logger log = LoggerFactory.getLogger(MediaFileController.class);
	 
	 @Autowired
	 public MediaFileService mediaFileService;
	 
	 @RequestMapping(value="/list.do")
	 public void getMediaFileList(HttpServletRequest request,HttpServletResponse response) {
		    log.info("MediaFileController is  start .....");
		 //   response.setCharacterEncoding("UTF-8");
		 //   response.setContentType("text/html;charset=UTF-8");
		    //查询列表数据
		    MediaFile file  = new MediaFile();
		    file.setFileType(1);
		    List<MediaFile> list = mediaFileService.findList(file);
		    outTxt(response, JSONArray.fromObject(list).toString());
		    log.info("MediaFileController is  end .....");
		 
	 }
	 
	 @RequestMapping(value="/musiclist.do")
	 public void musicList(HttpServletRequest request,HttpServletResponse response) {
		    log.info("MediaFileController is  start .....");
		 //   response.setCharacterEncoding("UTF-8");
		 //   response.setContentType("text/html;charset=UTF-8");
		    //查询列表数据
		    MediaFile file  = new MediaFile();
		    file.setFileType(2);
		    List<MediaFile> list = mediaFileService.findList(file);
		    outTxt(response, JSONArray.fromObject(list).toString());
		    log.info("MediaFileController is  end .....");
		 
	 }
	 
	 @RequestMapping(value="/videolist.do")
	 public void videoList(HttpServletRequest request,HttpServletResponse response) {
		    log.info("MediaFileController is  start .....");
		 //   response.setCharacterEncoding("UTF-8");
		 //   response.setContentType("text/html;charset=UTF-8");
		    //查询列表数据
		    MediaFile file  = new MediaFile();
		    file.setFileType(3);
		    List<MediaFile> list = mediaFileService.findList(file);
		    outTxt(response, JSONArray.fromObject(list).toString());
		    log.info("MediaFileController is  end .....");
		 
	 }
}
