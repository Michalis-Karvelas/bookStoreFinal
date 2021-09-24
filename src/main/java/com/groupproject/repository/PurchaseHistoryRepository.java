package com.groupproject.repository;

import com.groupproject.entities.PurchaseHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseHistoryRepository extends JpaRepository<PurchaseHistory, Long> {
//    PurchaseHistory findByAccountId(Long accountId);
}
