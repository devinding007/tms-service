package jp.co.basenet.weolab.tms_service.personel.dto;
import com.fasterxml.jackson.annotation.JsonProperty;
public class ResumeStrengthArea {
    @JsonProperty("領域") private String domain;
    @JsonProperty("理由") private String reason;
    public String getDomain(){return domain;} public void setDomain(String v){domain=v;}
    public String getReason(){return reason;} public void setReason(String v){reason=v;}
}
