package launcher;

/**
 * @author Will
 * Main class. Creates a launch controller with a simple exercise browser
 */
public class Launcher {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		LaunchController controller = new LaunchController(new SimpleExerciseBrowser());
	}
}
