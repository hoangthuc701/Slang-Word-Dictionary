import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class EditSlangWord extends JDialog {

	public class ReturnObject {
		private String _slang_word;
		private String _definition; 
		public ReturnObject(String slang_word, String definition) {
			this._slang_word = slang_word;
			this._definition = definition;
		}
		public String get_slang_word() {
			return _slang_word;
		}
		public String get_definition() {
			return _definition;
		}
		public void set_slang_word(String _slang_word) {
			this._slang_word = _slang_word;
		}
		public void set_definition(String _definition) {
			this._definition = _definition;
		}
		
	}
	
	private final JPanel contentPanel = new JPanel();

	/**
	 * Create the dialog.
	 */
	public EditSlangWord() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
