package com.say.popo.popoalbum.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.say.popo.popoalbum.entity.LoginLog;

public interface LoginLogRepository extends JpaRepository<LoginLog, Long> {

}
