package com.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

import com.po.User;

public class AdminInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if(user!=null && user.getNickname().equals("��������Ա")) {
			return true;
		}
		request.setAttribute("msg", "���ȵ�¼");
		request.getRequestDispatcher("/WEB-INF/jsp/admin_login.jsp").forward(request, response);
		return false;
	}
}
