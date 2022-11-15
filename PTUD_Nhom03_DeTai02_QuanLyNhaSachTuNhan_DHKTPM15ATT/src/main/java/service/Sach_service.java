/**
 * Đoàn Kiều Mỹ Ngọc -19446111 - nhóm 03
 * 
 *  mô tả lớp :
 */
package service;

import java.util.List;

import dao.SachDao;
import entity.Sach;

public class Sach_service {

	private SachDao sachDao = null;

    public Sach_service() {
        this.sachDao = new SachDao();
    }

  
    public List<Sach> getList(){
        return sachDao.getList();
    }
    
    public List<Sach> getList_Sach_BH(){
    	return sachDao.getList_Sach_BanHang();
    }
    public Sach getOne(String idsach) {
    	return sachDao.getOne(idsach);
    }
	
	
}
