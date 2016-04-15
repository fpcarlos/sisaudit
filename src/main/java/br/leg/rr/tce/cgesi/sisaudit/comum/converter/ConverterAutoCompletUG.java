package br.leg.rr.tce.cgesi.sisaudit.comum.converter;

import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.leg.rr.tce.cgesi.sisaudit.comum.entity.UnidadeGestora;

@FacesConverter("converterAutoCompletUG")
public class ConverterAutoCompletUG implements Converter {

	private void addAttribute(UIComponent component, UnidadeGestora o) {
		this.getAttributesFrom(component).put(o.getId().toString(), o);
	}

	private Map<String, Object> getAttributesFrom(UIComponent component) {
		return component.getAttributes();
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {

		if (value != null) {
			return this.getAttributesFrom(component).get(value);
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {

		if (value != null && !"".equals(value)) {
			UnidadeGestora entity = (UnidadeGestora) value;
			this.addAttribute(component, entity);
			Integer codigo = entity.getId();
			if (codigo != null) {
				return String.valueOf(codigo);
			}
		}
		return (String) value;
	}
	
}
