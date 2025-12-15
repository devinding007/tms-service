package jp.co.basenet.weolab.tms_service.personeltalents.controller;

import jp.co.basenet.weolab.tms_service.personeltalents.dto.TalentDTO;
import jp.co.basenet.weolab.tms_service.personeltalents.service.TalentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/api/talents")
@RequiredArgsConstructor
public class TalentController {

    private final TalentService talentService;

    // 全件取得
    @GetMapping
    public List<TalentDTO> getTalents() {
        return talentService.getAllTalents();
    }

    // ★★★ 追加: ID指定で1件取得 ★★★
    @GetMapping("/{id}")
    public ResponseEntity<TalentDTO> getTalentById(@PathVariable String id) {
        TalentDTO talent = talentService.getTalentById(id);
        if (talent == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(talent);
    }

    // 新規追加
    @PostMapping
    public TalentDTO createTalent(@RequestBody TalentDTO dto) {
        return talentService.createTalent(dto);
    }

    /**
     * 指定されたIDの人材データを更新する
     */
    @PutMapping("/{id}")
    public TalentDTO updateTalent(
            @PathVariable String id,
            @RequestBody TalentDTO dto) {
        // パスパラメータのIDとDTO内のtalentIdが一致することを確認
        if (dto.getTalentId() == null) {
            dto.setTalentId(id);
        } else if (!id.equals(dto.getTalentId())) {
            throw new IllegalArgumentException("パスパラメータのIDとDTO内のtalentIdが一致しません");
        }
        return talentService.updateTalent(dto);
    }


    @PatchMapping("/{id}/soft-delete")
    public void softDeleteTalent(@PathVariable String id) {
        talentService.softDeleteTalent(id);
    }
}

