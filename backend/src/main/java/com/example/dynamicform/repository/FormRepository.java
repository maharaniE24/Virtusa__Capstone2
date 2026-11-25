package com.example.dynamicform.repository;
import com.example.dynamicform.model.DynamicForm;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface FormRepository extends JpaRepository<DynamicForm,Long>{List<DynamicForm> findByPublishedTrue();}
