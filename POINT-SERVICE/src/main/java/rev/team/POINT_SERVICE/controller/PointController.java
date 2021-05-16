package rev.team.POINT_SERVICE.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rev.team.POINT_SERVICE.domain.request.PointReasonRequest;
import rev.team.POINT_SERVICE.domain.entity.PointReason;
import rev.team.POINT_SERVICE.domain.request.PointRecordRequest;
import rev.team.POINT_SERVICE.service.PointService;

import java.util.List;

@RestController
public class PointController {

    PointService pointService;

    @Autowired
    public PointController(PointService pointService) {
        this.pointService = pointService;
    }

    @GetMapping("/reasonList")
    public List<PointReason> getPointReason() {
        return pointService.getPointReason();
    }

    @PostMapping("/create")
    public String insertPointReason(@RequestBody PointReasonRequest pointReasonRequest) {
        return pointService.insert(pointReasonRequest);
    }

    @PatchMapping("/edit/{id}")
    public String updatePointReason(@PathVariable("id") Long id, @RequestBody PointReasonRequest pointReasonRequest) {
        return pointService.update(id, pointReasonRequest);
    }

    @PostMapping("/record")
    public String recordPoint(@RequestBody PointRecordRequest pointRecordRequest) {
        return pointService.record(pointRecordRequest);
    }
}