package org.example.quanlygiaovien.Controller;

import org.example.quanlygiaovien.Entity.ThongBao;
import org.example.quanlygiaovien.Service.EntityService.ThongBaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apiThongBao")
public class ThongBaoController {
    @Autowired
    ThongBaoService thongBaoService;

    @PostMapping("/createOrUpdate")
    public void createOrUpdate(@RequestBody ThongBao thongBao) {
        thongBaoService.saveOrUpdate(thongBao);
        thongBaoService.phanPhatDanChu();
    }
    @GetMapping("/getMaGVList")
    public ResponseEntity<List<String>> getMaGVList() {
        List<String> list = thongBaoService.getListMaGV();
        if(list.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<ThongBao>> getAll() {
        List<ThongBao> thongBaoList = thongBaoService.findAll();
        if (thongBaoList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(thongBaoList);
    }

    @DeleteMapping("/deleteByID")
    public void deleteByID(@RequestBody int id) {
        thongBaoService.deleteById(id);
    }

    @PostMapping("/getInfoByMaGV")
    public ResponseEntity<List<ThongBao>> getInfoByMaGV(@RequestBody String maGV) {
        List<ThongBao> thongBaoList = thongBaoService.findByMaGV(maGV);
        if (thongBaoList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(thongBaoList);
    }

}
