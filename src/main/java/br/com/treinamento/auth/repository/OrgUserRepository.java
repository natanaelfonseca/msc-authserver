package br.com.treinamento.auth.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.treinamento.auth.model.User;

@Repository
public interface OrgUserRepository extends CrudRepository<User, String> {
	public User findByEmail(String email);
}
