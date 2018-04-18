package backend.filters;

import java.io.IOException;
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

@WebFilter(filterName = "ClientFilter", urlPatterns = { "/client/*" })
public class ClientFilter implements Filter {
	private static final String USER_PAGES = "/home/home.xhtml";
	private UserProfileBean userProfileBean;
	private RoleEnum roleEnum;
	final static Logger logger = LogManager.getLogger(AdminFilter.class);

	public ClientFilter() {
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		try {
			HttpServletRequest reqt = (HttpServletRequest) request;
			HttpServletResponse resp = (HttpServletResponse) response;
			UserProfileBean userProfileBean = (UserProfileBean) reqt.getSession().getAttribute("userProfileBean");
			if ((userProfileBean.getUser().getId() != null) && (userProfileBean.hasRoleMember(roleEnum.MEMBER))) {
				chain.doFilter(reqt, resp);
			} else {
				resp.sendRedirect(reqt.getContextPath() + USER_PAGES);
			}

		} catch (Exception e) {
			logger.debug(e);
			e.printStackTrace();
		}

	}

	public UserProfileBean getUserProfileBean() {
		return userProfileBean;
	}

	public void setUserProfileBean(UserProfileBean userProfileBean) {
		this.userProfileBean = userProfileBean;
	}

	public RoleEnum getRoleEnum() {
		return roleEnum;
	}

	public void setRoleEnum(RoleEnum roleEnum) {
		this.roleEnum = roleEnum;
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
