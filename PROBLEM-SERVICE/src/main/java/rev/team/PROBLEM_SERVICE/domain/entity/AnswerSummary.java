package rev.team.PROBLEM_SERVICE.domain.entity;

import java.time.LocalDateTime;

public interface AnswerSummary {
    Long getAnswerMainId();
    String getUserId(); // 푼 사용자 ID
    LocalDateTime getDate(); // 문제 푼 날
    int getTotalCount();// 총 문제 갯수
    int getCorrectCount(); // 맞힌 문제 갯수
}
