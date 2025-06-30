package org.example.quanlygiaovien.Controller;

import org.example.quanlygiaovien.Entity.TaiKhoan;
import org.example.quanlygiaovien.Service.EntityService.TaiKhoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apiTaiKhoan")
public class TaiKhoanController {
    @Autowired
    TaiKhoanService taiKhoanService;

    @PostMapping("/kiemTraTaiKhoan")
    public ResponseEntity<TaiKhoan> kiemTraTaiKhoan(@RequestBody TaiKhoan TaiKhoan) {
       TaiKhoan taiKhoan = taiKhoanService.verifyAccount(TaiKhoan);
        if(taiKhoan == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
        return ResponseEntity.ok(taiKhoan);
    }
    @GetMapping("/layDanhSach")
    public List<TaiKhoan> layDanhSach() {
        return taiKhoanService.getAll();
    }
    @PostMapping("/themVaSua")
    public void themVaSua(@RequestBody TaiKhoan TaiKhoan) {
        taiKhoanService.create_update(TaiKhoan);
    }
    @DeleteMapping("/xoaTatCa")
    public void xoaTatCa() {
        taiKhoanService.deleteAll();
    }
    @DeleteMapping("/xoaDoiTuong")
    public void xoaDoiTuong(@RequestBody TaiKhoan TaiKhoan) {
        taiKhoanService.delete(TaiKhoan);
    }

}
