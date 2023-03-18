package com.kny.exam.jpaBoard.user.dao;

        import com.kny.exam.jpaBoard.user.domain.User;
        import org.springframework.data.jpa.repository.JpaRepository;

        import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);
}