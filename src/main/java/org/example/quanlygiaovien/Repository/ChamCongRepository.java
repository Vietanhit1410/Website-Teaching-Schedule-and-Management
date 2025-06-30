package org.example.quanlygiaovien.Repository;


import org.example.quanlygiaovien.Entity.ChamCong;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChamCongRepository extends CrudRepository<ChamCong, Integer> {

    @Query(value ="select pc from ChamCong pc where pc.thangChamCong =:thang and pc.ngayChamCong =:ngay and pc.thuChamCong =:thu")
    List<ChamCong> isExistBangChamCong(@Param("thang") int thang, @Param("ngay") int ngay, @Param("thu") int thu);

    @Query(value = "select cc from ChamCong cc where cc.maGV =:maGV and cc.lop =:lop")
    List<ChamCong> findByMaGVAndLop(@Param("maGV") String maGV, @Param("lop") String lop);

    @Query(value = "select cc from ChamCong cc where cc.maGV =:maGV and cc.thangChamCong =:thang and cc.ngayChamCong =:ngay")
    List<ChamCong> findByNgayChamCong(@Param("maGV") String maGV,@Param("thang") int thang, @Param("ngay") int ngay);

    @Query(value = "select cc from ChamCong cc where cc.thangChamCong =:thang and cc.ngayChamCong =:ngay")
    List<ChamCong> findByNgayChamCong(@Param("thang") int thang, @Param("ngay") int ngay);
 @Query(value = "select cc from ChamCong cc where cc.maGV =:maGV and cc.thangChamCong =:thang ")
    List<ChamCong> findByThangChamCong(@Param("maGV") String maGV,@Param("thang") int thang);
}
