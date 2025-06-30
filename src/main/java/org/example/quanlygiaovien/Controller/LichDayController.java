package org.example.quanlygiaovien.Controller;

import org.example.quanlygiaovien.Entity.LichDay;
import org.example.quanlygiaovien.Service.EntityService.LichDayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apiLichDay")
public class LichDayController {

    @Autowired
    LichDayService lichDayService;

    @PostMapping("/layLichDayTuan")
    public ResponseEntity<List<LichDay>> layLichDayTuan(@RequestBody String maGV) {
      List<LichDay> lichDayList =  lichDayService.layLichTrongTuan(maGV);
      if(lichDayList.isEmpty()){
          return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
      }
      return ResponseEntity.ok(lichDayList);
    }

    // Lấy tất cả danh sách lịch dạy
    @GetMapping("/layDanhSach")
    public ResponseEntity<List<LichDay>> layDanhSach() {
        List<LichDay> lichDayList = lichDayService.getAll();
        if (lichDayList.isEmpty()) {
            return ResponseEntity.noContent().build(); // Trả về mã 204 nếu không có dữ liệu
        }
        return ResponseEntity.ok(lichDayList); // Trả về danh sách lịch dạy
    }

    // Thêm mới hoặc sửa một đối tượng LichDay
    @PostMapping("/themVaSua")
    public ResponseEntity<String> themVaSua(@RequestBody LichDay lichDay) {
        try {
            lichDayService.create_update(lichDay);
            return ResponseEntity.ok("Thêm hoặc sửa thành công"); // Trả về thông báo thành công
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Lỗi: " + e.getMessage()); // Xử lý lỗi khi thêm hoặc sửa
        }
    }

    // Xóa tất cả lịch dạy
    @DeleteMapping("/xoaTatCa")
    public ResponseEntity<String> xoaTatCa() {
        try {
            lichDayService.deleteAll();
            return ResponseEntity.ok("Đã xóa tất cả lịch dạy"); // Trả về thông báo thành công
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Lỗi: " + e.getMessage()); // Xử lý lỗi khi xóa
        }
    }





    // Tìm lịch dạy theo lớp
    @GetMapping("/timTheoLop/{tenLop}")
    public ResponseEntity<List<LichDay>> timTheoLop(@PathVariable String tenLop) {
        List<LichDay> lichDayList = lichDayService.getLichDayByTenLop(tenLop);
        if (lichDayList.isEmpty()) {
            return ResponseEntity.noContent().build(); // Trả về mã 204 nếu không có dữ liệu
        }
        return ResponseEntity.ok(lichDayList); // Trả về danh sách lịch dạy
    }

    // Tìm lịch dạy theo môn học
    @GetMapping("/timTheoMon/{tenMon}")
    public ResponseEntity<List<LichDay>> timTheoMon(@PathVariable String tenMon) {
        List<LichDay> lichDayList = lichDayService.getLichDayByTenMon(tenMon);
        if (lichDayList.isEmpty()) {
            return ResponseEntity.noContent().build(); // Trả về mã 204 nếu không có dữ liệu
        }
        return ResponseEntity.ok(lichDayList); // Trả về danh sách lịch dạy
    }

}
