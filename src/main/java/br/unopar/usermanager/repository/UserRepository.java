package br.unopar.usermanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.unopar.usermanager.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
