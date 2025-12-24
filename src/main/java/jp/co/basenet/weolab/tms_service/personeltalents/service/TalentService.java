package jp.co.basenet.weolab.tms_service.personeltalents.service;

import jp.co.basenet.weolab.tms_service.personeltalents.dto.TalentDTO;
import jp.co.basenet.weolab.tms_service.personeltalents.entity.TalentDoc;
import jp.co.basenet.weolab.tms_service.personeltalents.repository.TalentdocRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TalentService {


    private final TalentdocRepository talentdocRepository;

    /**
     * 全人材データ取得
     */
    public List<TalentDTO> getAllTalents() {
        return talentdocRepository.findActiveTalents().stream()
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

        return talentdocRepository.findById(id)
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
        TalentDoc entity = new TalentDoc();


        if (dto.getTalentId() == null || dto.getTalentId().isBlank()) {
            entity.setTalentId(UUID.randomUUID().toString());
        } else {
            entity.setTalentId(dto.getTalentId());
        }
        BeanUtils.copyProperties(dto, entity, "talentId"); // DTO → Entity

        TalentDoc saved = talentdocRepository.save(entity);

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
        if (!talentdocRepository.existsById(dto.getTalentId())) {
            throw new RuntimeException("指定されたIDの人材データが見つかりません: " + dto.getTalentId());
        }

        TalentDoc entity = new TalentDoc();
        BeanUtils.copyProperties(dto, entity); // DTO → Entity（全フィールドをコピー）

        TalentDoc saved = talentdocRepository.save(entity); // IDが存在するためUPDATEが実行される

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
        TalentDoc entity = talentdocRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("人材データが見つかりません: " + id));

        entity.setDeletedFlag(true);
        talentdocRepository.save(entity);
    }
    /**
     * 複数の人材IDに基づいて人材情報を一括取得
     *
     * @param talentIds 人材IDリスト（nullまたは空の場合は空リストを返す）
     * @return 対応するTalentDTOリスト
     */
    public List<TalentDTO> getTalentsByIds(List<String> talentIds) {
        if (talentIds == null || talentIds.isEmpty()) {
            return List.of();
        }
        return talentdocRepository.findAllById(talentIds).stream()
                .map(entity -> {
                    TalentDTO dto = new TalentDTO();
                    BeanUtils.copyProperties(entity, dto);
                    return dto;
                })
                .toList();
    }
}
