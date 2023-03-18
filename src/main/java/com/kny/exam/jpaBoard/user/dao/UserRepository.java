package com.kny.exam.jpaBoard.user.dao;

        import com.kny.exam.jpaBoard.user.domain.User;
        import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
}