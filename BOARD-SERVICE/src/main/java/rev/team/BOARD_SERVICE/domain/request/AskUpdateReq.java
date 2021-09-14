package rev.team.BOARD_SERVICE.domain.request;
import lombok.Getter;


@Getter
public class AskUpdateReq {
    private Long askId;

    private String title;

    private String content;
}
