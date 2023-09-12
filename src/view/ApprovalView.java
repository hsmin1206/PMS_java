package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import model.ApprovalDAO;
import model.EmpDAO;
import model.rec.ApprovalVO;

public class ApprovalView extends JFrame {

   private JPanel contentPane;
   private JTextField txtWriter;
   private JLabel lblMaster;
   private JTextField txtAppDate;
   private JLabel lblAppDate;
   private JPanel panel;
   private JLabel lblNewLabel_3;
   private JTextField txtTitle;
   private JLabel lblContents;
   private JScrollPane scrollPane;
   private JTextField txtContents;
   private JButton btnApproval;
   ApprovalDAO dao;   
   ApprovalVO vo;
   private ArrayList<ApprovalVO> approvalList;
   private String empName;
   static EmpDAO empDAO = null;
    String empId;
    String password;
   private JTextField txtMaster;
   private JTextField txtPro;
   


   /**
    * Launch the application.
    */
   public static void main(String[] args) {
      EventQueue.invokeLater(new Runnable() {
         public void run() {
            try {
                empDAO = new EmpDAO();
//                empId = "EMPLOYEE_ID"; // 유효한 직원 ID 값으로 설정해야 함
//                password = "PASSWORD"; 
//               String empName = empDAO.SerchInfo2(empId, password).getEmp_name();
               ApprovalView frame = new ApprovalView();
               frame.setVisible(true);
            } catch (Exception e) {
               e.printStackTrace();
            }
         }
      });
   }

