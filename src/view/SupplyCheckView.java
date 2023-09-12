package view;

import java.awt.Button;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import model.EmpDAO;
import model.SupplyDAO;
import model.rec.SupplyVO;


	public class SupplyCheckView extends JFrame {
//		String pa_company;
	    private JPanel contentPane;
	    private JTable table;
	    private JComboBox comboBox;
	    private PartnerListTableModel tm;
	    private ArrayList list = null;
	    private SupplyDAO dao;
	    private SupplyVO vo;
	    String empId;
		String password;
	    

		/**
		 * Launch the application.
		 */
		public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						SupplyCheckView frame = new SupplyCheckView();
						frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}

		/**
		 */
		public SupplyCheckView() {
			this.empId ="";
			this.password = "";
			
			// DB 연결
			try {
				dao = new SupplyDAO();
				System.out.println("DB 연결  성공");
			} catch (Exception e) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(null, "DB 연결 실패: "+e.getMessage());
				e.printStackTrace();
			}
			setLocationRelativeTo(null);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setIconImage(Toolkit.getDefaultToolkit().getImage(MainView.class.getResource("/img/logo_i.png")));
			setBounds(100, 100, 966, 455);
			contentPane = new JPanel();
			contentPane.setBackground(new Color(255, 255, 255));
			contentPane.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "\uB0A9\uD488 \uD655\uC778", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));

			setContentPane(contentPane);
			contentPane.setLayout(null);
			
			JPanel panel = new JPanel();
			panel.setBackground(new Color(255, 255, 255));
			panel.setBounds(12, 20, 926, 350);
			contentPane.add(panel);
			panel.setLayout(null);
			
			JLabel lblDam_1 = new JLabel("사업 관리 번호");
			lblDam_1.setBounds(340, 25, 85, 30);
			panel.add(lblDam_1);
			
			
			JLabel lblNewLabel_3 = new JLabel("[확인 내용]");
			lblNewLabel_3.setBounds(12, 60, 76, 15);
			panel.add(lblNewLabel_3);

			
			
			comboBox = new JComboBox();
			
		    // SupplyDAO 인스턴스 생성
		    try {
				SupplyDAO SupplyDAO = new SupplyDAO();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		    try {
		        // 사업관리번호 조회
		        ArrayList<Integer> bmNoList = dao.getAllBusinessManagementNumbers();
		        
		        // JComboBox에 사업관리번호 추가
		        for (Integer bmNo : bmNoList) {
		            comboBox.addItem(bmNo);
		        }
		    } catch (Exception e) {
		        e.printStackTrace(); // 예외 처리
		    }
			
			
			comboBox.setBounds(437, 27, 106, 27);
			panel.add(comboBox);
			
			
			tm = new PartnerListTableModel();
	        table = new JTable(tm);
	        
	        comboBox.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                Object evt = e.getSource();
	                if (evt == comboBox) {
	                    Integer selectedBmNo = (Integer) comboBox.getSelectedItem();
	                    try {
	                        SupplyVO vo = dao.findBmno(selectedBmNo);

	                        // 프로젝트명 조회
	                        dao = new SupplyDAO(); // SupplyDAO 객체 생성
	                        vo = new SupplyVO(); // SupplyVO 객체 생성
	                        list = dao.supplyselect(selectedBmNo);
	                        tm.data = list;
	                        table.setModel(tm);
	                        tm.fireTableDataChanged(); // 수정확인
	                        clearScreen();
	                    } catch (Exception e2) {
	                        // TODO: 예외 처리
	                        JOptionPane.showMessageDialog(null, "사업 관리 번호 출력 실패: " + e2.getMessage());
	                        System.out.println(e2.getMessage());
	                    }
	                    detailShow();
	                }
	            }

	            private void detailShow() {
	                // TODO: 세부 정보 보여주기
	            }
	        });
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(12, 85, 902, 258);
			panel.add(scrollPane);
			
			table = new JTable();
			table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"업 체 명", "프로젝트 명", "발주 내용", "수 량", "금 액", "날 짜", "담 당 자", "전화 번호", "e-mail"
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

			
			Button button_1 = new Button("메인으로");
			button_1.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					MainView main;
					try {
						main = new MainView(empId, password);
						main.setVisible(true);
					} catch (Exception e3) {
						// TODO Auto-generated catch block
//						e1.printStackTrace();
				        JOptionPane.showMessageDialog(null, "메인 연결 실패"+e3.getMessage());
					}
					dispose();
				}
			});								
			button_1.setBounds(851, 376, 76, 29);
			contentPane.add(button_1);
			
		}

				void clearScreen() {
				comboBox.setSelectedItem("----선 택----");
			}
			//협력 업체 전체 내역 출력
				void detailshow(Object pa_company) {
				tm = new PartnerListTableModel();
				try {
					vo = dao.companyname();
					try {
						table.setModel(tm);
					} catch (Exception e) {
						// TODO: handle exception
						System.out.println(e.getMessage() + " 오류");
					}
					tm.fireTableDataChanged();// 변경 내용 저장
				} catch (Exception e) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "검색 실패" + e.getMessage());
				}
				}
				
		
		class PartnerListTableModel extends AbstractTableModel { //표 밑에 자동 생성

			ArrayList data = new ArrayList();
			String[] columnNames = {"업 체 명", "프로젝트 명", "발주 내용", "수 량", "금 액", "날 짜", "담 당 자", "전화 번호", "e-mail"};

			public int getColumnCount() {
				return columnNames.length;
			}

			public int getRowCount() {
				return data.size();
			}

			public Object getValueAt(int row, int col) {
				ArrayList temp = (ArrayList) data.get(row);
				return temp.get(col);
			}

			public String getColumnName(int col) {
				return columnNames[col];
			}

		}
	}


