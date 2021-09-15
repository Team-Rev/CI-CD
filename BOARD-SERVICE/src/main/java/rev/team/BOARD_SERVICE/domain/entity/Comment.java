package rev.team.BOARD_SERVICE.domain.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    //작성자
    private String userId;

    //닉네임
    private String nickname;

    //날짜
    @NonNull
    private LocalDateTime postDate;

    //댓글
    @Column(columnDefinition = "TEXT")
    private String comment;

    //참조 글
    private Long refAsk;

    /*
    //대댓글
    @Column(columnDefinition = "integer default 0")
    private Integer reComment = 0;

    //좋아용
    @Column(columnDefinition = "integer default 0")
    private Integer good = 0;

    //상위 댓글
    private Long refComment;
     */
}