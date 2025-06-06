package com.eyethu.proposals.repository;

import com.eyethu.proposals.model.Option;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OptionRepository extends JpaRepository<Option, Long> {
}
