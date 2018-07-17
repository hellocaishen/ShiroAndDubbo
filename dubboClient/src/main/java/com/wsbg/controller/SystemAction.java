package com.wsbg.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wsbg.common.Pager;
import com.wsbg.common.ResultData;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wsbg.entity.User;
import com.wsbg.service.ResourceService;
import com.wsbg.service.UserService;
import org.springframework.web.servlet.ModelAndView;


/**
 * @desc 系统菜单的处理
 * @author wsbg
 * */
@Controller
@RequestMapping("/system/")
public class SystemAction extends BaseAction{
      
	   private Logger logger = LoggerFactory.getLogger(SystemAction.class);
	   
	   @Resource
	   private UserService userService;
	   @Resource
	   private ResourceService resourceService;
	   
	   //@RequestMapping(value="menu.do",produces="application/json;charset=utf-8")
	   @RequestMapping("menu.do")
	   public void  findMenu(User user,HttpServletResponse response){
		   response.setCharacterEncoding("utf-8");
		   logger.info("[SystemAction] findMenu is start");
		   Session session = SecurityUtils.getSubject().getSession();
		   user= getUser(session);
		   logger.info("[SystemAction] findMenu :param----username:"+user.getUsername());
		   //根据用户id获取用户数据
		   user=userService.findUserByAccount(user);
		   //获取数据
		   List<Integer> list = userService.findResourceIds(user.getUsername());
		   Set<com.wsbg.entity.Resource>  sets = userService.findMenuByUserAccount(user.getUsername());
		   String menuJsonStr = menuJsonStr(sets,list);
		   //拼装数据
		  try {
			  response.getWriter().write(menuJsonStr);
		  } catch (IOException e) {
			  e.printStackTrace();
			  logger.info("输出失败！");
		   }
		   logger.info(menuJsonStr);
		   logger.info("[SystemAction] findMenu is end");
		 //  return menuJsonStr;
	   }
	   
	   /**
	    * @desc 拼装菜单json数据串
	    * @author liguocai
	    * @param Set<com.wsbg.entity.Resource>  sets
	    * @return
	    */
	   private String menuJsonStr(Set<com.wsbg.entity.Resource>  sets,List<Integer> list){
		     JSONArray  parentArr = new JSONArray();
		     for (com.wsbg.entity.Resource resource : sets) {
		    	 boolean bool = isExist(list, resource.getResourceId());
		    	 if(bool){
		    		 JSONObject parentObj = new JSONObject();
		    		 parentObj.put("id", String.valueOf(resource.getResourceId()));
		    		 parentObj.put("name",resource.getName());
		    		 parentObj.put("parentId", "0");
		    		 parentObj.put("url",StringUtils.isEmpty(resource.getUrl())?"":resource.getUrl());
		    		 parentObj.put("icon", "");//图标地址
		    		 parentObj.put("order", "1");//排序
		    		 if(StringUtils.isEmpty(resource.getUrl())){
		    			 parentObj.put("isHeader","1");//是否父级标签
		    			 JSONArray  sonArr = new JSONArray();
		    			 Set<com.wsbg.entity.Resource> set = resource.getResourceSet();
		    			 if(set!=null && set.size()>0){
		    				 for (com.wsbg.entity.Resource resou : set) {
		    			    	 boolean bool2 = isExist(list, resou.getResourceId());
		    				     if(bool2){
		    				    	 JSONObject parentObj2 = new JSONObject();
			    					 parentObj2.put("id", String.valueOf(resou.getResourceId()));
			    					 parentObj2.put("name",resou.getName());
			    					 parentObj2.put("parentId", String.valueOf(resource.getResourceId()));
			    					 parentObj2.put("url",StringUtils.isEmpty(resou.getUrl())?"":resou.getUrl());
			    					 parentObj2.put("icon", "&#xe604;");//图标地址 
			    					 parentObj2.put("order", "1");//排序	
			    					 parentObj2.put("isHeader","0");//是否父级标签
			    					 if(StringUtils.isEmpty(resou.getUrl())){
			    						 JSONArray  sonArr2 = new JSONArray();
			    						 Set<com.wsbg.entity.Resource> set2 = resou.getResourceSet();
			    						 if(set2!=null && set2.size() >0 ){
			    							 for (com.wsbg.entity.Resource resource2 : set2) {
			    		    			    	 boolean bool3 = isExist(list, resource2.getResourceId());
			    								 if(bool3){
			    									 JSONObject parentObj3 = new JSONObject();
				    								 parentObj3.put("id", String.valueOf(resource2.getResourceId()));
				    								 parentObj3.put("name",resource2.getName());
				    								 parentObj3.put("parentId", String.valueOf(resou.getResourceId()));
				    								 parentObj3.put("url",StringUtils.isEmpty(resource2.getUrl())?"":resource2.getUrl());
				    								 parentObj3.put("icon", "");//图标地址
				    								 parentObj3.put("order", "0");//排序	
				    								 parentObj3.put("isHeader","0");//是否父级标签
				    								 parentObj3.put("childMenus","");
				    								 sonArr2.add(parentObj3);
			    								 }
			    							 }
			    						 }else{
			    							 parentObj2.put("icon", "&#xe602;");//图标地址 
			    							 
			    						 }
			    						 parentObj2.put("childMenus", sonArr2);
			    					 }else{
			    						 parentObj2.put("childMenus", "");
			    					 }
			    					 sonArr.add(parentObj2);
		    				     }
		    				 }
		    			 }
		    			 parentObj.put("childMenus", sonArr);
		    		 }else{
		    			 parentObj.put("isHeader","0");//是否父级标签
		    			 parentObj.put("childMenus", "");
		    		 }
		    		 parentArr.add(parentObj);
		    	 }

			}
		  return parentArr.toString(); 
	   }
	   
