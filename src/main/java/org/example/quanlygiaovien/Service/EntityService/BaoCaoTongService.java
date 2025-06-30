package org.example.quanlygiaovien.Service.EntityService;

import org.example.quanlygiaovien.Entity.BaoCaoTong;
import org.example.quanlygiaovien.Entity.ChamCong;
import org.example.quanlygiaovien.Repository.BaoCaoTongRepository;
import org.example.quanlygiaovien.Repository.ChamCongRepository;
import org.example.quanlygiaovien.Repository.TongPhanCongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class BaoCaoTongService {

    @Autowired
    BaoCaoTongRepository baoCaoTongRepository;
    @Autowired
    ChamCongRepository chamCongRepository;
    @Autowired
    ChamCongService chamCongService;
    @Autowired
    private TongPhanCongRepository tongPhanCongRepository;

    public BaoCaoTong create_update(BaoCaoTong baoCaoTong) {
       return  baoCaoTongRepository.save(baoCaoTong);
    }

    public void deleteById(int id) {
        if (baoCaoTongRepository.existsById(id)) {
            baoCaoTongRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Không tìm thấy đối tượng với id: " + id);
        }
    }

    public void delete(BaoCaoTong baoCaoTong) {
        baoCaoTongRepository.delete(baoCaoTong);
    }

    public void deleteAll() {
        baoCaoTongRepository.deleteAll();
    }

    public List<BaoCaoTong> getAll() {
        return (List<BaoCaoTong>) baoCaoTongRepository.findAll();
    }

    public List<BaoCaoTong> taoBaoCaoTongChoNgay(String maGV, int thang, int ngay) {
        try {
            // Tìm các bản ghi ChamCong cho giáo viên, tháng, ngày
            List<ChamCong> chamCongList = chamCongRepository.findByNgayChamCong(maGV, thang, ngay);
            List<BaoCaoTong> baoCaoTongs = baoCaoTongRepository.findByNgayBaoCao(maGV, thang, ngay);

            if (!chamCongList.isEmpty() && baoCaoTongs.isEmpty()) { // Kiểm tra báo cáo đã có hay chưa
                // Cập nhật trạng thái cho các bản ghi ChamCong
                chamCongService.capNhatTrangThaiChamCong(chamCongList);

                // Tạo báo cáo tổng
                BaoCaoTong baoCaoTong = new BaoCaoTong();
                baoCaoTong.setMaGV(maGV);
                baoCaoTong.setThang(thang);
                baoCaoTong.setNgay(ngay);
                baoCaoTong.setNam(LocalDate.now().getYear());
                baoCaoTong.setDanhGia("Chờ duyệt");

                // Đếm số tiết dạy, nghỉ, đi muộn, tan sớm
                int soTietDay = 0;
                int soTietNghi = 0;
                int soTietDiMuon = 0;
                int soTietTanSom = 0;

                // Lặp qua các bản ghi ChamCong để tính số tiết
                for (ChamCong chamCong : chamCongList) {
                    switch (chamCong.getTrangThai()) {
                        case "Nghỉ":
                            soTietNghi++;
                            break;
                        case "Đi muộn":
                            soTietDiMuon++;
                            break;
                        case "Tan sớm":
                            soTietTanSom++;
                            break;
                        case "Đúng giờ":
                            soTietDay++;
                            break;
                    }
                }

                // Cập nhật thông tin báo cáo
                baoCaoTong.setSoTietDay(soTietDay);
                baoCaoTong.setSoTietNghi(soTietNghi);
                baoCaoTong.setSoTietDiMuon(soTietDiMuon);
                baoCaoTong.setSoTietTanSom(soTietTanSom);

                // Lưu báo cáo vào cơ sở dữ liệu
                baoCaoTongRepository.save(baoCaoTong);
                List<BaoCaoTong> baoCaoTongList = new ArrayList<>();
                baoCaoTongList.add(baoCaoTong);
                return baoCaoTongList;
            } else {
                System.out.println("Không có bảng chấm công hoặc báo cáo đã tồn tại");
            }
        } catch (Exception e) {
            System.out.println("Lỗi khi tạo báo cáo tổng cho giáo viên " + maGV + " vào ngày: " + ngay + "/" + thang);
        }
        List<BaoCaoTong> baoCaoTongs = baoCaoTongRepository.findByNgayBaoCao(maGV, thang, ngay);
        return baoCaoTongs;
    }


    public List<BaoCaoTong> taoBaoCaoTongChoNgay(int thang, int ngay) {
        List<BaoCaoTong> baoCaoTongList = new ArrayList<>();
        try {
            // Tìm tất cả các bản ghi ChamCong cho tháng và ngày
            List<ChamCong> chamCongList = chamCongRepository.findByNgayChamCong(thang, ngay);

            if (!chamCongList.isEmpty()) {
                // Gọi phương thức taoBaoCaoTongChoNgay cho từng giáo viên
                for (ChamCong chamCong : chamCongList) {
                    taoBaoCaoTongChoNgay(chamCong.getMaGV(), thang, ngay); // Tạo báo cáo cho từng giáo viên
                }

                // Lấy danh sách báo cáo tổng sau khi đã tạo
                baoCaoTongList = baoCaoTongRepository.findByNgayBaoCao(thang, ngay);
            } else {
                System.out.println("Không có bảng chấm công cho ngày: " + ngay + "/" + thang);
            }
        } catch (Exception e) {
            System.out.println("Lỗi khi tạo báo cáo tổng cho tất cả giáo viên vào ngày: " + ngay + "/" + thang);
        }
        return baoCaoTongList;
    }


    public void taoBaoCaoTongChoThang(String maGV,int thang) {
        try {
            List<ChamCong> chamCongList = chamCongRepository.findByThangChamCong(maGV,thang);

            chamCongService.capNhatTrangThaiChamCong(chamCongList);

            for (int ngay = 1; ngay <= LocalDate.now().lengthOfMonth(); ngay++) {
                taoBaoCaoTongChoNgay(maGV,thang, ngay);
            }

            System.out.println("Đã tạo báo cáo tổng cho tháng: " + thang);
        } catch (Exception e) {
            System.out.println("Lỗi khi tạo báo cáo tổng cho tháng: " + thang);
        }
    }
}

