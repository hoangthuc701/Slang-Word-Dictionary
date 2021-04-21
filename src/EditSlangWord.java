import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditSlangWord extends JDialog {

	public class ReturnObject {
		private String _slang_word;
		private String _definition;
		private boolean _status;

		public ReturnObject(String slang_word, String definition, boolean status) {
			this._slang_word = slang_word;
			this._definition = definition;
			this._status = status;
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

		public boolean get_status() {
			return this._status;
		}

	}
	
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextArea textArea;
	private boolean status = false;
	private JLabel lblNewLabel_2;

	/**
	 * Create the dialog.
	 */
	public EditSlangWord() {
		super((java.awt.Frame) null, true);
		setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
		setTitle("Edit");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Slang word");
			lblNewLabel.setBounds(22, 41, 76, 22);
			contentPanel.add(lblNewLabel);
		}
		{
			textField = new JTextField();
			textField.setEditable(false);
			textField.setBounds(108, 42, 259, 21);
			contentPanel.add(textField);
			textField.setColumns(10);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Definition");
			lblNewLabel_1.setBounds(22, 78, 76, 22);
			contentPanel.add(lblNewLabel_1);
		}
		{
			textArea = new JTextArea();
			textArea.setBounds(108, 82, 259, 109);
			contentPanel.add(textArea);
		}
		{
			lblNewLabel_2 = new JLabel("New label");
			lblNewLabel_2.setForeground(Color.RED);
			lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_2.setBounds(22, 203, 345, 14);
			lblNewLabel_2.setVisible(false);
			contentPanel.add(lblNewLabel_2);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (textArea.getText().length() == 0) {
							lblNewLabel_2.setText("Definition is required.");
							lblNewLabel_2.setVisible(true);
							return;
						}
						status = true;
						setVisible(false);
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	public ReturnObject showDialog(String slangWord, String definition) {
		textField.setText(slangWord);
		textArea.setText(definition);
		setVisible(true);
		return new ReturnObject(textField.getText(), textArea.getText(), status);
	}

}
