package rev.team.BOARD_SERVICE.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

public class NoticeSummaryDTO {
    private Long noticeId;

    private String title;

    private Date postDate;

    private Integer hits = 0;

    private String nickname;
}
