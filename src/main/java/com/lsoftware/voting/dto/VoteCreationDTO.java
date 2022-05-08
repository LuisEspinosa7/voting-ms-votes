/*
 * Developed by: Luis Espinosa, be aware that this project
 * is part of my personal portfolio.
 */
package com.lsoftware.voting.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The Class VoteCreationDTO.
 * 
 * @author Luis Espinosa
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class VoteCreationDTO implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 8710846831040714129L;

	/** The alias. */
	@NotNull(message = "Document can not be null")
	@NotEmpty(message = "Document can not be empty")
	@Size(min=8, message="Document should be composed of at least 8 characters")
	@Size(max=20, message="Document could be composed of 20 characters maximum")
	private String document;

	/** The code. */
	@NotNull(message = "Code can not be null")
	@NotEmpty(message = "Code can not be empty")
	@Size(min=20, message="Code should be composed of at least 20 characters")
	@Size(max=60, message="Code could be composed of 60 characters maximum")
	private String code;

	/** The candidate id. */
	@NotNull(message = "Candidate Id can not be null")
	private int candidateId;

}
