package org.example.quanlygiaovien.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "lichday")
public class LichDay {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "maGV", length = 10)
    private String maGV;
    @Column(name = "ngay")
    private LocalDate ngay;
    @Column(name = "thu")
    private int thu;
    @Column(name = "tiet")
    private int tiet;
    @Column(name = "tenLop", length = 10)
    private String tenLop;
    @Column(name = "tenMon", length = 10)
    private String tenMon;  // Đổi từ maLop thành tenMon để đúng với cột trong DB
}
