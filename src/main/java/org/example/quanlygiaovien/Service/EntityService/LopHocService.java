package org.example.quanlygiaovien.Service.EntityService;

import org.example.quanlygiaovien.Entity.LopHoc;
import org.example.quanlygiaovien.Repository.LopHocRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LopHocService {

    @Autowired
    LopHocRepository lopHocRepository;

    public void create_update(LopHoc lopHoc) {
        lopHocRepository.save(lopHoc);
    }

    public void delete(LopHoc lopHoc) {
        lopHocRepository.delete(lopHoc);
    }

    public void deleteAll() {
        lopHocRepository.deleteAll();
    }

    public List<LopHoc> getAll() {
        return (List<LopHoc>) lopHocRepository.findAll();
    }
}
