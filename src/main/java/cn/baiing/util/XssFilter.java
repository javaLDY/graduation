package cn.baiing.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

/**
 * 防止跨站攻击过滤器,对每个post请求的参数过滤一些关键字，替换成安全的，例如：< > ' " \ / # &
 * 配置参数中excludePaths用于排除过滤的URL
 * 
 * @author xueqi
 *
 */
public class XssFilter implements Filter {

	private FilterConfig filterConfig;
	private static String[] excludePaths;// 不进行拦截的url

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

		this.filterConfig = filterConfig;
		String excludePath = this.filterConfig.getInitParameter("excludePaths");
		if (StringUtils.isNotBlank(excludePath)) {
			excludePaths = excludePath.split(",");
		}

	}

	@Override
	public void destroy() {

		this.filterConfig = null;

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		String requestUrl = ((HttpServletRequest) request).getRequestURI();

		if (!excludeUrl(requestUrl)) {
			chain.doFilter(new XssHttpServletRequestWrapper(
					(HttpServletRequest) request), response);
		} else {
			chain.doFilter(request, response);
		}

	}

	private boolean excludeUrl(String url) {
		if (excludePaths != null && excludePaths.length > 0) {
			for (String path : excludePaths) {
				if (url.toLowerCase().equals(path)) {
					return true;
				}
			}
		}
		return false;
	}

}