package com.gm.rapid.framework.test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.gm.rapid.framework.action.BaseController;

@Controller
public class SpringMvcTestController {

	@RequestMapping(value="/springmvc_test",method={RequestMethod.GET})
	public ModelAndView handleRequest(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {
		// TODO Auto-generated method stub
		String aMessage = "Hello Spring MVC!";
		ModelAndView modelAndView = new ModelAndView("welcome");
		modelAndView.addObject("message", aMessage);
		
		return modelAndView;
	}

}
