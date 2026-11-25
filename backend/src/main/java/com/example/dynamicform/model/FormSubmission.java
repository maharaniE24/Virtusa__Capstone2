package com.example.dynamicform.model;
import jakarta.persistence.*;
import java.time.LocalDateTime;
@Entity
public class FormSubmission {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 private Long formId; private String formTitle; private Long userId; private String userName;
 @Column(columnDefinition="LONGTEXT") private String answers;
 private LocalDateTime submittedAt=LocalDateTime.now();
 public FormSubmission(){}
 public FormSubmission(Long formId,String formTitle,Long userId,String userName,String answers){this.formId=formId;this.formTitle=formTitle;this.userId=userId;this.userName=userName;this.answers=answers;}
 public Long getId(){return id;} public Long getFormId(){return formId;} public String getFormTitle(){return formTitle;}
 public Long getUserId(){return userId;} public String getUserName(){return userName;} public String getAnswers(){return answers;}
 public LocalDateTime getSubmittedAt(){return submittedAt;}
}
