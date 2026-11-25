package com.example.dynamicform.dto;
import jakarta.validation.constraints.NotNull;
import java.util.Map;
public class SubmissionRequest {
 @NotNull private Map<String,Object> answers;
 public Map<String,Object> getAnswers(){return answers;} public void setAnswers(Map<String,Object> v){answers=v;}
}
