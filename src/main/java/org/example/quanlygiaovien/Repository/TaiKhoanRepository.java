package org.example.quanlygiaovien.Repository;

import org.example.quanlygiaovien.Entity.TaiKhoan;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface TaiKhoanRepository  extends CrudRepository<TaiKhoan, Integer> {

    @Query(value = "select taikhoan from TaiKhoan taikhoan where taikhoan.tenDangNhap=:tenDangNhap and taikhoan.matKhau=:matKhau")
    TaiKhoan verifyAccount(@Param("tenDangNhap") String tenDangNhap, @Param("matKhau") String matKhau);
}
