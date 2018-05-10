package com.souleater.ent;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import com.souleater.util.LogUtils;

/**
 * 
 * @author love720720@163.com
 * @date 2017年5月5日 上午11:22:37
 */
@WebServlet(name = "enterServlet", urlPatterns = "/es", asyncSupported = false)
public class EnterServlet extends HttpServlet {

	private static final long serialVersionUID = -381198449475900L;

	private static Logger log = LogUtils.getLogger();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.info("进入EnterServlet doGet");
		request.getRequestDispatcher("/index.html").forward(request, response);
		return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.info("进入EnterServlet doPost");
	}
}