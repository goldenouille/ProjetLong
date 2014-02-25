package testGui;

import gui.ClassicGuiController;

/**
 * @author Will
 * Test d'interface non integree
 */
public class BasicTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	
		ClassicGuiController c = new ClassicGuiController(null);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
