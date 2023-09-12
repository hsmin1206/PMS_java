package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import model.EmpDAO;
import model.ProjectOpenDAO;
import model.rec.ProjectOpenVO;
import javax.swing.ScrollPaneConstants;
import javax.swing.JButton;

public class ProjectOpenView extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField textField;
	private JTable table;
	private String empName;
	static EmpDAO empDAO = null;
	static String empId;
	static String password;

	ArrayList list = null;
	ProjectOpenDAO dao;
	ProjectOpenVO vo;
	OpenTableModel tmVideo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
		            ProjectOpenView frame = new ProjectOpenView(empId, password);
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
	public ProjectOpenView(String empId, String password) {
//		this.empId = empId;
		newObject(empId, password);
		try {
			dao = new ProjectOpenDAO();
		} catch (Exception e) {
			System.out.println("ㄱㄱㅇ");
			e.printStackTrace();
			// TODO: handle exception
		}
	}

	void newObject(String empId, String password) {
		System.out.println("empId 값이 ProjectOpenView에 들어갔는지 확인 : " + empId);
//		this.empId = empId;
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 608, 579);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		setLocationRelativeTo(null); // 창 가운데 정렬
		setResizable(false);

		JPanel panel = new JPanel();
		panel.setBounds(12, 76, 566, 37);
		contentPane.add(panel);
		panel.setLayout(null);
		
		// 버튼 시작
		JButton btnNewButton = new JButton("프로젝트 등록");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProjectPutupView putup = new ProjectPutupView(empId, password);
				putup.setVisible(true);
			}
		});
		btnNewButton.setBounds(433, 24, 145, 31);
		contentPane.add(btnNewButton);
		// 버튼 끝
		

		// 실제 내용 출력되는 곳 시작
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 123, 566, 398);
		contentPane.add(scrollPane);

		tmVideo = new OpenTableModel();
		table = new JTable(tmVideo);
		table.setModel(
				new DefaultTableModel(new Object[][] {}, new String[] { "프로젝트번호","프로젝트명", "시작날짜", "만기날짜", "담당팀", "진행여부" }));
		scrollPane.setViewportView(table);
		//addActionListener
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int col = 0;
				int row = table.getSelectedRow();
				int vNum = (Integer) table.getValueAt(row, col);
				
				dispose();		// 현재창 닫기
				ProjectView project = new ProjectView(vNum, empId, password);
				project.setVisible(true);
			}
		});
		// 실제 내용 출력 꿑

		// 검색 스크롤바 시작
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "프로젝트", "담당팀" }));
		comboBox.setBounds(0, 0, 240, 37);
		panel.add(comboBox);
		// 검색 스크롤바 끝

		// 검색어 입력창 시작
		textField = new JTextField();
		textField.setBounds(239, 0, 327, 37);
		panel.add(textField);
		textField.setColumns(10);
		// 검색어 입력창 끝

		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int sel = comboBox.getSelectedIndex();
				String test = textField.getText();
				
				try {
					list = dao.ProjectSearch(sel, test);
					tmVideo.data = list;
					table.setModel(tmVideo);			
					tmVideo.fireTableDataChanged();		
				} catch (Exception e2) {
					// TODO: handle exception
					System.out.println(e2.getMessage() + " 여기 textField.addActionListener");
				}
			}
		});

	}
	
	class OpenTableModel extends AbstractTableModel{
		ArrayList data = new ArrayList();
		String[] columnNames = { "프로젝트번호", "프로젝트명", "시작날짜", "만기날짜", "담당팀", "진행여부" };
		
		
		public int getRowCount() {
			return data.size();
		}


		@Override
		public int getColumnCount() {
			return columnNames.length;
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			ArrayList temp = (ArrayList) data.get(rowIndex);
			return temp.get(columnIndex);
		}
		
		public String getColumnName(int col) {
			return columnNames[col];
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		
	}
}
