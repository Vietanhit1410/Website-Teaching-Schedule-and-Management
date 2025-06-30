package org.example.quanlygiaovien.Repository;

import org.example.quanlygiaovien.Entity.ThongBao;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ThongBaoRepository extends CrudRepository<ThongBao, Integer> {

    @Query(value = "select tb from ThongBao tb where tb.maGV =:maGV")
    List<ThongBao> findThongBaoByMaGV(@Param("maGV")String maGV);
}
