package frontend.filters;

import java.io.IOException;

import javax.faces.application.ResourceHandler;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import frontend.beans.RoleEnum;
import frontend.beans.UserProfileBean;

@WebFilter(filterName = "AdminFilter", urlPatterns = { "/admin/*" })
public class AdminFilter implements Filter {
	private static final String USER_PAGES = "/home/home.xhtml";
	private UserProfileBean userProfileBean;
	private static final String EXPIRES = "Expires";
	private static final String NO_CACHE = "no-cache";
	private static final String PRAGMA = "Pragma";
	private static final String NO_CACHE_NO_STORE_MUST_REVALIDATE = "no-cache, no-store, must-revalidate";
	private static final String CACHE_CONTROL = "Cache-Control";
	final static Logger logger = LogManager.getLogger(AdminFilter.class);

	public AdminFilter() {
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest reqt = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		UserProfileBean userProfileBean = (UserProfileBean) reqt.getSession().getAttribute("userProfileBean");
		if ((userProfileBean.getUser().getId() != null) && (userProfileBean.hasRoleAdmin(RoleEnum.ADMIN))) {
			if (!reqt.getRequestURI().startsWith(reqt.getContextPath() + ResourceHandler.RESOURCE_IDENTIFIER)) { // Skip
				// JSF
				// resources
				// (CSS/JS/Images/etc)
				resp.setHeader(CACHE_CONTROL, NO_CACHE_NO_STORE_MUST_REVALIDATE); // HTTP 1.1.
				resp.setHeader(PRAGMA, NO_CACHE); // HTTP 1.0.
				resp.setDateHeader(EXPIRES, 0); // Proxies.
			}
			chain.doFilter(reqt, resp);

		} else {
			resp.sendRedirect(reqt.getContextPath() + USER_PAGES);
		}

	}

	public UserProfileBean getUserProfileBean() {
		return userProfileBean;
	}

	public void setUserProfileBean(UserProfileBean userProfileBean) {
		this.userProfileBean = userProfileBean;
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
