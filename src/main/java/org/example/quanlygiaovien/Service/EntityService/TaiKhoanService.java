package org.example.quanlygiaovien.Service.EntityService;

import org.example.quanlygiaovien.Entity.TaiKhoan;
import org.example.quanlygiaovien.Repository.TaiKhoanRepository;
import org.example.quanlygiaovien.Repository.ThongTinGiaoVienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaiKhoanService {

    @Autowired
    TaiKhoanRepository taiKhoanRepository;
    @Autowired
    private ThongTinGiaoVienRepository thongTinGiaoVienRepository;

    public void create_update(TaiKhoan taiKhoan) {
        taiKhoanRepository.save(taiKhoan);
    }

    public void delete(TaiKhoan taiKhoan) {
        taiKhoanRepository.delete(taiKhoan);
    }

    public void deleteAll() {
        taiKhoanRepository.deleteAll();
    }

    public List<TaiKhoan> getAll() {
        return (List<TaiKhoan>) taiKhoanRepository.findAll();
    }
    public TaiKhoan verifyAccount(TaiKhoan taiKhoan) {
        if(taiKhoan.getTenDangNhap() != null && taiKhoan.getMatKhau()!=null){
            String username = taiKhoan.getTenDangNhap();
            String password = taiKhoan.getMatKhau();
            return taiKhoanRepository.verifyAccount(username, password);
        }
        return null;
    }
}
