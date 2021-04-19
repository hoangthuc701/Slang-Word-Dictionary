import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class EditSlangWord extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	/**
	 * Create the frame.
	 */
	public EditSlangWord() {
		setTitle("Edit");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 290);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Slang word");
		lblNewLabel.setBounds(43, 29, 72, 30);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(125, 32, 260, 25);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblDefinition = new JLabel("Definition");
		lblDefinition.setBounds(43, 75, 72, 30);
		contentPane.add(lblDefinition);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(125, 78, 260, 90);
		contentPane.add(textArea);
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.setBounds(125, 192, 95, 35);
		contentPane.add(btnNewButton);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(230, 192, 95, 35);
		contentPane.add(btnCancel);
	}

}
