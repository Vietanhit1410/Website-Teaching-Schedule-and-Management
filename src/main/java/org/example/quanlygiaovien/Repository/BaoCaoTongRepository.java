package org.example.quanlygiaovien.Repository;

import org.example.quanlygiaovien.Entity.BaoCaoTong;
import org.example.quanlygiaovien.Entity.ChamCong;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BaoCaoTongRepository extends CrudRepository<BaoCaoTong, Integer> {

    @Query(value = "select cc from BaoCaoTong cc where cc.maGV =:maGV and cc.thang =:thang and cc.ngay =:ngay")
    List<BaoCaoTong> findByNgayBaoCao(@Param("maGV") String maGV, @Param("thang") int thang, @Param("ngay") int ngay);

    @Query(value = "select cc from BaoCaoTong cc where cc.thang =:thang and cc.ngay =:ngay")
    List<BaoCaoTong> findByNgayBaoCao(@Param("thang") int thang, @Param("ngay") int ngay);

    @Query(value = "select cc from ChamCong cc where cc.maGV =:maGV and cc.thangChamCong =:thang ")
    List<BaoCaoTong> findByThangBaoCao(@Param("maGV") String maGV,@Param("thang") int thang);
}
