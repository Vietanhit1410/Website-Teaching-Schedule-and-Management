package org.example.quanlygiaovien.Service.EntityService;

import org.example.quanlygiaovien.Entity.ChamCong;
import org.example.quanlygiaovien.Entity.TaiKhoan;
import org.example.quanlygiaovien.Entity.TongPhanCong;
import org.example.quanlygiaovien.Repository.ChamCongRepository;
import org.example.quanlygiaovien.Repository.TongPhanCongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ChamCongService {
    @Autowired
    ChamCongRepository chamCongRepository;

    @Autowired
    TongPhanCongRepository tongPhanCongRepository;

    @Autowired
    TaiKhoanService taiKhoanService;

    LocalDate today = LocalDate.now();
    int thang = today.getMonthValue();
    int ngay = today.getDayOfMonth();
    int thu = today.getDayOfWeek().getValue()+1;
    String maGV;
    String status="";

    public void deleteById(int id) {
        if (chamCongRepository.existsById(id)) {
            chamCongRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Không tìm thấy đối tượng với id: " + id);
        }
    }
    public void create_update(ChamCong ChamCong) {
        chamCongRepository.save(ChamCong);
    }

    public void delete(ChamCong ChamCong) {
        chamCongRepository.delete(ChamCong);
    }

    public void deleteAll() {
        chamCongRepository.deleteAll();
    }

    public List<ChamCong> getAll() {
        return (List<ChamCong>) chamCongRepository.findAll();
    }
    public void capNhatTrangThaiChamCong(List<ChamCong> chamCongList) {
        List<ChamCong> updatedChamCongList = new ArrayList<>();

        for (ChamCong chamCong : chamCongList) {
            LocalTime defaultTimeIn = checkThoiGianTimeIn(chamCong.getTiet());
            LocalTime defaultTimeOut = checkThoiGianTimeOut(chamCong.getTiet());

            if (chamCong.getTimeIn() == null || chamCong.getTimeOut() == null) {
                chamCong.setTrangThai("Nghỉ");
            } else {
                if (chamCong.getTimeIn().isAfter(defaultTimeIn)) {
                    chamCong.setTrangThai("Đi muộn");
                } else if (chamCong.getTimeOut().isBefore(defaultTimeOut)) {
                    chamCong.setTrangThai("Tan sớm");
                } else {
                    chamCong.setTrangThai("Đúng giờ");
                }
            }

            updatedChamCongList.add(chamCong);
        }
        chamCongRepository.saveAll(updatedChamCongList);
    }

    public String chamCongTheoNgay(TaiKhoan taiKhoan,String lop){
        try{
            if(taiKhoanService.verifyAccount(taiKhoan)!= null) {
                maGV = taiKhoan.getTenDangNhap();
                LocalTime timeNow = LocalTime.now();
                List<ChamCong> chamCongHomNay = chamCongRepository.isExistBangChamCong(thang, ngay, thu);
                if (chamCongHomNay.isEmpty()) {
                    taoBangChamCongTheoNgay();
                } else {
                    List<ChamCong> chamCongList = chamCongRepository.findByMaGVAndLop(maGV,lop);
                    if (!chamCongList.isEmpty()) {
                        timeIn_Out(chamCongList,timeNow);
                    }else status= "Hom nay lop "+lop+" khong co lich day";
                }
            }
        }catch (Exception e){
            status = "Bảng chám công lỗi";
        }
        return status;
    }

    public void timeIn_Out(List<ChamCong> chamCongList,LocalTime timeNow){
        try {
            if(!chamCongList.isEmpty()){
                if(chamCongList.size()==1 && chamCongList.get(0) != null){
                    timeIn_Out_Tiet(chamCongList.get(0));
                }
                if(chamCongList.size()==2 && chamCongList.get(0) != null && chamCongList.get(1) != null){
                    LocalTime timeInTiet1 = checkThoiGianTimeIn(chamCongList.get(0).getTiet());
                    LocalTime timeoutTiet1 = checkThoiGianTimeOut(chamCongList.get(0).getTiet());
                    LocalTime timeInTiet2 = checkThoiGianTimeIn(chamCongList.get(1).getTiet());
                    LocalTime timeoutTiet2 = checkThoiGianTimeOut(chamCongList.get(1).getTiet());
                    if(timeNow.isAfter(timeInTiet1.minusMinutes(5)) && timeNow.isBefore(timeoutTiet1.plusMinutes(5))){
                        timeIn_Out_Tiet(chamCongList.get(0));
                    }
                    else if (timeNow.isAfter(timeInTiet2.minusMinutes(5)) && timeNow.isBefore(timeoutTiet2.plusMinutes(5))) {
                        timeIn_Out_Tiet(chamCongList.get(1));
                    }else status ="Qua gio hoac chua den gio diem danh";

                }
            }else System.out.println("list cham cong rong de time in out");
        }catch (Exception e){
            System.out.println("loi time in time out");
        }
    }

    public void timeIn_Out_Tiet(ChamCong chamCong) {
        try {
            LocalTime now = LocalTime.now();
            int tiet = chamCong.getTiet();
            LocalTime inTiet = checkThoiGianTimeIn(tiet);
            LocalTime outTiet = checkThoiGianTimeOut(tiet);

            if (now.isBefore(inTiet)) {
                if (chamCong.getTimeIn() == null) {
                    chamCong.setTimeIn(inTiet);
                    status = "TimeIn thanh cong";
                } else {
                    status = "Da timeIn Tiet " + tiet;
                }
            } else if (now.isBefore(outTiet)) {
                if (chamCong.getTimeIn() == null) {
                    chamCong.setTimeIn(now);
                    status = "TimeOut thanh cong";
                } else if (chamCong.getTimeOut() == null) {
                    chamCong.setTimeOut(outTiet);
                    status = "TimeOut thanh cong";
                } else {
                    status ="Da cham cong xong Tiet " + tiet;
                }
            } else {
                if (chamCong.getTimeIn() == null) {
                    chamCong.setTimeIn(null);
                    chamCong.setTimeOut(null);
                } else if (chamCong.getTimeOut() == null) {
                    chamCong.setTimeOut(now);
                } else {
                    status="Da cham cong xong Tiet " + tiet;
                }
            }
        } catch (Exception e) {
            System.out.println("Loi time in time out Tiet " + chamCong.getTiet() + ": " + e.getMessage());
        }
    }


    public void taoBangChamCongTheoNgay(){
        try{
//            LocalTime timeIn = null;
//            LocalTime timeOut = null;

            List<ChamCong> chamCongHomNayList = new ArrayList<>();
            List<TongPhanCong> tongPhanCongHomNay = tongPhanCongRepository.isExistTongPhanCong(thang,ngay,thu);
            if(tongPhanCongHomNay.isEmpty()){
                System.out.println("Hom nay duoc nghi hoac chua co lich");
            }else{
                for(TongPhanCong tongPhanCong : tongPhanCongHomNay){
                    ChamCong chamCong = getChamCong(tongPhanCong);
                    chamCongHomNayList.add(chamCong);
                }
                if(luuBangChamCongVaoDB(chamCongHomNayList)){
                    System.out.println("Luu bang cham cong thanh cong");
                }else System.out.println("Luu bang cham cong 0 thanh cong");

            }
        }catch (Exception e){
            System.out.println("Loi tao bang cham cong");
        }
    }

    private ChamCong getChamCong(TongPhanCong tongPhanCong) {
        ChamCong chamCong = new ChamCong();
        chamCong.setThangChamCong(thang);
        chamCong.setNgayChamCong(ngay);
        chamCong.setThuChamCong(thu);
        chamCong.setHoTen(tongPhanCong.getTenGV());
        chamCong.setMaGV(tongPhanCong.getMaGiaoVien());
        chamCong.setLop(tongPhanCong.getTenLop());
        if(tongPhanCong.getTiet() > 0){
            int tiet = tongPhanCong.getTiet();
            chamCong.setTiet(tiet);

        }
        chamCong.setTrangThai("");
        return chamCong;
    }

    public Boolean luuBangChamCongVaoDB(List<ChamCong> chamCongHomNayList){
        try {
            if (chamCongHomNayList != null && !chamCongHomNayList.isEmpty()) {
                chamCongRepository.saveAll(chamCongHomNayList);
                return true;
            } else {
                System.out.println("Danh sách chấm công rỗng, không có gì để lưu.");
                return false;
            }
        } catch (Exception e) {
            System.err.println("Lỗi khi lưu bảng chấm công: " + e.getMessage());
            return false;
        }
    }
    public LocalTime checkThoiGianTimeIn(int tiet){
        switch (tiet) {
            case 1:
                return LocalTime.of(7, 0);   // 7:00 AM
            case 2:
                return LocalTime.of(8, 0);   // 8:00 AM
            case 3:
                return LocalTime.of(8, 50);  // 8:50 AM
            case 4:
                return LocalTime.of(9, 40);  // 9:40 AM
            case 5:
                return LocalTime.of(10, 30); // 10:30 AM
            default:
                return null;
        }
    }

    public LocalTime checkThoiGianTimeOut(int tiet) {
        LocalTime timeIn = checkThoiGianTimeIn(tiet);
        if (timeIn != null) {
            return timeIn.plusMinutes(45);
        } else {
            return null;
        }
    }

}
