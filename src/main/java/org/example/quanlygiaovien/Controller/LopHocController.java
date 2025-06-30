package org.example.quanlygiaovien.Controller;

import org.example.quanlygiaovien.Entity.LopHoc;
import org.example.quanlygiaovien.Service.EntityService.LopHocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/apiLopHoc")
public class LopHocController {
    @Autowired
    LopHocService lopHocService;

    @GetMapping("/layDanhSach")
    public List<LopHoc> layDanhSach() {
        return lopHocService.getAll();
    }
    @PostMapping("/themVaSua")
    public void themVaSua(@RequestBody LopHoc LopHoc) {
        lopHocService.create_update(LopHoc);
    }
    @DeleteMapping("/xoaTatCa")
    public void xoaTatCa() {
        lopHocService.deleteAll();
    }
    @DeleteMapping("/xoaDoiTuong")
    public void xoaDoiTuong(@RequestBody LopHoc LopHoc) {
        lopHocService.delete(LopHoc);
    }
}