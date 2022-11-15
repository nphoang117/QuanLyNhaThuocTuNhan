/**
 * Nguyễn Viết Học _ 19533591 _ Nhóm 03 
 * 
 * Mô tả lớp : thực hiện chức năng chuyển các màn hình giao diện trên thanh menu
 */
package controller;

import java.awt.Color;
import java.util.List;
import java.awt.BorderLayout;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;
import javax.swing.plaf.ColorUIResource;

import org.w3c.dom.events.MouseEvent;

import gui_App.BaoCaoJPanel;
import gui_App.BaoCaoJPanel_NVBH;
import gui_App.HangHoaJPanel;
import gui_App.HoTroJPanel;
import gui_App.KhachHangJPanel;
import gui_App.HoaDonJPanel;
import gui_App.NhaCungCapJPanel;
import gui_App.NhanVienJPanel;

import gui_App.POPJPanel;


import manHinh.DanhMucManHinh;

public class ChuyenManHinhController {

	private JPanel root;
	private String kindSelected = "";
	private List<DanhMucManHinh> listItem;

	public ChuyenManHinhController(JPanel root) {
		super();
		this.root = root;
	}

	/**
	 * 
	 * @param pnlItem
	 * @param lblItem
	 */
	public void setView(JPanel pnlItem, JLabel lblItem) {

		kindSelected = "POP";
		pnlItem.setBackground(new Color(255, 255, 51));// mau vang nhat
		lblItem.setBackground(new Color(255, 255, 51));

		root.removeAll();
		root.setLayout(new BorderLayout());
		root.add(new POPJPanel());
		root.validate();
		root.repaint();
	}

	/**
	 * 
	 * @param listItem
	 */
	public void setEvent(List<DanhMucManHinh> listItem) {
		this.listItem = listItem;
		for (DanhMucManHinh item : listItem) {
			item.getJlb().addMouseListener(new lableEvent(item.getKind(), item.getJpn(), item.getJlb()));

		}
	}

	class lableEvent implements MouseInputListener {

		private JPanel node;
		private String kind;
		private JPanel pnlItem;
		private JLabel lblItem;

		public lableEvent(String kind, JPanel pnlItem, JLabel lblItem) {
			super();
			this.kind = kind;
			this.pnlItem = pnlItem;
			this.lblItem = lblItem;
		}

		public void mouseClicked(java.awt.event.MouseEvent e) {
			switch (kind) {

			case "POP":
				node = new POPJPanel();
				break;
			case "HangHoa":
				node = new HangHoaJPanel();
				break;

			case "HoaDon":
				node = new HoaDonJPanel();
				break;
			case "NhaCungCap":
				node = new NhaCungCapJPanel();
				break;
			case "KhachHang":
				node = new KhachHangJPanel();
				break;
			case "NhanVien":
				node = new NhanVienJPanel();
				break;
			case "BaoCao":
				node = new BaoCaoJPanel();
				break;
			case "BaoCao_nv":
				node = new BaoCaoJPanel_NVBH();
				break;
			case "HoTro":
				node = new HoTroJPanel();
				break;
			default:
				node = new POPJPanel();
				break;
			}
			root.removeAll();
			root.setLayout(new BorderLayout());
			root.add(node);
			root.validate();
			root.repaint();
			setChangeBackGroud(kind);

		}

		public void mousePressed(java.awt.event.MouseEvent e) {
			kindSelected = kind;
			pnlItem.setBackground(new Color(255, 255, 51));
			lblItem.setBackground(new Color(255, 255, 51));
		}

		public void mouseReleased(java.awt.event.MouseEvent e) {
			// TODO Auto-generated method stub

		}

		public void mouseEntered(java.awt.event.MouseEvent e) {
			pnlItem.setBackground(new Color(255, 255, 51));// mau vang nhat
			lblItem.setBackground(new Color(255, 255, 51));

		}

		public void mouseExited(java.awt.event.MouseEvent e) {
			if (!kindSelected.equalsIgnoreCase(kind)) {
				pnlItem.setBackground(new Color(255, 204, 0));
				lblItem.setBackground(new Color(255, 204, 0));
			}

		}

		public void mouseDragged(java.awt.event.MouseEvent e) {
			// TODO Auto-generated method stub

		}

		public void mouseMoved(java.awt.event.MouseEvent e) {
			// TODO Auto-generated method stub

		}

		/**
		 * thay đỗi hiệu ứng màu khi di chuyển chuột vào menu
		 * @param kind
		 */
		private void setChangeBackGroud(String kind) {
			for (DanhMucManHinh item : listItem) {
				if (item.getKind().equalsIgnoreCase(kind)) {
					item.getJpn().setBackground(new Color(255, 255, 51));// vang nhat
					item.getJlb().setBackground(new Color(255, 255, 51));
				} else {
					item.getJpn().setBackground(new Color(255, 204, 0));
					item.getJlb().setBackground(new Color(255, 204, 0));// vang dam
				}
			}
		}

	}

}
