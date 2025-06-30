package org.example.quanlygiaovien.Repository;

import org.example.quanlygiaovien.Entity.LopHoc;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LopHocRepository  extends CrudRepository<LopHoc, Integer> {
@Query(value = "select lop from LopHoc lop where lop.tenMon=:tenMon")
    List<LopHoc> findByTenMon(@Param("tenMon") String tenMon);

}
