package com.wsbg.controller;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.wsbg.common.Pager;
import com.wsbg.common.ResultData;
import com.wsbg.entity.MediaFile;
import com.wsbg.entity.User;
import com.wsbg.service.MediaFileService;
import com.wsbg.service.UserService;
import com.wsbg.common.AllTool;
import com.wsbg.common.PropertieUtil;

/***
 * 
 * 用户信息
 * */
@Controller
@RequestMapping("/user/")
public class UserAction extends BaseAction{
      
	  private final Logger logger = LoggerFactory.getLogger(UserAction.class);
	  
	  @Autowired
	  public UserService userService;
	  
	  @Autowired
	  public MediaFileService mediaFileService;
	  
	  @RequestMapping("list")
	  public String findUserAll(HttpServletRequest request,Model model,Pager page){
		     logger.info("get user list start .......");
		     //获取当前页码数
		     setCurrentPg(page);
		     ResultData result= userService.findUserAllPageList(page);
		     model.addAttribute("userList",result.getList());
		     model.addAttribute("pager", result.getPage());
		     logger.info("get user list end .......");
		     return "page/user/user-list";
	  }
	  
     @RequestMapping(value="logout",method=RequestMethod.GET)      
     public String logout(RedirectAttributes redirectAttributes,HttpServletRequest request){     
        //使用权限管理工具进行用户的退出，跳出登录，给出提示信息    
        logout(redirectAttributes);
        return "redirect:/login";    
     }     
     
     @RequestMapping(value="authorUser")
     public String authorUser(User user){
		return "page/user/author-user";
     }

     /***
	  * @desc 访问无权限页面
	  * @author wsbg
	  * */
    @RequestMapping("403")    
    public String unauthorizedRole(){    
        return "403";    
    }

	/****
	 * @desc 删除用户访问数据
	 */
	@RequestMapping("delete/{uId}.do")
	@ResponseBody
	public Integer deleteUserById(@PathVariable Integer uId){
		logger.info("[UserAction]deleteUserById: is start....."+uId);
		int in = userService.deleteByUserId(uId);
		if(in>0)
			  logger.info("delete user success");
		else
			  logger.info("delete user failed");
		logger.info("[UserAction]deleteUserById: is end....."+uId);
		return in;
	}

	/****
	 * @desc 禁用用户
	 * @wsbg
	 */
	@RequestMapping("disable/{uId}.do")
	@ResponseBody
	public Integer disableById(@PathVariable Integer uId,Integer isable){
		logger.info("[UserAction]disableById:is start" +uId);
		isable = isable==0?1:0;
		int in = userService.disableById(isable,uId);
		if(in>0)
			logger.info("disabled user success");
		else
			logger.info("disabled user failed");
		logger.info("[UserAction]disableById:is end");
		return in;
	}
	
	 /***
	  * @desc 访问无权限页面
	  * @author wsbg
	  * */
   @RequestMapping("add.do")    
   public String adduserObj(HttpServletRequest request,User user,Model model,HttpServletResponse response){    
	   model.addAttribute("user",user);
	   return "page/user/user-add";
   }

	/**
	 * @desc 新增用户数据内容
	 * **/
	@RequestMapping(value="/save.do",method=RequestMethod.POST)
	public  String saveUser(HttpServletRequest request,HttpServletResponse response,MultipartFile file,User user,Model model){
		logger.info("start operation user .........");
		try{
			 if(user==null || StringUtils.isEmpty(user.getUsername())){
				  model.addAttribute("user",user);
				  return "page/user/user-add";
			 }
			 //执行用户新增或者编辑操作
			 if(user.getUserId()!=null && user.getUserId()>0){
				  userService.update(user);
			 }else{
				  user.setCreateTime(new Date());
				  Session session= SecurityUtils.getSubject().getSession();
				  user.setIsable(0);//默认可用
				  user.setCreateUser(getUser(session).getUsername());
				  userService.save(user);
			 }
		}catch(Exception e){
			 logger.debug("操作用户失败!");
		}
		return "redirect:list.do";
	}
   
	  @RequestMapping("upload.do") 
	  @ResponseBody
	   public JSONObject uploadFile(HttpServletRequest request,MultipartFile file,HttpServletResponse response){    
		  JSONObject json=uploadfilehead(file,response);
		   return json;
	   }
	
   /**
    * @desc 将文件上传到文件服务器
    * @param multipartfile file
    * ***/
   protected JSONObject uploadfilehead(MultipartFile file,HttpServletResponse response){
	     JSONObject json = new JSONObject();
    	 if(file.isEmpty()){
    		  json.put("status", false);
    		  json.put("Data", "");
    		  json.put("msg", "头像不能为空!");
    		  return json;
    	 }
    	 //文件要小于100k
    	 long size = file.getSize();
    	 if(size>102400){
    		 json.put("status", false);
    		 json.put("Data", "");
    		 json.put("msg", "文件过大!");
    		 return json;
    	 }
    	 if(size==0){
    		 json.put("status", false);
    		 json.put("Data", "");
    		 json.put("msg", "文件内容为空!");
    		 return json;
    	 }
    	 //获取文件名称
    	 String fileName = file.getOriginalFilename();
    	 //获取文件后缀
    	 String suffix = fileName.substring(fileName.indexOf(".")+1);
    	 //为文件生成新的文件名称
    	 String newFileName = "tx_"+System.currentTimeMillis()+AllTool.randomNum(6)+"."+suffix;
    	 //文件上传文件服务器路径 +File.separator+newFileName  
    	 String imgurl =  new PropertieUtil("/prop/config.properties").readProperty("img_file_server_url");
    	 String requrl =  new PropertieUtil("/prop/config.properties").readProperty("img_file_request_url");
    	 logger.info("图片urlimgurl:"+imgurl);
    	 try {
			InputStream input = file.getInputStream();
			JSONObject jsons= FileUploadUtil.upload(requrl+imgurl, newFileName,input);
			//暂时不用ftp上传
			if((Integer)jsons.get("status")==1){
				 json.put("status", false);
	    		 json.put("Data", "");
	    		 json.put("msg", "上传失败!");
	    		 return json;
			}else{
				//存放图片信息
				 MediaFile mediafile = new MediaFile();
				 mediafile.setComeFrom(1);
				 mediafile.setCreateDate(new Date());
				 mediafile.setCreateMan(getUser(SecurityUtils.getSubject().getSession()).getUsername());
				 mediafile.setFileName(newFileName);
				 mediafile.setFileSize(size*0.01/1024); //计算成kb
				 mediafile.setFileType(1);
				 mediafile.setIsDel(0);
				 mediafile.setLikeNum(Long.valueOf(AllTool.randNum().toString()));
				// mediafile.setImgDesc("");//
				 mediafile.setUrl(String.valueOf(jsons.get("url")));
				 //保存对象
				 mediaFileService.saveObj(mediafile);
				 json.put("status", true);
	    		 json.put("Data", jsons.get("url"));
	    		 json.put("msg", "上传成功!");
			}
			
		} catch (IOException e) {
			logger.error("上传文件失败!");
			 json.put("status", false);
    		 json.put("Data", "");
    		 json.put("msg", e.getMessage());
    		 return json;
		}
         return json;	 
     }
   
   
}

