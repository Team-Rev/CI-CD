package rev.team.POINT_SERVICE.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import rev.team.POINT_SERVICE.domain.entity.PointReason;
import rev.team.POINT_SERVICE.domain.entity.PointRecord;
import rev.team.POINT_SERVICE.domain.repository.PointReasonRepository;
import rev.team.POINT_SERVICE.domain.repository.PointRecordRepository;
import rev.team.POINT_SERVICE.domain.request.PointRecordRequest;
import rev.team.POINT_SERVICE.domain.response.UserPointRecordRes;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Service
public class PointRecordService {

    PointReasonRepository pointReasonRepository;
    PointRecordRepository pointRecordRepository;

    @Autowired
    public PointRecordService(PointReasonRepository pointReasonRepository, PointRecordRepository pointRecordRepository) {
        this.pointReasonRepository = pointReasonRepository;
        this.pointRecordRepository = pointRecordRepository;
    }

    public String record(PointRecordRequest pointRecordRequest) {

        // 포인트 사유 아이디에 해당하는 튜플 가져오기
        PointReason reason = pointReasonRepository.findByPointReasonId(pointRecordRequest.getReasonId());

        // 사용자 누적 포인트 변경
        pointRecordRepository.updateUserPointById(pointRecordRequest.getUserId(), reason.getPoint());

        // 포인트 기록 테이블에 저장
        pointRecordRepository.save(PointRecord.builder()
                .userId(pointRecordRequest.getUserId())
                .pointReasonId(reason)
                .pointDate(LocalDateTime.now())
                .build());

        return "RECORD SUCCESS";
    }

    public List<UserPointRecordRes> userRecord(String userId, Pageable pageable) {
        Page<PointRecord> records = pointRecordRepository.findByUserId(userId, pageable);
        List<UserPointRecordRes> recordList = new LinkedList<>();

        for (PointRecord record : records) {
            UserPointRecordRes userPointRecordRes = UserPointRecordRes.builder()
                    .date(record.getPointDate())
                    .reason(record.getPointReasonId().getReason())
                    .point(record.getPointReasonId().getPoint())
                    .build();

            recordList.add(userPointRecordRes);
        }
        return recordList;
    }

}
