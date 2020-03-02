package com.skillbox.sw.repository;

import com.skillbox.sw.domain.Person;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer>,
    JpaSpecificationExecutor<Person> {

  Optional<Person> findByEmail(String email);

  List<Person> findByIdIn(List<Integer> ids);

  Page<Person> findAllByFirstNameContainingAndLastNameContainingAndTownContainingAndBirthDateBetween
      (String firstName, String lastName, String town, LocalDate startBirth, LocalDate endBirth,
          Pageable pageable);
}