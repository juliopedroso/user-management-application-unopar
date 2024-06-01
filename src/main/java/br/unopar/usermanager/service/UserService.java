package br.unopar.usermanager.service;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.unopar.usermanager.entity.User;
import br.unopar.usermanager.repository.UserRepository;
import br.unopar.usermanager.service.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;

@Service
public class UserService {

	private final UserRepository repository;

	public UserService(UserRepository repository) {
		super();
		this.repository = repository;
	}

	@Transactional(readOnly = true)
	public List<User> findAll() {
		List<User> users = repository.findAll();
		return users;
	}

	@Transactional(readOnly = true)
	public User findById(Long id) {

		Optional<User> obj = repository.findById(id);
		User entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		return entity;
	}

	@Transactional
	public User insert(User entity) {
		entity = repository.save(entity);
		return entity;
	}

	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id no found " + id);
		}
	}

	@Transactional
	public User update(User user) {

		try {
			User entity = repository.getById(user.getId());

			entity = repository.save(entity);
			return entity;
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id not found " + user.getId());
		}
	}

}