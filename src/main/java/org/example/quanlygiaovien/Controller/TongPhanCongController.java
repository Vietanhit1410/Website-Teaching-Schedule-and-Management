package org.example.quanlygiaovien.Controller;

import org.example.quanlygiaovien.Entity.TongPhanCong;

import org.example.quanlygiaovien.Service.EntityService.TongPhanCongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.example.quanlygiaovien.Repository.TongPhanCongRepository;


import java.util.List;



@RestController
@RequestMapping("/apiTongPhanCong")
public class TongPhanCongController {
    @Autowired
    private TongPhanCongRepository tongPhanCongRepository;
    @Autowired
    private TongPhanCongService tongPhanCongService;

    @GetMapping("/layDanhSach")
    public ResponseEntity<List<TongPhanCong>> layDanhSach() {
        List<TongPhanCong> list = tongPhanCongService.getAll();
        if (list.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(list);
    }


    @PostMapping("/themVaSua")
    public ResponseEntity<TongPhanCong> themVaSua(@RequestBody TongPhanCong tongPhanCong) {
            TongPhanCong tongPhanCong1 =  tongPhanCongService.createOrUpdate(tongPhanCong);
            if (tongPhanCong1 == null) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(tongPhanCong1);
    }

    @DeleteMapping("/xoaTatCa")
    public void xoaTatCa() {
        tongPhanCongService.deleteAll();
    }

    @DeleteMapping("/xoaDoiTuong")
    public void xoaDoiTuong(@RequestBody int id) {
        tongPhanCongService.deleteById(id); // Xóa đối tượng theo id
    }


    @GetMapping("/taoLichPhanCong")
    public ResponseEntity<Boolean> taoLichPhanCong() {
        Boolean bo = tongPhanCongService.createSchedule();
        return ResponseEntity.ok(bo);
    }
//    @GetMapping("/tongphancong")
//    public String getTongPhanCong(Model model) {
//        List<TongPhanCong> phanCongs = (List<TongPhanCong>) tongPhanCongRepository.findAll();
//        model.addAttribute("phanCongs", phanCongs);  // Truyền danh sách vào view
//        return "tongphancong";  // Tên của file Thymeleaf template
//    }
}

