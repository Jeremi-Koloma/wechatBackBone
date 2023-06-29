package com.sahelcrea.wechatback.Repositories;

import com.sahelcrea.wechatback.Models.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Cette classe va étendre de JpaRepository avec Entity et son id en Param
@Repository
public interface AppRoleRepository extends JpaRepository<AppRole, Long> {
    public AppRole findRoleByName(String name);
}
