package com.telran.bank.repository;

import com.telran.bank.entity.Account;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long>, JpaSpecificationExecutor<Account> {

    List<Account> findAllByCityInIgnoreCase(List<String> city);

    List<Account> findAllByCreationDate(LocalDate creationDate);

    List<Account> findAllByCityInIgnoreCaseAndCreationDate(List<String> city, LocalDate creationDate);

//    @Query(" SELECT a FROM Account a WHERE lower(a.city) IN(:city) and a.creationDate = :creationDate ")
//    public List<Account> findFiltrCityDate(List<String> city, LocalDate creationDate);
}
