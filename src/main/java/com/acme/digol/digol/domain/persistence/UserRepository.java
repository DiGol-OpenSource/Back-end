package com.acme.digol.digol.domain.persistence;



import com.acme.digol.digol.domain.model.entity.SportField;
import com.acme.digol.digol.domain.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAllByEmail(String email);
    User findByName(String name);
}
