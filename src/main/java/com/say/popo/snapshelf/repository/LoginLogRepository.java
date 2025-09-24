package com.say.popo.snapshelf.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.say.popo.snapshelf.entity.LoginLog;

public interface LoginLogRepository extends JpaRepository<LoginLog, Long> {

}
