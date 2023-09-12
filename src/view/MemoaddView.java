package view;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollBar;
import model.EmpDAO;
import model.ProjectDAO;
import model.rec.MemoVO;
import javax.swing.ScrollPaneConstants;
import java.awt.Color;

public class MemoaddView extends JFrame{
	static EmpDAO empDAO = null;
	static String empId;
	static String password;
	static int vNum;
	
	ProjectDAO dao;
	MemoVO mvo;
	private JTextField textField_2;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_3;
	private JTextArea textArea;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MemoaddView frame = new MemoaddView(vNum, empId, password);
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
	public MemoaddView(int vNum, String empId, String password) {
		getContentPane().setBackground(new Color(255, 255, 255));
		
		try {
			dao = new ProjectDAO();
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "projectview DB 연결 실패 : " + e.getMessage());
		}
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 909, 823);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("작성내용");
		lblNewLabel_2.setBounds(28, 78, 57, 15);
		getContentPane().add(lblNewLabel_2);
		
		JButton btnNewButton_2 = new JButton("작성완료");
		btnNewButton_2.setBounds(760, 751, 103, 23);
		getContentPane().add(btnNewButton_2);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					dispose();		// 현재창 닫기
					MainView main = new MainView(empId, password);
					main.setVisible(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
	
		//프로젝트 메모 작성완료
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setBounds(27, 103, 836, 638);
		getContentPane().add(scrollPane_1);
		
		JTextArea textArea = new JTextArea("");
		scrollPane_1.setViewportView(textArea);
		
		JLabel lblNewLabel_1 = new JLabel("메모제목");
		lblNewLabel_1.setBounds(28, 23, 58, 45);
		getContentPane().add(lblNewLabel_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(92, 30, 139, 32);
		getContentPane().add(textField_2);
		
		JLabel lblNewLabel_3_1 = new JLabel("프로젝트명");
		lblNewLabel_3_1.setBounds(243, 38, 72, 15);
		getContentPane().add(lblNewLabel_3_1);
		
		JLabel lblNewLabel_3_1_1 = new JLabel("작성자");
		lblNewLabel_3_1_1.setBounds(669, 38, 72, 15);
		getContentPane().add(lblNewLabel_3_1_1);
		
		textField = new JTextField();
		textField.setText((String) null);
		textField.setColumns(10);
		textField.setBounds(715, 30, 139, 32);
		getContentPane().add(textField);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(313, 30, 208, 32);
		getContentPane().add(textField_1);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(588, 30, 42, 32);
		getContentPane().add(textField_3);
		
		JLabel lblNewLabel_3_1_2 = new JLabel("팀명 :");
		lblNewLabel_3_1_2.setBounds(548, 38, 72, 15);
		getContentPane().add(lblNewLabel_3_1_2);
		
		
		
		
		try {
			mvo = new MemoVO();
			mvo = dao.memoaddOpen(empId);
			String teamName = dao.teams(empId);
			
			
			textField.setText(mvo.getMemo_write());
			textField_1.setText(mvo.getPro_name());
			textField_3.setText(teamName);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String memo_title = textField_2.getText();
				String contents = textArea.getText();
				try {
					mvo = new MemoVO(memo_title, empId, contents);
					int memoList = dao.Memoadd(mvo, vNum);
//					MemoVO team = dao.teams(empId);
//					String teamname = team.getTeam_name();
					System.out.println(memoList);
					
//					System.out.println(teamname);
					try {
						
						dao.MemoCheckListAdd(memoList, vNum);
						
					} catch (Exception e2) {
						// TODO: handle exception
						System.out.println("메모수신확인에서 오류");
					}

					
					
				} catch (Exception e2) {
					// TODO: handle exceptiono
					JOptionPane.showMessageDialog(null, "메모작성실패 : " + e2.getMessage());
				}
			}
		});
		
		
		
		
		
	}
}
