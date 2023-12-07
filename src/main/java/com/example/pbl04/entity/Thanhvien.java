package com.example.pbl04.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "thanhvien")
public class Thanhvien {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdTV", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "maTK", nullable = false)
    private Taikhoan maTK;

    @Lob
    @Column(name = "hoTen", nullable = false)
    private String hoTen;

    @Column(name = "ngaySinh", nullable = false)
    private LocalDate ngaySinh;

    @Column(name = "gioiTinh", nullable = false)
    private Byte gioiTinh;

    @Lob
    @Column(name = "diaChi", nullable = false)
    private String diaChi;

    @Column(name = "soDienThoai", nullable = false, length = 10)
    private String soDienThoai;

    @Column(name = "email", nullable = false, length = 50)
    private String email;

    @Column(name = "anh", length = 50)
    private String anh;

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

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public LocalDate getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(LocalDate ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public Byte getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(Byte gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAnh() {
        return anh;
    }

    public void setAnh(String anh) {
        this.anh = anh;
    }

}