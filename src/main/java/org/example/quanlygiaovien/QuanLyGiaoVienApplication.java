

package org.example.quanlygiaovien;

import org.example.quanlygiaovien.Controller.TaiKhoanController;
import org.example.quanlygiaovien.Entity.*;
import org.example.quanlygiaovien.Repository.TongPhanCongRepository;
import org.example.quanlygiaovien.Service.EntityService.ChamCongService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.*;

        @SpringBootApplication
        public class QuanLyGiaoVienApplication {

            private static TongPhanCongRepository tongPhanCongRepository;

            public static void main(String[] args) {
                ApplicationContext context = SpringApplication.run(QuanLyGiaoVienApplication.class, args);
                TaiKhoanController taiKhoanController = context.getBean(TaiKhoanController.class);
//                List<TaiKhoan> listTK = taiKhoanController.layDanhSach();
                TaiKhoan tk = new TaiKhoan();
                tk.setMatKhau("123");
                tk.setTenDangNhap("G");



//        TaiKhoan taiKhoan = new TaiKhoan();
//            taiKhoan.setTenDangNhap("admin");
//            taiKhoan.setMatKhau("admin");
//            taiKhoan.setQuyenHan("ROLE_ADMIN");
//        TaiKhoanService taiKhoanService = context.getBean(TaiKhoanService.class);
//        taiKhoanService.create_update(taiKhoan);


//        MonHocService monHocService = context.getBean(MonHocService.class);
//                LopHocService lopHocService = context.getBean(LopHocService.class);
//                ThongTinGiaoVienService thongTinGiaoVienService = context.getBean(ThongTinGiaoVienService.class);
//                TongPhanCongService tongPhanCongService = context.getBean(TongPhanCongService.class);
//
//
//
//                thongTinGiaoVienService.deleteAll();
//
//
//                List<String> ho = List.of("Nguyễn", "Trần", "Lê", "Phạm", "Hoàng", "Võ", "Đỗ", "Bùi", "Đặng", "Trịnh");
//                List<String> tenDem = List.of("Văn", "Thị", "Công", "Ngọc", "Đình", "Quốc", "Minh", "Gia", "Kim", "Thanh");
//                List<String> ten = List.of("Anh", "Bình", "Chung", "Dũng", "Hạnh", "Hoàng", "Khoa", "Lan", "Linh", "Tuấn");
//                List<String> monHoc = List.of("Toan", "Anh", "Van", "Sinh", "Su", "Dia", "GDCD", "TheChat", "CongNghe", "TinHoc");
//
//
//                Set<String> hoTenDaTao = new HashSet<>();
//                List<ThongTinGiaoVien> danhSachGiaoVien = new ArrayList<>();
//                Random random = new Random();
//
//// Danh sách một số tỉnh thành ở Việt Nam
//                List<String> diaChiList = List.of("Hà Nội", "TP. Hồ Chí Minh", "Đà Nẵng", "Cần Thơ", "Hải Phòng",
//                        "An Giang", "Bình Dương", "Bắc Giang", "Lâm Đồng", "Quảng Ninh");
//
//// Đảm bảo mỗi môn học có đúng 5 giáo viên
//                int soLuongMoiMon = 5;
//                for (int i = 0; i < monHoc.size(); i++) {
//                    for (int j = 0; j < soLuongMoiMon; j++) {
//                        String hoTen;
//                        do {
//                            hoTen = ho.get(random.nextInt(ho.size())) + " " +
//                                    tenDem.get(random.nextInt(tenDem.size())) + " " +
//                                    ten.get(random.nextInt(ten.size()));
//                        } while (hoTenDaTao.contains(hoTen)); // Đảm bảo tên không bị trùng
//                        hoTenDaTao.add(hoTen);
//
//                        ThongTinGiaoVien gv = new ThongTinGiaoVien();
//                        gv.setHoTen(hoTen);
//                        int year = 1980 + random.nextInt(20); // 1980 + random từ 0 đến 19
//
//                        // Tạo tháng ngẫu nhiên từ 1 đến 12
//                        int month = random.nextInt(12) + 1; // random từ 0 đến 11 + 1
//
//                        // Tạo ngày ngẫu nhiên từ 1 đến 28 (hoặc 31, tùy thuộc tháng)
//                        int day = random.nextInt(28) + 1; // random từ 0 đến 27 + 1
//
//                        // Tạo đối tượng LocalDate
//                        LocalDate ngaySinh = LocalDate.of(year, month, day);
//
//                        gv.setNgaySinh(ngaySinh);
//                        gv.setGioiTinh(random.nextBoolean() ? "Nam" : "Nữ");
//                        gv.setDiaChi(diaChiList.get(random.nextInt(diaChiList.size()))); // Chọn ngẫu nhiên tỉnh thành
//                        gv.setSoDienThoai("098" + (1000000 + random.nextInt(9000000)));
//                        gv.setEmail(hoTen.toLowerCase().replaceAll(" ", "") + "@example.com"); // Email không có dấu chấm ngăn cách
//                        gv.setChuyenMon(monHoc.get(i)); // Gán môn học tuần tự
//                        gv.setBangCap("Đại học");
//                        gv.setKinhNghiem(random.nextInt(11)); // 0-10 năm
//                        danhSachGiaoVien.add(gv);
//                    }
//                }
//
//// Thêm thông tin giáo viên vào cơ sở dữ liệu
//                for (ThongTinGiaoVien thongTinGiaoVien : danhSachGiaoVien) {
//                    thongTinGiaoVienService.create_update(thongTinGiaoVien);
//                }
//        List<TaiKhoan> listTaiKhoan = new ArrayList<>();
//        TaiKhoanService taiKhoanService = context.getBean(TaiKhoanService.class);
//        taiKhoanService.deleteAll();
//        for(ThongTinGiaoVien thongTinGiaoVien : thongTinGiaoVienService.getAll()){
//            TaiKhoan taiKhoan = new TaiKhoan();
//            taiKhoan.setTenDangNhap(thongTinGiaoVien.getMaGV());
//            taiKhoan.setMatKhau("123456");
//            taiKhoan.setQuyenHan("ROLE_USER");
//            listTaiKhoan.add(taiKhoan);
//        }
//        for(TaiKhoan taiKhoan : listTaiKhoan){
//            taiKhoanService.create_update(taiKhoan);
//        }

                // mấy cái kia là dữ ;iệu đầu vào nên tôi tu them thôi
                // tổng phân công thì dữa vào lớp ,giáo viên và môn
                // có 21 lớp(10v1-10v7,11v1-11v7,12v1-12v7)
                // 10 mon(trừ lí và hóa)
                // 50 giáo viên
                // 1 môn có 5 giáo viên dạy
//        the neu viet ham tu dong sap xếp giảng dạy thì ô làm dki giảng dạy ch
//                thế
//        // mình làm k phải là đi làm
//        // giả dụ cho giáo viên rảnh hết thôi
//        // update dần
//        // thowif gian có hạn
//        thế ô phải để tôi nhập dữ liệu tay vào chứ làm sao k dki mà mình đưa ra lich duoc
//                kiem tr
//                // thì tôi dã nói là rảnh hết
//// gi0./ cps nghĩa là làm lúc nào cũng được full time ấy
//        đợi 1h:30 p nhá đ
                // Tìm theo mã giáo viên


//        newPhanCong.setThang(11);
//        newPhanCong.setNgay(27);
//        newPhanCong.setThu(2);
//        newPhanCong.setTiet(3);\

//        newPhanCong.setMaGiaoVien("GV01");
//        newPhanCong.setTenGV("Nguyen Van A");
//        newPhanCong.setMaMon("MON01");
//        newPhanCong.setTenMon("Toan");
//        newPhanCong.setMaLop("L01");
//        newPhanCong.setTenLop("12A
//
//        1");
//
//        restTemplate.postForObject(BASE_URL + "/themVaSua", newPhanCong, Void.class);
//        System.out.println("Đã thêm mới một phân công.");


//                Object da = new Object();  // Replace this with actual data
//                List<ThongTinGiaoVien> thongTinGiaoVienList = thongTinGiaoVienService.getAll();
//                List<LopHoc> lopHocsList = lopHocService.getAll();
//                // Initialize the service
//                int kyhoc = 1;
//                int kyhoc2 = 2;
//                lopHocsList = lopHocService.getAll();
//                List<TongPhanCong> tongPhanCongList = tongPhanCongService.xepLichTuDong(thongTinGiaoVienList,lopHocsList,kyhoc);
//                List<TongPhanCong> tongPhanCongList2 = tongPhanCongService.xepLichTuDong(thongTinGiaoVienList,lopHocsList,kyhoc2);
//                // Kiểm tra nếu không có lịch phân công
//                if (tongPhanCongList == null || tongPhanCongList.isEmpty()) {
//                    System.out.println("Không có lịch phân công để hiển thị.");
//                    return;
//                }
//                List<TongPhanCong> aaa = new ArrayList<>();
//                for (TongPhanCong tongPhanCong : tongPhanCongList) {
//                    if (tongPhanCong.getMaGiaoVien().equalsIgnoreCase("GV0175")) {
//                        aaa.add(tongPhanCong);
//                    }
//                }
//                for (TongPhanCong tongPhanCong : tongPhanCongList2) {
//                    if (tongPhanCong.getMaGiaoVien().equalsIgnoreCase("GV0175")) {
//                        aaa.add(tongPhanCong);
//                    }
//                }
//
//                //         Hiển thị kết quả
////                drawTable(tongPhanCongList);
////                drawTable(tongPhanCongList2);
//                drawTable(aaa);
//
//            }
//

//            }
//                TongPhanCongService tongPhanCongService = context.getBean(TongPhanCongService.class);
//                drawTable(tongPhanCongService.autoXepLich());
//                ChamCongService chamCongService = context.getBean(ChamCongService.class);
//                TaiKhoan taiKhoan = new TaiKhoan();
//                taiKhoan.setTenDangNhap("GV0377");
//                taiKhoan.setMatKhau("123456");
//                chamCongService.chamCongTheoNgay(taiKhoan,"10V1");
            }
//            public static void drawTable(List<TongPhanCong> listPhanCong) {
//                // In header cho bảng lịch phân công
//                System.out.println("+-------+-------+-----+-----+------------+---------------------+------------+----------+-------+---------+");
//                System.out.println("| Thang | Ngay  | Thu | Tiet | MaGV       | HoTen               | MaMon    | TenMon | MaLop | TenLop |");
//                System.out.println("+-------+-------+-----+-----+------------+---------------------+------------+----------+-------+---------+");
//
//                // In từng dòng dữ liệu cho lịch phân công
//                for (TongPhanCong phanCong : listPhanCong) {
//                    System.out.printf("| %-5d | %-5d | %-3d | %-4d | %-10s | %-19s | %-8s | %-8s | %-5s | %-7s |\n",
//                            phanCong.getThang(),
//                            phanCong.getNgay(),
//                            phanCong.getThu(),
//                            phanCong.getTiet(),
//                            phanCong.getMaGiaoVien(),
//                            phanCong.getTenGV(),
//                            phanCong.getMaMon(),
//                            phanCong.getTenMon(),
//                            phanCong.getMaLop(),
//                            phanCong.getTenLop());
//                }
//                System.out.println("+-------+-------+-----+-----+------------+---------------------+------------+----------+-------+---------+");
//            }

        }


