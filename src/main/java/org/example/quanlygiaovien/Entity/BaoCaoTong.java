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
@Table(name = "baocaotong")
public class BaoCaoTong {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "maBC" , length = 10)
    private String maBC;
    @PostPersist
    protected void onCreate() {this.maBC ="BC" + String.format("%04d",id);}
    @Column(name = "maGV" , length = 10)
    private String maGV;
    @Column(name = "thang")
    private int thang;
    @Column(name = "nam")
    private int nam;
    @Column(name = "ngay")
    private int ngay;
    @Column(name = "soTietDay")
    private int soTietDay;
    @Column(name = "soTietNghi")
    private int soTietNghi;
    @Column(name = "soTietDiMuon")
    private int soTietDiMuon;
    @Column(name = "soTietTanSom")
    private int soTietTanSom;
    @Column(name = "danhGia")
    private String danhGia;


}
