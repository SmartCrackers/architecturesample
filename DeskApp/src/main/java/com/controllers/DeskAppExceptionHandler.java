package com.controllers;

import com.DeskAppWebException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class DeskAppExceptionHandler {

	private final String ERROR_PAGE = "error";

	private final String ACCESS_DENIED="403";
	private final String ERROR_CODE = "errorCode";
	private final String ERROR_MESSAGE = "errorMessage";

	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(value = DeskAppWebException.class)
	public ModelAndView handleApplicationError(Exception e) {
		
		DeskAppWebException es = (DeskAppWebException) e;
		
		/*System.out.println(es.code);
		
		if(es.code == 401){
			
			ModelAndView mav = new ModelAndView("signin");
			mav.addObject("tokenExpired", "session expired");
			return mav;
		}
		if(es.code == 403){
			
			ModelAndView mav = new ModelAndView(ACCESS_DENIED);
			mav.addObject(ERROR_CODE, HttpStatus.FORBIDDEN);
			mav.addObject(ERROR_MESSAGE, e.getMessage());
			return mav;
		}*/
		ModelAndView mav = new ModelAndView(ERROR_PAGE);
		
		mav.addObject(ERROR_CODE, HttpStatus.INTERNAL_SERVER_ERROR);
		mav.addObject(ERROR_MESSAGE, e.getMessage());
		return mav;
	}

	/*@ResponseStatus(value = HttpStatus.FORBIDDEN)
	@ExceptionHandler(value = AccessDeniedException.class)
	public ModelAndView handleAccessDeniedException(Exception e) {

		ModelAndView mav = new ModelAndView(ERROR_PAGE);

		mav.addObject(ERROR_CODE, HttpStatus.FORBIDDEN);
		mav.addObject(ERROR_MESSAGE, e.getMessage());
		return mav;
	}*/

	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = IllegalArgumentException.class)
	public ModelAndView handleIllegalArgumentException(Exception e) {
		ModelAndView mav = new ModelAndView(ERROR_PAGE);
		
		mav.addObject(ERROR_CODE, HttpStatus.BAD_REQUEST);
		mav.addObject(ERROR_MESSAGE, e.getMessage());
		return mav;
	}

}
