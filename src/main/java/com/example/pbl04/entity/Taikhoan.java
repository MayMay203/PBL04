package com.example.pbl04.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "taikhoan")
public class Taikhoan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "maTK", nullable = false)
    private Integer id;

    @Column(name = "tenDN", nullable = false, length = 50)
    private String tenDN;

    @Column(name = "matKhau", nullable = false, length = 20)
    private String matKhau;

    @Column(name = "loaiTK", nullable = false)
    private Boolean loaiTK = false;

    @Column(name = "anhDaiDien", length = 50)
    private String anhDaiDien;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTenDN() {
        return tenDN;
    }

    public void setTenDN(String tenDN) {
        this.tenDN = tenDN;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public Boolean getLoaiTK() {
        return loaiTK;
    }

    public void setLoaiTK(Boolean loaiTK) {
        this.loaiTK = loaiTK;
    }

    public String getAnhDaiDien() {
        return anhDaiDien;
    }

    public void setAnhDaiDien(String anhDaiDien) {
        this.anhDaiDien = anhDaiDien;
    }

}