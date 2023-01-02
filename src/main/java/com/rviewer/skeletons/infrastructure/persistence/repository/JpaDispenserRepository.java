package com.rviewer.skeletons.infrastructure.persistence.repository;

import com.rviewer.skeletons.infrastructure.persistence.dao.DispenserDao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaDispenserRepository extends JpaRepository<DispenserDao, String> {
}
