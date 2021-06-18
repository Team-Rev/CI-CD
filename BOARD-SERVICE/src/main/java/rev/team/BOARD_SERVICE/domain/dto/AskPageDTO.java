package rev.team.BOARD_SERVICE.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import rev.team.BOARD_SERVICE.domain.entity.Ask;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

public class AskPageDTO {
    private Long pageCount;
    private Page<Ask> asks;
}
