import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

public class Game_Definition_Slang extends JFrame {

	private JPanel contentPane;
	static JProgressBar progressBar;
	private int live = 3;
	private int score = 0;
	private String question;
	private String answer1;
	private String answer2;
	private String answer3;
	private String answer4;
	private String correct_answer;
	private int correct_order;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	private JButton btnpn;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel;

	public HashMap<String, String> hashmap = new HashMap<String, String>();
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private boolean isPlaying = false;
	private JLabel lblNewLabel_4;


	/**
	 * Create the frame.
	 */
	public Game_Definition_Slang(HashMap<String, String> hashmap) {
		this.hashmap = hashmap;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 756, 386);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		btnNewButton = new JButton("\u0110\u00E1p \u00E1n 1");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (isPlaying) {
					check_answer(answer1, btnNewButton);
				}
			}
		});
		btnNewButton.setBounds(70, 111, 283, 56);
		panel.add(btnNewButton);

		btnNewButton_1 = new JButton("\u0110\u00E1p \u00E1n 2");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (isPlaying) {
					check_answer(answer2, btnNewButton_1);
				}
			}
		});
		btnNewButton_1.setBounds(392, 111, 283, 56);
		panel.add(btnNewButton_1);

		btnpn = new JButton("\u0110\u00E1p \u00E1n 3");
		btnpn.setBounds(70, 195, 283, 56);
		btnpn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (isPlaying) {
					check_answer(answer3, btnpn);
				}
			}
		});
		panel.add(btnpn);

		btnNewButton_2 = new JButton("\u0110\u00E1p \u00E1n 4");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (isPlaying) {
					check_answer(answer4, btnNewButton_2);
				}
			}
		});
		btnNewButton_2.setBounds(392, 195, 283, 56);
		panel.add(btnNewButton_2);

		lblNewLabel = new JLabel("(exp) T\u00F4i \u0111\u00E3 ....");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(49, 60, 626, 40);
		panel.add(lblNewLabel);

		progressBar = new JProgressBar();
		progressBar.setValue(100);
		progressBar.setStringPainted(true);
		progressBar.setBounds(49, 44, 626, 14);
		progressBar.setIndeterminate(false);
		panel.add(progressBar);

		lblNewLabel_1 = new JLabel("0");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(621, 7, 54, 22);
		panel.add(lblNewLabel_1);

		lblNewLabel_2 = new JLabel("Lives: ");
		lblNewLabel_2.setBounds(10, 11, 37, 14);
		panel.add(lblNewLabel_2);

		lblNewLabel_3 = new JLabel(String.valueOf(live));
		lblNewLabel_3.setBounds(54, 11, 46, 14);
		panel.add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("Score");
		lblNewLabel_4.setBounds(600, 11, 37, 14);
		panel.add(lblNewLabel_4);

		setVisible(true);
		setLocationRelativeTo(null);
	}

	public void showCorrectAnswer() {
		if (correct_order == 1) {
			btnNewButton.setBackground(new Color(157, 227, 211));
		} else if (correct_order == 2) {
			btnNewButton_1.setBackground(new Color(157, 227, 211));
		} else if (correct_order == 3) {
			btnNewButton_2.setBackground(new Color(157, 227, 211));
		} else {
			btnpn.setBackground(new Color(157, 227, 211));
		}
	}

	public void check_answer(String answer, JButton button) {
		isPlaying = false;
		if (answer == correct_answer) {
			score += 10;
			lblNewLabel_1.setText(String.valueOf(score));
		} else {
			button.setBackground(new Color(248, 212, 213));
			live -= 1;
			lblNewLabel_3.setText(String.valueOf(live));

		}
		showCorrectAnswer();
		Timer t = new Timer(5000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				playGame();
				System.out.print("1");
			}
		});
		t.setRepeats(false);
		t.start();
	}

	public String randomSlangWord() {
		Object randomName = hashmap.keySet().toArray()[new Random().nextInt(hashmap.keySet().toArray().length)];
		return randomName.toString();
	}

	public String randomDefinition() {
		Object randomName = hashmap.keySet().toArray()[new Random().nextInt(hashmap.keySet().toArray().length)];
		String slang_word = randomName.toString();
		return hashmap.get(slang_word);
	}

	public int random(int min, int max) {
		return min + (int) (Math.random() * ((max - min) + 1));
	}

	public void generateData() {
		question = randomSlangWord();
		correct_answer = hashmap.get(question);
		correct_order = random(1, 4);
		if (correct_order == 1) {
			answer1 = correct_answer;
			answer2 = randomDefinition();
			answer3 = randomDefinition();
			answer4 = randomDefinition();
		} else if (correct_order == 2) {
			answer1 = randomDefinition();
			answer2 = correct_answer;
			answer3 = randomDefinition();
			answer4 = randomDefinition();
		} else if (correct_order == 4) {
			answer1 = randomDefinition();
			answer2 = randomDefinition();
			answer4 = correct_answer;
			answer4 = randomDefinition();
		} else {
			answer1 = randomDefinition();
			answer2 = randomDefinition();
			answer3 = randomDefinition();
			answer4 = correct_answer;
		}
	}

	public void initScreen() {
		btnNewButton.setText(answer1);
		btnNewButton_1.setText(answer2);
		btnpn.setText(answer3);
		btnNewButton_2.setText(answer4);
		lblNewLabel.setText(question);
		btnNewButton.setBackground(new Color(252, 250, 242));
		btnNewButton_1.setBackground(new Color(252, 250, 242));
		btnNewButton_2.setBackground(new Color(252, 250, 242));
		btnpn.setBackground(new Color(252, 250, 242));
		progressBar.setValue(100);
	}

	public void playGame() {
		if (live>0) {
			generateData();
			initScreen();
			isPlaying = true;
			Timer t = new Timer(1000, new ActionListener() {
				private int counter = 0;

				@Override
				public void actionPerformed(ActionEvent e) {
					counter++;
					var currentValue = progressBar.getValue();
					progressBar.setValue(currentValue - 10);
					
					if (!isPlaying) {
						((Timer) e.getSource()).stop();
						return;
					}
					
					if (counter == 10) {
						((Timer) e.getSource()).stop();
						TimeOut();
						return;
					}
				}
			});
			t.start();
		}
		else 
		{
			JOptionPane.showMessageDialog(null, "Your score is: "+ score);
			dispose();
		}
	}

	public void TimeOut() {
		check_answer(answer1, btnNewButton);
	}
}
