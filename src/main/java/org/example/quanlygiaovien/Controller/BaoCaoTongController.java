package org.example.quanlygiaovien.Controller;


import org.example.quanlygiaovien.Entity.BaoCaoTong;
import org.example.quanlygiaovien.Service.EntityService.BaoCaoTongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/apiBaoCaoTong")
public class BaoCaoTongController {
    @Autowired
    BaoCaoTongService baoCaoTongService;

    @GetMapping("/layDanhSach")
    public List<BaoCaoTong> layDanhSach() {
        return baoCaoTongService.getAll();
    }
    @PostMapping("/themVaSua")
    public ResponseEntity<BaoCaoTong> themVaSua(@RequestBody BaoCaoTong baoCaoTong) {
       BaoCaoTong a = baoCaoTongService.create_update(baoCaoTong);
       return ResponseEntity.ok(a);
    }
    @DeleteMapping("/xoaTatCa")
    public void xoaTatCa() {
        baoCaoTongService.deleteAll();
    }
    @DeleteMapping("/xoaDoiTuong")
    public void xoaDoiTuong(@RequestBody int id) {
        baoCaoTongService.deleteById(id); // Xóa đối tượng theo id
    }


    @GetMapping("/baoCaoTongTheoNgayAdmin")
    public ResponseEntity<List<BaoCaoTong>> baoCaoTongTheoNgayAdmin() {
        int thang = LocalDate.now().getMonthValue();
        int ngay = LocalDate.now().getDayOfMonth();
        List<BaoCaoTong> list = baoCaoTongService.taoBaoCaoTongChoNgay(thang, ngay);

        // Kiểm tra kết quả list trước khi trả về
        if (list == null || list.isEmpty()) {
            System.out.println("Danh sách báo cáo trống hoặc null");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);  // Trả về mã 204 nếu không có dữ liệu
        }

        System.out.println("Danh sách báo cáo: " + list);

        return ResponseEntity.ok(list);  // Trả về dữ liệu khi danh sách có dữ liệu
    }

    @PostMapping("/baoCaoTongTheoNgayUser")
    public ResponseEntity<BaoCaoTong> baoCaoTongTheoNgayUser(@RequestBody String maGV) {
        int thang = LocalDate.now().getMonthValue();
        int ngay = LocalDate.now().getDayOfMonth();
        List<BaoCaoTong> list = baoCaoTongService.taoBaoCaoTongChoNgay(maGV,thang,ngay);
        BaoCaoTong baoCaoTong = list.get(0);
        if(list.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
        return ResponseEntity.ok(baoCaoTong);
    }
    @GetMapping("/baoCaoTongTheoThang")
    public void baoCaoTongTheoThang(String maGV,int thang) {
        baoCaoTongService.taoBaoCaoTongChoThang(maGV,thang);
    }


}
