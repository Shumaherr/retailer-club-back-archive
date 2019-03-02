package com.auchanclub.security.repository;

import com.auchanclub.model.security.Authority;
import com.auchanclub.model.security.Role;
import com.auchanclub.model.security.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends JpaRepository <Authority, Long> {
    Authority saveAndFlush(Authority authority);
}
