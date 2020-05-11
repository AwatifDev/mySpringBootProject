package com.jtech.gbib.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jtech.gbib.exception.EmptyListException;
import com.jtech.gbib.exception.NotFoundObjectException;
import com.jtech.gbib.repository.AutorRepository;
import com.jtech.gbib.repository.entity.Author;

@Service
public class AuthorService {

	private static final Logger LOGGER = LoggerFactory.getLogger(AuthorService.class);

	@Autowired
	AutorRepository autorRepository;

	@Transactional
	public Author findById(Long authorId) {
		LOGGER.info("authorId: " + authorId);
		Validate.notNull(authorId, "authorId must be not null");
		Optional<Author> author = autorRepository.findById(authorId);
		if (!author.isPresent()) {
			throw new NotFoundObjectException("Author not found id:" + authorId);
		}
		LOGGER.info("Author : " + author.get());
		return author.get();

	}

	@Transactional
	public List<Author> findAll() {
		List<Author> authors = autorRepository.findAll();
		if (authors.isEmpty()) {
			throw new EmptyListException("No author found ");
		}
		LOGGER.info("Authors :" + authors);
		return authors;
	}
	
	@Transactional
	public  Author save(Author authorToSave) {
		LOGGER.info("Author to save  : " + authorToSave);
		Validate.notNull(authorToSave, "author must be not null");
		Author savedAuthor = autorRepository.save(authorToSave);
		LOGGER.info("Author saved : " + savedAuthor);
		return savedAuthor;
	}
	

}
