package com.example.pbl04.entity;

import com.example.pbl04.entity.Taikhoan;
import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "thongbao")
public class Thongbao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "maTK", nullable = false)
    private Taikhoan maTK;

    @Lob
    @Column(name = "noiDung", nullable = false)
    private String noiDung;

    @Column(name = "thoiGianTB", nullable = false)
    private Instant thoiGianTB = Instant.now();

    @Column(name = "loaiTB", nullable = false)
    private Integer loaiTB;

    @Column(name = "ma", nullable = false)
    private Integer ma;

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

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public Instant getThoiGianTB() {
        return thoiGianTB;
    }

    public void setThoiGianTB(Instant thoiGianTB) {
        this.thoiGianTB = thoiGianTB;
    }

    public Integer getLoaiTB() {
        return loaiTB;
    }

    public void setLoaiTB(Integer loaiTB) {
        this.loaiTB = loaiTB;
    }

    public Integer getMa() {
        return ma;
    }

    public void setMa(Integer ma) {
        this.ma = ma;
    }

    public Boolean getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Boolean trangThai) {
        this.trangThai = trangThai;
    }

}