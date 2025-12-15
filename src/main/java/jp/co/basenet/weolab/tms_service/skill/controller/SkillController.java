package jp.co.basenet.weolab.tms_service.skill.controller;

import jp.co.basenet.weolab.tms_service.skill.dto.PersonnelSkillPayload;
import jp.co.basenet.weolab.tms_service.skill.service.SkillService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Collections;
import java.util.List;

/**
 * スキルデータの REST API コントローラー
 * - GET /api/skill/{talentId} : スキル取得
 * - PUT /api/skill/{talentId} : スキル保存（全量上書き）
 */
@RestController
@RequestMapping("/api/skill")
@RequiredArgsConstructor
public class SkillController {

    private final SkillService skillService;

    /**
     * 指定された人材IDのスキルデータを取得する
     */
    @GetMapping("/{talentId}")
    public ResponseEntity<PersonnelSkillPayload> getSkill(@PathVariable String talentId) {
        PersonnelSkillPayload payload = skillService.getSkillPayloadByTalentId(talentId);
        if (payload == null) {
            payload = new PersonnelSkillPayload();
            payload.set人材ＩＤ(talentId);
            payload.setスキル(Collections.emptyList());
        }
        return ResponseEntity.ok(payload);
    }

    /**
     * スキルデータを保存する（全量上書き）
     */
    @PutMapping("/{talentId}")
    public ResponseEntity<Void> saveSkill(
            @PathVariable String talentId,
            @RequestBody PersonnelSkillPayload payload
    ) {
        // パスパラメータとDTOのIDを整合性チェック（任意）
        if (!talentId.equals(payload.get人材ＩＤ())) {
            return ResponseEntity.badRequest().build();
        }
        skillService.saveSkillPayload(payload);
        return ResponseEntity.ok().build();
    }

    // SkillController.java に追加
    @GetMapping("/options")
    public List<String> getSkillNames() {
        return skillService.getAllSkillNames(); // 全スキル名を重複除去して返す
    }
}