package com.example.instagram_project.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.instagram_project.Service.InstagramService;
import com.example.instagram_project.dto.DMDto;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class InstagramApiController {
	
	@Autowired
	private InstagramService instagramService;

	@PostMapping("/api/create/DM")
	public ResponseEntity<DMDto> create(@RequestBody DMDto dto) {
		log.info("value from json : " + dto.toString());
		DMDto createdDto = instagramService.create(dto);

		log.info(createdDto.toString());
		return ResponseEntity.status(HttpStatus.OK).body(createdDto);
	}

}
