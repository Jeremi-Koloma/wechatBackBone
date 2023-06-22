package com.sahelcrea.wechatback.repositories;

import com.sahelcrea.wechatback.models.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Cette classe va étendre de JpaRepository avec Entity et son id en Param
@Repository // Annotation bean
public interface AppRoleRepository extends JpaRepository<AppRole, Long> {
    public AppRole findRoleByName(String name);
}
