package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import model.AdministratorDAO;
import model.rec.AdministratorVO;
import model.rec.AdministratorVOList;
import model.rec.EmpVO;

public class AdministratorView extends JFrame implements ActionListener {

	JPanel contentPane;
	JTextField empNoTextField;
	JTextField empNameTextField;
	JTextField empJgtextField;
	JComboBox<Integer> comEmpNo;
	JComboBox comEmpJob;
	


	EmpVO empVO = null;
	AdministratorVO administratorVO = null;
	AdministratorVOList administratorVOList = null;
	AdministratorDAO administratorDAO = null;
	String empId;
	String password;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdministratorView frame = new AdministratorView();
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
	public AdministratorView(String empId) {
		setLocationRelativeTo(null);
		System.out.println("ADministratorView empID 값 받아오는지 확인: " + empId);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 463, 584);
//		newObject();

		contentPane = new JPanel();
		empNoTextField = new JTextField();
		empNameTextField = new JTextField();
		empJgtextField = new JTextField();
		comEmpNo = new JComboBox();
		comEmpJob = new JComboBox();
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
//		comEmpNo = new JComboBox();
//		contentPane.add(comEmpNo);

		getContentPane().setLayout(null);
		
		
		
		JLabel empNumLbl = new JLabel("사원번호");
		empNumLbl.setBounds(61, 49, 48, 32);
		getContentPane().add(empNumLbl);
		
		empNoTextField = new JTextField();
		empNoTextField.setBounds(123, 43, 176, 45);
		getContentPane().add(empNoTextField);
		empNoTextField.setColumns(10);
		administratorVO = new AdministratorVO();
		administratorDAO = new AdministratorDAO();
		administratorVO = administratorDAO.SelectEmp(empId);
		int empNo = administratorVO.getEmp_no();
//		String empNo = administratorVO.getEmp_no();
		String empNoString = String.valueOf(empNo);
	    
//	    String empNo = administratorDAO.SelectEmpCombo().getEmp_no();
//		administratorDAO.SelectEmp();
//		empNoTextField.setText(administratorDAO.SelectEmp().getEmp_no());
//	    empNoTextField.setText(empNoString);
	    empNoTextField.setText("");
		
		
		
		// JComboBox 생성
		comEmpNo = new JComboBox<Integer>();
		comEmpNo.setBounds(311, 46, 62, 38);
		getContentPane().add(comEmpNo);
		// DB에서 사원번호 가져와서 콤보 상자에 추가
		administratorDAO = new AdministratorDAO();
		administratorVOList = new AdministratorVOList();
		administratorVOList = administratorDAO.SelectEmpCombo(); // 사원 정보를 가져오는 메서드를 호출
		List<Integer> empTxtNo = administratorVOList.getEmp_no(); // 사원번호를 가져옴
		// 콤보 상자에 사원번호 추가
		for (Integer empNum : empTxtNo) {
			comEmpNo.addItem(empNum);
		}

//		comEmpNo.addItem(administratorVO);
		// DB에서 사원번호 가져와서 콤보 상자에 추가
//		int empNoList = (int) .SelectEmp().getEmp_no(); // 사원번호를 가져오는 메서드를 구현해야 합니다.
//		for (int i = 0;empNoList;i++) {
//			
//		}
		
//		JComboBox comboBox = new JComboBox();
//		comboBox.setBounds(311, 46, 62, 38);
//		contentPane.add(comboBox);
		
		
		JLabel empNameLbl = new JLabel("이름");
		empNameLbl.setBounds(61, 108, 41, 23);
		getContentPane().add(empNameLbl);
		
		empNameTextField = new JTextField();
		empNameTextField.setBounds(123, 104, 176, 32);
		getContentPane().add(empNameTextField);
		empNameTextField.setColumns(10);
		empNameTextField.setEditable(false);  // 편집 불가능 설정
//		empNameTextField.setText(administratorDAO.SelectEmp(empId).getEmp_name());
		empNameTextField.setText("");
		
		JLabel empJobLbl = new JLabel("직급");
		empJobLbl.setBounds(61, 156, 33, 32);
		getContentPane().add(empJobLbl);
		
		empJgtextField = new JTextField();
		empJgtextField.setBounds(123, 162, 176, 26);
		getContentPane().add(empJgtextField);
		empJgtextField.setColumns(10);	
//		empJgtextField.setText(administratorDAO.SelectEmp(empId).getEmp_job());
		empJgtextField.setText("");
		
		
		
		
		
//		comEmpJob = new JComboBox<Integer>();

		administratorDAO = new AdministratorDAO();
		administratorVOList = new AdministratorVOList();
		administratorVOList = administratorDAO.SelectEmpCombo();
		
//		List<Integer> empTxtjg = administratorVOList.getJg_no();
//		
//		for (Integer empjgNo : empTxtjg) {
//			comEmpJob.addItem(empjgNo);
//		}
		String jobJg[] = {"1","2","3","4","5","6" };
		int JobJg1[] = {1,2,3,4,5,6};
		comEmpJob = new JComboBox<String>(jobJg);
		comEmpJob.setSelectedItem(administratorVO.getJg_no());
		

		comEmpJob.setBounds(311, 165, 62, 23);
//		contentPane.add(comEmpJob);
		getContentPane().add(comEmpJob);


		
		comEmpJob.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
//				int jobjg1 = (int) comEmpJob.getSelectedItem();
				
				
				String jobjg = (String) comEmpJob.getSelectedItem();
				System.out.println("jobjg  : " + jobjg);
				
