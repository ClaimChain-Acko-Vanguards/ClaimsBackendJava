package com.acko.ClaimChain.ClaimChain.repository;

import com.acko.ClaimChain.ClaimChain.models.Claims;
import com.acko.ClaimChain.ClaimChain.models.Claims;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ClaimsRepository extends JpaRepository<Claims, Long> {

    @Query("SELECT DISTINCT c.insurerName FROM Claims c")
    List<String> findDistinctInsurerNames();

    List<Claims> findAllByInsurerNameAndPolicyTypeAndSettlementDateBetween(String insurerName , String policyType, Date startDate, Date endDate);
    List<Claims> findAllByInsurerNameAndSettlementDateBetween(String insurerName , Date startDate, Date endDate);
    List<Claims> findAllByPolicyTypeAndSettlementDateBetween(String policyType , Date startDate, Date endDate);



}