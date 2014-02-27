package controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.text.BadLocationException;

import parser.Parser;
import controller.ModelController;
import model.UMLNature;

public class ModelController {
	private ClassicGuiController cgc;
	private Parser p;
	private StepFactory sf;
	private Exercise exo;

	public void setCGC(ClassicGuiController cgc) {
		this.cgc = cgc;
		this.sf = new StepFactory();
	}

	public void setParser(Parser p) {
		this.p = p;
	}

	public void askSelectText(int[] pourcentages) {
		exo.selectText(pourcentages);
	}

	public void askUnSelectText(int[] pourcentages) {
		exo.unselectText(pourcentages);
	}

	public Object[] askUmlInstancesNatures() {
		Object[] natures = { UMLNature.CLASS, UMLNature.ABSTRACT_CLASS, UMLNature.INTERFACE, UMLNature.ATTRIBUTE, UMLNature.METHOD };
		return natures;
	}

	public void askValidateStep(Object step) {
		System.out.println("Correction de " + ((Step) s).getFrenchName());
		((Step) s).getCorrection(exo);
	}

	public void askAddText(String text, String comment) {
		System.out.println("askAddText " + text + "\n" + comment);
		exo.addText(text);
	}

	// BEAUCOUP DE QUESTIONS EN SUSPENT ICI
	private void askCreateClass(int firstWord, int lastWord, boolean userText, String name) {
		exo.addClass(fisrtWord,lastWord,userText,name);
		System.out.println("askCreateClass " + name);
	}

	// BEAUCOUP DE QUESTIONS EN SUSPENT ICI
	private void askCreateAbstractClass(int firstWord, int lastWord, boolean userText, String name) {
		exo.addAbstractClass(firstWord, lastWord, userText, name)
		System.out.println("askCreateAbstractClass " + name);
	}

	// BEAUCOUP DE QUESTIONS EN SUSPENT ICI
	private void askCreateInterface(int firstWord, int lastWord, boolean userText, String name) {
		exo.addInterface(firstWord, lastWord, userText, name);
		System.out.println("askCreateInterface " + name);
	}

	// BEAUCOUP DE QUESTIONS EN SUSPENT ICI
	private void askCreateAttribute(int firstWord, int lastWord, boolean userText, String name, String type, String visibility) {
		exo.addAttribute()
		System.out.println("askCreateAttribute " + name + " " + type + " " + visibility);
	}

	public void doPrintMessage(String title, String message) {
		cgc.doPrintMessage(title, message);
	}

	public void doValidateText(int firstWord, int lastWord, boolean userText) throws BadLocationException {
		cgc.doValidateText(firstWord,lastWord,userText);
	}

	public void doInvalidateText(int firstWord, int lastWord, boolean userText) throws BadLocationException {
		cgc.doInvalidateText(firstWord, lastWord, userText);
	}
}


public class ClassicGuiController implements GuiController {






	/**
	 * Demande au code metier le nom de l'instance Uml de nature donnée
	 * correspondant au mot cle defini
	 * 
	 * @param firstWord
	 *            indice du premier mot de l'expression mot-cle
	 * @param lastWord
	 *            indice du dernier mot de l'expression mot-cle
	 * @param userText
	 *            specifie si le mot-cle provient du texte entre par
	 *            l'utilisateur
	 * @param nature
	 *            decrit la nature Uml de l'instance recherchee
	 * @return nom de l'instance Uml correspondant au mot cle
	 */
	private String askUmlInstanceName(int firstWord, int lastWord, boolean userText, Object nature) {
		// TODO Auto-generated method stub
		return null;
	}









	/**
	 * Transmet au code metier la demande de l'utilisateur de creer une nouvelle
	 * instance de methode liee au mot cle defini
	 * 
	 * @param firstWord
	 *            indice du premier mot de l'expression mot-cle
	 * @param lastWord
	 *            indice du dernier mot de l'expression mot-cle
	 * @param userText
	 *            specifie si le mot-cle provient du texte entre par
	 *            l'utilisateur
	 * @param name
	 *            nom voulu pour la nouvelle instance de methode
	 * @param paramTypes
	 *            liste des types des parametres voulus pour la nouvelle
	 *            instance de methode
	 * @param returnType
	 *            type de retour voulu pour la nouvelle instance de methode
	 * @param visibility
	 *            visibilitee voulue pour la nouvelle instance de methode
	 */
	private void askCreateMethod(int firstWord, int lastWord, boolean userText, String name, ArrayList<String> paramTypes, String returnType, String visibility) {
		// TODO Auto-generated method stub
		System.out.println("askCreateMethod " + name + " " + paramTypes.toString() + " " + returnType + " " + visibility);
	}

