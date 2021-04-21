import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import java.awt.Color;
import javax.swing.SwingConstants;

public class AddNewSlangWord extends JDialog {

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
	private JLabel lblNewLabel_1;
	private boolean status = false;

	/**
	 * Create the dialog.
	 */
	public AddNewSlangWord() {
		super((java.awt.Frame) null, true);
		setTitle("Add ");
		setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Slang word");
			lblNewLabel.setBounds(31, 53, 83, 14);
			contentPanel.add(lblNewLabel);
		}
		{
			textField = new JTextField();
			textField.setBounds(108, 50, 256, 20);
			contentPanel.add(textField);
			textField.setColumns(10);
		}
		{
			JLabel lblDefinition = new JLabel("Definition");
			lblDefinition.setBounds(31, 92, 83, 14);
			contentPanel.add(lblDefinition);
		}
		{
			textArea = new JTextArea();
			textArea.setBounds(108, 92, 256, 95);
			contentPanel.add(textArea);
		}
		{
			lblNewLabel_1 = new JLabel("New label");
			lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_1.setForeground(Color.RED);
			lblNewLabel_1.setBounds(31, 197, 335, 20);
			lblNewLabel_1.setVisible(false);
			contentPanel.add(lblNewLabel_1);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (textField.getText().length() == 0) {
							lblNewLabel_1.setText("Slang word is required.");
							lblNewLabel_1.setVisible(true);
							return;
						}
						if (textArea.getText().length() == 0) {
							lblNewLabel_1.setText("Definition is required.");
							lblNewLabel_1.setVisible(true);
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

	public ReturnObject showDialog() {
		setVisible(true);
		return new ReturnObject(textField.getText(), textArea.getText(), status);
	}
}
