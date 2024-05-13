package com.accent.sav.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.accent.sav.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String username);
    User findByEmail(String email);

    @Query(value = "(select * from  user u where u.id=:idUser)", nativeQuery = true)
    public User findByIdUser(int idUser);

    @Query(value = "(select  u.img from  user u where u.id=:idUser)", nativeQuery = true)
    public String findImgByIdUser(int idUser);

    @Query(value = "(select u.* from  user u, user_authority ua, authority a where u.id = ua.user_id and ua.authority_id = a.id and a.name='ROLE_DEP')", nativeQuery = true)
    public List<User> findAllDeps();

    @Query(value = "(select * from  user u where u.phone=:phone)", nativeQuery = true)
    public User findByPhone(int phone);

    @Query(value = "SELECT FLOOR(DATEDIFF(CURDATE(), u.birth_date) / 365.25) AS age, COUNT(*) AS user_count " +
            "FROM user u " +
            "WHERE EXISTS (SELECT 1 FROM user_authority ua WHERE u.id = ua.user_id AND ua.authority_id = 4) " +
            "GROUP BY age " +
            "ORDER BY age", nativeQuery = true)
    public List<Map<String, Long>> userAgeStats();

    public boolean existsByUsername(String username);

}
