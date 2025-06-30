package org.example.quanlygiaovien.Entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "chamcong")
public class ChamCong {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    @Column(name = "maCC")
    private String maCC;
    @PostPersist
    protected void onCreate() {
        this.maCC = "CC" + String.format("%04d", id);
    }
    @Column(name = "thangChamCong")
    private int thangChamCong;
    @Column(name ="ngayChamCong")
    private int ngayChamCong;
    @Column(name = "thuChamCong")
    private int thuChamCong;
    @Column(name = "hoTen")
    private String hoTen;
    @Column(name = "maGV")
    private String maGV;
    @Column(name = "lop")
    private String lop;
    @Column(name = "tiet")
    private int tiet;
    @Column(name ="timeIn")
    private LocalTime timeIn;
    @Column(name = "timeOut")
    private LocalTime timeOut;
    @Column(name = "trangThai")
    private String trangThai;
}
