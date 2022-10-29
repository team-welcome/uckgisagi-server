package server.uckgisagi.app.scrap.service;

import org.jetbrains.annotations.NotNull;
import server.uckgisagi.common.exception.custom.NotFoundException;
import server.uckgisagi.domain.scrap.entity.Scrap;
import server.uckgisagi.domain.scrap.repository.ScrapRepository;

import static server.uckgisagi.common.exception.ErrorResponseResult.*;

public class ScrapServiceUtils {

    @NotNull
    public static Scrap findByPostIdAndUserId(ScrapRepository scrapRepository, Long postId, Long userId) {
        Scrap scrap = scrapRepository.findScrapByPostIdAndUserId(postId, userId);
        if (scrap == null) {
            throw new NotFoundException(String.format("스크랩되지 않은 글 (%s) 입니다", postId), NOT_FOUND_SCRAP_EXCEPTION);
        }
        return scrap;
    }
}
