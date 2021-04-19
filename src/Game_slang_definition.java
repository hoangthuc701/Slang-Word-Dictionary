import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;

public class Game_slang_definition extends JFrame {

	private JPanel contentPane;
	/**
	 * Create the frame.
	 */
	public Game_slang_definition() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 323);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("\u0110\u00E1p \u00E1n 1");
		btnNewButton.setBounds(70, 111, 117, 56);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("\u0110\u00E1p \u00E1n 2");
		btnNewButton_1.setBounds(253, 111, 123, 56);
		panel.add(btnNewButton_1);
		
		JButton btnpn = new JButton("\u0110\u00E1p \u00E1n 3");
		btnpn.setBounds(70, 195, 117, 56);
		btnpn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panel.add(btnpn);
		
		JButton btnNewButton_2 = new JButton("\u0110\u00E1p \u00E1n 4");
		btnNewButton_2.setBounds(253, 195, 123, 56);
		panel.add(btnNewButton_2);
		
		JLabel lblNewLabel = new JLabel("(exp) T\u00F4i \u0111\u00E3 ....");
		lblNewLabel.setBounds(49, 60, 327, 40);
		panel.add(lblNewLabel);
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setBounds(49, 44, 327, 14);
		panel.add(progressBar);
		
		JLabel lblNewLabel_1 = new JLabel("0");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(338, 11, 54, 22);
		panel.add(lblNewLabel_1);
	}
}
