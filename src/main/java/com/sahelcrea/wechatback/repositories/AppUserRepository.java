package com.sahelcrea.wechatback.repositories;

import com.sahelcrea.wechatback.models.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// Cette classe va étendre de JpaRepository
@Repository // Annotation bean
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    public AppUser findByUsername(String username);
    public AppUser findByEmail(String email);
    public AppUser findUserById(Long appUser_id);
    public List<AppUser> findByUsernameContaining(String username);
}
