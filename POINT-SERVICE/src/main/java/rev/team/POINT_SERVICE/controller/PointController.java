package rev.team.POINT_SERVICE.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rev.team.POINT_SERVICE.domain.entity.PointDTO;
import rev.team.POINT_SERVICE.domain.entity.PointReason;
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
    public String insertPointReason(@RequestBody PointDTO pointDTO) {
        return pointService.insert(pointDTO);
    }

    @PatchMapping("/edit/{id}")
    public String updatePointReason(@PathVariable("id") Long id, @RequestBody PointDTO pointDTO) {
        return pointService.update(id, pointDTO);
    }

    @PostMapping("/record")
    public String recordPoint(@RequestBody String userId, @RequestBody Long reasonId) {
        return pointService.record(userId, reasonId);
    }
}