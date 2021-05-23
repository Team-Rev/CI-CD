package rev.team.POINT_SERVICE.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
public class PointReasonService {

    PointReasonRepository pointReasonRepository;
    PointRecordRepository pointRecordRepository;

    @Autowired
    public PointReasonService(PointReasonRepository pointReasonRepository, PointRecordRepository pointRecordRepository) {
        this.pointReasonRepository = pointReasonRepository;
        this.pointRecordRepository = pointRecordRepository;
    }

    @Transactional
    public List<PointReason> getPointReason(Pageable pageable) {
        Page<PointReason> pointReasons = pointReasonRepository.findAll(pageable);
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

}