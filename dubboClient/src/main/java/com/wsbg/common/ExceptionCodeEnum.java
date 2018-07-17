package com.wsbg.common;

public enum ExceptionCodeEnum  implements ExceptionEnums {  
    
	AccountIsNotExistException(1001,"该账号不存在!"),
	AccountIsNull(1005,"账户不能为空!"),
	AccountIsStopException(1002,"账户停用!"),
	PasswordIsErrException(1003,"密码错误!"),
	CapatalIsErrException(1004,"图形验证码错误!"),
	
	ParamIsNotIllegalException(1005,"页码数不合法");
	
    public int code;
   
    public String msg;
	
	private ExceptionCodeEnum(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	@Override
	public int getCode() {
		return code;
	}
	@Override
	public String getMsg() {
		return msg;
	}
	
	public static String valueOf(int code){
		
		for (int i = 0; i <ExceptionCodeEnum.values().length; i++) {
			   int codes = ExceptionCodeEnum.values()[i].code;
			   if(codes==code){
				   return ExceptionCodeEnum.values()[i].msg;
			   }
		}
		
		return null;
	}
	
	
	public static void main(String[] args) {
		String msg =  valueOf(1002); 
		System.out.println(msg);
	}
}