package org.example.quanlygiaovien.Repository;

import org.example.quanlygiaovien.Entity.LichDay;
import org.example.quanlygiaovien.Entity.TongPhanCong;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface LichDayRepository extends JpaRepository<LichDay, Integer> {

    // Tìm tất cả lịch dạy của một giáo viên
    List<LichDay> findByMaGV(String maGV);

    // Tìm tất cả lịch dạy của một lớp
    List<LichDay> findByTenLop(String tenLop);

    // Tìm tất cả lịch dạy của một môn học
    List<LichDay> findByTenMon(String tenMon);

    // Tìm lịch dạy của một giáo viên trong một lớp cụ thể
    List<LichDay> findByMaGVAndTenLop(String maGV, String tenLop);

    // Tìm lịch dạy của một giáo viên trong một môn học cụ thể
    List<LichDay> findByMaGVAndTenMon(String maGV, String tenMon);

    // Tìm lịch dạy theo ngày trong tuần (thu)
    List<LichDay> findByThu(int thu);

    // Tìm lịch dạy theo tiết học
    List<LichDay> findByTiet(int tiet);

    // Tìm lịch dạy của một giáo viên tại một tiết học cụ thể
    List<LichDay> findByMaGVAndTiet(String maGV, int tiet);

    // Tìm lịch dạy theo ngày (thu), tiết học và lớp học
    @Query("SELECT l FROM LichDay l WHERE l.thu = :thu AND l.tiet = :tiet AND l.tenLop = :tenLop")
    List<LichDay> findByThuTietAndLop(int thu, int tiet, String tenLop);

    // Kiểm tra lịch dạy có tồn tại hay không cho giáo viên tại một tiết và ngày cụ thể
    @Query("SELECT COUNT(l) > 0 FROM LichDay l WHERE l.maGV = :maGV AND l.thu = :thu AND l.tiet = :tiet")
    boolean existsByMaGVAndThuAndTiet(String maGV, int thu, int tiet);

    // Tìm lịch dạy của một giáo viên trong một khoảng thời gian cụ thể (theo ngày và tiết)
    @Query("SELECT l FROM LichDay l WHERE l.maGV = :maGV AND l.thu BETWEEN :startThu AND :endThu AND l.tiet BETWEEN :startTiet AND :endTiet")
    List<LichDay> findLichDayByTimeRange(String maGV, int startThu, int endThu, int startTiet, int endTiet);

    // Tìm tất cả các lịch dạy của giáo viên theo thời gian cụ thể (theo tuần)
    @Query("SELECT l FROM LichDay l WHERE l.maGV = :maGV AND l.thu BETWEEN :startThu AND :endThu")
    List<LichDay> findLichDayByWeek(String maGV, int startThu, int endThu);

    // Tìm tất cả lịch dạy theo lớp và tuần
    @Query("SELECT l FROM LichDay l WHERE l.tenLop = :tenLop AND l.thu BETWEEN :startThu AND :endThu")
    List<LichDay> findLichDayByLopAndWeek(String tenLop, int startThu, int endThu);

    // Tìm tất cả lịch dạy của một giáo viên trong một ngày cụ thể
    @Query("SELECT l FROM LichDay l WHERE l.maGV = :maGV AND l.thu = :thu")
    List<LichDay> findLichDayByMaGVAndDate(String maGV, int thu);

    // Tìm lịch dạy theo tên lớp và tiết học
    List<LichDay> findByTenLopAndTiet(String tenLop, int tiet);

    // Tìm lịch dạy của một giáo viên trong một môn học và tiết cụ thể
    List<LichDay> findByMaGVAndTenMonAndTiet(String maGV, String tenMon, int tiet);

    // Tìm tất cả lịch dạy theo điều kiện (thu, tiết, môn học)
    @Query("SELECT l FROM LichDay l WHERE l.thu = :thu AND l.tiet = :tiet AND l.tenMon = :tenMon")
    List<LichDay> findByThuTietAndMon(int thu, int tiet, String tenMon);

    // Tìm lịch dạy theo nhiều điều kiện kết hợp với JPQL
    @Query("SELECT l FROM LichDay l WHERE l.maGV = :maGV AND l.ngay BETWEEN :startOfWeek AND :endOfWeek")
    List<LichDay> timLichTrongTuan(@Param("maGV") String maGV,
                                   @Param("startOfWeek") LocalDate startOfWeek,
                                   @Param("endOfWeek") LocalDate endOfWeek);

}