	   /**
		 * 私有判断一个集合里面是否含有Integer 对象值
		 * */
		private boolean isExist(List<Integer> list,Integer integer){
			 boolean bool = false;
			 for (Integer integer2 : list) {
				if(integer == integer2){
					bool = true;
				}
			}
			 return bool;
		}

		/**
		 * @desc 获取系统菜单列表
		 * @author wsbg
		 * @return
		 * */
		@RequestMapping("pores")
		public ModelAndView possessResource(Pager page,String uName,Integer uId){
			logger.info("[SystemAction]possessResource is start.....");
			//由于wsbg是超级 管理员 所以获取出来的数据也就是所有的资源
			ModelAndView view = new ModelAndView("/page/system/menu-list");
			ResultData result = userService.findMenuPageList(page);
			//装配资源
			view.addObject("resources",result.getList());
			view.addObject("pager",result.getPage());
			//获取数据
			List<Integer> list = userService.findResourceIds(uName);
			view.addObject("Exists",list);
			view.addObject("userId",uId);

			logger.info("[SystemAction]possessResource is end.....");

			return view;
		}

		/**
		 * @desc 获取菜案列表对象
		 * @author  wsbg
		 * */
	   @RequestMapping("authorres")
	   @ResponseBody
	   public String authorres(String rIds,Integer uId){
	   	logger.info("[SystemAction]authorres is start.....");
	    //获取数据
	    Integer roleId = userService.findRoleIdByUserId(uId);
		Integer  in = userService.addResourceByRoleId(rIds,uId);
	   	logger.info("[SystemAction]authorres is end.....");
		return  in+"";
	   }
	   
	   /***
	    * @Desc新增菜单列表
	    * */
	   @RequestMapping("add.do")
	   public ModelAndView saveObj(HttpServletRequest request,com.wsbg.entity.Resource resobj,Model model){
		   logger.info("[SystemAction]add is start.....");
		   ModelAndView view = new ModelAndView("page/system/menu-add");
		   if(null==resobj.getName() || "".equals(resobj.getName())){
			   Map<Integer,Object> map = userService.findResourceObj();
			   model.addAttribute("map", map);
			   model.addAttribute("resource",resobj);
			   return view;
		   }
		   if(resobj.getResourceId()!=null && resobj.getResourceId()>0)
			     resourceService.update(resobj);
		   else
			     resobj.setCreateTime(new Date());
		         Session session = SecurityUtils.getSubject().getSession();
		         if(resobj.getIsParent()==0){
		        	 resobj.setUrl(null);
		         }
		         resobj.setCreateUser(getUser(session).getUsername());
			     resourceService.save(resobj);
		   logger.info("[SystemAction]add is end.....");
		   return new ModelAndView("redirect:/system/menu.do");
	   }
}
