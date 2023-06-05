package com.atguigu.servicebase.exceptionhandler;

import com.atguigu.commonutils.ExceptionUtil;
import com.atguigu.commonutils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一异常处理类
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

	@ExceptionHandler(Exception.class)
	@ResponseBody
	public com.atguigu.commonutils.R error(Exception e){
		e.printStackTrace();
		return R.error().message("执行了全局异常处理。。。");
	}

	@ExceptionHandler(ArithmeticException.class)
	@ResponseBody
	public com.atguigu.commonutils.R error(ArithmeticException e){
		e.printStackTrace();
		return R.error().message("执行了ArithmeticException异常处理。。。");
	}

	@ExceptionHandler(GuliException.class)
	@ResponseBody
	public com.atguigu.commonutils.R error(GuliException e){
		log.error(ExceptionUtil.getMessage(e));
		e.printStackTrace();
		return R.error().code(e.getCode()).message(e.getMsg());
	}
}