	// ***//
	// Do //
	// ***//

	/**
	 * Surligne la portion de texte definie avec la couleur de selection
	 * "SELECTION_COLOR"
	 * 
	 * @param firstWord
	 *            indice du premier mot de l'expression a surligner
	 * @param lastWord
	 *            indice du dernier mot de l'expression a surligner
	 * @param userText
	 *            vrai si l'expression a surligner fait partie du texte entre
	 *            par l'utilisateur
	 * @throws BadLocationException
	 *             si la zone definie par firstWord et lastWord n'existe pas
	 */
	public void doSelectText(int firstWord, int lastWord, boolean userText) throws BadLocationException {
		(userText ? userTextPanel : textPanel).highlight(SELECTION_COLOR, firstWord, lastWord);
	}

	/**
	 * Efface tout surlignage de la couleur de selection "SELECTION_COLOR" de la
	 * portion de texte definie.
	 * 
	 * @param firstWord
	 *            indice du premier mot de l'expression a desurligner
	 * @param lastWord
	 *            indice du dernier mot de l'expression a desurligner
	 * @param userText
	 *            vrai si l'expression a desurligner fait partie du texte entre
	 *            par l'utilisateur
	 * @throws BadLocationException
	 *             si la zone definie par firstWord et lastWord n'existe pas
	 */
	public void doUnSelectText(int firstWord, int lastWord, boolean userText) throws BadLocationException {
		(userText ? userTextPanel : textPanel).unHighlight(SELECTION_COLOR, firstWord, lastWord);
	}

	/**
	 * Surligne la portion de texte definie avec la couleur de validation
	 * "VALIDATION_COLOR"
	 * 
	 * @param firstWord
	 *            indice du premier mot de l'expression a surligner
	 * @param lastWord
	 *            indice du dernier mot de l'expression a surligner
	 * @param userText
	 *            vrai si l'expression a surligner fait partie du texte entre
	 *            par l'utilisateur
	 * @throws BadLocationException
	 *             si la zone definie par firstWord et lastWord n'existe pas
	 */
	public void doValidateText(int firstWord, int lastWord, boolean userText) throws BadLocationException {
		(userText ? userTextPanel : textPanel).highlight(VALIDATION_COLOR, firstWord, lastWord);
	}

	/**
	 * Efface tout surlignage de la couleur de validation "VALIDATION_COLOR" de
	 * la portion de texte definie.
	 * 
	 * @param firstWord
	 *            indice du premier mot de l'expression a desurligner
	 * @param lastWord
	 *            indice du dernier mot de l'expression a desurligner
	 * @param userText
	 *            vrai si l'expression a desurligner fait partie du texte entre
	 *            par l'utilisateur
	 * @throws BadLocationException
	 *             si la zone definie par firstWord et lastWord n'existe pas
	 */
	public void doUnValidateText(int firstWord, int lastWord, boolean userText) throws BadLocationException {
		(userText ? userTextPanel : textPanel).unHighlight(VALIDATION_COLOR, firstWord, lastWord);
	}

	/**
	 * Surligne la portion de texte definie avec la couleur d'invalidation
	 * "INVALIDATION_COLOR"
	 * 
	 * @param firstWord
	 *            indice du premier mot de l'expression a surligner
	 * @param lastWord
	 *            indice du dernier mot de l'expression a surligner
	 * @param userText
	 *            vrai si l'expression a surligner fait partie du texte entre
	 *            par l'utilisateur
	 * @throws BadLocationException
	 *             si la zone definie par firstWord et lastWord n'existe pas
	 */
	public void doInvalidateText(int firstWord, int lastWord, boolean userText) throws BadLocationException {
		(userText ? userTextPanel : textPanel).highlight(INVALIDATION_COLOR, firstWord, lastWord);
	}

