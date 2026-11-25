package com.example.dynamicform.model;
import jakarta.persistence.*;
import java.util.*;
@Entity
public class DynamicForm {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false) private String title;
 @Column(length=1500) private String description;
 private boolean published;
 private Long createdBy;
 @OneToMany(mappedBy="form",cascade=CascadeType.ALL,orphanRemoval=true,fetch=FetchType.EAGER)
 private List<FormField> fields=new ArrayList<>();
 public DynamicForm(){}
 public Long getId(){return id;} public void setId(Long v){id=v;} public String getTitle(){return title;} public String getDescription(){return description;}
 public boolean isPublished(){return published;} public Long getCreatedBy(){return createdBy;} public List<FormField> getFields(){return fields;}
 public void setTitle(String v){title=v;} public void setDescription(String v){description=v;} public void setPublished(boolean v){published=v;}
 public void setCreatedBy(Long v){createdBy=v;}
 public void setFields(List<FormField> values){fields.clear();if(values!=null)values.forEach(this::addField);}
 public void addField(FormField f){f.setForm(this);fields.add(f);}
}
