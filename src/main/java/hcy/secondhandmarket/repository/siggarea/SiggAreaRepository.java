package hcy.secondhandmarket.repository.siggarea;

import hcy.secondhandmarket.domain.SiggArea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SiggAreaRepository extends JpaRepository<SiggArea, Long> {

    @Query("select sigg from SiggArea sigg left join sigg.sidoArea sido where sido.id = :id")
    List<SiggArea> findAllBySidoId(@Param("name") Long id);

}
