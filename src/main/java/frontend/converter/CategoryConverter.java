package frontend.converter;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import backend.model.Category;
import frontend.beans.TrainerBean;

@FacesConverter("categoryConverter")
public class CategoryConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String cid) {
		ValueExpression vex = context.getApplication().getExpressionFactory()
				.createValueExpression(context.getELContext(), "#{trainerBean}", TrainerBean.class);

		TrainerBean tbean = (TrainerBean) vex.getValue(context.getELContext());
		return tbean.getCategory(Integer.valueOf(cid));
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object category) {
		return ((Category) category).getId().toString();

	}

}
