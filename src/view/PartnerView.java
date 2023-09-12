package view;

import java.awt.Button;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import model.EmpDAO;
import model.PartnerDAO;
import model.rec.PartnerVO;

public class PartnerView extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField textField_2;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_8;
	private JTable table;
	PartnerListTableModel tm;
	PartnerDAO dao;
	PartnerVO vo;
	ArrayList list = null;
	private String category;
	JTable tableRecentList;
	static EmpDAO empDAO = null;
	String empId;
	String password;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PartnerView frame = new PartnerView("6", "1234");
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
	public PartnerView(String empId, String password) {
		this.empId = "";
		this.password = "";
		// DB ����
		try {
			dao = new PartnerDAO();
			System.out.println("DB 연결  성공");

		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "DB 연결 실패: " + e.getMessage());
			e.printStackTrace();
		}
		// ��� ������ ��ü ����
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 870, 692);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));

		setContentPane(contentPane);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(507, 3, 338, 213);
		panel.setBorder(new TitledBorder(null, "협력 업체 확인", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(14, 4, 481, 212);
		panel_1.setBorder(new TitledBorder(null, "협력 업체 등록", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(14, 226, 831, 388);

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[]{ "업 체 명", "담 당 자", "전화 번호", "e-mail", "업체 분류명", "지 역" }) {
			Class[] columnTypes = new Class[] { String.class, String.class, String.class, String.class, String.class,
					String.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		detailshow();

		scrollPane.setViewportView(table);
		
		table.getTableHeader().setBackground(Color.WHITE);
		table.setFillsViewportHeight(true);
		
		panel.setLayout(null);
		// ��ü�з��� ���� ������
		textField_8 = new JTextField();
		textField_8.setColumns(10);
		textField_8.setBounds(140, 81, 116, 34);
		panel.add(textField_8);
		panel_1.setLayout(null);
		textField_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String pd_name = textField_8.getText();
				table = new JTable();//�ʱ�ȭ
				try {	
//					System.out.println("��ü �з��� ����");
					// Ŭ���� �߰��Ȱ� ���� �̱�
					list = dao.partnerCheck1(pd_name);//pd_name�� list ��Ƽ� ����
					tm.data = list;
					table.setModel(tm);
					System.out.println(list);
					tm.fireTableDataChanged();//����Ȯ��
					clrearScreen();
					
				} catch (Exception e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "업체 분류명 출력 실패 : " + e2.getMessage());
				}
				detailshow();
			}
			
		});

		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(72, 22, 135, 29);
		panel_1.add(textField_2);

		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(72, 79, 135, 29);
		panel_1.add(textField);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(72, 139, 135, 29);
		panel_1.add(textField_1);

		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(316, 22, 136, 29);
		panel_1.add(textField_3);

		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(316, 79, 136, 29);
		panel_1.add(textField_4);

		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(316, 139, 136, 29);
		panel_1.add(textField_5);

		JLabel lblNewLabel = new JLabel("업 체 명");
		lblNewLabel.setBounds(12, 29, 57, 15);
		panel_1.add(lblNewLabel);

		JLabel lblDam = new JLabel("담 당 자");
		lblDam.setBounds(12, 86, 57, 15);
		panel_1.add(lblDam);

		JLabel lblTel = new JLabel("전화 번호");
		lblTel.setBounds(12, 146, 57, 15);
		panel_1.add(lblTel);

		JLabel lblEamil = new JLabel("e-mail");
		lblEamil.setBounds(258, 29, 150, 15);
		panel_1.add(lblEamil);

		JLabel lblCcode = new JLabel("업체 분류명");
		lblCcode.setBounds(244, 86, 71, 15);
		panel_1.add(lblCcode);

		JLabel lblLoc = new JLabel("지 역");
		lblLoc.setBounds(258, 146, 57, 15);
		panel_1.add(lblLoc);

		// JTable�� Ŭ�� �̺�Ʈ
		Button button = new Button("등 록");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object evt = e.getSource();
				if (evt == button) {
					String pa_company = textField_2.getText();
					String pa_dam = textField.getText();
					String pa_email = textField_3.getText();
					String pa_tel = textField_1.getText();
					String pd_name = textField_4.getText();
					String pa_loc = textField_5.getText();
					
					PartnerVO vo = new PartnerVO(pa_company, pa_dam, pa_email, pa_tel, pd_name, pa_loc);
//	 			System.out.println(pa_company + pa_dam + pa_tel + pa_email + pd_name + pa_loc); ��ġ Ȯ��        
					try {
						dao.partnerInsert(vo);
						System.out.println("등록 성공");
						// Ŭ���� �߰��Ȱ� ���� �̱�
						list = dao.partnerCheck();
						tm.data = list;
						table.setModel(tm);	
						tm.fireTableDataChanged();
						table.repaint();
						detailshow();
						
					} catch (Exception e2) {
						// TODO: handle exception
						JOptionPane.showMessageDialog(null, "���� ��ü ���� ��� ���� : " + e2.getMessage());
					}

				}

			}
		});
			
		
		button.setBounds(406, 179, 46, 23);
		panel_1.add(button);
		contentPane.setLayout(null);
		contentPane.add(scrollPane);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(0, 0, 10, 10);
		contentPane.add(panel_2);
		contentPane.add(panel_1);
		contentPane.add(panel);

		JLabel lblNewLabel_1 = new JLabel("업체 분류명");
	
		lblNewLabel_1.setBounds(63, 91, 70, 15);
		panel.add(lblNewLabel_1);
		
		Button button_1 = new Button("메인으로");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				MainView main = null;
				try {
					main = new MainView(empId, password);
					main.setVisible(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
//	               e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "Main 연결 실패" + e1.getMessage());
				}
				dispose();
			}
		});

		button_1.setBounds(768, 620, 76, 23);
		contentPane.add(button_1);

	}
	
	void clrearScreen() {
		textField_8.setText("");
	}
	

	// table ��ü ���� ���
	void detailshow() {
		tm = new PartnerListTableModel();

		try {
			list = dao.partnerCheck();
			tm.data = list;
			try {
				table.setModel(tm);
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e.getMessage() + " ����");
			}
			tm.fireTableDataChanged();// ���� ���� ����
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "�˻� ����" + e.getMessage());
		}
	}

	class PartnerListTableModel extends AbstractTableModel { //�ؿ� �ڵ� ����

		ArrayList data = new ArrayList();
		String[] columnNames = { "업 체 명", "담 당 자", "e-mail", "전화 번호",  "업체 분류명", "지 역" };

		public int getColumnCount() {
			return columnNames.length;
		}

		public int getRowCount() {
			return data.size();
		}

		public Object getValueAt(int row, int col) {
			ArrayList temp = (ArrayList) data.get(row);
			return temp.get(col);
		}

		public String getColumnName(int col) {
			return columnNames[col];
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
}
