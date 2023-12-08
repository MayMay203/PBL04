package com.example.pbl04.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "anhtongket")
public class Anhtongket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDAnhTK", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "maTongKet", nullable = false)
    private Tongket maTongKet;

    @Column(name = "anh", length = 50)
    private String anh;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Tongket getMaTongKet() {
        return maTongKet;
    }

    public void setMaTongKet(Tongket maTongKet) {
        this.maTongKet = maTongKet;
    }

    public String getAnh() {
        return anh;
    }

    public void setAnh(String anh) {
        this.anh = anh;
    }

}