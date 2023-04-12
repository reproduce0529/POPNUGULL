package com.example.popNaGull.repository;

import com.example.popNaGull.entity.Nugull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NugullRepository extends JpaRepository<Nugull, Long> {

    Optional<Nugull> findByUserIP(String userIP);

}
