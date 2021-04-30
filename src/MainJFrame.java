import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class MainJFrame extends JFrame {

	enum MODE {
		SLANG_WORD_TO_DEFINITION, DEFINITION_TO_SLANG_WORD
	}

	private JPanel contentPane;
	private JTextField textField;
	public HashMap<String, String> hashmap = new HashMap<String, String>();
	public HashMap<String, String> hashmap_value_to_key = new HashMap<String, String>();
	private ArrayList<String> listSlangWord = new ArrayList<String>();
	private ArrayList<String> historySlangWord = new ArrayList<String>();
	private JList<String> list;
	private JTextArea currentTextArea;
	private MODE currentMode;
	private JTextArea textArea;
	private JRadioButton rdbtnNewRadioButton_1;
	private JRadioButton rdbtnNewRadioButton;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JList list1;
	private AbstractButton JtmNewMenuItem_6;
	private JMenuItem mntmNewMenuItem_6;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainJFrame frame = new MainJFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainJFrame() {

		this.loadDataFromFile();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1101, 523);
		historySlangWord.add("");

		textArea = new JTextArea();
		textArea.setBounds(10, 11, 629, 369);
		textArea.setEnabled(true);
		textArea.setEditable(false);
		currentTextArea = textArea;

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("File");
		menuBar.add(mnNewMenu);

		JMenuItem mntmNewMenuItem = new JMenuItem("Exit");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveDataToFile();
				try {
					copyToBackUp();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				dispose();
			}
		});
		mnNewMenu.add(mntmNewMenuItem);

		JMenu mnNewMenu_1 = new JMenu("Dictionary");
		menuBar.add(mnNewMenu_1);

		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Reset");
		mntmNewMenuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hashmap = new HashMap<String, String>();
				hashmap_value_to_key = new HashMap<String, String>();
				resetData();
			}
		});
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Random Slang word");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object randomName = hashmap.keySet().toArray()[new Random().nextInt(hashmap.keySet().toArray().length)];
				String slang_word = randomName.toString();
				String mean = hashmap.get(slang_word);
				JOptionPane.showMessageDialog(null,slang_word + ": "+mean);
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_1);
		mnNewMenu_1.add(mntmNewMenuItem_4);

		JMenu mnNewMenu_2 = new JMenu("Game");
		menuBar.add(mnNewMenu_2);

		JMenuItem mntmNewMenuItem_5 = new JMenuItem("Slang word -> Definition");
		mntmNewMenuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Game_slang_definition game_slang_def_screen = new Game_slang_definition(hashmap);
				game_slang_def_screen.playGame();
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_5);

		mntmNewMenuItem_6 = new JMenuItem("Definition -> Slang word  ");
		mntmNewMenuItem_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Game_Definition_Slang game_slang_def_screen = new Game_Definition_Slang(hashmap_value_to_key);
				game_slang_def_screen.playGame();
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_6);

		JMenu mnNewMenu_3 = new JMenu("Help");
		menuBar.add(mnNewMenu_3);

		JMenuItem mntmNewMenuItem_7 = new JMenuItem("About");
		mntmNewMenuItem_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				About about_screen = new About();
				about_screen.setVisible(true);
			}
		});
		mnNewMenu_3.add(mntmNewMenuItem_7);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		Panel panel = new Panel();
		panel.setBounds(5, 5, 1070, 33);
		contentPane.add(panel);

		JButton btnNewButton = new JButton("Add");
		btnNewButton.setBounds(683, 5, 84, 23);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddNewSlangWord addNewSlangWordScreen = new AddNewSlangWord();
				AddNewSlangWord.ReturnObject value = addNewSlangWordScreen.showDialog();
				if (value.get_status()) {
					String newSlangWord = value.get_slang_word();
					String newDefinition = value.get_definition();
					AddNewSlangWord(newSlangWord, newDefinition);
				}
				saveDataToFile();
				try {
					copyToBackUp();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		panel.setLayout(null);

		JScrollPane panel_1 = new JScrollPane();
		panel_1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		panel_1.setLocation(5, 38);
		panel_1.setSize(new Dimension(210, 419));
		contentPane.add(panel_1);

		list = new JList(listSlangWord.toArray());
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (list.getSelectedValue() != null) {

					String selected = list.getSelectedValue().toString();
					String text = hashmap.get(selected);
					if (historySlangWord.size() > 0) {
						String firstWord = historySlangWord.get(0);
						if (firstWord != selected) {
							historySlangWord.add(0, selected);
							list1.setListData(convertArrayListToStringArray(historySlangWord));
						}
					}
					currentTextArea.setText(text);
				}
			}
		});
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setVisibleRowCount(4);
		list.setFixedCellHeight(12);
		list.setFixedCellWidth(200);
		panel_1.setViewportView(list);

		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				String keyword = textField.getText();
				listSlangWord = searchByKey(keyword);
				list.setListData(convertArrayListToStringArray(listSlangWord));
			}
		});

		textField.setBounds(10, 6, 479, 20);
		panel.add(textField);
		textField.setColumns(40);
		panel.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Edit");
		btnNewButton_1.setBounds(777, 5, 89, 23);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditSlangWord screen = new EditSlangWord();
				if (list.getSelectedValue() != null) {
					String slangWord = list.getSelectedValue().toString();
					String definition = hashmap.get(slangWord);
					EditSlangWord.ReturnObject value = screen.showDialog(slangWord, definition);
					if (value.get_status()) {
						String newSlangWord = value.get_slang_word();
						String newDefinition = value.get_definition();
						UpdateSlangWord(newSlangWord, newDefinition);
					}
					saveDataToFile();
					try {
						copyToBackUp();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(null, "Please select a slang word.");
				}

			}
		});
		panel.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("Delete");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (list.getSelectedValue() != null) {
					String slangWord = list.getSelectedValue().toString();
					String definition = hashmap.get(slangWord);

					int input = JOptionPane.showConfirmDialog(null, "Do you want to delete this slang word?",
							"Select an Option...", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
					if (input == 0) {
						hashmap.remove(slangWord);
						hashmap_value_to_key.remove(definition);
						String keyword = textField.getText();
						listSlangWord = searchByKey(keyword);
						list.setListData(convertArrayListToStringArray(listSlangWord));
						currentTextArea.setText("");
					}
					saveDataToFile();
					try {
						copyToBackUp();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(null, "Please select a slang word.");
				}
			}
		});
		btnNewButton_2.setBounds(876, 5, 84, 23);
		panel.add(btnNewButton_2);

		JRadioButton rdbtnNewRadioButton = new JRadioButton("Definition");
		rdbtnNewRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changeMode(MODE.DEFINITION_TO_SLANG_WORD);
			}
		});
		buttonGroup.add(rdbtnNewRadioButton);
		rdbtnNewRadioButton.setBounds(586, 5, 89, 23);
		panel.add(rdbtnNewRadioButton);

		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Slang word");
		rdbtnNewRadioButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changeMode(MODE.SLANG_WORD_TO_DEFINITION);
			}
		});
		buttonGroup.add(rdbtnNewRadioButton_1);

		rdbtnNewRadioButton_1.setSelected(true);
		rdbtnNewRadioButton_1.setBounds(495, 5, 89, 23);
		panel.add(rdbtnNewRadioButton_1);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addChangeListener(new javax.swing.event.ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (e.getSource() instanceof JTabbedPane) {
					JTabbedPane pane = (JTabbedPane) e.getSource();
					int currentSelectedPane = pane.getSelectedIndex();
					MODE mode = currentSelectedPane == 0 ? MODE.SLANG_WORD_TO_DEFINITION
							: MODE.DEFINITION_TO_SLANG_WORD;
					changeMode(mode);
				}
			}
		});
		tabbedPane.setBounds(215, 38, 654, 419);
		contentPane.add(tabbedPane);

		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Definition - Slang", null, panel_3, null);
		panel_3.setLayout(null);

		panel_3.add(textArea);

		JPanel panel_4 = new JPanel();
		panel_4.setBounds(869, 38, 206, 419);
		contentPane.add(panel_4);

		JLabel lblNewLabel = new JLabel("History");
		String labels1[] = { "None" };
		list1 = new JList(labels1);
		list1.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (list1.getSelectedValue() != null) {
					String selected = list1.getSelectedValue().toString();
					String text = hashmap.get(selected);
					currentTextArea.setText(text);
				}
			}
		});
		list1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list1.setVisibleRowCount(4);
		list1.setFixedCellHeight(12);
		list1.setFixedCellWidth(200);
		panel_4.add(lblNewLabel);
		panel_4.add(list1);
	}

	public void changeMode(MODE mode) {
		currentTextArea = textArea;
		currentTextArea.setText("");
		currentMode = mode;
		String keyword = textField.getText();
		listSlangWord = searchByKey(keyword);
		list.setListData(convertArrayListToStringArray(listSlangWord));
	}

	public void AddNewSlangWord(String slang_word, String definition) {
		boolean isExist = hashmap.get(slang_word) != null;
		if (isExist) {
			int input = JOptionPane.showConfirmDialog(null, "The slang word is exist. Do you want do replace it?",
					"Select an Option...", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
			if (input == 0) {
				hashmap.replace(slang_word, definition);
				hashmap_value_to_key.replace(definition, slang_word);
			} else if (input == 1) {

			}
		} else {
			hashmap.put(slang_word, definition);
			hashmap_value_to_key.put(definition, slang_word);
		}
	}

	public void UpdateSlangWord(String slang_word, String definition) {
		hashmap.replace(slang_word, definition);
		hashmap_value_to_key.replace(definition, slang_word);
	}

	public ArrayList<String> searchByKey(String keyword) {
		ArrayList<String> result = new ArrayList<String>();
		if (currentMode == MODE.SLANG_WORD_TO_DEFINITION) {
			hashmap.entrySet().stream().filter(e -> e.getKey().toLowerCase().contains(keyword.toLowerCase()))
					.forEach(e -> {
						result.add(e.getKey());
					});
		} else {
			hashmap_value_to_key.entrySet().stream()
					.filter(e -> e.getKey().toLowerCase().contains(keyword.toLowerCase())).forEach(e -> {
						result.add(e.getValue());
					});
		}
		return result;

	}

	public String[] convertArrayListToStringArray(ArrayList arr) {
		String str[] = new String[arr.size()];
		for (int j = 0; j < arr.size(); j++) {
			str[j] = (String) arr.get(j);
		}

		return str;
	}

	public void resetData() {
		String fileName = "slang.backup";
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(fileName));
			String line = reader.readLine();
			while (line != null) {
				String[] parts = line.split("`");
				hashmap.put(parts[0], parts[1]);
				hashmap_value_to_key.put(parts[1], parts[0]);
				listSlangWord.add(parts[0]);
				// read next line
				line = reader.readLine();
			}
			reader.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void loadDataFromFile() {
		String fileName = "slang.data";
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(fileName));
			String line = reader.readLine();
			while (line != null) {
				String[] parts = line.split("`");
				hashmap.put(parts[0], parts[1]);
				hashmap_value_to_key.put(parts[1], parts[0]);
				listSlangWord.add(parts[0]);
				// read next line
				line = reader.readLine();
			}
			reader.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<String> generateSlangWord(int number) {
		ArrayList<String> result = new ArrayList<String>();
		for (int i = 0; i < number; i++) {
			Object randomName = hashmap.keySet().toArray()[new Random().nextInt(hashmap.keySet().toArray().length)];
			result.add(randomName.toString());
		}

		return result;

	}

	public void removeLastLine() throws IOException {
//		String fileName = "slang.data";
//		RandomAccessFile f = new RandomAccessFile(fileName, "rw");
//		long length = f.length() - 1;
//		byte b;
//		do {                     
//		  length -= 1;
//		   f.seek(length);
//		   b = f.readByte();
//		} while(b != 10);
//		f.setLength(length+1);
//		f.close();
	}

	public void saveDataToFile() {
		String fileName = "slang.data";
		BufferedWriter writer;
		try {
			writer = new BufferedWriter(new FileWriter(fileName));
			hashmap.entrySet().parallelStream().forEach((entry) -> {
				String currentKey = entry.getKey().toString();
				String currentValue = entry.getValue().toString();
				try {
					writer.write(currentKey + "`" + currentValue + "\n");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void copyToBackUp() throws IOException {
		InputStream in = new FileInputStream(new File("slang.data"));
		OutputStream out = new FileOutputStream(new File("slang.backup"));
		byte[] buf = new byte[1024];
		int len;

		while ((len = in.read(buf)) > 0) {
			out.write(buf, 0, len);
		}
		in.close();
		out.close();
	}
}
