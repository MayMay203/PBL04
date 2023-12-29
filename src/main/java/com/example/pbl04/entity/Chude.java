package com.example.pbl04.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "chude")
public class Chude {
    @Id
    @Column(name = "maChuDe", nullable = false)
    private Integer id;

    @Lob
    @Column(name = "tenChuDe", nullable = false)
    private String tenChuDe;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTenChuDe() {
        return tenChuDe;
    }

    public void setTenChuDe(String tenChuDe) {
        this.tenChuDe = tenChuDe;
    }

}