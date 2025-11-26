package jp.co.basenet.weolab.tms_service.personel.dto;
import com.fasterxml.jackson.annotation.JsonProperty;
public class ResumeSkillScore {
    @JsonProperty("スキル名") private String skillName;
    @JsonProperty("点数") private int score;
    public String getSkillName(){return skillName;} public void setSkillName(String v){skillName=v;}
    public int getScore(){return score;} public void setScore(int v){score=v;}
}
