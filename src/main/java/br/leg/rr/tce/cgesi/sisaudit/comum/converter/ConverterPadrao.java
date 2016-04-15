/*
 * Autor: Adiel dos Santos de Araujo
 */
package br.leg.rr.tce.cgesi.sisaudit.comum.converter;


import java.lang.reflect.Field;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.Id;


  
/** 
* Converter para entidades JPA.  
* Baseia-se na anotação @Id para identificar o atributo que representa a identidade da entidade.   
* @author Flávio Henrique  
* @version 1.0  
* @since 05/10/2010 
*/  
@FacesConverter(value = "converterPadrao")
public class ConverterPadrao implements Converter {  
  
    public Object getAsObject(FacesContext ctx, UIComponent component,  
            String value) {      
        if (value != null) {  
        	Object obj =  component.getAttributes().get(value);
            return   obj;
        }  
        return null;  
    }  
  
    public String getAsString(FacesContext ctx, UIComponent component,  
            Object obj) { 
        if (obj != null && !"".equals(obj)) {  
            String id;  
            try {  
//                id = this.getId(getClazz(ctx, component), obj);  
                id = getId(getClazz(ctx, component), obj);  
                if (id == null){  
                    id = "";  
                }  
                id = id.trim();  
                component.getAttributes().put(id,  
                        getClazz(ctx, component).cast(obj));  
                return id;  
            } catch (SecurityException e) {  
                e.printStackTrace(); // seu log aqui  
            } catch (IllegalArgumentException e) {  
                e.printStackTrace(); // seu log aqui  
            } catch (NoSuchFieldException e) {  
                e.printStackTrace(); // seu log aqui  
            } catch (IllegalAccessException e) {  
                e.printStackTrace(); // seu log aqui  
            }  
        }  
        return null;  
    }  
  
    private Class<?> getClazz(FacesContext facesContext, UIComponent component) {
        Class<?> aux1 = component.getValueExpression("value").getType(  
                facesContext.getELContext()); 

        return aux1;
    }  
  
    private String getId(Class<?> clazz, Object obj) throws SecurityException,  
            NoSuchFieldException, IllegalArgumentException,  
            IllegalAccessException {  

        for (Field field : clazz.getDeclaredFields()) {  
            if ((field.getAnnotation(Id.class)) != null) {  
                Field privateField = clazz.getDeclaredField(field.getName());  
                privateField.setAccessible(true);  
                if (privateField.get(clazz.cast(obj)) != null) {  
                    return (String)field.getType()  
                            .cast(privateField.get(clazz.cast(obj))).toString();  
                } else {  
                    return null;  
                }  
            }  
        }  
        return null;  
    }  
}  