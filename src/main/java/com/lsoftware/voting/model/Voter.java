/*
 * Developed by: Luis Espinosa, be aware that this project
 * is part of my personal portfolio.
 */
package com.lsoftware.voting.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

/**
 * The Class Voter.
 * 
 * @author Luis Espinosa
 */
@Data
@Document(collection = "voters")
public class Voter {
	
	/** The id. */
	@Id
	private String id;

	/** The document. */
	@Indexed(unique = true)
	private String document;

	/** The code. */
	@Indexed(unique = true)
	private String code;

}
