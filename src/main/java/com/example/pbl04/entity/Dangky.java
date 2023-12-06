package com.example.pbl04.entity;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "dangky")
public class Dangky {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDDangKy", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER,optional = false)
    @JoinColumn(name = "maTK", nullable = false)
    private Taikhoan maTK;

    @ManyToOne(fetch = FetchType.EAGER,optional = false)
    @JoinColumn(name = "maHD", nullable = false)
    private Hoatdong maHD;

    @Column(name = "thoiGianDK", nullable = false)
    private Instant thoiGianDK;

    @Column(name = "phanQuyen", nullable = false)
    private Boolean phanQuyen = false;

    @Column(name = "trangThai", nullable = false)
    private Boolean trangThai = false;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Taikhoan getMaTK() {
        return maTK;
    }

    public void setMaTK(Taikhoan maTK) {
        this.maTK = maTK;
    }

    public Hoatdong getMaHD() {
        return maHD;
    }

    public void setMaHD(Hoatdong maHD) {
        this.maHD = maHD;
    }

    public Instant getThoiGianDK() {
        return thoiGianDK;
    }

    public void setThoiGianDK(Instant thoiGianDK) {
        this.thoiGianDK = thoiGianDK;
    }

    public Boolean getPhanQuyen() {
        return phanQuyen;
    }

    public void setPhanQuyen(Boolean phanQuyen) {
        this.phanQuyen = phanQuyen;
    }

    public Boolean getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Boolean trangThai) {
        this.trangThai = trangThai;
    }

}