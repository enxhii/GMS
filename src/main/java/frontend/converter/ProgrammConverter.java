package frontend.converter;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import backend.model.Programm;
import frontend.beans.CustomerBean;

@FacesConverter("programmConverter")
public class ProgrammConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		ValueExpression vex = context.getApplication().getExpressionFactory()
				.createValueExpression(context.getELContext(), "#{customerbean}", CustomerBean.class);

		CustomerBean customerbean = (CustomerBean) vex.getValue(context.getELContext());
		return customerbean.getProgramm(Integer.valueOf(value));
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		return ((Programm) value).getId().toString();
	}
}
