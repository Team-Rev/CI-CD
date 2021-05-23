package rev.team.POINT_SERVICE.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import rev.team.POINT_SERVICE.domain.request.PointReasonRequest;
import rev.team.POINT_SERVICE.domain.entity.PointReason;
import rev.team.POINT_SERVICE.domain.request.PointRecordRequest;
import rev.team.POINT_SERVICE.domain.response.UserPointRecordRes;
import rev.team.POINT_SERVICE.service.PointReasonService;
import rev.team.POINT_SERVICE.service.PointRecordService;

import java.util.List;

@RestController
public class PointController {

    PointReasonService pointReasonService;
    PointRecordService pointRecordService;

    @Autowired
    public PointController(PointReasonService pointReasonService, PointRecordService pointRecordService) {
        this.pointReasonService = pointReasonService;
        this.pointRecordService = pointRecordService;
    }

    @GetMapping("/reasonList")
    public List<PointReason> getPointReason(Pageable pageable) {
        return pointReasonService.getPointReason(pageable);
    }

    @PostMapping("/create")
    public String insertPointReason(@RequestBody PointReasonRequest pointReasonRequest) {
        return pointReasonService.insert(pointReasonRequest);
    }

    @PatchMapping("/edit/{id}")
    public String updatePointReason(@PathVariable("id") Long id, @RequestBody PointReasonRequest pointReasonRequest) {
        return pointReasonService.update(id, pointReasonRequest);
    }

    @PostMapping("/record")
    public String recordPoint(@RequestBody PointRecordRequest pointRecordRequest) {
        return pointRecordService.record(pointRecordRequest);
    }

    @GetMapping("/userRecord/{userId}")
    public List<UserPointRecordRes> getUserRecord(@PathVariable("userId") String userId, Pageable pageable) {
        return pointRecordService.userRecord(userId, pageable);
    }
}