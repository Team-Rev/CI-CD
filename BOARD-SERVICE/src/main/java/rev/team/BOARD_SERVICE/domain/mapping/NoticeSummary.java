package rev.team.BOARD_SERVICE.domain.mapping;

import java.util.Date;

// AUTHOR 김태영
// CREATED AT 2021-05-12
// 공지사항 게시글 요약본 매핑 인터페이스

public interface NoticeSummary {
    Long getNoticeId(); // ID
    String getUserId(); // 작성자
    String getTitle(); // 제목
    Date getPostDate(); // 작성일
    Integer getHits(); // 조회수
}
