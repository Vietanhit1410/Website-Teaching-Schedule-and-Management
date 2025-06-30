package org.example.quanlygiaovien.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.quanlygiaovien.Entity.ChamCong;
import org.example.quanlygiaovien.Entity.TaiKhoan;
import org.example.quanlygiaovien.Service.EntityService.ChamCongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/apiChamCong")
public class ChamCongController {
    @Autowired
    private ChamCongService chamCongService;

    @GetMapping("/layDanhSach")
    public List<ChamCong> layDanhSach() {
        return chamCongService.getAll();
    }
    @PostMapping("/themVaSua")
    public void themVaSua(@RequestBody ChamCong ChamCong) {
        chamCongService.create_update(ChamCong);
    }
    @DeleteMapping("/xoaTatCa")
    public void xoaTatCa() {
        chamCongService.deleteAll();
    }


    @PostMapping("/chamCong")
    public ResponseEntity<String> chamCong(@RequestBody Map<String, Object> request) {
        // Lấy taiKhoan và lopHoc từ Map

        String lopHoc = (String) request.get("lopHoc");
        TaiKhoan taiKhoan = new ObjectMapper().convertValue(request.get("taiKhoan1"), TaiKhoan.class);
        String status = chamCongService.chamCongTheoNgay(taiKhoan, lopHoc);
        System.out.println(status);
        if (status.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
                return ResponseEntity.ok(status);
    }
    @DeleteMapping("/xoaDoiTuong")
    public void xoaDoiTuong(@RequestBody int id) {
        chamCongService.deleteById(id); // Xóa đối tượng theo id
    }

}
