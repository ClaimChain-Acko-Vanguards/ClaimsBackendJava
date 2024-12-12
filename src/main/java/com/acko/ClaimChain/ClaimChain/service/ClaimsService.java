package com.acko.ClaimChain.ClaimChain.service;

import com.acko.ClaimChain.ClaimChain.dto.ClaimsRequestDto;
import com.acko.ClaimChain.ClaimChain.dto.ClaimsSuperDto;
import com.acko.ClaimChain.ClaimChain.dto.ResponseDto;
import com.acko.ClaimChain.ClaimChain.models.Claims;
import com.acko.ClaimChain.ClaimChain.repository.ClaimsRepository;
import com.acko.ClaimChain.ClaimChain.utils.ClaimSettlementUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ClaimsService {

    private final ExcelReader excelReader;
    private final BlockChainService blockChainService;
    private final ClaimsRepository claimsRepository;
    private final ClaimSettlementUtils claimSettlementUtils;
    @PersistenceContext
    private EntityManager entityManager;

    public ResponseDto<Object> getClaimsRatioByCriteria(@RequestBody ClaimsRequestDto claimsRequestDto) {
        List<Claims> claims = findClaimsByCriteria(claimsRequestDto);
        return ResponseDto.builder().data(claimSettlementUtils.getClaimRatio(claims)).build();
    }

    public List<Claims> findClaimsByCriteria(ClaimsRequestDto claimsRequestDto) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Claims> query = cb.createQuery(Claims.class);
        Root<Claims> claims = query.from(Claims.class);

        List<Predicate> predicates = new ArrayList<>();

        if (claimsRequestDto.getClaimsCategory() != null) {
            predicates.add(cb.equal(claims.get("policyType"), claimsRequestDto.getClaimsCategory()));
        }
        if (claimsRequestDto.getClaimsInsurer() != null) {
            predicates.add(cb.equal(claims.get("insurerName"), claimsRequestDto.getClaimsInsurer()));
        }
        if (claimsRequestDto.getState() != null) {
            predicates.add(cb.equal(claims.get("state"), claimsRequestDto.getState()));
        }
        if (claimsRequestDto.getStartDate() != null && claimsRequestDto.getEndDate() != null) {
            predicates.add(cb.between(claims.get("settlementDate"), claimsRequestDto.getStartDate(), claimsRequestDto.getEndDate()));
        }
        if (claimsRequestDto.getPincode() != null) {
            predicates.add(cb.equal(claims.get("pincode"), claimsRequestDto.getPincode()));
        }
        if(claimsRequestDto.getEmail()!=null){
            predicates.add(cb.equal(claims.get("email"),claimsRequestDto.getEmail()));
        }
        if(claimsRequestDto.getPhoneNumber()!=null){
            predicates.add(cb.equal(claims.get("phone"),claimsRequestDto.getPhoneNumber()));
        }
        query.where(predicates.toArray(new Predicate[0]));

        return entityManager.createQuery(query).getResultList();
    }

    public ResponseDto<Object> getInsurer() {
        List<String> insurers =  claimsRepository.findDistinctInsurerNames();
        return ResponseDto.builder().data(insurers).build();
    }

    /*public ResponseDto<Double> getClaimsRatio(@RequestBody ClaimsRequestDto claimsRequestDto) {
        String claimRatio = "";

        if (!StringUtils.isEmpty(claimsRequestDto.getClaimsCategory()) && !StringUtils.isEmpty(claimsRequestDto.getClaimsInsurer())) {
            claimRatio = getClaimsRatioCategoryPerInsurer(claimsRequestDto);
        } else if (!StringUtils.isEmpty(claimsRequestDto.getClaimsCategory())) {
            claimRatio = getClaimsRatioCategory(claimsRequestDto);
        }else if (!StringUtils.isEmpty(claimsRequestDto.getClaimsInsurer())) {
            claimRatio = getClaimsRatioInsurer(claimsRequestDto);
        }else if()
    }*/

   /* private String getClaimsRatioCategory(ClaimsRequestDto claimsRequestDto) {
        List<Claims> claims =  claimsRepository.findAllByPolicyTypeAndSettlementDateBetween(claimsRequestDto.getClaimsCategory(), claimsRequestDto.getStartDate() , claimsRequestDto.getEndDate());
        return claimSettlementUtils.getClaimRatio(claims);
    }

    private String getClaimsRatioInsurer(ClaimsRequestDto claimsRequestDto) {
        List<Claims> claims =  claimsRepository.findAllByInsurerNameAndSettlementDateBetween(claimsRequestDto.getClaimsInsurer() , claimsRequestDto.getStartDate() , claimsRequestDto.getEndDate());
        return claimSettlementUtils.getClaimRatio(claims);
    }

    private String getClaimsRatioCategoryPerInsurer(ClaimsRequestDto claimsRequestDto) {
       List<Claims> claims =  claimsRepository.findAllByInsurerNameAndPolicyTypeAndSettlementDateBetween(claimsRequestDto.getClaimsInsurer() , claimsRequestDto.getClaimsCategory() , claimsRequestDto.getStartDate() , claimsRequestDto.getEndDate());
       return claimSettlementUtils.getClaimRatio(claims);
    }


    public ResponseDto<String> getClaimsSettlementRatioForInsurer(String insurerId) {
        return null ;// blockChainService.getClaimsSettlementRatioForInsurer(insurerId);
    }
*/
    public ResponseDto<Object> readAndPushToBlockChain(MultipartFile file) throws  Exception{
        List<ClaimsSuperDto> superDtos =  excelReader.readExcel(file);
        if(CollectionUtils.isEmpty(superDtos)){
            return ResponseDto.builder().data("Empty data").build();
        }
        superDtos.forEach(blockChainService::addDatatoBlockChain);
        return null;
    }



}
