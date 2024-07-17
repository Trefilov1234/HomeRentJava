package ru.exfadra.homeRent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.exfadra.homeRent.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
     User findByNickname(String nickname);
     User findUserById(Long id);
     User findUserByEmail(String email);
}
