package rev.team.BOARD_SERVICE.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class Comment {
    @Id
    @Column(name = "comment_id")
    private Long commentId;

    //작성자
    private String userId;

    //닉네임
    private String nickname;

    //날짜
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date postDate;

    //댓글
    @Column(columnDefinition = "TEXT")
    private String comment;

    //대댓글
    @Column(columnDefinition = "integer default 0")
    private Integer reComment = 0;

    //좋아용
    @Column(columnDefinition = "integer default 0")
    private Integer good = 0;

    //참조 글
    private Long refAsk;

    //상위 댓글
    private Long refComment;
}