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
@Table(name = "lophoc")
public class LopHoc {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "maLop", length = 10)
    private String maLop;
    @PostPersist
    public void onCreate(){this.maLop ="LH" + String.format("%04d",id);}
    @Column(name = "tenLop", length = 50)
    private String tenLop;
    @Column(name = "khoiLop")
    private int khoiLop;
    @Column(name = "tenMon", length = 20)
    private String tenMon;
    @Column(name = "tenGV")
    private String tenGV;

}
