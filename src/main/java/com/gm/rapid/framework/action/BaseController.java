package com.gm.rapid.framework.action;

import org.apache.log4j.Logger;

public class BaseController {
	protected Logger log;
	
	public BaseController(){
		log = Logger.getLogger(getClass());
	}
}
