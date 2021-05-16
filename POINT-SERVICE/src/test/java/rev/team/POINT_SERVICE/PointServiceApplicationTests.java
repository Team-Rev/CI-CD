package rev.team.POINT_SERVICE;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import rev.team.POINT_SERVICE.domain.entity.PointReason;
import rev.team.POINT_SERVICE.domain.repository.PointReasonRepository;

@SpringBootTest
class PointServiceApplicationTests {
	PointReasonRepository pointReasonRepository;

	@Autowired
	public PointServiceApplicationTests(PointReasonRepository pointReasonRepository) {
		this.pointReasonRepository = pointReasonRepository;
	}

	@Test
	void save() { // insert
		pointReasonRepository.save(PointReason.builder()
				.point(2)
				.reason("포인트 테스트 2")
				.build());
	}

	@Test
	public void findAll() { // select *
		pointReasonRepository.findAll();
	}

}
