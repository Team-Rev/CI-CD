package rev.team.BOARD_SERVICE.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import rev.team.BOARD_SERVICE.domain.dto.AskPageDTO;
import rev.team.BOARD_SERVICE.domain.entity.Ask;
import rev.team.BOARD_SERVICE.domain.repository.AskRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class AskService {

    private final AskRepository askRepository;

    @Autowired
    public AskService(AskRepository askRepository){
        this.askRepository = askRepository;
    }

    public AskPageDTO getAskPage(int page) {
        Pageable pageable = PageRequest.of(page, 10, Sort.Direction.DESC, "askId");

        return AskPageDTO.builder()
                .pageCount(askRepository.count())
                .asks(askRepository.findAll(pageable))
                .build();
    }

    public void createAsk(Ask ask) {
        askRepository.save(ask);
    }

    @Transactional
    public Ask getAsk(Long id) {
        askRepository.hits(id);
        askRepository.flush();
        Optional<Ask> askOptional= askRepository.findById(id);
        return askOptional.orElse(null);
    }

    public List<Ask> getAskOfFrequency() {
        return askRepository.findAllDesc();
    }
}
