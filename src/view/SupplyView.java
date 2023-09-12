package view;
import java.awt.Button;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import model.SupplyDAO;
import model.SupplyDAO;
import model.rec.SupplyVO;

public class SupplyView extends JFrame {

	private static final String pa_company = null;
	private JPanel contentPane;
	private JTable table;
	private JComboBox comboBox;
//	PartnerListTableModel tm;
	ArrayList list = null;
	SupplyDAO dao;
	SupplyVO vo;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private Object[] data;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SupplyView frame = new SupplyView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
//        SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
//                new OrderView();
//            }
//        });		
	}

	/**
	 * Create the frame.
	 * @throws Exception 
	 */
	public SupplyView() throws Exception {
		// DB ����
		try {
			dao = new SupplyDAO();
			System.out.println("DB 연결");
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "DB 연결안됨: "+e.getMessage());
			e.printStackTrace();
		}
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainView.class.getResource("/img/logo_i.png")));
		setBounds(100, 100, 996, 455);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "납 품 서", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(12, 21, 956, 349);
		contentPane.add(panel);
		panel.setLayout(null);
//		tm = new PartnerListTableModel();
//		table = new JTable(tm);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(new Color(255, 255, 255));
		scrollPane.setBounds(12, 85, 932, 258);
		panel.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
					"업 체 명", "프로젝트명", "납품 내용", "수 량", "금 액", "날 짜", "담 당 자", "전화 번호", "e-mail"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, Integer.class, Integer.class, String.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		scrollPane.setViewportView(table);
		table.getTableHeader().setBackground(Color.WHITE);
		table.setFillsViewportHeight(true);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(12, 10, 932, 74);
		panel.add(panel_1);
		
		JLabel lblNewLabel = new JLabel("업 체 명");
		
	       textField = new JTextField();
	        textField.setColumns(10);
	        textField.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                String pa_company = textField.getText();

	                try {
	                    SupplyVO SupplyVO = dao.companyname(pa_company);

	                    textField.setText(SupplyVO.getPa_company());
	                    textField_6.setText(SupplyVO.getPa_dam());
	                    textField_7.setText(SupplyVO.getPa_tel());
	                    textField_8.setText(SupplyVO.getPa_email());
	                } catch (Exception ex) {
	                    ex.printStackTrace();
	                }
	            }
	        });		
	        
	        
		JLabel lblNewLabel_9 = new JLabel("프로젝트명");				
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		

		JLabel lblNewLabel_1 = new JLabel("납품내용");		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		String OrderText = textField_2.getText();
		
		
		JLabel lblNewLabel_2 = new JLabel("수  량");				
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		String OrderCount = textField_3.getText();
		  

		JLabel lblNewLabel_3 = new JLabel("금  액");		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		String OrderAmount = textField_4.getText();	



		JLabel lblNewLabel_4 = new JLabel("날  짜");		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		String inputDate = textField_5.getText(); // textField_5에서 날짜 입력값 가져오기
		


		JLabel lblNewLabel_5 = new JLabel("담 당 자");		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		
		
		JLabel lblNewLabel_6 = new JLabel("전화번호");		
		textField_7 = new JTextField();
		textField_7.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("e-mail");
		textField_8 = new JTextField();
		textField_8.setColumns(10);		

		
		
		
		ArrayList<Object[]> dataList1 = new ArrayList<>();

		// textField_7에서 엔터 키 이벤트 처리
		textField_5.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        // 입력된 값들 가져오기
		        String pa_company = textField.getText();
		        String pro_name = textField_1.getText(); // textField_1에서 텍스트 값 가져오기
		        String sm_contents = textField_2.getText();
		        int sm_count = Integer.parseInt(textField_3.getText());
		        int sm_amount = Integer.parseInt(textField_4.getText());
		        String sm_date = textField_5.getText();
		        String pa_dam = textField_6.getText();
		        String pa_tel = textField_7.getText();
		        String pa_email = textField_8.getText();

		        // 데이터를 Object 배열로 생성하여 ArrayList에 추가
		        data = new Object[]{pa_company, pro_name, sm_contents, sm_count, sm_amount, sm_date, pa_dam, pa_tel, pa_email};
		        dataList1.add(data);
		        
		        DefaultTableModel model = (DefaultTableModel) table.getModel();
		        model.addRow(data);
		        
		        // 입력 필드 초기화
		        textField.setText("");
		        textField_1.setText("");
		        textField_2.setText("");
		        textField_3.setText("");
		        textField_4.setText("");
		        textField_5.setText("");
		        textField_6.setText("");
		        textField_7.setText("");
		        textField_8.setText("");
		        
		        
		    }
		});
		
		JLabel lblNewLabel_8 = new JLabel("사업관리번호");		
		JComboBox comboBox_1 = new JComboBox();
		
	    // SupplyDAO 인스턴스 생성
	    SupplyDAO SupplyDAO = new SupplyDAO();

	    try {
	        // 사업관리번호 조회
	        ArrayList<Integer> bmNoList = dao.getAllBusinessManagementNumbers();
	        
	        // JComboBox에 사업관리번호 추가
	        for (Integer bmNo : bmNoList) {
	            comboBox_1.addItem(bmNo);
	        }
	    } catch (Exception e) {
	        e.printStackTrace(); // 예외 처리
	    }
		
			

		
			    // JComboBox 선택 이벤트 리스너 등록
			    comboBox_1.addActionListener(new ActionListener() {
			        public void actionPerformed(ActionEvent e) {
			            JComboBox<Integer> comboBox = (JComboBox<Integer>) e.getSource();
			            Integer selectedBmNo = (Integer) comboBox.getSelectedItem();
			            
			            try {
			                // 선택된 사업관리번호로 suuplyDAO에서 정보 조회
			            	SupplyVO vo = dao.findBmno(selectedBmNo);
		
			                // 프로젝트명 조회
			            	SupplyVO projectVO = dao.findProjectName(selectedBmNo);
			                String projectName = projectVO.getPro_name();
			                
			                // 조회된 정보를 활용하여 필요한 작업 수행
			                // 예: 다른 컴포넌트에 조회된 정보를 표시하거나 사용
			                
			                // 프로젝트명을 textField_8에 설정
			                textField_1.setText(projectName);
			                
			            } catch (Exception ex) {
			                ex.printStackTrace(); // 예외 처리
			            }
			        }
			    });

	
		
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(lblNewLabel_3)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblNewLabel_4)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField_5, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblNewLabel_5)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField_6, GroupLayout.PREFERRED_SIZE, 142, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblNewLabel_6)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField_7, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblNewLabel_7)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(textField_8, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(lblNewLabel)
							.addGap(18)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblNewLabel_8)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblNewLabel_9)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblNewLabel_1)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField_2, GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblNewLabel_2)
							.addGap(18)
							.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addGap(13))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_8)
								.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_9, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_1)
								.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_2))
							.addComponent(lblNewLabel)))
					.addPreferredGap(ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_3)
						.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_4)
						.addComponent(textField_5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_5)
						.addComponent(lblNewLabel_6)
						.addComponent(textField_7, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_7)
						.addComponent(textField_8, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		panel_1.setLayout(gl_panel_1);
	
		Button button_1 = new Button("납품내역저장");
		button_1.setFont(new Font("굴림", Font.PLAIN, 14));
		button_1.setBounds(880, 376, 76, 29);
		contentPane.add(button_1);
		
		// button_1.addActionListener 메소드
		button_1.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        try {
		            // data에서 값 가져오기
		            String pa_company = (String) data[0];
		            String pro_name = (String) data[1];
		            String sm_contents = (String) data[2];
		            int sm_count = (int) data[3];
		            int sm_amount = (int) data[4];
		            String sm_date = (String) data[5];


		            // pro_name과 pa_company를 각각 bm_no와 part_no로 변환합니다.
		            int bm_no = dao.convertProNameToBmNo(pro_name);
		            System.out.println("번호 확인" + bm_no);
		            int part_no = dao.convertPaCompanyToPartNo(pa_company);
		            System.out.println("pa번호 확인" + part_no);



		            // insertOrder 메소드를 호출하여 데이터를 삽입합니다.
		            dao.insertSupply(sm_contents, sm_count, sm_amount, sm_date, bm_no, part_no);
		            
		            
			        clearTable();
			        
		        } catch (Exception ex) {
		        	System.out.println("에러발생");
		            ex.printStackTrace();
		        }
		        

		    }
		});
		
	}
		
 
	void clearTable() {
	    DefaultTableModel model = (DefaultTableModel) table.getModel();
	    model.setRowCount(0);
	    System.out.println("테이블 클리어 성공");

	}
}
