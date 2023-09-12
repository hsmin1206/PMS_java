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
		System.out.println("MainView empId �� �޾� ������ Ȯ�� : " + empId);
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
		
		setLocationRelativeTo(null); 		// â ��� ����
		setResizable(false);
		
		
		// ������Ʈ ��ư ����
		JButton btnNewButton = new JButton("������Ʈ");		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				empDAO = new EmpDAO();
				dispose();		// ����â �ݱ�
				ProjectOpenView project = new ProjectOpenView(empId, password);
				project.setVisible(true);
			}
		});
		btnNewButton.setIcon(new ImageIcon(MainView.class.getResource("/img/p1.png")));
		btnNewButton.setBounds(326, 63, 147, 37);
		contentPane.add(btnNewButton);
		
		btnNewButton.setCursor(new Cursor(Cursor.HAND_CURSOR));			// Ŀ�� ��� ���� 								
		btnNewButton.setBorderPainted(false); 							// ���� ���� ���� ���¿�, ���콺 �÷� ���� �� �׵θ�	
		btnNewButton.setContentAreaFilled(false); 						// ���										
		btnNewButton.setFocusPainted(false);								// ���� ���� �� ��							
		btnNewButton.setOpaque(false);
		
		// ������Ʈ���� ��ư ����
		JButton btnNewButton2 = new JButton("������Ʈ����");		
		btnNewButton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();		// ����â �ݱ�
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
		
		
		
		// ������� ��ư ����
		JButton btnNewButton3 = new JButton("�������");		
	     btnNewButton3.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent e) {
		            empDAO = new EmpDAO();
		            empVO = new EmpVO();
		            empVO = empDAO.SerchInfo2(empId, password);
		            int jgNum = empVO.getJg_no();
		            System.out.println("������ ��ư Ŭ���ϱ� ������ empID �� Ȯ�� : " + empId);
		            System.out.println("jgNum �� Ȯ�� : " + jgNum);
		            if(jgNum == 4 || jgNum == 5 || jgNum == 6)  {
		               dispose();      // ����â �ݱ�
		               ApprovalView approvalView = new ApprovalView(empId, password);
		               approvalView.setVisible(true);
		            }else {
		            	JOptionPane.showMessageDialog(null, "���庸�� ������ ���ƾ� Ȯ�� �� �� �ֽ��ϴ�.", "", JOptionPane.DEFAULT_OPTION);
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

		
		
		//�������� ��ư ����
		JButton btnNewButton4 = new JButton("��������");	
	     btnNewButton4.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent e) {
		            empDAO = new EmpDAO();
		            empVO = new EmpVO();
		            empVO = empDAO.SerchInfo2(empId, password);
		            int jgNum = empVO.getJg_no();
		            System.out.println("������ ��ư Ŭ���ϱ� ������ empID �� Ȯ�� : " + empId);
		            System.out.println("jgNum �� Ȯ�� : " + jgNum);
		            if(jgNum == 1 || jgNum == 2 || jgNum ==3){
	                dispose();
//	                ApprovalView approvalView = new ApprovalView();
	                ApprovalAdminView approvalAdminView = new ApprovalAdminView(empId,password);
	                approvalAdminView.setVisible(true);
	             }else {
	            	 JOptionPane.showMessageDialog(null, "�ӿ��̻� Ȯ�� ���� �մϴ�.", "", JOptionPane.DEFAULT_OPTION);
	             }
		            		            
//		           dispose();      // ����â �ݱ�

		         }
		      });

		btnNewButton4.setIcon(new ImageIcon(MainView.class.getResource("/img/p4.png")));
		btnNewButton4.setOpaque(false);
		btnNewButton4.setFocusPainted(false);
		btnNewButton4.setContentAreaFilled(false);
		btnNewButton4.setBorderPainted(false);
		btnNewButton4.setBounds(326, 204, 147, 37);
		contentPane.add(btnNewButton4);

		
		// ���� ��ư 
		JButton btnNewButton8 = new JButton("��  ��");
		btnNewButton8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();		// ����â �ݱ�
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

		
		// ��ǰ ��ư ����
		JButton btnNewButton6 = new JButton("��  ǰ");		
		btnNewButton6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();		// ����â �ݱ�
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


		
	      // ��ǰȮ�� ��ư ����
	     JButton btnNewButton5 = new JButton("��ǰȮ��");
	     btnNewButton5.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
				dispose();		// ����â �ݱ�
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
		
		
		// ���»� ��ư ����
		JButton btnNewButton7 = new JButton("�� �� ��");		
		btnNewButton7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();		// ����â �ݱ�
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

		
		
		// ��ǰ������ ��ư ����
		JButton btnNewButton9 = new JButton("��ǰ������");		
		btnNewButton9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();		// ����â �ݱ�
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
			System.out.println("MainView �⺻ ������ �����");

	
			
			
		// ���Ѻ��� ��ư ����
		JButton btnNewButton10 = new JButton("���Ѻ���");		
		btnNewButton10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			       empDAO = new EmpDAO();
		            
		            int grantAccess = empDAO.SerchInfo2(empId, password).getD_no();
		            System.out.println("�����ڵ� : " + grantAccess);

		            System.out.println("������ ��ư Ŭ���ϱ� ������ empID �� Ȯ�� : " + empId);
		               if(grantAccess == 5) {
		                  dispose();      // ����â �ݱ�
		                  AdministratorView administrator = new AdministratorView(empId);
		                  administrator.setVisible(true);
		               }else {
		                  
		               JOptionPane.showMessageDialog(null, "�λ�ΰ� �ƴϱ� ������ ������ �����ϴ�.", "���Ѿ���", JOptionPane.DEFAULT_OPTION);
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
				System.out.println("MainView �⺻ ������ �����");

		
		
		
		
		empDAO = new EmpDAO();
		empName = empDAO.SerchInfo2(empId, password).getEmp_name();

		JLabel lblNewLabel_1 = new JLabel(empName +"�� ȯ���մϴ�.");
		lblNewLabel_1.setBounds(88, 369, 147, 24);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton_1 = new JButton("�α׾ƿ�");
		ImageIcon logouticon = new ImageIcon(MainView.class.getResource("/img/logout.png"));
		Image logoutimg = logouticon.getImage();
		Image logout = logoutimg.getScaledInstance(100, 23, Image.SCALE_SMOOTH);
		ImageIcon logoutImg = new ImageIcon(logout);
		btnNewButton_1.setIcon(logoutImg);
		
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//logout ��� �����ϴ� ��
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
		System.out.println("Client ��Ʈ��ũ ���õ� �޼ҵ� ����");
		Socket socket = null;
		DataInputStream in = null; //�� ������ ����ڰ� �Է��ϴ� ä�� �κп� �ش�˴ϴ�.
		BufferedReader in2 = null; //�� ������ ������� �г��ӿ� �ش�˴ϴ�. 
		
		DataOutputStream out = null; //�� ������ ����ڰ� �Է��� �����͸� ����� �� ����մϴ�.
		
		try {
			InetAddress ia = null; //Local Host IP Address �������� ���� ����
			ia = InetAddress.getLocalHost(); //���� PC�� IP Address ��������
			socket = new Socket("192.168.0.93", 9003); //Client�� IP�� port ��ȣ �Է�
//			socket = new Socket("192.168.0.90", 9000);
			/*
			 * DataInputStream�� �Է� ��Ʈ���� �޴� �Ű������̸�,
			 * socket.getInputStream()�Լ��� ���� ���Ͽ��� ���޵Ǵ� ������ ��Ʈ���� �о�ɴϴ�.
			 * BufferedReader�� Scanner�� ����� �����Դϴ�.
			 * Scanner���� �����ٴ� ������ ������ String������ ���ۿ� �����ϱ� ������
			 * ���� �����͸� �����ؼ� ����ؾ��ϴ� ��찡 �����ϴ�.
			 */
			in = new DataInputStream(socket.getInputStream());
			in2 = new BufferedReader(new InputStreamReader(System.in));
			out = new DataOutputStream(socket.getOutputStream());
			
//			System.out.println("�г����� �Է����ּ��� : ");
//			String data = in2.readLine(); // ä�ÿ� ����� �г����� �޾ƿɴϴ�.

//			EmpDAO empDAO = new EmpDAO();
//			empId = empDAO.SerchInfo2(String empId, String password).getEmp_no();
			
			System.out.println("client empId : " + empId);
//			out.write(empId);
			out.writeUTF(empId); //�г����� UTF-8�� ���� �� ��½�Ʈ���� �ֽ��ϴ�.
			Thread th = new Thread(new Send(out)); //���ο� �����忡 out�� ����ֵ��� �մϴ�.
			th.start();  //������ ����
		} catch (IOException e) {
			System.out.println("Client excception");
			e.printStackTrace();
		}
	}
}

