package com.example.pbl04.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "dexuat")
public class Dexuat {
    @Id
    @Column(name = "maDX", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "maTK", nullable = false)
    private Taikhoan maTK;

    @Lob
    @Column(name = "viTri", nullable = false)
    private String viTri;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "maChuDe", nullable = false)
    private Chude maChuDe;

    @Lob
    @Column(name = "moTa", nullable = false)
    private String moTa;

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

    public String getViTri() {
        return viTri;
    }

    public void setViTri(String viTri) {
        this.viTri = viTri;
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

}