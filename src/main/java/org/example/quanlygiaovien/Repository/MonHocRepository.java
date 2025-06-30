package org.example.quanlygiaovien.Repository;

import org.example.quanlygiaovien.Entity.MonHoc;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonHocRepository  extends CrudRepository<MonHoc, Integer> {
}
