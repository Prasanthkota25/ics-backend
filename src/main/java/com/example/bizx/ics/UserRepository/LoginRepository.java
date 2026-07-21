package com.example.bizx.ics.UserRepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.bizx.ics.UserEntity.LoginEntity;

public interface LoginRepository extends JpaRepository<LoginEntity, Integer> {

	LoginEntity findByUsername(String username);

	LoginEntity findByUsernameIgnoreCase(String username);

	@Query("""
			    SELECT u
			    FROM LoginEntity u
			    WHERE LOWER(u.firstname) LIKE LOWER(CONCAT('%', :keyword, '%'))
			       OR LOWER(u.lastname) LIKE LOWER(CONCAT('%', :keyword, '%'))
			       OR LOWER(u.username) LIKE LOWER(CONCAT('%', :keyword, '%'))
			""")
	List<LoginEntity> searchUsers(@Param("keyword") String keyword);

}
