package fr.ifsic.tpj2ee;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;


public class FormulaireValidator  implements Validator{

	private static final String NOM_REGEX ="[0-9]+";
	
	public void validate(FacesContext arg0,UIComponent arg1, Object value) throws ValidatorException {
		Pattern mask = Pattern.compile(NOM_REGEX);
		String nom = (String)value;
		Matcher matcher = mask.matcher(nom);
		if (!matcher.matches()){
			FacesMessage message = new FacesMessage();
			message.setDetail("Number not valid");
			message.setSummary("Number not valid");
			message.setSeverity(
					FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}
	}


}
