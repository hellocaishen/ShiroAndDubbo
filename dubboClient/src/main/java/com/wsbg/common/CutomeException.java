package com.wsbg.common;

/**
 * @desc 自定义异常类
 * 
 * */
public class CutomeException extends RuntimeException{
	   /**
	 * 
	 */
	private static final long serialVersionUID = -7827592355431925477L;
	private ExceptionCodeEnum exceptionCodeEnum;  
	   
   public CutomeException(ExceptionCodeEnum exceptionCodeEnum){  
           this.exceptionCodeEnum = exceptionCodeEnum;  
   }  
	 
	public ExceptionCodeEnum getExceptionCodeEnum() {
		return exceptionCodeEnum;
	}


	public void setExceptionCodeEnum(ExceptionCodeEnum exceptionCodeEnum) {
		this.exceptionCodeEnum = exceptionCodeEnum;
	}


	   
 

}
