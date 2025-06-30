package org.example.quanlygiaovien.Controller;

import org.example.quanlygiaovien.Entity.ThongTinGiaoVien;
import org.example.quanlygiaovien.Service.EntityService.ThongTinGiaoVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apiThongTinGiaoVien")
public class ThongTinGiaoVienController {
    @Autowired
    ThongTinGiaoVienService thongTinGiaoVienService;

    @PostMapping("/layGiaoVienTheoMaGV")
    public ResponseEntity<ThongTinGiaoVien> kiemTraTaiKhoan(@RequestBody String maGV) {
        ThongTinGiaoVien giaoVien = thongTinGiaoVienService.getById(maGV);
        if(giaoVien == null || giaoVien.getMaGV() == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
                return ResponseEntity.ok(giaoVien);
    }


    @GetMapping("/layDanhSach")
    public List<ThongTinGiaoVien> layDanhSach() {
        return thongTinGiaoVienService.getAll();
    }

    @PostMapping("/themVaSua")
    public void themVaSua(@RequestBody ThongTinGiaoVien ThongTinGiaoVien) {
        thongTinGiaoVienService.create_update(ThongTinGiaoVien);
    }
    @DeleteMapping("/xoaTatCa")
    public void xoaTatCa() {
        thongTinGiaoVienService.deleteAll();
    }
    @DeleteMapping("/xoaDoiTuong")
    public void xoaDoiTuong(@RequestBody ThongTinGiaoVien ThongTinGiaoVien) {
        thongTinGiaoVienService.delete(ThongTinGiaoVien);
    }
}
