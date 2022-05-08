/*
 * Developed by: Luis Espinosa, be aware that this project
 * is part of my personal portfolio.
 */
package com.lsoftware.voting.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.lsoftware.voting.model.Voter;

/**
 * The Interface VoterRepository.
 * 
 * @author Luis Espinosa
 */
@Repository
public interface VoterRepository extends MongoRepository<Voter, String> {
	
	
	/**
	 * Find by document and code.
	 *
	 * @param document the document
	 * @param code the code
	 * @return the optional
	 */
	Optional<Voter> findByDocumentAndCode(String document, String code);

}
