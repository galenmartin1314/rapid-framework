package com.gm.rapid.framework.action.flatform.orm;

import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gm.rapid.framework.action.BaseController;
import com.gm.rapid.framework.widget.ComboBox;

@Controller
@Scope("session")
@RequestMapping(value="/flatform/orm")
public class ORMManager extends BaseController {
	/**
	 * 
	 * @param request HttpServletRequest实例
	 * @return 所有数据表
	 * @throws Exception
	 */
	@RequestMapping(value="/tables")
	public String tables(HttpServletRequest request) throws Exception{
		URL url = getClass().getClassLoader().getResource("jdbc.properties");
		InputStream in = new FileInputStream(url.getFile());
		Properties properties = new Properties();
		properties.load(in);
		Class.forName(properties.get("datasource.driverClassName").toString());
		Connection conn = DriverManager.getConnection(properties.get("datasource.url").toString(),properties.get("datasource.username").toString(),properties.get("datasource.password").toString());
		DatabaseMetaData meta = conn.getMetaData();
		ResultSet tables = meta.getTables(null, "CEMCIP", "%", new String[]{"TABLE"});
		List<ComboBox> table = new LinkedList<ComboBox>();
		table.add(new ComboBox("","---请选择---"));
		while(tables.next()){
			table.add(new ComboBox(tables.getString("TABLE_NAME"),tables.getString("TABLE_NAME")));
		}
		conn.close();
		request.setAttribute("table", table);
		return "/flatform/orm/tables";
	}
	
	@RequestMapping("/tables/${tbname}")
	@ResponseBody
	public void getTable(@PathVariable String tbname){
		System.out.println(tbname);
	}
}