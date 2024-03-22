package com.sh.app.actor.repository;

import com.sh.app.actor.dto.ActorDetailDto;
import com.sh.app.actor.entity.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface ActorRepository extends JpaRepository<Actor, Long> {
    Optional<Actor> findByActorNm(String actorNm);

    List<Actor> findByIdIn(ArrayList<Long> longs);
}
