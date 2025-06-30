package org.example.quanlygiaovien.Service.EntityService;

import org.example.quanlygiaovien.Entity.MonHoc;
import org.example.quanlygiaovien.Repository.MonHocRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MonHocService {

    @Autowired
    MonHocRepository monHocRepository;


    public void create_update(MonHoc monHoc) {
        monHocRepository.save(monHoc);
    }

    public void delete(MonHoc monHoc) {
        monHocRepository.delete(monHoc);
    }

    public void deleteAll() {
        monHocRepository.deleteAll();
    }

    public List<MonHoc> getAll() {
        return (List<MonHoc>) monHocRepository.findAll();
    }
}
