package com.sahelcrea.wechatback.Repositories;

import com.sahelcrea.wechatback.Models.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// Cette classe va étendre de JpaRepository
@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    public AppUser findByUsername(String username);
    public AppUser findByEmail(String email);
    public AppUser findUserById(Long appUser_id);
    public List<AppUser> findByUsernameContaining(String username);
}
