package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import model.EmpDAO;
import model.ProjectDAO;
import model.rec.MemoVO;

public class MemoOpenView extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextArea textArea;
	static EmpDAO empDAO = null;
	static String empId;
	static String password;
	static int vMemo;
	
	ArrayList list = null;
	ProjectDAO dao;
	MemoVO mvo; 

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MemoOpenView frame = new MemoOpenView(vMemo, empId);
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

	public MemoOpenView(int vMemo, String empId) {
		try {
			dao = new ProjectDAO();
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "MemoOpenView DB 연결 실패 : " + e.getMessage());
		}
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 626, 580);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("메모제목");
		lblNewLabel.setBounds(12, 30, 66, 15);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("작성일자");
		lblNewLabel_1.setBounds(212, 30, 81, 15);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("프로젝트명");
		lblNewLabel_2.setBounds(12, 77, 81, 15);
		contentPane.add(lblNewLabel_2);
		
		textField = new JTextField();
		textField.setBounds(90, 27, 108, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(90, 74, 108, 21);
		contentPane.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(267, 27, 97, 21);
		contentPane.add(textField_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(458, 18, 140, 43);
		contentPane.add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
		JLabel lblNewLabel_3 = new JLabel("수신자목록");
		lblNewLabel_3.setBounds(376, 30, 66, 15);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_2_1 = new JLabel("작성자명");
		lblNewLabel_2_1.setBounds(212, 80, 81, 15);
		contentPane.add(lblNewLabel_2_1);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(267, 77, 97, 21);
		contentPane.add(textField_3);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(12, 129, 586, 376);
		contentPane.add(scrollPane_1);
		
		JTextArea textArea_1 = new JTextArea();
		scrollPane_1.setViewportView(textArea_1);
		
		JLabel lblNewLabel_4 = new JLabel("메모내용");
		lblNewLabel_4.setBounds(12, 108, 57, 15);
		contentPane.add(lblNewLabel_4);
		
		JButton btnNewButton = new JButton("홈으로");
		btnNewButton.setBounds(501, 515, 97, 23);
		contentPane.add(btnNewButton);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPane_2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane_2.setBounds(458, 71, 140, 43);
		contentPane.add(scrollPane_2);
		
		JTextArea textArea_2 = new JTextArea();
		scrollPane_2.setViewportView(textArea_2);
		
		JLabel lblNewLabel_3_1 = new JLabel("수신미확인자");
		lblNewLabel_3_1.setBounds(376, 80, 85, 15);
		contentPane.add(lblNewLabel_3_1);
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String sysdate = dateFormat.format(new Date());
		try {
			mvo = dao.memoOpen(vMemo);
			textField.setText(mvo.getMemo_title());
			textField_2.setText(mvo.getMemo_date());
			textField_1.setText(mvo.getPro_name());
			textField_3.setText(mvo.getMemo_write());
			textArea_1.setText(mvo.getMemo_contents());

			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		try {
			StringBuilder sb = new StringBuilder();
			list = dao.MemoOpenCheck(vMemo);
			
			int size = list.size();
			for (int i = 0; i < size; i++) {
				sb.append(list.get(i));
				if (i < size - 1) {
					sb.append(", ");
				}
			}
			
			textArea.setText(sb.toString());
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		try {
			StringBuilder sb = new StringBuilder();
			list = new ArrayList();
			list = dao.MemoOpenNoCheck(vMemo);
			
			int size = list.size();
			for (int i = 0; i < size; i++) {
				sb.append(list.get(i));
				if (i < size - 1) {
					sb.append(", ");
				}
			}
			
			textArea_2.setText(sb.toString());
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	

	
}
