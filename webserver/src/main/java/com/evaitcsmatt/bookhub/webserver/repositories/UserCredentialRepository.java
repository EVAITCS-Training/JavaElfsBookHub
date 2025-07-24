package com.evaitcsmatt.bookhub.webserver.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.evaitcsmatt.bookhub.webserver.entities.UserCredential;

public interface UserCredentialRepository extends JpaRepository<UserCredential, String> {

}
