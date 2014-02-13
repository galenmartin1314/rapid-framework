package com.gm.rapid.framework.tools;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class ApplicationProperty extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ApplicationProperty() {
        super();
    }

	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		config.getServletContext().setAttribute("appName", "企业应用快速开发平台");
		config.getServletContext().setAttribute("root", config.getServletContext().getContextPath());
	}
}
