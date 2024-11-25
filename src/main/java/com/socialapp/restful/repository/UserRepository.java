package com.socialapp.restful.repository;

import com.socialapp.restful.domain.User;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author JulianFisla
 */
@Repository
public interface UserRepository extends MongoRepository<User, Integer> {

    /**
     * Find a user by userId
     * @param userId
     * @return
     */
    @Override
    Optional<User> findById(Integer userId);

    /**
     * Check if a user exists by userId
     * @param userId
     * @return
     */
    @Override
    boolean existsById(Integer userId);

    /**
     * Delete a user by userId
     * @param userId
     */
    @Override
    void deleteById(Integer userId);

    /**
     * Custom query methods (if needed)
     * Find a user by username
     * @param userName
     * @return
     */
    Optional<User> findByUserName(String userName);

    /**
     * Find a user by email
     * @param email
     * @return
     */
    Optional<User> findByEmail(String email);

    /**
     * Check if a user exists by email
     * @param email
     * @return
     */
    boolean existsByEmail(String email);

    /**
     * Custom delete method by username
     * @param userName
     */
    void deleteByUserName(String userName);
}
