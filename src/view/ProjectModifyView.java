package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import model.ProjectOpenDAO;
import model.ProjectPutuDAO;
import model.rec.ProjectPutupVO;
import view.ProjectOpenView;

public class ProjectModifyView extends JFrame {

	private JPanel contentPane;
	private JTextField nameBOX;
	private JTextField budgetBOX;
	private JTextField goBOX;
	private JLabel lblNewLabel_5;
	private JTextField endBOX;
	private JLabel lblNewLabel_6;
	private JButton enrolmentButton;
	JTextArea textArea;
	private JLabel lblNewLabel_7;
	private JLabel lblNewLabel_8;
	ProjectOpenView view;
	
	static String empId;
	static String password;
	ProjectPutupVO vo;
	ProjectPutuDAO dao;
	private JLabel lblNewLabel_2;
	private JTextField timBOX;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProjectModifyView frame = new ProjectModifyView(18);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param vNum 
	 */
	public ProjectModifyView(int vNum) {
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 497, 497);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		setLocationRelativeTo(null); 		//  ��� ����
		setResizable(false);

		
		JLabel lblNewLabel = new JLabel("프로젝트 수정");
		lblNewLabel.setBounds(12, 10, 87, 32);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("프로젝트명");
		lblNewLabel_1.setFont(new Font("굴림", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(12, 67, 87, 35);
		contentPane.add(lblNewLabel_1);
		
		nameBOX = new JTextField();
		nameBOX.setBounds(153, 71, 261, 28);
		contentPane.add(nameBOX);
		nameBOX.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("프로젝트 총예산");
		lblNewLabel_3.setFont(new Font("굴림", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(12, 112, 122, 32);
		contentPane.add(lblNewLabel_3);
		
		budgetBOX = new JTextField();
		budgetBOX.setBounds(153, 115, 261, 28);
		contentPane.add(budgetBOX);
		budgetBOX.setColumns(10);
		
		lblNewLabel_2 = new JLabel("담당팀");
		lblNewLabel_2.setFont(new Font("굴림", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(12, 166, 74, 28);
		contentPane.add(lblNewLabel_2);
		
		timBOX = new JTextField("");
		timBOX.setForeground(Color.BLACK);
		timBOX.setFont(new Font("굴림", Font.PLAIN, 14));
		timBOX.setColumns(10);
		timBOX.setBounds(153, 165, 142, 28);
		contentPane.add(timBOX);
		
		JLabel lblNewLabel_4 = new JLabel("시작일");
		lblNewLabel_4.setFont(new Font("굴림", Font.PLAIN, 14));
		lblNewLabel_4.setBounds(12, 217, 74, 28);
		contentPane.add(lblNewLabel_4);
		
		
		lblNewLabel_7 = new JLabel("");
		lblNewLabel_7.setForeground(new Color(128, 128, 128));
		lblNewLabel_7.setFont(new Font("굴림", Font.PLAIN, 12));
		lblNewLabel_7.setBounds(153, 245, 230, 15);
		contentPane.add(lblNewLabel_7);
		

		goBOX = new JTextField("");
		goBOX.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				lblNewLabel_7.setText("날짜는 2023/01/01 형식을 입력해주세요");
			}
			public void mouseExited(MouseEvent e) {
				try {
					Thread.sleep(900);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				lblNewLabel_7.setText("");
			}
		});
		
		goBOX.setForeground(new Color(0, 0, 0));
		goBOX.setFont(new Font("굴림", Font.PLAIN, 14));
		goBOX.setBounds(153, 216, 142, 28);
		contentPane.add(goBOX);
		goBOX.setColumns(10);
		
		lblNewLabel_5 = new JLabel("종료일");
		lblNewLabel_5.setFont(new Font("굴림", Font.PLAIN, 14));
		lblNewLabel_5.setBounds(12, 270, 74, 28);
		contentPane.add(lblNewLabel_5);
		
		
		lblNewLabel_8 = new JLabel("");
		lblNewLabel_8.setForeground(new Color(128, 128, 128));
		lblNewLabel_8.setFont(new Font("굴림", Font.PLAIN, 12));
		lblNewLabel_8.setBounds(153, 301, 230, 15);
		contentPane.add(lblNewLabel_8);
		
		endBOX = new JTextField("");
		endBOX.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				lblNewLabel_8.setText("날짜는 2023/01/01 형식으로 입력해주세요");
			}
			public void mouseExited(MouseEvent e) {
				try {
					Thread.sleep(900);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				lblNewLabel_8.setText("");
			}
		});
		endBOX.setForeground(new Color(0, 0, 0));
		endBOX.setFont(new Font("굴림", Font.PLAIN, 14));
		endBOX.setColumns(10);
		endBOX.setBounds(153, 270, 142, 28);
		contentPane.add(endBOX);
		
		lblNewLabel_6 = new JLabel("사업세부분야");
		lblNewLabel_6.setFont(new Font("굴림", Font.PLAIN, 14));
		lblNewLabel_6.setBounds(12, 326, 122, 28);
		contentPane.add(lblNewLabel_6);
		
		JComboBox detailsBOX = new JComboBox();
		detailsBOX.setModel(new DefaultComboBoxModel(new String[] {
				"애견박람회", "인테리어박람회", "건축박람회", "박물관상설전시", "박물관특별전시", 
				"미술관상설전시", "미술관특별전시", "전자기기발표회", "신차발표회"}));
		detailsBOX.setBounds(153, 326, 142, 28);
		contentPane.add(detailsBOX);
		
		enrolmentButton = new JButton("수정");
		enrolmentButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = nameBOX.getText();
				String tim = timBOX.getText();
				String budget = budgetBOX.getText();
				String go = goBOX.getText();
				String end = endBOX.getText();
				String details = (String)detailsBOX.getSelectedItem();;
				ProjectPutupVO vo = new ProjectPutupVO(name, tim, budget, go,end,details);
				
				
				try {
					dao = new ProjectPutuDAO();		// �μ�Ʈ DAO, VO ��Ȱ��
					int cnt = dao.modify(vo, vNum);
					System.out.println(cnt + "프로젝트 수정 성공");
					JOptionPane.showMessageDialog(null, "프로젝트 수정 성공", "프로젝트 수정 알람 메세지", JOptionPane.PLAIN_MESSAGE);
					
					dispose();		// ����â �ݱ�
					ProjectOpenView open = new ProjectOpenView(empId, password);
					open.setVisible(true);
				} catch (Exception e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "프로젝트 수정 실패 : " + e2.getMessage());
					System.out.println(e2.getMessage());
				}
				
			}
		});
		enrolmentButton.setBounds(370, 423, 97, 23);
		contentPane.add(enrolmentButton);
		
		// ���� ������ ���鼭 ������ ���� ��ȣ(vNum)�� ���õ� ���� ���
		try {
			dao = new ProjectPutuDAO();
			vo = dao.modifyinfo(vNum);
			
			// ��¥ ���� �������� 2023-01-01 00:00:00���� �������� ������ ���ڿ� �߸��� �ٿ��� �ٽ� ������ �־���
			String go = vo.getGo();		// ���� ��¥
			String go2 = go.substring(0,4) + "/" + go.substring(5,7) + "/" + go.substring(8,10);
			
			String end = vo.getEnd();		// ����¥
			String end2 = end.substring(0,4) + "/" + end.substring(5,7) + "/" + end.substring(8,10);
			
			nameBOX.setText(vo.getName());		// ������
			timBOX.setText(vo.getTim());		// ����
			budgetBOX.setText(vo.getBudget());	// �ѿ���
			goBOX.setText(go2);		
			endBOX.setText(end2);		
			detailsBOX.setSelectedItem(vo.getDetails());;		// �Һз�
			
			
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "수정 정보 가져오는거 실패했다! : " + e.getMessage());
			System.out.println(e.getMessage() + " 수정 정보 오류 확인");
		}
		
		
	}
	
	

}
