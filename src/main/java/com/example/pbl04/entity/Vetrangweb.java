package com.example.pbl04.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "vetrangweb")
public class Vetrangweb {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Ma", nullable = false)
    private Integer id;

    @Lob
    @Column(name = "TieuDe", nullable = false)
    private String tieuDe;

    @Lob
    @Column(name = "NoiDung", nullable = false)
    private String noiDung;

    @Column(name = "Anh", nullable = false)
    private byte[] anh;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTieuDe() {
        return tieuDe;
    }

    public void setTieuDe(String tieuDe) {
        this.tieuDe = tieuDe;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public byte[] getAnh() {
        return anh;
    }

    public void setAnh(byte[] anh) {
        this.anh = anh;
    }

}