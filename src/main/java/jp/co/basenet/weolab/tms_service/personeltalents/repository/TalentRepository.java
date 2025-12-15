package jp.co.basenet.weolab.tms_service.personeltalents.repository;

import jp.co.basenet.weolab.tms_service.personeltalents.entity.Talent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

@Repository
public interface TalentRepository extends JpaRepository<Talent, String> {
    @Query("SELECT t FROM Talent t WHERE t.deletedFlag = false OR t.deletedFlag IS NULL")
    List<Talent> findActiveTalents();

}
