package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import model.ProjectAdminDAO;
import model.rec.EmpVO;
import model.EmpDAO;


public class ProjectAdminView extends JFrame {
 JPanel contentPane;
 JTable table;
 ProjectAdminDAO dao;
JButton ProDepartInsert;
 JComboBox comboBox;
 JComboBox comboBox_1;
 JComboBox PjAdminSearch;
 JTextField pjAdminnum, dePartmentName, TeamName, proJectName, proCessName, proCessdate;
 String str = "";
	EmpVO empVO = null;
	EmpDAO empDAO = null;
	String empId;
	String password;
	



	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProjectAdminView frame = new ProjectAdminView("6", "1234");
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
	public ProjectAdminView(String empId, String password) {
		this.empId = "";
		this.password = "";
		
		
		try {
			dao = new ProjectAdminDAO();
			System.out.println("관리자 디비 연결 성공");
		}catch (Exception e) {
			// TODO: handle exception
	         JOptionPane.showMessageDialog(null, "관리자 디비 연결 실패 : " + e.getMessage());
		}
		
		setLocationRelativeTo(null);
		
		
		getContentPane().setForeground(Color.WHITE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainView.class.getResource("/img/logo_i.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 620);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(12, 33, 731, 53);
		contentPane.add(panel);
		
	       JLabel lblNewLabel = new JLabel("부서명");


	        String[] searchText = { "A부서", "B부서", "C부서" };
	        comboBox = new JComboBox(searchText);


	        JLabel lblNewLabel_1 = new JLabel("팀명");


	        String[][] searchText2 = {
	            { "1팀", "2팀" },
	            
	            {  "3팀", "4팀" },
	            {  "5팀" }
	        };

	        HashMap<String, String[]> teamMap = new HashMap<>();
	        teamMap.put("A부서", searchText2[0]);
	        teamMap.put("B부서", searchText2[1]);
	        teamMap.put("C부서", searchText2[2]);

	        comboBox_1 = new JComboBox();

	
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(144)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 168, GroupLayout.PREFERRED_SIZE)
					.addGap(53)
					.addComponent(lblNewLabel_1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(125, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
						.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1))
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
		
		JLabel lblNewLabel_2 = new JLabel("프로젝트");
		lblNewLabel_2.setBounds(35, 112, 79, 15);
		contentPane.add(lblNewLabel_2);
		
		JButton btnNewButton = new JButton("메인으로");
		btnNewButton.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        MainView main;
		        try {
		            main = new MainView(empId, password);
		            main.setVisible(true);
		            dispose();
		        } catch (Exception e1) {
		            String errorMessage = (e1.getMessage() != null) ? e1.getMessage() : "";
		            JOptionPane.showMessageDialog(null, "메인 연결 실패: " + errorMessage);
		        }
		    }
		});
		btnNewButton.setBounds(647, 538, 97, 23);
		contentPane.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(24, 125, 731, 403);
		contentPane.add(scrollPane);
		
 

        comboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedDepartment = (String) comboBox.getSelectedItem();
                String[] teams = teamMap.get(selectedDepartment);
                comboBox_1.setModel(new DefaultComboBoxModel<String>(teams));
                
                // 선택된 팀명 가져오기
 
            }
        });
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"  부 서 명", "  팀   명 ", "프로젝트명", "공정명", "잔여기간"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(158);
		table.getColumnModel().getColumn(1).setPreferredWidth(126);
		table.getColumnModel().getColumn(2).setPreferredWidth(106);
		table.getColumnModel().getColumn(3).setPreferredWidth(112);
		table.getColumnModel().getColumn(4).setPreferredWidth(87);
		scrollPane.setViewportView(table);
		table.getTableHeader().setBackground(Color.WHITE);
		table.setFillsViewportHeight(true);
	
	// 이벤트 리스너 설정
		comboBox_1.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        // 선택된 팀명 가져오기
		        String selectedTeam = (String) comboBox_1.getSelectedItem();
		        
		        ProjectAdminDAO dao;

		        try {
		            dao = new ProjectAdminDAO();
		            String selectedDepartment = (String) comboBox_1.getSelectedItem();
		            String selectDepartment = (String) comboBox.getSelectedItem();
		            ArrayList<ArrayList<String>> teamInfo = dao.searchTeam(selectedTeam);

		            for (ArrayList<String> info : teamInfo) {
			            String department = selectDepartment;
		                String teamName = info.get(0);
		                String projectName = info.get(1);
		                String processName = info.get(2);
		                String proCessdate = info.get(3);

		                DefaultTableModel model = (DefaultTableModel) table.getModel();
		                model.addRow(new Object[] { department, teamName, projectName, processName, proCessdate });
		            }
		        } catch (Exception ex) {
		            ex.printStackTrace();
		        }
		    } 
		});
	
	}
	
		   void clearScreen() {					// 페이지 삭제
			   dePartmentName.setText(" "); //부서명
			   TeamName.setText(" "); //팀명
			   proJectName.setText(" "); // 프로젝트명
			   proCessName.setText(" "); // 공정명
			   proCessdate.setText(" "); 
			   
			    int defaultValue = 0; // 기본값 설정
			    pjAdminnum.setText(String.valueOf(defaultValue));
			    proCessdate.setText(String.valueOf(defaultValue));
		   
	}
		   
		   

		   class ProjectAdminListTableModel extends AbstractTableModel {
			    ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();
			    String[] columnNames = {"구  분", "부 서 명", "팀  명", "프로젝트명", "공 정 명", "잔여기간", "비  고"};

			    public int getColumnCount() {
			        return columnNames.length;
			    }

			    public int getRowCount() {
			        return data.size();
			    }

			    public Object getValueAt(int row, int col) {
			        ArrayList<String> temp = data.get(row);
			        return temp.get(col);
			    }

			    public String getColumnName(int col) {
			        return columnNames[col];
			    }

			    public void setData(ArrayList<ArrayList<String>> newData) {
			        data = newData;
			        fireTableDataChanged();
			    }
			}

}