	/**
	 * Efface tout surlignage de la couleur d'invalidation "INVALIDATION_COLOR"
	 * de la portion de texte definie.
	 * 
	 * @param firstWord
	 *            indice du premier mot de l'expression a desurligner
	 * @param lastWord
	 *            indice du dernier mot de l'expression a desurligner
	 * @param userText
	 *            vrai si l'expression a desurligner fait partie du texte entre
	 *            par l'utilisateur
	 * @throws BadLocationException
	 *             si la zone definie par firstWord et lastWord n'existe pas
	 */
	public void doUnInvalidateText(int firstWord, int lastWord, boolean userText) throws BadLocationException {
		(userText ? userTextPanel : textPanel).unHighlight(INVALIDATION_COLOR, firstWord, lastWord);
	}

	/**
	 * Affiche un pop up permettant de specifier les proprietes d'une nouvelle
	 * instance Uml de nature donnee, et, en cas de validation, invoque la
	 * methode de creation d'instance associee
	 * 
	 * @param firstWord
	 *            indice du premier mot de l'expression associee a la nouvelle
	 *            instance
	 * @param lastWord
	 *            indice du dernier mot de l'expression associee a la nouvelle
	 *            instance
	 * @param nature
	 *            UMLNature de la nouvelle instance
	 * @throws BadLocationException
	 *             si la zone definie par firstWord et lastWord n'existe pas
	 */
	public void doShowUmlInstanceCreationPopup(int firstWord, int lastWord, Object nature) throws BadLocationException {
		if (nature.equals(UMLNature.CLASS)) {
			VertexEditionPanel panel = new VertexEditionPanel(this, firstWord, lastWord, null);
			int result = JOptionPane.showConfirmDialog(null, panel, "Nouvelle classe", JOptionPane.OK_CANCEL_OPTION);
			if (result == JOptionPane.OK_OPTION) {
				askCreateClass(firstWord, lastWord, userTextFocus, panel.getVertexName());
			}
		} else if (nature.equals(UMLNature.ABSTRACT_CLASS)) {
			VertexEditionPanel panel = new VertexEditionPanel(this, firstWord, lastWord, null);
			int result = JOptionPane.showConfirmDialog(null, panel, "Nouvelle classe abstraite", JOptionPane.OK_CANCEL_OPTION);
			if (result == JOptionPane.OK_OPTION) {
				askCreateAbstractClass(firstWord, lastWord, userTextFocus, panel.getVertexName());
			}
		} else if (nature.equals(UMLNature.INTERFACE)) {
			VertexEditionPanel panel = new VertexEditionPanel(this, firstWord, lastWord, null);
			int result = JOptionPane.showConfirmDialog(null, panel, "Nouvelle interface", JOptionPane.OK_CANCEL_OPTION);
			if (result == JOptionPane.OK_OPTION) {
				askCreateInterface(firstWord, lastWord, userTextFocus, panel.getVertexName());
			}
		} else if (nature.equals(UMLNature.ATTRIBUTE)) {
			AttributeEditionPanel panel = new AttributeEditionPanel(this, firstWord, lastWord, null, null, null);
			int result = JOptionPane.showConfirmDialog(null, panel, "Nouvel attribut", JOptionPane.OK_CANCEL_OPTION);
			if (result == JOptionPane.OK_OPTION) {
				askCreateAttribute(firstWord, lastWord, userTextFocus, panel.getAttributeName(), panel.getAttributeType(), panel.getAttributeVisibility());
			}
		} else if (nature.equals(UMLNature.METHOD)) {
			MethodEditionPanel panel = new MethodEditionPanel(this, firstWord, lastWord, null, null, null, null);
			int result = JOptionPane.showConfirmDialog(null, new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER),
					"Nouvelle methode", JOptionPane.OK_CANCEL_OPTION);
			if (result == JOptionPane.OK_OPTION) {
				askCreateMethod(firstWord, lastWord, userTextFocus, panel.getMethodName(), panel.getMethodParams(), panel.getMethodReturnType(),
						panel.getMethodVisibility());
			}
		}
	}

	/**
	 * Affiche un pop up permettant de modifier les proprietes d'une instance
	 * Uml de nature donnee, et, en cas de validation, invoque la methode de
	 * modification d'instance associee
	 * 
	 * @param firstWord
	 *            indice du premier mot de l'expression associee a l'instance a
	 *            modifier
	 * @param lastWord
	 *            indice du dernier mot de l'expression associee a l'instance a
	 *            modifier
	 * @param userText
	 *            vrai si l'expression associee a l'instance a modifier fait
	 *            partie du texte entre par l'utilisateur
	 * @param nature
	 *            UMLNature de l'instance a modifier
	 * @throws BadLocationException
	 *             si la zone definie par firstWord et lastWord n'existe pas
	 */
	public void doShowUmlInstanceEditionPopup(int firstWord, int lastWord, boolean userText, Object nature) throws BadLocationException {
		// TODO Auto-generated method stub
	}

	/**
	 * Affiche un pop up permettant d'ajouter du texte et un commentaire le
	 * justifiant. Invoque la methode askAddText en cas de confirmation.
	 */
	public void doShowTextAdditionPopup() {
		TextAdditionPanel textPanel = new TextAdditionPanel(this);
		int result = JOptionPane.showConfirmDialog(null, textPanel, "Ajout de texte", JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) {
			askAddText(textPanel.getNewText(), textPanel.getComment());
		}
	}

	/**
	 * Ajoute du texte
	 * 
	 * @param userText
	 *            vrai si le texte doit etre ajoute a celui entre par
	 *            l'utilisateur
	 * @param text
	 *            le texte a ajouter
	 */
	public void doAddText(boolean userText, String[] text) {
		(userText ? userTextPanel : textPanel).apendText(text);
	}

	/**
	 * Applique la police donnee au texte affiche
	 * 
	 * @param font
	 *            la police a appliquer
	 */
	public void doSetTextFont(Font font) {
		textPanel.setTextFont(font);
		userTextPanel.setTextFont(font);
	}

	// *********//
	// Internal //
	// *********//

	/**
	 * Methode interne. Renvoie le dernier panel de texte ayant recu une
	 * activité utilisateur.
	 * 
	 * @return dernier panel de texte ayant recu une activité utilisateur
	 */
	private TextPanel getActiveTextPanel() {
		if (userTextFocus)
			return userTextPanel;
		else
			return textPanel;
	}

	/**
	 * Methode interne. Permet de definir le dernier panel de texte ayant recu
	 * une activité utilisateur.
	 * 
	 * @param userTextFocus
	 *            vrai si le dernier panel de texte ayant recu une activité
	 *            utilisateur est celui du texte de l'utilisateur
	 */
	public void setUserTextFocus(boolean userTextFocus) {
		this.userTextFocus = userTextFocus;
	}

	/**
	 * Methode interne. Permet de rechercher si une expression validée occupe
	 * les coordonnees donnees dans le panel de texte actif.
	 * 
	 * @param point
	 *            coordonnees ou chercher une expression validée
	 * @return indices des premiers et derniers mots de l'expression trouvée,
	 *         null sinon
	 */
	public int[] getKeyword(Point point) {
		try {
			return getActiveTextPanel().getKeywordPosition(point);
		} catch (BadLocationException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Methode interne. Recherche l'expression delimitee dans le panel de texte
	 * actif
	 * 
	 * @param firstWord
	 *            indice du premier mot de l'expression a trouver
	 * @param lastWord
	 *            indice du dernier mot de l'expression a trouver
	 * @return l'expression delimitee
	 * @throws BadLocationException
	 *             si la zone definie par firstWord et lastWord n'existe pas
	 */
	public String getText(int firstWord, int lastWord) throws BadLocationException {
		return getActiveTextPanel().getText(firstWord, lastWord);
	}

	/**
	 * Trouve la police d'affichage des panels de texte
	 * 
	 * @return la police utilisee
	 */
	public Font getTextFont() {
		return textPanel.getFont();
	}

	// ******************************************************************************************************//
	// *****************************************Navigation_methods*******************************************//
	// ******************************************************************************************************//

	// ****//
	// Ask //
	// ****//

	/**
	 * Transmet au code metier un objet identifiant la partie que l'utilisateur
	 * a selectionne
	 * 
	 * @param part
	 *            objet permettant au code metier d'identifier la partie a
	 *            laquelle il correspond
	 */
	public void askSelectPart(Object part) {
		// TODO Auto-generated method stub
		System.out.println("askSelectPart " + part.toString());
	}

	/**
	 * Transmet au code metier un objet identifiant l'etape dont l'utilisateur a
	 * demande la correction
	 * 
	 * @param step
	 *            objet permettant au code metier d'identifier l'etape a
	 *            laquelle il correspond
	 */
	public void askCorrectStep(Object step) {
		// TODO Auto-generated method stub
		System.out.println("askCorrectStep " + step.toString());
	}

	/**
	 * Demande au code metier un objet identifiant la partie suivante de
	 * l'exercice. Le premier appel doit donc renvoyer la premiere partie et
	 * ainsi de suite.
	 * 
	 * @return Un objet permettant au code metier d'identifier la partie a
	 *         laquelle il correspond
	 */
	public Object askNextPart() {
		return null;
		// TODO Auto-generated method stub

		// try {
		// return itP.next();
		// } catch (Exception e) {
		// return null;
		// }
	}

	/**
	 * Demande au code metier un objet identifiant l'etape suivante de la partie
	 * courante de l'exercice. Le premier appel doit donc renvoyer la premiere
	 * etape et ainsi de suite.
	 * 
	 * @return Un objet permettant au code metier d'identifier l'etape a
	 *         laquelle il correspond
	 */
	public Object askNextStep() {
		return null;
		// TODO Auto-generated method stub
		// try {
		// if(itP.hasNext())
		// return itS1.next();
		// else return itS2.next();
		// } catch (Exception e) {
		// return null;
		// }
	}

	// ****************************************************************************************************//
	// *********************************************Timer_methods******************************************//
	// ****************************************************************************************************//

	// ****//
	// Ask //
	// ****//

	/**
	 * Demande au code metier le chronometre a afficher
	 * 
	 * @return le score souhaite
	 */
	public String askTimer() {
		// TODO Auto-generated method stub
		return "0";
	}

	/**
	 * Demande au code metier la police a utiliser pour afficher le chronometre
	 * 
	 * @return la police souhaitee
	 */
	public Font askTimerFont() {
		// TODO Auto-generated method stub
		return new Font("Arial", Font.BOLD, 40);
	}

	/**
	 * Demande au code metier la couleur a utiliser pour l'arriere plan du
	 * chronometre
	 * 
	 * @return la couleur souhaitee
	 */
	public Color askTimerBgColor() {
		// TODO Auto-generated method stub
		return Color.GRAY;
	}

	// ***//
	// Do //
	// ***//

	/**
	 * Change le chronometre affiche
	 * 
	 * @param timer
	 *            le nouveau chronometre a afficher
	 */
	public void doSetTimer(String timer) {
		timerPanel.setTimer(timer);
	}

	/**
	 * Change la police du chronometre
	 * 
	 * @param font
	 *            la nouvelle police souhaitee
	 */
	public void doSetTimerFont(Font font) {
		timerPanel.setTimerFont(font);
	}

	/**
	 * Change la couleur d'arriere plan du chronometre
	 * 
	 * @param color
	 *            la nouvelle couleur souhaitee
	 */
	public void doSetTimerBgColor(Color color) {
		timerPanel.setTimerBgColor(color);
	}

	// ******************************************************************************************************//
	// *******************************************Score_methods**********************************************//
	// ******************************************************************************************************//

	// ****//
	// Ask //
	// ****//

	/**
	 * Demande au code metier le score a afficher
	 * 
	 * @return le score souhaite
	 */
	public String askScore() {
		// TODO Auto-generated method stub
		return "0 / 100";
	}

	/**
	 * Demande au code metier la police a utiliser pour afficher le score
	 * 
	 * @return la police souhaitee
	 */
	public Font askScoreFont() {
		// TODO Auto-generated method stub
		return new Font("Arial", Font.BOLD, 40);
	}

	/**
	 * Demande au code metier la couleur a utiliser pour l'arriere plan du score
	 * 
	 * @return la couleur souhaitee
	 */
	public Color askScoreBgColor() {
		// TODO Auto-generated method stub
		return Color.GRAY;
	}

	// ***//
	// Do //
	// ***//

	/**
	 * Change le score affiche
	 * 
	 * @param score
	 *            le nouveau score a afficher
	 */
	public void doSetScore(String score) {
		scorePanel.setScore(score);
	}

	/**
	 * Change la police du score
	 * 
	 * @param font
	 *            la nouvelle police souhaitee
	 */
	public void doSetScoreFont(Font font) {
		scorePanel.setScoreFont(font);
	}

	/**
	 * Change la couleur d'arriere plan du score
	 * 
	 * @param color
	 *            la nouvelle couleur souhaitee
	 */
	public void doSetScoreBgColor(Color color) {
		scorePanel.setScoreBgColor(color);
	}

}
