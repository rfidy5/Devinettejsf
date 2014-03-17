/**
 * 
 */
package fr.ifsic.tpj2ee;

import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 * @author 13000018
 *
 */
public class NumberBean {
	public int userNumber;
	private int randomNumber;

	// representent les bornes du domaine de definition
	private int lowNumber;
	private int highNumber;
	
	
	public NumberBean() {
		refresh();
		FacesContext facescontext=FacesContext.getCurrentInstance();
		HttpSession session=(HttpSession)facescontext.getExternalContext().getSession(true);
		session.setAttribute("randomNumber", randomNumber);
	}

	public int getUserNumber() {
		return userNumber;
	}

	public void setUserNumber(int userNumber) {
		this.userNumber = userNumber;
	}

	public int getRandomNumber() {
		return randomNumber;
	}

	public void setRandomNumber(int randomNumber) {
		this.randomNumber = randomNumber;
	}
	
	/**
	 * pour tester si le nombre entre est juste ou pas 
	 * @param numberToTest
	 */
	private void testNumber(int numberToTest){
		FacesContext facescontext=FacesContext.getCurrentInstance();
		HttpSession session=(HttpSession)facescontext.getExternalContext().getSession(true);
		int randomNumber=(Integer)session.getAttribute("randomNumber");
		
		if(numberToTest<randomNumber){
			facescontext=FacesContext.getCurrentInstance();
			facescontext.addMessage(null, new FacesMessage("Essayez un nombre plus grand"));
		}if(numberToTest>randomNumber){
			facescontext=FacesContext.getCurrentInstance();
			facescontext.addMessage(null, new FacesMessage("Essayez un nombre plus petit"));
		}if(numberToTest==randomNumber){
			try {
				facescontext=FacesContext.getCurrentInstance();
				NavigationHandler navigationHandler = facescontext.getApplication().getNavigationHandler();
			    navigationHandler.handleNavigation(facescontext, null, "success");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * pour valider le nombre entre
	 */
	public void soumettre(){
		testNumber(userNumber);
	}
	/**
	 * pour rejouer
	 */
	public void playAgain(){
		FacesContext facescontext=FacesContext.getCurrentInstance();
		NavigationHandler navigationHandler = facescontext.getApplication().getNavigationHandler();
	    navigationHandler.handleNavigation(facescontext, null, "invite");
		invalidateSession();
		refresh();
	}

	/**
	 * pour invalider la session en cours	
	 */
	private void invalidateSession(){
		FacesContext context=FacesContext.getCurrentInstance();
		HttpSession session=(HttpSession)context.getExternalContext().getSession(false);
		session.invalidate();
	}
	
	
	/**
	 * creation du nombre aleatoire a deviner
	 */
	private void createRandomNumber(){
		randomNumber = (int)(Math.random() * (highNumber-lowNumber)) + lowNumber;
	}
	
	/**
	 * permet de definir l'intervalle de definition des nombres
	 * @param lower borne inferieure
	 * @param higher borne superieure
	 */
	private void initialiseLowHighNumber(int lower, int higher){
		lowNumber=lower;
		highNumber=higher;
	}
	
	/**
	 * fonction appele permettant de creer l'intervalle de definition et d'initialiser le nombre a touver
	 */
	public void refresh(){
		initialiseLowHighNumber(0,100);
		createRandomNumber();
	}
	
}
