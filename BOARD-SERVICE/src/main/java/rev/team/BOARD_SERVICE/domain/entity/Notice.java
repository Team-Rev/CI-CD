package rev.team.BOARD_SERVICE.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

// AUTHOR 김태영
// CREATED AT 2021-05-12
// 공지사항 게시글용 테이블

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class Notice {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notice_id")
    private Long noticeId; //아이디

    private String userId; // 작성자

    @Column(columnDefinition = "TEXT")
    private String title; // 제목

    @Column(columnDefinition = "TEXT")
    private String content; // 내용

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date postDate; // 작성일

    @Column(columnDefinition = "integer default 0")
    private Integer hits = 0; // 조회수

    @Column(columnDefinition = "boolean default false")
    private Boolean pined = false; // 고정 여부
}
