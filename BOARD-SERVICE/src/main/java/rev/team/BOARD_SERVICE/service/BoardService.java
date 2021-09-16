/*
package rev.team.BOARD_SERVICE.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import rev.team.BOARD_SERVICE.domain.entity.Board;
import rev.team.BOARD_SERVICE.domain.dto.BoardDTO;
import rev.team.BOARD_SERVICE.domain.repository.BoardRepository;
import rev.team.BOARD_SERVICE.util.Category;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BoardService {

    private final BoardRepository boardRepository;
    
    @Autowired
    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public ResponseEntity<Board> getPost(Long id) {
        Optional<Board> post = boardRepository.findById(id);
        if(post.isPresent()) return ResponseEntity.ok(post.get());
        else return ResponseEntity.notFound().build();
    }

    public Board save(Board board) {
        boardRepository.save(board);
        return board;
    }

    public String deletePost(Long id) {
        boardRepository.deleteById(id);
        return "OK";
    }

    @Transactional
    public String updatePost(Long id, String title) {
        boardRepository.updateById(id, title);
        return "OK";
    }

    public List<BoardDTO> getMainPosts() {
        Pageable pageable = PageRequest.of(0, 10, Sort.Direction.DESC, "boardId");
//        RestTemplate restTemplate = new RestTemplate();

        List<BoardDTO> boardDTOs= new ArrayList<>();
        for(Board board : boardRepository.findAllByStatusAndCategory(1, Category.Announcement ,pageable)){
//            String nickname = restTemplate.getForObject("http://localhost:8760/nickname?id="+board.getUserId() ,String.class);
            boardDTOs.add(BoardDTO.builder()
                    .boardId(board.getBoardId())
                    .category(board.getCategory())
                    .content(board.getContent())
                    .hits(board.getHits())
                    .nickname("관리자")
                    .postDate(board.getPostDate())
                    .title(board.getTitle())
                    .status(board.getStatus())
                    .build());
        }

        return boardDTOs;
    }
}
*/
