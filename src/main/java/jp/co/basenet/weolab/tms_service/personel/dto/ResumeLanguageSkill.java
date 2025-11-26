package jp.co.basenet.weolab.tms_service.personel.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResumeLanguageSkill {
    @JsonProperty("言語名")
    private String name;

    @JsonProperty("読み")
    private String reading;

    @JsonProperty("書き")
    private String writing;

    @JsonProperty("会話")
    private String speaking;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getReading() { return reading; }
    public void setReading(String reading) { this.reading = reading; }
    public String getWriting() { return writing; }
    public void setWriting(String writing) { this.writing = writing; }
    public String getSpeaking() { return speaking; }
    public void setSpeaking(String speaking) { this.speaking = speaking; }
}
