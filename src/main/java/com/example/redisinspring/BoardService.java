package com.example.redisinspring;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {
    private BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    // @Cacheable: 메서드 실행 결과를 캐시에 저장
    // Cache Aside 전략으로 캐싱 적용 (cacheNames: 캐시 이름을 설정 / key: Redis에 저장할 Key의 이름을 설정(#변수: 매개변수 값) / cacheManager: 사용할 cacheManager의 Bean 이름을 지정)
    @Cacheable(cacheNames = "getBoards", key = "'boards:page:' + #page + ':size:' + #size", cacheManager = "boardCacheManager")
    public List<Board> getBoards(int page, int size) { // page: 요청 페이지 번호, size: 한 페이지에 표시할 데이터 수
        Pageable pageable = PageRequest.of(page - 1, size); // JPA에서 페이징은 0부터 시작하므로 입력한 페이지 값에 1을 뺌
        Page<Board> pageOfBoards = boardRepository.findAllByOrderByCreatedAtDesc(pageable);
        return pageOfBoards.getContent();
    }
}
