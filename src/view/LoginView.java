package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import model.EmpDAO;
import model.rec.EmpVO;

public class LoginView extends JFrame {

	private JPanel contentPane;
	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	JPasswordField jPasswordField;
	JButton btnNewButton;
	EmpVO empVO = null;
	EmpDAO empDAO = null;

	// 세션 상태를 나타내는 변수
	static boolean loggedIn = false;

	String empId;
	String password;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginView frame = new LoginView();
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
	public LoginView() {
		
		this.empId = "";
		this.password = "";
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainView.class.getResource("/img/logo_i.png")));

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 501, 536);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		setLocationRelativeTo(null); // 창 가운데 정렬
		setResizable(false);

		JLabel lblNewLabel = new JLabel();
//		lblNewLabel.setIcon(new ImageIcon(MainView.class.getResource("/img/logo_p.png")));
		ImageIcon icon = new ImageIcon(MainView.class.getResource("/img/logo_p.png"));
		Image img = icon.getImage();
		Image updateImg = img.getScaledInstance(295, 108, Image.SCALE_SMOOTH);
		ImageIcon updateIcon = new ImageIcon(updateImg);
		lblNewLabel.setIcon(updateIcon);

		lblNewLabel.setBounds(98, 41, 295, 108);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("사원번호");
		lblNewLabel_1.setBounds(147, 267, 57, 15);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("비밀번호");
		lblNewLabel_2.setBounds(147, 315, 57, 15);
		contentPane.add(lblNewLabel_2);

		// 아이디 입력 필드
		textField = new JTextField();
		textField.setBounds(238, 264, 116, 21);
		contentPane.add(textField);
		textField.setColumns(10);

		// 비밀번호 입력 필드 JPasswordField
		jPasswordField = new JPasswordField();
		jPasswordField.setBounds(238, 312, 116, 21);
		contentPane.add(jPasswordField);
		jPasswordField.setColumns(10);

		btnNewButton = new JButton("로그인");

		ImageIcon icon2 = new ImageIcon(MainView.class.getResource("/img/login.png"));
		Image img2 = icon2.getImage();
		Image updateImg2 = img2.getScaledInstance(115, 23, Image.SCALE_SMOOTH);
		ImageIcon updateIcon2 = new ImageIcon(updateImg2);
		btnNewButton.setIcon(updateIcon2);

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = textField.getText().trim();
				char[] passwordChars = jPasswordField.getPassword(); // 1.getPassword() 메서드를 호출하여 char 배열로 비밀번호를 가져온 다음,
				password = new String(passwordChars).trim(); // 2.String으로 변환하여 사용
				// jPasswordField.getText().trim(); //getText 메소드를 이용하면 입력된 비밀번호가 일반텍스트로 변환되어
				// 보안에 취약할 수가 있어서 위의 코드로 변경
				try {
					if (id.length() == 0 || password.length() == 0) {
						JOptionPane.showMessageDialog(null, "사원번호 또는 PW 입력이 안되었습니다.", "사번 또는 비번 입력안됨",
								JOptionPane.DEFAULT_OPTION);

						return;
					}
					if (checkLogin(id, password)) {
//					    empDAO.SerchInfo2(id, password);
//					    int aa = empDAO.SerchInfo2(id, password).getJg_no();
//					    System.out.println("jg_no 값이 나오는지 확인 : " +  aa);
						openMainView();
//						dispose();			// 현재창 닫기
//						MainView main = new MainView();
//						main.setVisible(true);//main으로 화면 이동
					} else {
						JOptionPane.showMessageDialog(null, "사번 또는 비밀번호를 잘못 입력하였습니다.", "사번 또는 비번 잘못 입력됨",
								JOptionPane.DEFAULT_OPTION);
					}

				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}

		});
		btnNewButton.setBounds(257, 408, 97, 23);
		contentPane.add(btnNewButton);

	}

	private boolean checkLogin(String id, String password) {
		empDAO = new EmpDAO();
		boolean found = empDAO.SerchInfo(id, password);
		this.empId = id;

		System.out.println();

		if (found) {
			loggedIn = true; // 세션 상태를 로그인으로 변경
			System.out.println("checkLogin 메소드의 empId : " + empId);
		}
		return found;
	}

	private void openMainView() {
		if (loggedIn) {

			System.out.println("세션 로그인 성공");
			System.out.println("loggedIn : " + loggedIn);
			try {
				String empName = empDAO.SerchInfo2(empId, password).getEmp_name();// 비밀번호 파라미터 제외하고 아이디만 받아도 될지?
				JOptionPane.showMessageDialog(null, empName + "님 로그인이 완료 되었습니다", "로그인 완료", JOptionPane.DEFAULT_OPTION);
				dispose(); // 현재 창 닫기
				MainView main = new MainView(empId, password);
				main.setVisible(true); // main으로 화면 이동
			} catch (Exception e) {
				// TODO: handle exception
			}

		} else {
			JOptionPane.showMessageDialog(null, "로그인을 해야 합니다.", "로그인 필요", JOptionPane.DEFAULT_OPTION);
		}
	}

}
