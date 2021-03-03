import javax.swing.JButton;

public class NormalTheme extends BoardViewer {

	/*
	 * A constructor that creates a Board Viewer
	 * 
	 * @param aModel the Board that this panel observes
	 */
	public NormalTheme(Board aModel) {
		super(aModel);
	}

	/*
	 * Get this board's specific button.
	 * 
	 * @return this board's button.
	 */
	public JButton getInitialButton() {
		JButton normalTheme = new JButton("Normal Theme");
		return normalTheme;
	}
}