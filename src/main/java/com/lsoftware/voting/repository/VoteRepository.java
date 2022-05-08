/*
 * Developed by: Luis Espinosa, be aware that this project
 * is part of my personal portfolio.
 */
package com.lsoftware.voting.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.lsoftware.voting.model.Vote;

/**
 * The Interface VoteRepository.
 * 
 * @author Luis Espinosa
 */
@Repository
public interface VoteRepository extends MongoRepository<Vote, String> {
	
	
	/**
	 * Find by document and code.
	 *
	 * @param document the document
	 * @param code the code
	 * @return the optional
	 */
	Optional<Vote> findByDocumentAndCode(String document, String code);

}
