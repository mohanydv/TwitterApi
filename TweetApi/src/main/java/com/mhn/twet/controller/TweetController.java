package com.mhn.twet.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.server.ResponseStatusException;

import com.mhn.twet.service.TweeterService;

@RestController
@RequestMapping("/Tweeter")
public class TweetController {

	private static final Logger log = LoggerFactory.getLogger(TweetController.class);

	@Autowired
	private TweeterService service;

//	API 1: Search Tweet user

	@GetMapping("/username/{username}")
	public ResponseEntity<String> getUserByUserName(@PathVariable("username") String username) {
		try {

			
				ResponseEntity<String> tweetUser = service.getTweetUser(username);
			log.info(tweetUser.toString());
				return tweetUser;
		} catch (HttpClientErrorException ex) {
			log.error("something went wrong  from client");
			return ResponseEntity.status(ex.getStatusCode()).body(ex.getMessage());
		} catch (HttpServerErrorException ex) {
			log.error("something went wrong  from server");
			return ResponseEntity.status(ex.getStatusCode()).body(ex.getMessage());
		} catch (Exception ex) {
			log.error("something went wrong while getting value from external Api");
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An error occurred from external api.");
		}
	}

//	API 2: Get tweets of the selected user

}
