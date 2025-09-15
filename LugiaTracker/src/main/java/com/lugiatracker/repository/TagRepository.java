package com.lugiatracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.lugiatracker.model.Tag;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
}