   public ApprovalView() {

	      

   }
   /**
    * Create the frame.
    */
   public ApprovalView(String empId, String password) {
	   this.empId = empId;
	   this.password = password;
	   setLocationRelativeTo(null);
	      System.out.println("메인에서 넘어온 empId 값 : " + empId);
	      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      setBounds(100, 100, 571, 690);
	      contentPane = new JPanel();
	      contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

	      setContentPane(contentPane);
	      
		     JLabel proLbl = new JLabel("프로젝트 번호");
		     proLbl.setBounds(50, 42, 83, 14);
		      
		     txtPro = new JTextField();
		     txtPro.setBounds(145, 34, 116, 31);
		     txtPro.setText((String) null);
		     txtPro.setColumns(10);
		      
		      try {
		    	    dao = new ApprovalDAO();
		    	    
		    	    // project number 값을 가져와서 textField에 설정
		    	    ApprovalVO proVO = dao.ProjectNo(empId);
		    	    int proNum = proVO.getPro_no();
//		    	    System.out.println("proNum : " + proNum);
		    	    //int형을 String으로 변환
		    	    String proNumString = String.valueOf(proNum);
		    	    txtPro.setText(proNumString);
		    	} catch (Exception e) {
		    	    e.printStackTrace();
		    	}
		      
		      txtPro.addActionListener(new ActionListener() {
		     	    public void actionPerformed(ActionEvent e) {
		     	        try {
		     	            // ApprovalDAO를 사용하여 데이터베이스에 저장
		     	        	ApprovalVO proVO = dao.ProjectNo(empId);
		     	        	 int proNum = proVO.getPro_no();
		    	    	    String proNumString = String.valueOf(proNum);
		     	            // projectNo 값을 textField에 설정
		    	    	    txtPro.setText(proNumString);
//		    	    	    System.out.println("txtpro값 : " + txtPro.getText());
		     	        } catch (Exception e1) {
		     	            e1.printStackTrace();
		     	        }
		     	    }
		     	});
		     
	      JLabel lblWriter = new JLabel("기안자");
	      lblWriter.setBounds(50, 83, 53, 15);
	      
	      txtWriter = new JTextField();
	      txtWriter.setBounds(145, 75, 116, 31);
	      txtWriter.setColumns(10);
	      
	      try {
	  	    dao = new ApprovalDAO();

	  	    // 문서 작성자 값을 가져와서 textField에 설정
	  	    ApprovalVO writerVO = dao.Writer(empId);
	  	    String writer = writerVO.getApp_writer();
	  	    txtWriter.setText(writer);
	  	} catch (Exception e) {
	  	    e.printStackTrace();
	  	}

	  	// 기안자 값을 가져오지 않는 코드
	  	// textField.setText(empName);

	  	txtWriter.addActionListener(new ActionListener() {
	  	    public void actionPerformed(ActionEvent e) {
	  	        try {
	  	            // ApprovalDAO를 사용하여 데이터베이스에 저장
	  	            String empName = dao.Writer(empId).getApp_writer(); // 문서제목 필드의 값을 가져옴
	  	            
	  	            // 기안자 값을 textField에 설정
	  	            txtWriter.setText(empName);
	  	        } catch (Exception e1) {
	  	            e1.printStackTrace();
	  	        }
	  	    }
	  	});
	      
	  	
	  	
	      lblMaster = new JLabel("결재자");
	      lblMaster.setBounds(50, 131, 53, 15);
		      txtMaster = new JTextField();
		      txtMaster.setBounds(145, 124, 116, 29);
		      txtMaster.setColumns(10);
		      
		      try {
		    	    dao = new ApprovalDAO();
		    	    ApprovalVO masterVO = dao.Master(empId);
		    	    String master = masterVO.getApp_master();
		    	    System.out.println("문서기한 결제자 아이디번호 : " + empId);
		    	    System.out.println("문서기한 결제자 : " + master);
		    	    txtMaster.setText(master);
		    	} catch (Exception e) {
		    	    e.printStackTrace();
		    	}
		      
		     	txtMaster.addActionListener(new ActionListener() {
		     	    public void actionPerformed(ActionEvent e) {
		     	        try {
		     	            // ApprovalDAO를 사용하여 데이터베이스에 저장
		     	            String masterName = dao.Master(empId).getApp_master(); // 문서제목 필드의 값을 가져옴
		     	            
		     	            // 기안자 값을 textField에 설정
		     	            txtMaster.setText(masterName);
		     	        } catch (Exception e1) {
		     	            e1.printStackTrace();
		     	        }
		     	    }
		     	});
	      
	      
	      txtAppDate = new JTextField();
	      txtAppDate.setBounds(145, 171, 116, 31);
	      txtAppDate.setColumns(10);
	      
	      SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	      String sysdate = dateFormat.format(new Date());
	      
	      txtAppDate.setText(sysdate);
	      
	      

	      
	      lblAppDate = new JLabel("기안일자");
	      lblAppDate.setBounds(38, 179, 65, 15);
	      
	      panel = new JPanel();
	      panel.setBounds(21, 211, 491, 38);
	      
	      lblContents = new JLabel("기안내용");
	      lblContents.setBounds(27, 277, 59, 15);
	      
	      scrollPane = new JScrollPane();
	      scrollPane.setBounds(17, 304, 521, 337);
	      
	      btnApproval = new JButton("결재상신");
	      btnApproval.setBounds(343, 38, 91, 23);
	      

	      // "결재상신" 버튼 ActionListener
	      btnApproval.addActionListener(new ActionListener() {
	          public void actionPerformed(ActionEvent e) {
	              try {System.out.println("결재상신버튼 클릭함");
	              	  // ApprovalDAO를 사용하여 데이터베이스에 저장
	              	  String proNString = txtPro.getText();
	              	  String writer = txtWriter.getText();
	              	  String master = txtMaster.getText();
	              	  String app_date = txtAppDate.getText();
	              	  
	              	  
	                  // SimpleDateFormat을 사용하여 텍스트를 Date로 변환
	                  //SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // 입력된 날짜 형식에 맞는 SimpleDateFormat 객체 생성
//	                  SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
//	                  java.util.Date utilDate = dateFormat.parse(app_date);
	                  java.util.Date utilDate = dateFormat.parse(app_date);
	                  // java.util.Date를 java.sql.Date로 변환
	                  java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
	              	  
	                  String title = txtTitle.getText();
	                  String contents = txtContents.getText(); 
	              	  int empNo = Integer.parseInt(empId);
	              	  
	              	  System.out.println("proNString : " + proNString);
	              	System.out.println("writer : " + writer);
	              	System.out.println("master : " + master);
	              	System.out.println("sqlDate : " + sqlDate);
	              	System.out.println("sqlDate : " + sqlDate);
	              	System.out.println("title : " + title);
	              	System.out.println("empNo : " + empNo);
	              	  
//	                 Date date = new Date();
//	                 String dateString = dateFormat.format(date);
//	                 java.sql.Date sqlDate = java.sql.Date.valueOf(dateString);

	              	  
	                 // ApprovalVO 객체 생성 및 데이터 설정
	                 ApprovalVO approvalVO = new ApprovalVO();
	                 approvalVO.setPro_no(Integer.parseInt(proNString));
	                 approvalVO.setApp_writer(writer);
	                 approvalVO.setApp_master(master);
	                 approvalVO.setApp_date(sqlDate);
	                 approvalVO.setApp_title(title);
	                 approvalVO.setApp_contents(contents);
	                 approvalVO.setEmp_no(empNo);
	                 
	 
	                 int result = dao.ApprovalInsert(approvalVO);
	                 
	                 if(result == 1) {
	                	 //inesrt 성공
	                	 JOptionPane.showMessageDialog(null, "결재상신이 완료되었습니다.", "결재상신", JOptionPane.DEFAULT_OPTION);
	                 }else {
	                	 JOptionPane.showMessageDialog(null, "결재상신 실패하였습니다.", "결재실패", JOptionPane.DEFAULT_OPTION);
	                 }
	                  
	                 clearScreen();
	              } catch (Exception ex) {
	            	  System.out.println("결재상신 등록 에러");
	                  ex.printStackTrace();
	              }
	          }
	      });
	      
	      JButton btnMainView = new JButton("메인화면");
	      btnMainView.setBounds(447, 38, 91, 23);
	      contentPane.add(btnMainView);
	      
	      btnMainView.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				MainView main;
				try {
					main = new MainView(empId,password);
					main.setVisible(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		      
	   
	      
	      txtContents = new JTextField();
	      scrollPane.setViewportView(txtContents);
	      txtContents.setColumns(10);
	      panel.setLayout(null);
	      
	      lblNewLabel_3 = new JLabel("문서제목");
	      lblNewLabel_3.setBounds(12, 10, 73, 15);
	      panel.add(lblNewLabel_3);
	      
	      txtTitle = new JTextField();
	      txtTitle.setBounds(124, 2, 210, 31);
	      txtTitle.setColumns(10);
	      panel.add(txtTitle);
	      contentPane.setLayout(null);
	      contentPane.add(lblContents);
	      contentPane.add(scrollPane);
	      contentPane.add(proLbl);
	      contentPane.add(txtPro);
	      contentPane.add(lblWriter);
	      contentPane.add(lblMaster);
	      contentPane.add(lblAppDate);
	      contentPane.add(txtMaster);
	      contentPane.add(txtAppDate);
	      contentPane.add(txtWriter);
	      contentPane.add(btnApproval);
	      contentPane.add(panel);
	      


       
   }
   
   void clearScreen() {
 	  txtTitle.setText("");
 	  txtContents.setText("");
 	 
   }
}