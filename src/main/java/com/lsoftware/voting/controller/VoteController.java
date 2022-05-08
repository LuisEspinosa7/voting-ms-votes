/*
 * Developed by: Luis Espinosa, be aware that this project
 * is part of my personal portfolio.
 */
package com.lsoftware.voting.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lsoftware.voting.dto.VoteCreationDTO;
import com.lsoftware.voting.service.VoteService;
import com.lsoftware.voting.shared.api.ApiCustomResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

/**
 * The Class VoteController.
 * 
 * @author Luis Espinosa
 */
@RestController
@RequestMapping("/api/v1/votes")
public class VoteController {
	
	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(VoteController.class);

	/** The vote service. */
	private VoteService voteService;
	
	/**
	 * Instantiates a new vote controller.
	 *
	 * @param voteService the vote service
	 */
	public VoteController(VoteService voteService) {
		this.voteService = voteService;
	}
	
	/**
	 * Creates the.
	 *
	 * @param voteCreationDTO the vote creation DTO
	 * @return the response entity
	 */
	@Operation(summary = "Create a new Vote")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Create the new Vote", content = {
			@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ApiCustomResponse.class)) }) })
	@PostMapping
	public ResponseEntity<ApiCustomResponse> create(@Valid @RequestBody VoteCreationDTO voteCreationDTO) {
		LOG.info("method: create");

		VoteCreationDTO vote = voteService.add(voteCreationDTO);
		ApiCustomResponse response = new ApiCustomResponse.ApiResponseBuilder(200).message("Vote created")
				.data(vote).build();

		return ResponseEntity.ok(response);
	}
	
}
