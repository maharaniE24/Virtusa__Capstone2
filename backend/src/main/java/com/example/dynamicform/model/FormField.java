package com.example.dynamicform.model;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.*;
@Entity
public class FormField {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 private String label; private String type; private boolean required;
 @ElementCollection(fetch=FetchType.EAGER) private List<String> options=new ArrayList<>();
 @JsonIgnore @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="form_id") private DynamicForm form;
 public FormField(){}
 public Long getId(){return id;} public String getLabel(){return label;} public String getType(){return type;}
 public boolean isRequired(){return required;} public List<String> getOptions(){return options;} public DynamicForm getForm(){return form;}
 public void setLabel(String v){label=v;} public void setType(String v){type=v;} public void setRequired(boolean v){required=v;}
 public void setOptions(List<String> v){options=v==null?new ArrayList<>():v;} public void setForm(DynamicForm v){form=v;}
}
