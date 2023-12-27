package com.example.pbl04.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "hoatdong")
public class Hoatdong {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "maHD", nullable = false)
    private Integer id;

    @Lob
    @Column(name = "tenhd", nullable = false)
    private String tenhd;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "maChuDe", nullable = false)
    private Chude maChuDe;

    @Lob
    @Column(name = "moTa", nullable = false)
    private String moTa;

    @Lob
    @Column(name = "diaDiem", nullable = false)
    private String diaDiem;

    @Column(name = "soTNVToiThieu", nullable = false)
    private Integer soTNVToiThieu;

    @Column(name = "soTNVToiDa", nullable = false)
    private Integer soTNVToiDa;

    @Column(name = "thoiGianBD", nullable = false)
    private LocalDate thoiGianBD;

    @Column(name = "thoiGianKT", nullable = false)
    private LocalDate thoiGianKT;

    @Column(name = "tinhTrangHD", nullable = false)
    private Byte tinhTrangHD;

    @Column(name = "tinhTrangDuyet", nullable = false)
    private Byte tinhTrangDuyet;

    @Lob
    @Column(name = "liDoHuy")
    private String liDoHuy;

    @Column(name = "anh", length = 50)
    private String anh;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTenhd() {
        return tenhd;
    }

    public void setTenhd(String tenhd) {
        this.tenhd = tenhd;
    }

    public Chude getMaChuDe() {
        return maChuDe;
    }

    public void setMaChuDe(Chude maChuDe) {
        this.maChuDe = maChuDe;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getDiaDiem() {
        return diaDiem;
    }

    public void setDiaDiem(String diaDiem) {
        this.diaDiem = diaDiem;
    }

    public Integer getSoTNVToiThieu() {
        return soTNVToiThieu;
    }

    public void setSoTNVToiThieu(Integer soTNVToiThieu) {
        this.soTNVToiThieu = soTNVToiThieu;
    }

    public Integer getSoTNVToiDa() {
        return soTNVToiDa;
    }

    public void setSoTNVToiDa(Integer soTNVToiDa) {
        this.soTNVToiDa = soTNVToiDa;
    }

    public LocalDate getThoiGianBD() {
        return thoiGianBD;
    }

    public void setThoiGianBD(LocalDate thoiGianBD) {
        this.thoiGianBD = thoiGianBD;
    }

    public LocalDate getThoiGianKT() {
        return thoiGianKT;
    }

    public void setThoiGianKT(LocalDate thoiGianKT) {
        this.thoiGianKT = thoiGianKT;
    }

    public Byte getTinhTrangHD() {
        return tinhTrangHD;
    }

    public void setTinhTrangHD(Byte tinhTrangHD) {
        this.tinhTrangHD = tinhTrangHD;
    }

    public Byte getTinhTrangDuyet() {
        return tinhTrangDuyet;
    }

    public void setTinhTrangDuyet(Byte tinhTrangDuyet) {
        this.tinhTrangDuyet = tinhTrangDuyet;
    }

    public String getLiDoHuy() {
        return liDoHuy;
    }

    public void setLiDoHuy(String liDoHuy) {
        this.liDoHuy = liDoHuy;
    }

    public String getAnh() {
        return anh;
    }

    public void setAnh(String anh) {
        this.anh = anh;
    }

}