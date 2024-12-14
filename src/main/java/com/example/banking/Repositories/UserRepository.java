package com.example.banking.Repositories;

import com.example.banking.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Integer> {
    // SELECT * FROM user WHERE firstname = "mohammed" AND lastname = "bakkali"
    List<User> findAllByFirsNameAndLastName(String lastName,String firstName);
    // SELECT * FROM user WHERE firstname like "%mohammed%"
    List<User> findAllByFirsNameContaining(String firstName);
    // SELECT * FROM user WHERE firstname ilike "%mohammed%"

    List<User> findAllByFirsNameContainingIgnoreCase(String firstName);
    // SELECT * FROM user u inner join account a ON u.id = a.id_user AND a.iban ="DE5414" "
    List<User> findAllByAccountIban(String iban);
    User findByFirsNameContainingIgnoreCaseAndEmailContainingIgnoreCase(String firstName,String email);

    @Query(value = "SELECT u FROM User u WHERE u.firsName =:fn")
    List<User> searchByFirstName(@Param("fn") String firstname);
    @Query(value = "SELECT u FROM User u WHERE u.firsName LIKE '%:firstName%'")
    List<User> searchByFirsNameContaining(@Param("firstName") String firstName);

    @Query("SELECT COUNT(u) FROM User u")
    long countAllUsers();

    @Query("SELECT MAX(u.age) FROM User u")
    int findMaxAge();

    @Query("SELECT AVG(u.age) FROM User u")
    double findAverageAge();

    // Modifications

    @Modifying
    @Query("UPDATE User u SET u.firsName = :newName WHERE u.id = :id")
    int updateUserName(@Param("id") Long id, @Param("newName") String newfirsName);

    @Query(value = "select * from _user u inner join  account a on u.id = a.id_user and a.iban =:iban",nativeQuery = true)
    List<User> searchByIbanNative(@Param("iban") String iban);
}
