package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.IntervalCategoryDataset;

import model.EmpDAO;
import model.ProjectDAO;
import model.rec.MemoVO;
import model.rec.ProjectVO;

public class ProjectView extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	static EmpDAO empDAO = null;
	static String empId;
	static String password;
	static int vNum;
	
	OpenTableModel tmMemo;
	ArrayList list;
	ProjectDAO dao;
	ProjectVO vo;
	MemoVO mvo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProjectView frame = new ProjectView(vNum, empId, password);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private JFreeChart createGanttChart(int vNum) {
		final IntervalCategoryDataset dataset = GraphView.createDataset(vNum);
		final JFreeChart chart = GraphView.createChart(dataset);
		    return chart;
	}
	
	/**
	 * Create the frame.
	 * @param vNum 
	 */
	
	public ProjectView(int vNum, String empId, String password) {
		
		try {
			dao = new ProjectDAO();
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "projectview DB ���� ���� : " + e.getMessage());
		}
		
		System.out.println(vNum + "��ȣ�͵�");
		System.out.println(empId + "�����ȣ�Ե�");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1008, 921);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		setLocationRelativeTo(null); 		// â ��� ����
		setResizable(false);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(503, 547, 479, 317);
		contentPane.add(scrollPane);
		scrollPane.setToolTipText("");
		
		tmMemo = new OpenTableModel ();
		table = new JTable();
//		table.setBorder(new TitledBorder(new EtchedBorder()));

