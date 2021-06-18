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

@Table(name = "ask")

public class Ask {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "ask_id")
    private Long askId;

    private String userId; // 작성자

    private String nickname; // 작성자 작성 시 닉네임

    @Column(columnDefinition = "TEXT")
    private String title; // 제목

    @Column(columnDefinition = "TEXT")
    private String content; // 내용

    @Column(columnDefinition = "integer default 0")
    private Integer comments = 0;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date postDate; // 작성일

    @Column(columnDefinition = "integer default 0")
    private Integer hits = 0; // 조회수

    @Column(columnDefinition = "integer default 0")
    private Integer recommend = 0;
}
