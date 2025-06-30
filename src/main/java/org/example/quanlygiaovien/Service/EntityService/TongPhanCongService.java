package org.example.quanlygiaovien.Service.EntityService;

import org.example.quanlygiaovien.Entity.*;
import org.example.quanlygiaovien.Repository.LopHocRepository;
import org.example.quanlygiaovien.Repository.MonHocRepository;
import org.example.quanlygiaovien.Repository.ThongTinGiaoVienRepository;
import org.example.quanlygiaovien.Repository.TongPhanCongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;


@Service
public class TongPhanCongService {

    @Autowired
     TongPhanCongRepository tongPhanCongRepository;
    @Autowired
     ThongTinGiaoVienRepository thongTinGiaoVienRepository;
    @Autowired
    LopHocRepository lopHocRepository;
    @Autowired
    MonHocRepository monHocRepository;

    // Tạo mới hoặc cập nhật phân công
    public TongPhanCong createOrUpdate(TongPhanCong tongPhanCong) {
       TongPhanCong tongPhanCong1 = tongPhanCongRepository.save(tongPhanCong);
       return tongPhanCong1;
    }

    public void deleteById(int id) {
        if (tongPhanCongRepository.existsById(id)) {
            tongPhanCongRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Không tìm thấy đối tượng với id: " + id);
        }
    }
    // Xóa phân công
    public void delete(TongPhanCong tongPhanCong) {
        tongPhanCongRepository.delete(tongPhanCong);
    }

    // Xóa tất cả phân công
    public void deleteAll() {
        tongPhanCongRepository.deleteAll();
    }

    // Lấy tất cả phân công
    public List<TongPhanCong> getAll() {
        return (List<TongPhanCong>) tongPhanCongRepository.findAll();
    }

    // Kiểm tra phân công có tồn tại không
    public Boolean createSchedule() {
        int day = LocalDate.now().getDayOfMonth();
        int month = LocalDate.now().getMonthValue();
        if(!tongPhanCongRepository.existsPhanCong(month, day)){
            autoXepLich();
            return true;
        }
        return false;
    }

