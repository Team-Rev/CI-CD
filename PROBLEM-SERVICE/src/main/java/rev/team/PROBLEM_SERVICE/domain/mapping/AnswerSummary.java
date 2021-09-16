package rev.team.PROBLEM_SERVICE.domain.mapping;

import java.time.LocalDateTime;

public interface AnswerSummary {
    Long getAnswerMainId();
    String getUserId(); // 푼 사용자 ID
    LocalDateTime getDate(); // 문제 푼 날
    int getTotalCount();// 총 문제 갯수
    int getCorrectCount(); // 맞힌 문제 갯수
    String getMainCategory();// 메인 카테고리
    String getSubCategory(); // 서브 카테고리
}
