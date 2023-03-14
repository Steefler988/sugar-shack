package com.maplr.sugarshack.repository;

import com.maplr.sugarshack.entity.MapleSyrup;
import com.maplr.sugarshack.entity.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderLineRepository extends JpaRepository<OrderLine, String> {
}
