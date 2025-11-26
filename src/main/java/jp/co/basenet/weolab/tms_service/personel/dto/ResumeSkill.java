package jp.co.basenet.weolab.tms_service.personel.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResumeSkill {
    @JsonProperty("スキル名")
    private String name;
    @JsonProperty("レベル")
    private String level;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getLevel() { return level; }
    public void setLevel(String level) { this.level = level; }
}
