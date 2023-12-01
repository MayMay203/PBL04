package com.example.pbl04.service;
import jakarta.persistence.*;

@Entity
@Table(name = "anhtongket")
public class Anhtongket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDAnhTK", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "maTongKet", nullable = false)
    private Tongket maTongKet;

    @Column(name = "anh", nullable = false)
    private byte[] anh;

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

    public byte[] getAnh() {
        return anh;
    }

    public void setAnh(byte[] anh) {
        this.anh = anh;
    }

}