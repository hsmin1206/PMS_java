package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.EmpDAO;
import model.JobGrantDAO;
import model.rec.EmpVO;
import model.rec.JobGrantVO;
import network.Send;

public class MainView extends JFrame {
	private JPanel contentPane;
	private String empId;
	JobGrantDAO jobGrantDAO = null;
	JobGrantVO jobGrantVO = null;
	EmpVO empVO = null;
	EmpDAO empDAO = null;
	String password = null;
	String empName = null;

	/**
	 * Launch the ap plication.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainView frame = new MainView("11", "1234");
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
	
	
	public MainView(String empId, String password) throws Exception{
		System.out.println("MainView empId 값 받아 오는지 확인 : " + empId);
		this.empId = empId;
		this.empName = empName;
		setLocationRelativeTo(null);
		getContentPane().setForeground(Color.WHITE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainView.class.getResource("/img/logo_i.png")));
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 501, 636);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel();
//		lblNewLabel.setIcon(new ImageIcon(MainView.class.getResource("/img/logo.png")));
		ImageIcon icon = new ImageIcon(MainView.class.getResource("/img/logo.png"));
		Image img = icon.getImage();
		Image updateImg = img.getScaledInstance(305, 295, Image.SCALE_SMOOTH);
		ImageIcon updateIcon = new ImageIcon(updateImg);
		lblNewLabel.setIcon(updateIcon);
		
		lblNewLabel.setBounds(0, 94, 265, 277);
		contentPane.add(lblNewLabel);
		
		setLocationRelativeTo(null); 		// 창 가운데 정렬
		setResizable(false);
		
		
		// 프로젝트 버튼 시작
		JButton btnNewButton = new JButton("프로젝트");		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				empDAO = new EmpDAO();
				dispose();		// 현재창 닫기
				ProjectOpenView project = new ProjectOpenView(empId, password);
				project.setVisible(true);
			}
		});
		btnNewButton.setIcon(new ImageIcon(MainView.class.getResource("/img/p1.png")));
		btnNewButton.setBounds(326, 63, 147, 37);
		contentPane.add(btnNewButton);
		
		btnNewButton.setCursor(new Cursor(Cursor.HAND_CURSOR));			// 커서 모양 변경 								
		btnNewButton.setBorderPainted(false); 							// 선택 되지 않은 상태와, 마우스 올려 놨을 때 테두리	
		btnNewButton.setContentAreaFilled(false); 						// 배경										
		btnNewButton.setFocusPainted(false);								// 선택 됐을 때 선							
		btnNewButton.setOpaque(false);
		
		// 프로젝트관리 버튼 시작
		JButton btnNewButton2 = new JButton("프로젝트관리");		
		btnNewButton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();		// 현재창 닫기
				ProjectAdminView administrator = new ProjectAdminView(empId, password);
				administrator.setVisible(true);
			
			}
		});
		btnNewButton2.setIcon(new ImageIcon(MainView.class.getResource("/img/p2.png")));
		btnNewButton2.setOpaque(false);
		btnNewButton2.setFocusPainted(false);
		btnNewButton2.setContentAreaFilled(false);
		btnNewButton2.setBorderPainted(false);
		btnNewButton2.setBounds(326, 110, 147, 37);
		contentPane.add(btnNewButton2);
		
		
		
		// 문서기안 버튼 시작
		JButton btnNewButton3 = new JButton("문서기안");		
	     btnNewButton3.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent e) {
		            empDAO = new EmpDAO();
		            empVO = new EmpVO();
		            empVO = empDAO.SerchInfo2(empId, password);
		            int jgNum = empVO.getJg_no();
		            System.out.println("관리자 버튼 클릭하기 직전의 empID 값 확인 : " + empId);
		            System.out.println("jgNum 값 확인 : " + jgNum);
		            if(jgNum == 4 || jgNum == 5 || jgNum == 6)  {
		               dispose();      // 현재창 닫기
		               ApprovalView approvalView = new ApprovalView(empId, password);
		               approvalView.setVisible(true);
		            }else {
		            	JOptionPane.showMessageDialog(null, "부장보다 직급이 낮아야 확인 할 수 있습니다.", "", JOptionPane.DEFAULT_OPTION);
//		               dispose();
		            }
		         }
		      });
		btnNewButton3.setIcon(new ImageIcon(MainView.class.getResource("/img/p3.png")));
		btnNewButton3.setOpaque(false);
		btnNewButton3.setFocusPainted(false);
		btnNewButton3.setContentAreaFilled(false);
		btnNewButton3.setBorderPainted(false);
		btnNewButton3.setBounds(326, 157, 147, 37);
		contentPane.add(btnNewButton3);

		
		
		//문서결재 버튼 시작
		JButton btnNewButton4 = new JButton("문서결재");	
	     btnNewButton4.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent e) {
		            empDAO = new EmpDAO();
		            empVO = new EmpVO();
		            empVO = empDAO.SerchInfo2(empId, password);
		            int jgNum = empVO.getJg_no();
		            System.out.println("관리자 버튼 클릭하기 직전의 empID 값 확인 : " + empId);
		            System.out.println("jgNum 값 확인 : " + jgNum);
		            if(jgNum == 1 || jgNum == 2 || jgNum ==3){
	                dispose();
//	                ApprovalView approvalView = new ApprovalView();
	                ApprovalAdminView approvalAdminView = new ApprovalAdminView(empId,password);
	                approvalAdminView.setVisible(true);
	             }else {
	            	 JOptionPane.showMessageDialog(null, "임원이상만 확인 가능 합니다.", "", JOptionPane.DEFAULT_OPTION);
	             }
		            		            
//		           dispose();      // 현재창 닫기

		         }
		      });

		btnNewButton4.setIcon(new ImageIcon(MainView.class.getResource("/img/p4.png")));
		btnNewButton4.setOpaque(false);
		btnNewButton4.setFocusPainted(false);
		btnNewButton4.setContentAreaFilled(false);
		btnNewButton4.setBorderPainted(false);
		btnNewButton4.setBounds(326, 204, 147, 37);
		contentPane.add(btnNewButton4);

		
		// 발주 버튼 
		JButton btnNewButton8 = new JButton("발  주");
		btnNewButton8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();		// 현재창 닫기
				OrderView order = null;
				try {
					order = new OrderView();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				order.setVisible(true);
			}
		});
		btnNewButton8.setIcon(new ImageIcon(MainView.class.getResource("/img/p5.png")));		
		btnNewButton8.setOpaque(false);
		btnNewButton8.setFocusPainted(false);
		btnNewButton8.setContentAreaFilled(false);
		btnNewButton8.setBorderPainted(false);
		btnNewButton8.setBounds(326, 251, 147, 37);
		contentPane.add(btnNewButton8);

		
		// 납품 버튼 시작
		JButton btnNewButton6 = new JButton("납  품");		
		btnNewButton6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();		// 현재창 닫기
				SupplyView supply = null;
				try {
					supply = new SupplyView();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				supply.setVisible(true);
			}
		});
		btnNewButton6.setIcon(new ImageIcon(MainView.class.getResource("/img/p6.png")));
		btnNewButton6.setOpaque(false);
		btnNewButton6.setFocusPainted(false);
		btnNewButton6.setContentAreaFilled(false);
		btnNewButton6.setBorderPainted(false);
		btnNewButton6.setBounds(326, 298, 147, 37);
		contentPane.add(btnNewButton6);


		
	      // 납품확인 버튼 시작
	     JButton btnNewButton5 = new JButton("납품확인");
	     btnNewButton5.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
				dispose();		// 현재창 닫기
				SupplyCheckView supplycheck = new SupplyCheckView();
				supplycheck.setVisible(true);

	         }
	      });
		btnNewButton5.setIcon(new ImageIcon(MainView.class.getResource("/img/p7.png")));
		btnNewButton5.setOpaque(false);
		btnNewButton5.setFocusPainted(false);
		btnNewButton5.setContentAreaFilled(false);
		btnNewButton5.setBorderPainted(false);
		btnNewButton5.setBounds(326, 392, 147, 37);
		contentPane.add(btnNewButton5);
		
		
		// 협력사 버튼 시작
		JButton btnNewButton7 = new JButton("협 력 사");		
		btnNewButton7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();		// 현재창 닫기
				PartnerView partner = new PartnerView(empId, password);
				partner.setVisible(true);
			}
		});
		btnNewButton7.setIcon(new ImageIcon(MainView.class.getResource("/img/p8.png")));
		btnNewButton7.setOpaque(false);
		btnNewButton7.setFocusPainted(false);
		btnNewButton7.setContentAreaFilled(false);
		btnNewButton7.setBorderPainted(false);
		btnNewButton7.setBounds(326, 345, 147, 37);
		contentPane.add(btnNewButton7);

		
		
		// 납품내역서 버튼 시작
		JButton btnNewButton9 = new JButton("납품내역서");		
		btnNewButton9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();		// 현재창 닫기
				ClientView client = new ClientView();
				client.setVisible(true);
			}
		});
		
		btnNewButton9.setIcon(new ImageIcon(MainView.class.getResource("/img/p9.png")));
		btnNewButton9.setOpaque(false);
		btnNewButton9.setFocusPainted(false);
		btnNewButton9.setContentAreaFilled(false);
		btnNewButton9.setBorderPainted(false);
		btnNewButton9.setBounds(326, 439, 147, 37);
		contentPane.add(btnNewButton9);
			System.out.println("MainView 기본 생성자 실행됨");

	
			
			
		// 권한변경 버튼 시작
		JButton btnNewButton10 = new JButton("권한변경");		
		btnNewButton10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			       empDAO = new EmpDAO();
		            
		            int grantAccess = empDAO.SerchInfo2(empId, password).getD_no();
		            System.out.println("권한코드 : " + grantAccess);

		            System.out.println("관리자 버튼 클릭하기 직전의 empID 값 확인 : " + empId);
		               if(grantAccess == 5) {
		                  dispose();      // 현재창 닫기
		                  AdministratorView administrator = new AdministratorView(empId);
		                  administrator.setVisible(true);
		               }else {
		                  
		               JOptionPane.showMessageDialog(null, "인사부가 아니기 때문에 권한이 없습니다.", "권한없음", JOptionPane.DEFAULT_OPTION);
		               return;
		            }

		         }
		      });
			
		btnNewButton10.setIcon(new ImageIcon(MainView.class.getResource("/img/p10.png")));
		btnNewButton10.setOpaque(false);
		btnNewButton10.setFocusPainted(false);
		btnNewButton10.setContentAreaFilled(false);
		btnNewButton10.setBorderPainted(false);
		btnNewButton10.setBounds(326, 486, 147, 37);
		contentPane.add(btnNewButton10);
				System.out.println("MainView 기본 생성자 실행됨");

		
		
		
		
		empDAO = new EmpDAO();
		empName = empDAO.SerchInfo2(empId, password).getEmp_name();

		JLabel lblNewLabel_1 = new JLabel(empName +"님 환영합니다.");
		lblNewLabel_1.setBounds(88, 369, 147, 24);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton_1 = new JButton("로그아웃");
		ImageIcon logouticon = new ImageIcon(MainView.class.getResource("/img/logout.png"));
		Image logoutimg = logouticon.getImage();
		Image logout = logoutimg.getScaledInstance(100, 23, Image.SCALE_SMOOTH);
		ImageIcon logoutImg = new ImageIcon(logout);
		btnNewButton_1.setIcon(logoutImg);
		
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//logout 기능 실행하는 곳
//				empId = "";
				dispose();
				LoginView login = new LoginView();
				login.loggedIn = false;
				login.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(12, 558, 82, 23);
		contentPane.add(btnNewButton_1);
		
		Client(empId);




				
		
	}
	
	public void Client(String empId) {
		System.out.println("Client 네트워크 관련된 메소드 탔음");
		Socket socket = null;
		DataInputStream in = null; //이 변수는 사용자가 입력하는 채팅 부분에 해당됩니다.
		BufferedReader in2 = null; //이 변수는 사용자의 닉네임에 해당됩니다. 
		
		DataOutputStream out = null; //이 변수는 사용자가 입력한 데이터를 출력할 때 사용합니다.
		
		try {
			InetAddress ia = null; //Local Host IP Address 가져오기 위한 변수
			ia = InetAddress.getLocalHost(); //현재 PC의 IP Address 가져오기
			socket = new Socket("192.168.0.93", 9003); //Client의 IP와 port 번호 입력
//			socket = new Socket("192.168.0.90", 9000);
			/*
			 * DataInputStream은 입력 스트림을 받는 매개변수이며,
			 * socket.getInputStream()함수를 통해 소켓에서 전달되는 데이터 스트림을 읽어옵니다.
			 * BufferedReader는 Scanner와 비슷한 개념입니다.
			 * Scanner보다 빠르다는 장점이 있지만 String형으로 버퍼에 저장하기 때문에
			 * 따로 데이터를 가공해서 사용해야하는 경우가 많습니다.
			 */
			in = new DataInputStream(socket.getInputStream());
			in2 = new BufferedReader(new InputStreamReader(System.in));
			out = new DataOutputStream(socket.getOutputStream());
			
//			System.out.println("닉네임을 입력해주세요 : ");
//			String data = in2.readLine(); // 채팅에 사용할 닉네임을 받아옵니다.

//			EmpDAO empDAO = new EmpDAO();
//			empId = empDAO.SerchInfo2(String empId, String password).getEmp_no();
			
			System.out.println("client empId : " + empId);
//			out.write(empId);
			out.writeUTF(empId); //닉네임을 UTF-8로 변경 후 출력스트림에 넣습니다.
			Thread th = new Thread(new Send(out)); //새로운 쓰레드에 out을 집어넣도록 합니다.
			th.start();  //쓰레드 시작
		} catch (IOException e) {
			System.out.println("Client excception");
			e.printStackTrace();
		}
	}
}

