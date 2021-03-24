package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
public abstract class AbstractBaseEntity implements Persistable<Long>, Serializable {

	@org.springframework.data.annotation.Id
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	@Transient
	@org.springframework.data.annotation.Transient
	@JsonIgnore
	public boolean isNew() {
		return id == null;
	}

}
