package com.example.pbl04.dao;

import com.example.pbl04.entity.Taikhoan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Taikhoan,Integer>{
//   public Taikhoan getTKByID(Integer ID);
}
