package jp.co.basenet.weolab.tms_service.exam.dto;

public class SkillReflectionResult {
    private String スキル名;
    private Integer スキル点数更新前;
    private Integer スキル点数更新後;

    public String getスキル名() {
        return スキル名;
    }

    public void setスキル名(String スキル名) {
        this.スキル名 = スキル名;
    }

    public Integer getスキル点数更新前() {
        return スキル点数更新前;
    }

    public void setスキル点数更新前(Integer スキル点数更新前) {
        this.スキル点数更新前 = スキル点数更新前;
    }

    public Integer getスキル点数更新後() {
        return スキル点数更新後;
    }

    public void setスキル点数更新後(Integer スキル点数更新後) {
        this.スキル点数更新後 = スキル点数更新後;
    }
}