    public void autoXepLich() {
        // Lấy danh sách tất cả giáo viên, lớp học và môn học
        List<ThongTinGiaoVien> allTeacher = (List<ThongTinGiaoVien>) thongTinGiaoVienRepository.findAll();
        List<LopHoc> allClass = lopHocRepository.findByTenMon("Toan");  // Lấy tất cả các lớp học
        List<MonHoc> allSubject = (List<MonHoc>) monHocRepository.findAll();

        // Khởi tạo lịch trống cho các lớp
        List<TongPhanCong> allSchedules = new ArrayList<>();

        // Khởi tạo bản đồ lưu số tiết dạy của giáo viên
        Map<String, Integer> teacherTeachingHours = new HashMap<>(); // key: maGV, value: tổng số tiết dạy trong tuần

        // Phân nhóm giáo viên theo môn học
        Map<String, List<ThongTinGiaoVien>> teacherBySubject = new HashMap<>();
        for (ThongTinGiaoVien teacher : allTeacher) {
            teacherBySubject
                    .computeIfAbsent(teacher.getChuyenMon(), k -> new ArrayList<>())
                    .add(teacher);
            // Khởi tạo số tiết dạy cho mỗi giáo viên
            teacherTeachingHours.put(teacher.getMaGV(), 0);
        }

        // Lưu các lịch học đã được phân công
        Set<String> teacherScheduleSet = new HashSet<>(); // Lưu các lịch đã xếp của giáo viên (Mã giáo viên + Ngày + Tiết học)
        Map<String, Map<Integer, Map<Integer, String>>> classSchedules = new HashMap<>(); // Giữ lịch của lớp học

        try {
            Calendar calendar = Calendar.getInstance();
            // Tính toán ngày đầu tuần (thứ 2) trong tuần hiện tại
            calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY); // Đặt ngày là thứ 2 của tuần hiện tại
            int startOfWeek = calendar.get(Calendar.DAY_OF_MONTH); // Lấy ngày trong tháng của thứ 2

            // Xếp lịch cho từng lớp học
            for (LopHoc lop : allClass) {
                // Mỗi lớp học cần đủ 30 tiết trong tuần
                int totalPeriodsForClass = 30;

                // Lặp qua từng ngày trong tuần (thứ 2 - thứ 7)
                for (int dayOfWeek = 1; dayOfWeek <= 6; dayOfWeek++) { // Từ Thứ 2 đến Thứ 7
                    for (int period = 1; period <= 5; period++) { // Mỗi ngày có 5 tiết học
                        // Lặp qua các môn học (10 môn học cho mỗi lớp)
                        for (MonHoc subject : allSubject) {
                            String subjectCode = subject.getTenMon(); // Mã môn học (M1, M2, ..., M10)

                            // Kiểm tra xem có giáo viên dạy môn này không
                            List<ThongTinGiaoVien> teachersForSubject = teacherBySubject.get(subjectCode);

                            if (teachersForSubject == null || teachersForSubject.isEmpty()) {
                                continue; // Nếu không có giáo viên, tiếp tục với môn học tiếp theo
                            }

                            // Lấy giáo viên dạy môn này
                            for (ThongTinGiaoVien teacher : teachersForSubject) {
                                String teacherCode = teacher.getMaGV();

                                // Kiểm tra số tiết dạy của giáo viên, không vượt quá số tiết tối đa cho môn học
                                int currentTeachingHours = teacherTeachingHours.get(teacherCode);
                                if (currentTeachingHours >= 3) {
                                    // Nếu giáo viên đã dạy đủ số tiết cho môn này, chuyển sang giáo viên khác
                                    continue;
                                }

                                // Kiểm tra xem giáo viên có bị trùng lịch không
                                String teacherScheduleKey = teacherCode + "-" + dayOfWeek + "-" + period;
                                if (teacherScheduleSet.contains(teacherScheduleKey)) {
                                    // Nếu trùng lịch, bỏ qua và kiểm tra giáo viên khác
                                    continue;
                                }

                                // Kiểm tra xem lớp đã có giáo viên nào dạy trong tiết này chưa
                                Map<Integer, String> periodTeachers = classSchedules
                                        .computeIfAbsent(lop.getMaLop(), k -> new HashMap<>())
                                        .computeIfAbsent(dayOfWeek, k -> new HashMap<>());

                                if (periodTeachers.containsKey(period)) {
                                    // Nếu tiết này đã có giáo viên dạy, bỏ qua
                                    continue;
                                }

                                // Tạo một lịch học mới cho lớp
                                TongPhanCong schedule = new TongPhanCong();
                                schedule.setThang(calendar.get(Calendar.MONTH) + 1); // Lấy tháng hiện tại
                                schedule.setNgay(startOfWeek + dayOfWeek - 1); // Tính ngày trong tháng (bắt đầu từ thứ 2)
                                schedule.setThu(dayOfWeek+1); // Ngày trong tuần (1 - 6)
                                schedule.setTiet(period); // Tiết học
                                schedule.setMaGiaoVien(teacher.getMaGV());
                                schedule.setTenGV(teacher.getTenGV());
                                schedule.setMaMon(subject.getMaMon());
                                schedule.setTenMon(subject.getTenMon());
                                schedule.setMaLop(lop.getMaLop());
                                schedule.setTenLop(lop.getTenLop());

                                // Thêm lịch vào danh sách
                                allSchedules.add(schedule);

                                // Cập nhật số tiết dạy của giáo viên
                                teacherTeachingHours.put(teacherCode, currentTeachingHours + 1);

                                // Thêm lịch của giáo viên vào danh sách để kiểm tra trùng lịch
                                teacherScheduleSet.add(teacherScheduleKey);

                                // Lưu lịch của lớp vào bản đồ (ngày - tiết - giáo viên)
                                periodTeachers.put(period, teacherCode);

                                // Đã phân công giáo viên thành công cho tiết này, chuyển sang giáo viên tiếp theo
                                break;
                            }
                        }
                    }
                }
            }

        } catch (Exception e) {
            System.out.println("Lỗi trong quá trình xếp lịch tự động.");
            e.printStackTrace();
        }

