package org.example.quanlygiaovien.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "taikhoan")
    public class TaiKhoan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "tenDangNhap")
    private String tenDangNhap;
    @Column(name = "matKhau")
    private String matKhau;
    @Column(name = "quyenHan")
    private String quyenHan;


}