				if(jobjg != null && jobjg.equals("1")) {
					empJgtextField.setText("사장");
				}else if (jobjg != null && jobjg.equals("2")) {
					empJgtextField.setText("상무");
				}else if (jobjg != null && jobjg.equals("3")) {
					empJgtextField.setText("부장");
				}else if (jobjg != null && jobjg.equals("4")) {
					empJgtextField.setText("팀장");
				}else if (jobjg != null && jobjg.equals("5")) {
					empJgtextField.setText("과장");
				}else{
					empJgtextField.setText("사원");
				}
				
			}
		});
		
		
		//사원정보 콤보상자 관련된 기능 
		comEmpNo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
//				Integer selectedEmpNo = (Integer) comEmpNo.getSelectedItem();
//				empNoTextField.setText(selectedEmpNo.toString());
				Integer selectedEmpNo = (Integer) comEmpNo.getSelectedItem();
				String empNo = Integer.toString(selectedEmpNo);
				empNoTextField.setText(empNo);
				
//				String EmpTextName = empNameTextField.getSelectedText();
//				문제는 empNameTextField.getSelectedText() 메서드를 사용하여 
//				EmpTextName 값을 가져오려고 하고 있기 때문에 발생합니다. 
//				getSelectedText() 메서드는 텍스트 필드에서 선택된 텍스트를 반환하는 것이 아니라
//				, 현재 선택된 텍스트를 가져옵니다. 그러나 empNameTextField에는 텍스트가 선택되지 않은 상태이기 때문에 null이 반환되는 것입니다.
//				대신에 empNameTextField.getText() 메서드를 사용하여 
//				empNameTextField의 텍스트 값을 가져올 수 있습니다. 아래와 같이 수정해 보세요:
				AdministratorDAO administratorDAO = null;
				AdministratorVO administratorVO = new AdministratorVO();
				
				administratorDAO = new AdministratorDAO();
				administratorVO = new AdministratorVO();
				administratorVO = administratorDAO.SelectEmp(empNo);
				String EmpTextName = administratorVO.getEmp_name();
//						empNameTextField.getText();
				System.out.println("EmpTextName : " + EmpTextName);
				empNameTextField.setText(EmpTextName);
				
				
				
				String empJobText = administratorVO.getEmp_job();
				empJgtextField.setText(empJobText);
				
			}
		});
		
		//버튼
		JButton empJgUpdateBtn = new JButton("권한변경");
		empJgUpdateBtn.setBounds(303, 214, 97, 23);
		getContentPane().add(empJgUpdateBtn);
		empJgUpdateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.out.println("버튼클릭");
//				administratorVO.setD_no();

				
				
//		        String jobjgComtxt = (String) comEmpJob.getSelectedItem();
		        
				String jobjg = (String) comEmpJob.getSelectedItem();
				
				if(jobjg != null && jobjg.equals("1")) {
					empJgtextField.setText("사장");
				}else if (jobjg != null && jobjg.equals("2")) {
					empJgtextField.setText("상무");
				}else if (jobjg != null && jobjg.equals("3")) {
					empJgtextField.setText("부장");
				}else if (jobjg != null && jobjg.equals("4")) {
					empJgtextField.setText("팀장");
				}else if (jobjg != null && jobjg.equals("5")) {
					empJgtextField.setText("과장");
				}else{
					empJgtextField.setText("사원");
				}
		        
		        int jgNo = Integer.parseInt(jobjg);
		        System.out.println("jobjg : " + jgNo);
		        
		        
				
			
				String empJob =	empJgtextField.getText();//직급txt
				System.out.println("empJob :  " + empJob);
//				
				String empNoText = empNoTextField.getText();//사원번호txt
				int empNo =	Integer.parseInt(empNoText);
				System.out.println("empNo :  " + empNo);

				//사원번호, 이름, 직급 파라미터로 받기
				try {
//					JTextField empNoTextField;
//					JTextField empNameTextField;
//					JTextField empJgtextField;
//					JComboBox<Integer> comEmpNo;
//					JComboBox comEmpJob;
					//set jg_no = ?, emp_job = ? where emp_no = ?"
					
				

//					int empNo = administratorVO.getEmp_no();
//					String empNo = administratorVO.getEmp_no();
//					String empNoString = String.valueOf(empNo);
//				    empNoTextField.setText(empNoString);
	
					int rowNum = administratorDAO.EmpGrantModify(jgNo,empJob, empNo);
					
//					int rowNum = administratorDAO.EmpGrantModify(administratorVO);
					if(rowNum > 0) {
						System.out.println("업데이트");
						System.out.println("rownum : " + rowNum);
					
						
						
						
						
						
					}else {
						System.out.println("권한변경 업데이트 안함");
					}
				} catch (Exception e1) {
					System.err.println("권한변경 업데이트 안됨");
					e1.printStackTrace();
				}
				
			}
		});
		

	

	}
	
	   //##############################################
	   // 멤버필드 객체 생성 그러나 아직 어설프게 정리되어서 추가 수정이 필요
//	   void   newObject(){
//		   
//		   
//			contentPane = new JPanel();
//			empNoTextField = new JTextField();
//			empNameTextField = new JTextField();
//			empJgtextField = new JTextField();
//			comEmpNo = new JComboBox();
//			comEmpJob = new JComboBox();
//			
//			contentPane = new JPanel();
//			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
//			setContentPane(contentPane);
//			
//	   }

	public AdministratorView() {
		
			
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
