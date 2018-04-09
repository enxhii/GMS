package facesconverter;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import backend.model.Role;
import frontend.beans.UserBean;
import javax.el.ValueExpression;
@FacesConverter("roleConverter")
public class RoleConverter implements Converter {
 
	@Override
    public Object getAsObject(FacesContext ctx, UIComponent uiComponent, String roleId) {
        ValueExpression vex =
                ctx.getApplication().getExpressionFactory()
                        .createValueExpression(ctx.getELContext(),
                                "#{userbean}", UserBean.class);

        UserBean userbean = (UserBean)vex.getValue(ctx.getELContext());
        return userbean.getRole(Integer.valueOf(roleId));
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object role) {
        return ((Role)role).getId().toString();
    }

} 