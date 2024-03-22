package com.sh.app.vod.repository;

import com.sh.app.vod.entity.Vod;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VodRepository extends JpaRepository<Vod, Long> {
}
