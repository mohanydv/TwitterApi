package com.mhn.twet.service;

import java.net.http.HttpClient;
import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class TweeterServiceImpl implements TweeterService {

	private static final Logger log = LoggerFactory.getLogger(TweeterServiceImpl.class);

	@Value("${twitter.api.url}")
	private String twitterApiUrl;

	@Value("${twitter.api.key}")
	private String twitterApiKey;

	@Value("${twitter.api.secret}")
	private String twitterApiSecret;

	@Value("${twitter.access.token}")
	private String twitterAccessToken;

	@Value("${twitter.access.token.secret}")
	private String twitterAccessTokenSecret;

	@Value("${twitter.bearer.token}")
	private String bearerToken;

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public ResponseEntity<String> getTweetUser(String username) {

		UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString(twitterApiUrl + "/2/users/by")
				.queryParam("username", username).queryParam("user.fields", "created_at,description,pinned_tweet_id");

		HttpHeaders headers = new HttpHeaders();

		headers.setBearerAuth(bearerToken);

		headers.set("Api-Host", twitterApiUrl);
		headers.set("Api-Key", twitterApiKey);
		headers.set("Access-Token", twitterAccessToken);
		headers.set("Access-Token-Secret", twitterAccessTokenSecret);

		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

		HttpEntity<String> requestEntity = new HttpEntity<>(headers);

		ResponseEntity<String> response = restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.GET, requestEntity,
				String.class);

		log.info("json output response", response);
		return response;

	}

}
