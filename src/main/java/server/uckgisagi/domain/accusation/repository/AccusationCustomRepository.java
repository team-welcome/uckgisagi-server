package server.uckgisagi.domain.accusation.repository;

import server.uckgisagi.domain.accusation.entity.Accusation;

import java.util.List;
import java.util.Optional;

public interface AccusationCustomRepository{

    List<Accusation> findAllByPostId(Long postId);

    Optional<Accusation> findByUserIdAndPostId(Long userId, Long postId);
}
