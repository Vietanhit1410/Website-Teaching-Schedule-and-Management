package org.example.quanlygiaovien.Repository;

import org.example.quanlygiaovien.Entity.ThongTinGiaoVien;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ThongTinGiaoVienRepository  extends CrudRepository<ThongTinGiaoVien, Integer> {

    @Query(value = "select gv from ThongTinGiaoVien gv where gv.maGV =:maGV")
    ThongTinGiaoVien findByMaGV(@Param("maGV") String maGV);
}
