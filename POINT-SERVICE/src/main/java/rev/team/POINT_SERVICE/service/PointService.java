package rev.team.POINT_SERVICE.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rev.team.POINT_SERVICE.domain.request.PointReasonRequest;
import rev.team.POINT_SERVICE.domain.entity.PointReason;
import rev.team.POINT_SERVICE.domain.entity.PointRecord;
import rev.team.POINT_SERVICE.domain.repository.PointRecordRepository;
import rev.team.POINT_SERVICE.domain.repository.PointReasonRepository;
import rev.team.POINT_SERVICE.domain.request.PointRecordRequest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PointService {

    PointReasonRepository pointReasonRepository;
    PointRecordRepository pointRecordRepository;

    @Autowired
    public PointService(PointReasonRepository pointReasonRepository, PointRecordRepository pointRecordRepository) {
        this.pointReasonRepository = pointReasonRepository;
        this.pointRecordRepository = pointRecordRepository;
    }

    public List<PointReason> getPointReason() {
        List<PointReason> pointReasons = pointReasonRepository.findAll();
        List<PointReason> pointReasonList = new ArrayList<>();

        for (PointReason pointReason : pointReasons) {
            PointReason reason = PointReason.builder()
                    .pointReasonId(pointReason.getPointReasonId())
                    .point(pointReason.getPoint())
                    .reason(pointReason.getReason())
                    .build();
            pointReasonList.add(reason);
        }
        return pointReasonList;
    }

    public String insert(PointReasonRequest pointReasonRequest) {
        pointReasonRepository.save(PointReason.builder()
                .reason(pointReasonRequest.getReason())
                .point(pointReasonRequest.getPoint())
                .build());
        return "CREATE SUCCESS";
    }

    public String update(Long id, PointReasonRequest pointReasonRequest) {
        pointReasonRepository.updateById(id, pointReasonRequest.getReason(), pointReasonRequest.getPoint());
        return "UPDATE SUCCESS";
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

}