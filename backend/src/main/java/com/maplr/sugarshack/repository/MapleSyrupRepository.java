package com.maplr.sugarshack.repository;

import com.maplr.sugarshack.entity.MapleSyrup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MapleSyrupRepository extends JpaRepository<MapleSyrup, String> {
}
