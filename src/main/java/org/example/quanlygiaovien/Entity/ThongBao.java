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
@Table(name = "thongbao")
public class ThongBao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "maTB")
    private String maTB;
    @PostPersist
    protected void onCreate() {this.maTB ="TB"+String.format("04%d",id);}
    @Column(name = "maGV")
    private String maGV;
    @Column(name = "title")
    private String title;
    @Column(name = "content")
    private String content;

}
