package org.example.quanlygiaovien.Repository;

import org.example.quanlygiaovien.Entity.TongPhanCong;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TongPhanCongRepository extends CrudRepository<TongPhanCong, Integer> {

    // Check if a specific schedule exists
    @Query("SELECT pc FROM TongPhanCong pc WHERE pc.thang = :thang AND pc.ngay = :ngay AND pc.thu = :thu")
    List<TongPhanCong> isExistTongPhanCong(@Param("thang") int thang, @Param("ngay") int ngay, @Param("thu") int thu);

    // Find schedule by date (thang, ngay, thu)
    @Query("SELECT pc FROM TongPhanCong pc WHERE pc.thang = :thang AND pc.ngay = :ngay AND pc.thu = :thu")
    List<TongPhanCong> findByDate(@Param("thang") int thang, @Param("ngay") int ngay, @Param("thu") int thu);

    // Find schedule by teacher ID
    @Query("SELECT pc FROM TongPhanCong pc WHERE pc.maGiaoVien = :maGiaoVien")
    List<TongPhanCong> findByMaGiaoVien(@Param("maGiaoVien") String maGiaoVien);

    // Find schedule by subject ID
    List<TongPhanCong> findByMaMon(String maMon);

    // Find schedule by class ID
    List<TongPhanCong> findByMaLop(String maLop);

    // Find schedule by teacher ID and subject ID
    @Query("SELECT pc FROM TongPhanCong pc WHERE pc.maGiaoVien = :maGiaoVien AND pc.maMon = :maMon")
    List<TongPhanCong> findByGiaoVienAndMon(@Param("maGiaoVien") String maGiaoVien, @Param("maMon") String maMon);

    // Find schedule within a date range for a specific month
    @Query("SELECT pc FROM TongPhanCong pc WHERE pc.ngay BETWEEN :startNgay AND :endNgay AND pc.thang = :thang")
    List<TongPhanCong> findBetweenDates(@Param("startNgay") int startNgay, @Param("endNgay") int endNgay, @Param("thang") int thang);

    // Check if a specific schedule exists based on several conditions
    @Query("SELECT COUNT(pc) > 0 FROM TongPhanCong pc WHERE pc.thang = :thang AND pc.ngay = :ngay")
    boolean existsPhanCong(@Param("thang") int thang, @Param("ngay") int ngay);

    // Find schedule by teacher name (partial match)
    @Query("SELECT pc FROM TongPhanCong pc WHERE pc.tenGV LIKE %:tenGV%")
    List<TongPhanCong> findByTenGiaoVien(@Param("tenGV") String tenGV);

    // Find schedule by class name (partial match)
    @Query("SELECT pc FROM TongPhanCong pc WHERE pc.tenLop LIKE %:tenLop%")
    List<TongPhanCong> findByTenLop(@Param("tenLop") String tenLop);

    // Find schedule by subject name (partial match)
    @Query("SELECT pc FROM TongPhanCong pc WHERE pc.tenMon LIKE %:tenMon%")
    List<TongPhanCong> findByTenMon(@Param("tenMon") String tenMon);
}
