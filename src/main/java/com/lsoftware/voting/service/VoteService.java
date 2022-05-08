/*
 * Developed by: Luis Espinosa, be aware that this project
 * is part of my personal portfolio.
 */
package com.lsoftware.voting.service;

import java.util.Optional;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lsoftware.voting.dto.VoteCreationDTO;
import com.lsoftware.voting.exception.ExceptionValueNotPermitted;
import com.lsoftware.voting.model.Vote;
import com.lsoftware.voting.model.Voter;
import com.lsoftware.voting.repository.VoteRepository;
import com.lsoftware.voting.repository.VoterRepository;
import com.lsoftware.voting.shared.service.ServiceCreationMethods;

/**
 * The Class VoteService.
 * 
 * @author Luis Espinosa
 */
@Service
public class VoteService implements ServiceCreationMethods<VoteCreationDTO> {

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(VoteService.class);
	
	/** The voter repository. */
	private VoterRepository voterRepository;
	
	/** The vote repository. */
	private VoteRepository voteRepository;
	
	private ModelMapper modelMapper;
	
	/**
	 * Instantiates a new vote service.
	 *
	 * @param voteRepository the vote repository
	 * @param voterRepository the voter repository
	 */
	public VoteService(VoteRepository voteRepository, VoterRepository voterRepository, ModelMapper modelMapper) {
		this.voteRepository = voteRepository;
		this.voterRepository = voterRepository;
		this.modelMapper = modelMapper;
	}
	
	
	
	/**
	 * Adds the.
	 *
	 * @param obj the obj
	 * @return the vote creation DTO
	 */
	@Override
	@Transactional
	public VoteCreationDTO add(VoteCreationDTO obj) {
		LOG.info("method: add");
		
		Voter voter = 
				voterRepository.findByDocumentAndCode(obj.getDocument(), 
						obj.getCode())
				.orElseThrow(() -> new ExceptionValueNotPermitted("The voter is not registered"));
		
		LOG.info("Voter attempting to make a vote: {} - {}", voter.getDocument(), voter.getCode() );
		
		Optional<Vote> searchedVote = voteRepository.findByDocumentAndCode(obj.getDocument(), 
						obj.getCode());
		
		if (searchedVote.isPresent()) throw new ExceptionValueNotPermitted("The voter already voted");
		
		Vote vote = modelMapper.map(obj, Vote.class);
		vote.setId(UUID.randomUUID().toString());
				
		Vote saved = voteRepository.insert(vote);
		return modelMapper.map(saved, VoteCreationDTO.class);
	}
	
	
}
