package hcy.secondhandmarket.repository.siggarea;

import hcy.secondhandmarket.domain.SidoArea;
import hcy.secondhandmarket.domain.SiggArea;
import hcy.secondhandmarket.repository.sidoarea.SidoAreaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SiggAreaRepositoryTest {

    @Autowired
    SiggAreaRepository siggAreaRepository;

    @Autowired
    SidoAreaRepository sidoAreaRepository;

    @Rollback
    @Test
    public void findAllSigg() throws Exception {
        SidoArea seoul = SidoArea.builder().name("서울시").build();
        sidoAreaRepository.save(seoul);

        SiggArea songpa = SiggArea.builder().name("송파구").sidoArea(seoul).build();
        siggAreaRepository.save(songpa);
        SiggArea gangnam = SiggArea.builder().name("강남구").sidoArea(seoul).build();
        siggAreaRepository.save(gangnam);

        List<SiggArea> result = siggAreaRepository.findAllBySidoName(seoul.getName());

        for (SiggArea siggArea : result) {
            System.out.println(siggArea);
        }
    }

}