//		table.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "�׷���", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {"�޸�����", "�ۼ���", "�ۼ�����"}
		));
		scrollPane.setViewportView(table);
		
		//�׷���
		
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int col = 0;
				int row = table.getSelectedRow();
				int vMemo = (Integer) table.getValueAt(row, col);				
				
				try {
					dao.MemoClicked(vMemo, empId);
					MemoOpenView MemoOpen = new MemoOpenView(vMemo, empId);
					MemoOpen.setVisible(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.out.println(vMemo + "�޸�ѹ��Ե�");
			}
			
			
		});
		
		JScrollPane Graph = new JScrollPane();
		Graph.setViewportBorder(new TitledBorder(null, "�׷���", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		Graph.setBounds(12, 47, 970, 446);
		contentPane.add(Graph);
		
		JFreeChart chart = createGanttChart(vNum);
	    ChartPanel chartPanel = new ChartPanel(chart);
	    Graph.setViewportView(chartPanel);
		// �׷��� ��
		
		
		JLabel lblNewLabel = new JLabel("������Ʈ �α׸�");
		lblNewLabel.setFont(new Font("����", Font.PLAIN, 14));
		lblNewLabel.setBounds(12, 10, 103, 29);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("������Ʈ ����");
		lblNewLabel_1.setFont(new Font("����", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(12, 510, 103, 25);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("������Ʈ �޸�");
		lblNewLabel_2.setFont(new Font("����", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(503, 510, 95, 27);
		contentPane.add(lblNewLabel_2);
		
		JButton btnNewButton = new JButton("Ȩ");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					dispose();		// ����â �ݱ�
					MainView main = new MainView(empId, password);
					main.setVisible(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setOpaque(false);
		btnNewButton.setFocusPainted(false);
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.setBorderPainted(false);
		btnNewButton.setBounds(813, 7, 154, 40);
		contentPane.add(btnNewButton);
		// �޸��� ��
		
		
		
		JLabel lblNewLabel_3 = new JLabel("������Ʈ��");
		lblNewLabel_3.setFont(new Font("����", Font.PLAIN, 17));
		lblNewLabel_3.setBounds(12, 547, 95, 29);
		contentPane.add(lblNewLabel_3);
		
		textField = new JTextField();
		textField.setBounds(103, 547, 388, 29);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_3_1 = new JLabel("�ѿ���");
		lblNewLabel_3_1.setFont(new Font("����", Font.PLAIN, 17));
		lblNewLabel_3_1.setBounds(12, 608, 103, 29);
		contentPane.add(lblNewLabel_3_1);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(103, 608, 388, 29);
		contentPane.add(textField_1);
		
		JLabel lblNewLabel_3_2 = new JLabel("������");
		lblNewLabel_3_2.setFont(new Font("����", Font.PLAIN, 17));
		lblNewLabel_3_2.setBounds(12, 663, 95, 29);
		contentPane.add(lblNewLabel_3_2);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(12, 702, 209, 29);
		contentPane.add(textField_2);
		
		JLabel lblNewLabel_3_3 = new JLabel("������");
		lblNewLabel_3_3.setFont(new Font("����", Font.PLAIN, 17));
		lblNewLabel_3_3.setBounds(258, 663, 95, 29);
		contentPane.add(lblNewLabel_3_3);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(258, 702, 233, 29);
		contentPane.add(textField_3);
		
		JLabel lblNewLabel_3_2_1 = new JLabel("�����");
		lblNewLabel_3_2_1.setFont(new Font("����", Font.PLAIN, 17));
		lblNewLabel_3_2_1.setBounds(12, 756, 95, 29);
		contentPane.add(lblNewLabel_3_2_1);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(258, 795, 233, 29);
		contentPane.add(textField_4);
		
		JLabel lblNewLabel_3_3_1 = new JLabel("�з�");
		lblNewLabel_3_3_1.setFont(new Font("����", Font.PLAIN, 17));
		lblNewLabel_3_3_1.setBounds(258, 756, 95, 29);
		contentPane.add(lblNewLabel_3_3_1);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(12, 795, 209, 29);
		contentPane.add(textField_5);
		
		// ������Ʈ ���� ��ư
		JButton projectMod = new JButton("������Ʈ ����");
		projectMod.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProjectModifyView modify = new ProjectModifyView(vNum);
				modify.setVisible(true);
			}
		});
		projectMod.setBounds(382, 511, 109, 23);
		contentPane.add(projectMod);
		// ������Ʈ ���� ��ư
		setStyle();
		
		
		// ������ȣ vNum
		// ȭ�鿡 �������
		try {
			vo = dao.ProjectOut(vNum);
			System.out.println("������Ʈ �� ������Ʈ ��ȣ �ѱ� : " + vNum);
			System.out.println("������Ʈ �� ������Ʈ ��ȣ �Ѱܼ� ���� ���� : " + vo.getProName());
			textField.setText(vo.getProName());
			textField_1.setText(Integer.toString(vo.getProBudget()));
			textField_2.setText(vo.getProStart());
			textField_3.setText(vo.getProEnd());
			textField_4.setText(vo.getBdfName());
			textField_5.setText(vo.getTeamName());		
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.println(e1.getMessage()+ " ���� �������� �� ��������!");
		}
		try {
			list = dao.MemoTable(vNum);
			tmMemo.data = list;
			table.setModel(tmMemo);	
			
			JButton projectMod_1 = new JButton("�޸��ۼ�");
			projectMod_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.out.println("�޸��ۼ� ��ư �ν� �Ǵ��� Ȯ��");
					MemoaddView memoadd = new MemoaddView(vNum, empId, password);
					
					memoadd.setVisible(true);
				}
			});
			projectMod_1.setBounds(858, 511, 109, 23);
			contentPane.add(projectMod_1);
				
		}catch(Exception e2) {
			 JOptionPane.showMessageDialog(null, "���̺� ��� ���� :" + e2.getMessage()); 
		}
			
		}
			
		
		
		// ȭ�鿡 ������� ��
		
		
		// �̺�Ʈ �θ���
		// �ؽ�Ʈ �ʵ� ���� �Ұ��� ��Ÿ��
	
		
		
		
		void setStyle() {
			// �ؽ�Ʈ�ʵ� ������������ �ʵ��� ����
			textField.setEditable(false);
			textField_1.setEditable(false);
			textField_2.setEditable(false);
			textField_3.setEditable(false);
			textField_4.setEditable(false);
			textField_5.setEditable(false);
			
		}
		
//	void selectTable() {
//		try {
//			list = dao.MemoTable(vNum);
//			rListTable.data = list;
//			table.setModel(rListTable);	
//		} catch (Exception e) {
//			// TODO: handle exception
//			 JOptionPane.showMessageDialog(null, "���̺� ��� ���� :" + e.getMessage()); 
//		}
//	}

class OpenTableModel extends AbstractTableModel{
	ArrayList data = new ArrayList();
	String [] columnNames = { "�޸��ȣ", "�ۼ���", "�޸�����", "�ۼ�����" };
	
    public int getRowCount() { 
        return data.size(); 
    } 
	
    public int getColumnCount() { 
        return columnNames.length;  
    } 
     
    public Object getValueAt(int rowIndex, int columnIndex) { 
      ArrayList temp = (ArrayList) data.get(rowIndex);
        return temp.get( columnIndex ); 
    }
    public String getColumnName(int col) {
		return columnNames[col];
	}

}
public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
	Object o = e.getSource();
	
}
}