        // Trả về danh sách lịch đã phân công
        tongPhanCongRepository.saveAll(allSchedules);
    }





































    public List<TongPhanCong> xepLichTuDong(List<ThongTinGiaoVien> danhSachGiaoVien, List<LopHoc> danhSachLop, int kyHoc) {
        List<TongPhanCong> lichPhanCong = new ArrayList<>();
        Map<String, Set<String>> TongPhanCongGiaoVien = new HashMap<>(); // Lịch dạy của giáo viên (Giáo viên -> "YYYY-MM-DD-Tiết")

        // 1. Kiểm tra kỳ học hợp lệ
        if (kyHoc < 1 || kyHoc > 2) {
            throw new IllegalArgumentException("Kỳ học phải là 1 hoặc 2");
        }

        // 2. Xác định tháng bắt đầu và kết thúc của kỳ
        int thangBatDau = (kyHoc == 1) ? 1 : 7;
        int thangKetThuc = (kyHoc == 1) ? 6 : 12;
        int[] daysInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int namHienTai = 2024;

        // Điều chỉnh cho năm nhuận
        if ((namHienTai % 4 == 0 && namHienTai % 100 != 0) || (namHienTai % 400 == 0)) {
            daysInMonth[1] = 29;
        }

        // 3. Khởi tạo lịch trống cho giáo viên
        for (ThongTinGiaoVien gv : danhSachGiaoVien) {
            TongPhanCongGiaoVien.put(gv.getMaGV(), new HashSet<>());
        }

        // 4. Xếp lịch dạy
        for (ThongTinGiaoVien gv : danhSachGiaoVien) {
            // Xác định lịch dạy cho từng ngày trong tuần (từ Thứ 2 đến Thứ 7)
            for (int ngayTrongTuan = 1; ngayTrongTuan <= 6; ngayTrongTuan++) { // Thứ 2 đến Thứ 7
                // Xác định số tiết dạy ngẫu nhiên trong khoảng 2 đến 5
                int soTietTrongNgay = (int) (Math.random() * 4) + 2; // Random từ 2 đến 5 tiết

                // Duyệt qua số tiết và phân công cho từng tiết trong ngày
                for (int tiet = 1; tiet <= soTietTrongNgay; tiet++) {
                    // Tìm một lớp phù hợp cho giáo viên
                    for (LopHoc lop : danhSachLop) {
                        // Kiểm tra xem giáo viên đã có lịch dạy trong thời gian này chưa
                        String thoiGian = String.format("%d-%02d-%02d-%d", namHienTai, thangBatDau, ngayTrongTuan, tiet);

                        Set<String> lichGiaoVien = TongPhanCongGiaoVien.get(gv.getMaGV());
                        if (lichGiaoVien.contains(thoiGian)) {
                            continue; // Giáo viên đã có lịch dạy ở thời gian này
                        }

                        // Phân công lịch dạy
                        TongPhanCong phanCong = new TongPhanCong();
                        phanCong.setThang(thangBatDau);
                        phanCong.setNgay(ngayTrongTuan);
                        phanCong.setThu(ngayTrongTuan); // Quy đổi ngày sang thứ trong tuần
                        phanCong.setTiet(tiet);
                        phanCong.setMaGiaoVien(gv.getMaGV());
                        phanCong.setTenGV(gv.getTenGV());

                        // Dữ liệu lớp học
                        phanCong.setTenMon(lop.getTenMon());
                        phanCong.setMaLop(lop.getMaLop());
                        phanCong.setTenLop(lop.getTenLop());

                        // Thêm vào danh sách lịch phân công
                        lichPhanCong.add(phanCong);
                        lichGiaoVien.add(thoiGian);
                        break; // Sau khi phân công xong, thoát vòng lặp lớp
                    }
                }
            }
        }

        // 5. Đảm bảo giáo viên dạy đủ tiết trong tuần
//        for (ThongTinGiaoVien gv : danhSachGiaoVien) {
//            Set<String> lich = TongPhanCongGiaoVien.get(gv.getMaGV());
//            for (int ngayTrongTuan = 1; ngayTrongTuan < 7; ngayTrongTuan++) { // Kiểm tra từng ngày trong tuần
//                boolean coTiet = lich.stream().anyMatch(thoiGian -> thoiGian.contains(String.format("-%02d-", ngayTrongTuan)));
//                if (!coTiet) {
//                    System.out.println("Giáo viên " + gv.getHoTen() + " không được phân công đủ các tiết trong tuần");
//                }
//            }
//        }

        return lichPhanCong;
    }




    public List<TongPhanCong> findByMaGiaoVien(String maGiaoVien) {
        return tongPhanCongRepository.findByMaGiaoVien(maGiaoVien);
    }





}
