package com.ew.component.shiro;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;

import com.alibaba.fastjson.JSONObject;
import com.ew.common.Constant;
import com.ew.common.utils.HttpServletUtil;
import com.ew.common.utils.ResultDtoUtil;

/**
 * 
 * @author Mr`Huang
 * @Date 2020-11-4 14:15:01
 */
public class UserAuthFilter extends AccessControlFilter {

	private static final String BAD_REQUEST_JSON = JSONObject.toJSONString(ResultDtoUtil.RequestError.badRequest());
	
	/**
	 * 登入用户首次请求标记@RequiresPermissions注解接口执行
	 */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
    	System.err.println("UserAuthFilter.isAccessAllowed.mappedValue = "+mappedValue);
        if (isLoginRequest(request, response)) {
            return true;
        } else {
            Subject subject = getSubject(request, response);
            return subject.getPrincipal() != null && (subject.isRemembered() || subject.isAuthenticated());
        }
    }

    /**
     * 用户登入验证 subject.login
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpRequest = WebUtils.toHttp(request);
        HttpServletResponse httpResponse = WebUtils.toHttp(response);
        if (HttpServletUtil.isAjaxRequest()) {
        	response.setCharacterEncoding(Constant.CHARSET_UTF8);
        	httpResponse.setContentType("application/json");
        	httpResponse.getWriter().println(BAD_REQUEST_JSON);
        } else {
            redirectToLogin(httpRequest, httpResponse);
        }
        return false;
    }
}