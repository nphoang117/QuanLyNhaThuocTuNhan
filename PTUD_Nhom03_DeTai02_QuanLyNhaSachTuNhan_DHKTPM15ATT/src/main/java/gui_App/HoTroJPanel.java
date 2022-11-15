/**
 * Nguyễn Phạm Hoàng Long - 19443481 - nhóm 03
 * 
 * mô tả lớp : thực hiện chức năng hổ trợ hướng dẫn sử dung phần mềm cho nhân viên
 */
package gui_App;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionEvent;

public class HoTroJPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public HoTroJPanel() {
		setLayout(null);
		setBounds(0, 0, 1502, 984);
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1502, 985);
		panel.setBackground(Color.WHITE);
		add(panel);
		panel.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.ORANGE);
		panel_1.setBounds(0, 0, 1502, 89);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnNewButton = new JButton("Nhấn vào nút này để được hỗ trợ");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] commands = {"cmd","/c","data\\trogiup.chm"
				};
				try {
					Runtime.getRuntime().exec(commands);
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.setFont(new Font("Arial", Font.BOLD, 18));
		btnNewButton.setBounds(560, 396, 374, 68);
		panel.add(btnNewButton);
	}

}
