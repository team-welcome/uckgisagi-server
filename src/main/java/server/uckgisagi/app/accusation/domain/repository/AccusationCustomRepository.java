package server.uckgisagi.app.accusation.domain.repository;

import server.uckgisagi.app.accusation.domain.entity.Accusation;

import java.util.List;
import java.util.Optional;

public interface AccusationCustomRepository{

    List<Accusation> findAllByPostId(Long postId);

    Optional<Accusation> findByUserIdAndPostId(Long userId, Long postId);
}
