package ru.myPackage.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.myPackage.models.Builder;

public interface BuilderRepository extends JpaRepository<Builder, Integer> {

    Builder findBuilderByEmail(String email);

}
