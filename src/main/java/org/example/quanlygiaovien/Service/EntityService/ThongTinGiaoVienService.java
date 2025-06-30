package org.example.quanlygiaovien.Service.EntityService;

import org.example.quanlygiaovien.Entity.ThongTinGiaoVien;
import org.example.quanlygiaovien.Repository.ThongTinGiaoVienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class ThongTinGiaoVienService {

    @Autowired
    ThongTinGiaoVienRepository thongTinGiaoVienRepository;

    public void create_update(ThongTinGiaoVien thongTinGiaoVien) {
        thongTinGiaoVienRepository.save(thongTinGiaoVien);
    }

    public void delete(ThongTinGiaoVien thongTinGiaoVien) {
        thongTinGiaoVienRepository.delete(thongTinGiaoVien);
    }

    public void deleteAll() {
        thongTinGiaoVienRepository.deleteAll();
    }

    public List<ThongTinGiaoVien> getAll() {
        return (List<ThongTinGiaoVien>) thongTinGiaoVienRepository.findAll();
    }

    public ThongTinGiaoVien getById(String maGV) {
            return thongTinGiaoVienRepository.findByMaGV(maGV);
    }

//    public void excel() {
//        test<ThongTinGiaoVien,ThongTinGiaoVienRepository>
//    }
}
