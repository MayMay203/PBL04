package com.example.pbl04.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tongket")
public class Tongket {
    @Id
    @Column(name = "maTongKet", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "maHD", nullable = false)
    private Hoatdong maHD;

    @Lob
    @Column(name = "bangTongKet", nullable = false)
    private String bangTongKet;

    @Lob
    @Column(name = "loiKet", nullable = false)
    private String loiKet;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Hoatdong getMaHD() {
        return maHD;
    }

    public void setMaHD(Hoatdong maHD) {
        this.maHD = maHD;
    }

    public String getBangTongKet() {
        return bangTongKet;
    }

    public void setBangTongKet(String bangTongKet) {
        this.bangTongKet = bangTongKet;
    }

    public String getLoiKet() {
        return loiKet;
    }

    public void setLoiKet(String loiKet) {
        this.loiKet = loiKet;
    }

}