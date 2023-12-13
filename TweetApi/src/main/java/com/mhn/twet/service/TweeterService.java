package com.mhn.twet.service;

import org.springframework.http.ResponseEntity;

public interface TweeterService {

	ResponseEntity<String> getTweetUser(String username );

}
