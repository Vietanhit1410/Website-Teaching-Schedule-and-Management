package org.example.quanlygiaovien.Service.EntityService;

import org.example.quanlygiaovien.Entity.ThongBao;
import org.example.quanlygiaovien.Entity.ThongTinGiaoVien;
import org.example.quanlygiaovien.Repository.ThongBaoRepository;
import org.example.quanlygiaovien.Repository.ThongTinGiaoVienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ThongBaoService {
    @Autowired
    ThongBaoRepository thongBaoRepository;

    @Autowired
    ThongTinGiaoVienRepository thongTinGiaoVienRepository;

    public ThongBao saveOrUpdate(ThongBao thongBao) {
        return thongBaoRepository.save(thongBao);
    }
    public List<ThongBao> findAll() {
        return (List<ThongBao>) thongBaoRepository.findAll();
    }
    public void deleteById(int id) {
        thongBaoRepository.deleteById(id);
    }

    public List<String> getListMaGV(){
        List<String> list = new ArrayList<>();
        List<ThongTinGiaoVien> thongTinGiaoVienList = (List<ThongTinGiaoVien>) thongTinGiaoVienRepository.findAll();
        for(ThongTinGiaoVien thongTinGiaoVien : thongTinGiaoVienList){
            list.add(thongTinGiaoVien.getMaGV());
        }
        return list;
    }

    public List<ThongBao> findByMaGV(String maGV) {
        if(maGV==null || maGV.isEmpty()){
            return null;
        }
        return thongBaoRepository.findThongBaoByMaGV(maGV);
    }

    public void phanPhatDanChu() {
        List<ThongBao> newList = new ArrayList<>();
        List<ThongBao> listRepos = (List<ThongBao>) thongBaoRepository.findAll();
        if (listRepos.size()>1) {
            for (ThongBao thongBao : listRepos) {
                if (thongBao.getMaGV().trim().equalsIgnoreCase("ALL")) {
                    for (String maGiaoVien : getListMaGV()) {
                        ThongBao thongBao1 = new ThongBao();
                        thongBao1.setMaGV(maGiaoVien);
                        thongBao1.setTitle(thongBao.getTitle());
                        thongBao1.setContent(thongBao.getContent());
                        newList.add(thongBao1);
                    }
                    thongBaoRepository.saveAll(newList);
                    thongBaoRepository.delete(thongBao);
                }else System.err.println("Loi");
            }
        }else System.out.println("Loi phan phat dan chu");
    }

}
