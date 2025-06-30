package org.example.quanlygiaovien.Service.EntityService;

import org.example.quanlygiaovien.Entity.LichDay;
import org.example.quanlygiaovien.Entity.TongPhanCong;
import org.example.quanlygiaovien.Repository.LichDayRepository;
import org.example.quanlygiaovien.Repository.TongPhanCongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

@Service
public class LichDayService {

    @Autowired
    private LichDayRepository lichDayRepository;
    @Autowired
    private TongPhanCongRepository tongPhanCongRepository;


    public List<LichDay> getAll() {
        return lichDayRepository.findAll();
    }


    public void create_update(LichDay lichDay) {
        lichDayRepository.save(lichDay);
    }


    public void deleteAll() {
        lichDayRepository.deleteAll();
    }


    public void delete(LichDay lichDay) {
        lichDayRepository.delete(lichDay);
    }

    public void isExist() {

    }

    public List<LichDay> getLichDayByMaGV(String maGV) {
        return lichDayRepository.findByMaGV(maGV);
    }


    public List<LichDay> getLichDayByTenLop(String tenLop) {
        return lichDayRepository.findByTenLop(tenLop);
    }


    public List<LichDay> getLichDayByTenMon(String tenMon) {
        return lichDayRepository.findByTenMon(tenMon);
    }


    public List<LichDay> layLichTrongTuan(String maGV) {
        LocalDate today = LocalDate.now();
        LocalDate startOfWeek = today.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY)); // Thứ 2
        LocalDate endOfWeek = startOfWeek.plusDays(5); // Thứ 7 (5 ngày sau thứ 2)


        List<LichDay> lichDayList = lichDayRepository.timLichTrongTuan(maGV, startOfWeek, endOfWeek);

        if (lichDayList.isEmpty()) {
            List<TongPhanCong> tongPhanCongList = (List<TongPhanCong>) tongPhanCongRepository.findAll();
            List<LichDay> list = new ArrayList<>();
            for(TongPhanCong tongPhanCong : tongPhanCongList) {
                if(tongPhanCong.getMaGiaoVien().equalsIgnoreCase(maGV)){
                    LichDay lichDay = new LichDay();
                    lichDay.setMaGV(tongPhanCong.getMaGiaoVien());
                    lichDay.setThu(tongPhanCong.getThu());
                    lichDay.setTiet(tongPhanCong.getTiet());
                    lichDay.setNgay(LocalDate.of(2024,tongPhanCong.getThang(),tongPhanCong.getNgay()));
                    lichDay.setTenLop(tongPhanCong.getTenLop());
                    lichDay.setTenMon(tongPhanCong.getTenMon());
                    list.add(lichDay);
                }
            }
            lichDayRepository.saveAll(list);
        }

        return lichDayList;

    }
}
