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
@Table(name = "monhoc")
public class MonHoc {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "maMon", length = 10)
    private String maMon;
    @PostPersist
    protected void onCreate() {this.maMon = "MH" + String.format("%04d", id);}
    @Column(name = "tenMon", length = 100)
    private String tenMon;
}
