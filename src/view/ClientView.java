package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import model.ClientDAO;
import model.rec.ClientVO;

public class ClientView extends JFrame {

	JTextField textField;
	JButton btnNewButton;
	JButton btnNewButton_1;
	JTable table;
	OpenTableModel tmVideo;

	ClientDAO dao;
	ClientVO vo;
	ArrayList list;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientView frame = new ClientView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ClientView() {
		newObject();
	try {
			dao = new ClientDAO();
			System.out.println("발주기간 디비 연결 성공");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, " 비디오 DB연결 실패 : " + e.getMessage());
		}
	}
		
	void newObject() {
		setLocationRelativeTo(null);
		setBounds(100, 100, 981, 874);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		getContentPane().setBackground(new Color(255, 255, 255));
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainView.class.getResource("/img/logo_i.png")));
		
		JLabel lblNewLabel = new JLabel("프로젝트명");
		lblNewLabel.setBounds(12, 20, 129, 15);
		getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(12, 36, 195, 26);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 100, 929, 658);
		getContentPane().add(scrollPane);
		
		tmVideo = new OpenTableModel();
		table = new JTable(tmVideo);
		table.setModel(
				new DefaultTableModel(new Object[][] {}, new String[] {"협 력 업 체 명", "납 품 일 자", "납 품 내 용", "납 품 갯 수", "납 품 비 용"}));
		scrollPane.setViewportView(table);
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int col = 0;
				int row = table.getSelectedRow();
				int vNum = (Integer) table.getValueAt(row, col);
			}
		});
		table.getTableHeader().setBackground(Color.WHITE);
		table.setFillsViewportHeight(true);
		
		btnNewButton_1 = new JButton("검색하기");
		btnNewButton_1.setBounds(214, 34, 97, 28);
		getContentPane().add(btnNewButton_1);
		
		JLabel lblNewLabel_1 = new JLabel("납품내역서");
		lblNewLabel_1.setBounds(12, 75, 86, 15);
		getContentPane().add(lblNewLabel_1);
		
		
		btnNewButton_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String name = textField.getText();
				
				try {
					ArrayList<ArrayList<String>> clientInfo = dao.searcProject(name);
										System.out.println(clientInfo);
					for (ArrayList<String> Info : clientInfo) {
						String ProjectName = Info.get(0);
						String SupplyDate = Info.get(1);
						String SupplyContent = Info.get(2);
						String SupplyCount = String.valueOf(Info.get(3));
						String SupplyAmount = String.valueOf(Info.get(4));						
						
						DefaultTableModel model = (DefaultTableModel) table.getModel();
	                    model.addRow(new Object[] {ProjectName, SupplyDate, SupplyContent, SupplyCount, SupplyAmount});
					}
					
					
					
				} catch (Exception e2) {
					// TODO: handle exception
					 e2.printStackTrace();

				}
				
			}
		});

}

class OpenTableModel extends AbstractTableModel {
	ArrayList data = new ArrayList();
	String[] columnNames = { "협 력 업 체 명", "납 품 일 자", "납 품 내 용", "납 품 갯 수", "납 품 비 용" };
	
	
	public int getColumnCount() {
		return columnNames.length;
	}
	
	public int getRowCount() {
		return data.size();
	}

	public Object getValueAt(int row, int col) {
		ArrayList temp = (ArrayList)data.get(row);
		return temp.get(col);
		}
	public String getColumName(int col) {
		return columnNames[col];
		}
}
}

