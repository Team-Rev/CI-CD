package rev.team.BOARD_SERVICE.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import rev.team.BOARD_SERVICE.util.Category;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

public class BoardDTO {
    private Long boardId;

    private String nickname;

    private Category category;

    private String title;

    private String content;

    private Date postDate;

    private Integer hits = 0;

    private Integer status = 0;
}
