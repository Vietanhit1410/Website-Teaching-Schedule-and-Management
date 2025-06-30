package org.example.quanlygiaovien.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "giaovien")
public class ThongTinGiaoVien {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "maGV" , length = 10)
    private String maGV;
    @PostPersist
    protected void onCreate() {
        this.maGV = "GV" + String.format("%04d", id);
    }
    @Column(name = "tenGV" , length = 100)
    private String tenGV;
    @Column(name = "ngaySinh")
    private LocalDate ngaySinh;
    @Column(name = "gioiTinh" , length = 10)
    private String gioiTinh;
    @Column(name = "diaChi" , length = 200)
    private String diaChi;
    @Column(name = "soDienThoai" , length = 15)
    private String soDienThoai;
    @Column(name = "email" , length = 100)
    private String email;
    @Column(name = "chuyenMon")

    private String chuyenMon;
    @Column(name = "bangCap" , length = 100)
    private String bangCap;
    @Column(name = "kinhNghiem")
    private int kinhNghiem;
}
