package hcy.secondhandmarket.dto.page;

import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Data
public class PageResponseDTO<EN, DTO>{

    private List<DTO> dataList;
    private int page;
    private int size;
    private int totalPage;
    private int start, end;
    private boolean prev, next;

    private List<Integer> pageList = new ArrayList<>();

    public PageResponseDTO (Function<EN, DTO> fn, Page<EN> result) {
        dataList = result.stream().map(fn).collect(Collectors.toList());
        totalPage = result.getTotalPages();

        makePage(result.getPageable());
    }

    private void makePage(Pageable pageable) {

        page = pageable.getPageNumber() + 1;
        size = pageable.getPageSize();

        int tempEnd = (int) Math.ceil(page / 10.0) * 10;

        start = tempEnd - 9;

        prev = start > 1;

        end = totalPage > tempEnd ? tempEnd : totalPage;

        next = totalPage > end;

        pageList = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());

    }

}
