import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Panel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.List;
import java.awt.Scrollbar;
import javax.swing.JList;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.ListModel;
import javax.swing.JLabel;
import java.awt.Dimension;
import java.awt.Component;
import javax.swing.ListSelectionModel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MainJFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private HashMap<String, String> hashmap = new HashMap<String, String>();
	private ArrayList<String> listSlangWord = new ArrayList<String>();
	private JList<String> list;

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

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("File");
		menuBar.add(mnNewMenu);

		JMenuItem mntmNewMenuItem = new JMenuItem("Exit");
		mnNewMenu.add(mntmNewMenuItem);

		JMenu mnNewMenu_1 = new JMenu("Dictionary");
		menuBar.add(mnNewMenu_1);

		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Import");
		mnNewMenu_1.add(mntmNewMenuItem_2);

		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Export");
		mnNewMenu_1.add(mntmNewMenuItem_3);

		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Reset");
		mnNewMenu_1.add(mntmNewMenuItem_4);

		JMenu mnNewMenu_2 = new JMenu("Game");
		menuBar.add(mnNewMenu_2);

		JMenuItem mntmNewMenuItem_5 = new JMenuItem("Slang word -> Definition");
		mntmNewMenuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Game_slang_definition game_slang_def_screen = new Game_slang_definition();
				game_slang_def_screen.setVisible(true);
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_5);

		JMenuItem mntmNewMenuItem_6 = new JMenuItem("Definition -> Slang word  ");
		mnNewMenu_2.add(mntmNewMenuItem_6);

		JMenu mnNewMenu_3 = new JMenu("Help");
		menuBar.add(mnNewMenu_3);

		JMenuItem mntmNewMenuItem_7 = new JMenuItem("About");
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
			}
		});
		panel.setLayout(null);

		JScrollPane panel_1 = new JScrollPane();
		panel_1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		panel_1.setLocation(5, 38);
		panel_1.setSize(new Dimension(210, 419));
		contentPane.add(panel_1);

		JList list = new JList(listSlangWord.toArray());
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
				System.out.println(listSlangWord.size());
				list.setListData(convertArrayListToStringArray(listSlangWord));

			}
		});

		textField.setBounds(10, 6, 537, 20);
		panel.add(textField);
		textField.setColumns(40);
		panel.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Edit");
		btnNewButton_1.setBounds(777, 5, 89, 23);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panel.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("Delete");
		btnNewButton_2.setBounds(876, 5, 84, 23);
		panel.add(btnNewButton_2);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(215, 38, 654, 419);
		contentPane.add(tabbedPane);

		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Definition - Slang", null, panel_3, null);

		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Slang - Definition", null, panel_2, null);

		JPanel panel_4 = new JPanel();
		panel_4.setBounds(869, 38, 206, 419);
		contentPane.add(panel_4);

		JLabel lblNewLabel = new JLabel("History");
		String labels1[] = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J" };
		JList list1 = new JList(labels1);
		list1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list1.setVisibleRowCount(4);
		list1.setFixedCellHeight(12);
		list1.setFixedCellWidth(200);
		panel_4.add(lblNewLabel);
		panel_4.add(list1);
	}

	public ArrayList<String> searchByKey(String keyword) {
		ArrayList<String> result = new ArrayList<String>();
		hashmap.entrySet().stream().filter(e -> e.getKey().toLowerCase().contains(keyword.toLowerCase())).forEach(e -> {
			result.add(e.getKey());
		});
		return result;

	}

	public String[] convertArrayListToStringArray(ArrayList arr) {
		String str[] = new String[arr.size()];
		for (int j = 0; j < arr.size(); j++) {
			str[j] = (String) arr.get(j);
		}

		return str;
	}

	public void loadDataFromFile() {
		String fileName = "slang.txt";
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(fileName));
			String line = reader.readLine();
			while (line != null) {
				String[] parts = line.split("`");
				System.out.println(parts[1]);
				hashmap.put(parts[0], parts[1]);
				listSlangWord.add(parts[0]);
				// read next line
				line = reader.readLine();
			}
			reader.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void saveDataToFile() {
		String fileName = "slang.out";
		BufferedWriter writer;
		try {
			writer = new BufferedWriter(new FileWriter(fileName));
			hashmap.entrySet().parallelStream().forEach((entry) -> {
	            Object currentKey = entry.getKey();
	            Object currentValue = entry.getValue();
	            try {
					writer.write(currentKey+"`"+currentValue);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        });
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
