package jp.co.basenet.weolab.tms_service.personeltalents.service;

import jp.co.basenet.weolab.tms_service.personeltalents.dto.TalentDTO;
import jp.co.basenet.weolab.tms_service.personeltalents.entity.Talent;
import jp.co.basenet.weolab.tms_service.personeltalents.repository.TalentRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TalentService {

    private final TalentRepository talentRepository;

    /**
     * 全人材データ取得
     */
    public List<TalentDTO> getAllTalents() {
        return talentRepository.findActiveTalents().stream()
                .map(entity -> {
                    TalentDTO dto = new TalentDTO();
                    BeanUtils.copyProperties(entity, dto);
                    return dto;
                })
                .toList();
    }

    /**
     * ★★★ 追加: ID指定で1件の人材データを取得 ★★★
     */
    public TalentDTO getTalentById(String id) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("IDが指定されていません");
        }

        return talentRepository.findById(id)
                .map(entity -> {
                    TalentDTO dto = new TalentDTO();
                    BeanUtils.copyProperties(entity, dto);
                    return dto;
                })
                .orElse(null); // 見つからない場合はnullを返す
    }
    /**
     * 人材データ追加
     */
    public TalentDTO createTalent(TalentDTO dto) {
        Talent entity = new Talent();


        if (dto.getTalentId() == null || dto.getTalentId().isBlank()) {
            entity.setTalentId(UUID.randomUUID().toString());
        } else {
            entity.setTalentId(dto.getTalentId());
        }
        BeanUtils.copyProperties(dto, entity, "talentId"); // DTO → Entity

        Talent saved = talentRepository.save(entity);

        TalentDTO result = new TalentDTO();
        BeanUtils.copyProperties(saved, result); // Entity → DTO
        return result;
    }

    /**
     * 既存の人材データを更新する
     */
    public TalentDTO updateTalent(TalentDTO dto) {
        // 更新時にはtalentIdが必須
        if (dto.getTalentId() == null || dto.getTalentId().isBlank()) {
            throw new IllegalArgumentException("更新時にはtalentIdが必須です");
        }

        // 指定されたIDのレコードが存在するか確認
        if (!talentRepository.existsById(dto.getTalentId())) {
            throw new RuntimeException("指定されたIDの人材データが見つかりません: " + dto.getTalentId());
        }

        Talent entity = new Talent();
        BeanUtils.copyProperties(dto, entity); // DTO → Entity（全フィールドをコピー）

        Talent saved = talentRepository.save(entity); // IDが存在するためUPDATEが実行される

        TalentDTO result = new TalentDTO();
        BeanUtils.copyProperties(saved, result); // Entity → DTO
        return result;
    }

    /**
     * 指定されたIDの人材を論理削除する（deletedFlag を true に更新）
     */
    @Transactional
    public void softDeleteTalent(String id) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("IDが指定されていません");
        }
        Talent entity = talentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("人材データが見つかりません: " + id));

        entity.setDeletedFlag(true);
        talentRepository.save(entity);
    }
}
