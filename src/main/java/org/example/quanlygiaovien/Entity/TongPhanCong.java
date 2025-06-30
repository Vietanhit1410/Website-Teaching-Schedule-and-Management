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
@Table(name = "tongphancong")
public class TongPhanCong {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "thang")
    private int thang;
    @Column(name ="ngay")
    private int ngay;
    @Column(name = "thu")
    private int thu;
    @Column(name = "tiet")
    private int tiet;
    @Column(name = "maGV", length = 10)
    private String maGiaoVien;
    @Column(name = "tenGV" , length = 100)
    private String tenGV;
    @Column(name = "maMon" , length = 10)
    private String maMon;
    @Column(name = "tenMon" , length = 100)
    private String tenMon;
    @Column(name = "maLop" , length = 10)
    private String maLop;
    @Column(name = "tenLop")
    private String tenLop;
    @Override
    public String toString() {
        return "TongPhanCong{" +
                "id=" + id +
                ", thang=" + thang +
                ", ngay=" + ngay +
                ", thu=" + thu +
                ", tiet=" + tiet +
                ", maGiaoVien='" + maGiaoVien + '\'' +
                ", tenGV='" + tenGV + '\'' +
                ", maMon='" + maMon + '\'' +
                ", tenMon='" + tenMon + '\'' +
                ", maLop='" + maLop + '\'' +
                ", tenLop='" + tenLop + '\'' +
                '}';
    }
}
