package org.example.quanlygiaovien.Controller;

import org.example.quanlygiaovien.Entity.MonHoc;
import org.example.quanlygiaovien.Service.EntityService.MonHocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apiMonHoc")
public class MonHocController {
    @Autowired
    MonHocService monHocService;

    @GetMapping("/layDanhSach")
    public List<MonHoc> layDanhSach() {
        return monHocService.getAll();
    }
    @PostMapping("/themVaSua")
    public void themVaSua(@RequestBody MonHoc MonHoc) {
        monHocService.create_update(MonHoc);
    }
    @DeleteMapping("/xoaTatCa")
    public void xoaTatCa() {
        monHocService.deleteAll();
    }
    @DeleteMapping("/xoaDoiTuong")
    public void xoaDoiTuong(@RequestBody MonHoc MonHoc) {
        monHocService.delete(MonHoc);
    }
}
