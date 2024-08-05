package com.Meshal.PMS.Repositories;

import com.Meshal.PMS.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findUserByEmailAndPassword(String email, String password);
    Optional<User> findUserByEmail(String email);
}
