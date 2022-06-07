package com.dinnerdash.backend.models;

import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class Roles {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id; //This is primary key

	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private ERoles name;

	public Roles() {
	}

	public Roles(Integer id, ERoles name) {
		this.id = id;
		this.name = name;
	}

	public Roles(ERoles name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ERoles getName() {
		return name;
	}
    
	public void setName(ERoles name) {
		this.name = name;
	}
}
