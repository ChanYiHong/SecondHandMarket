package hcy.secondhandmarket.repository.emdarea;

import hcy.secondhandmarket.domain.EmdArea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmdAreaRepository extends JpaRepository<EmdArea, Long> {

    @Query("select e from EmdArea e left join e.siggArea s where s.id = :id")
    List<EmdArea> findAllBySiggId(@Param("id") Long id);

}
