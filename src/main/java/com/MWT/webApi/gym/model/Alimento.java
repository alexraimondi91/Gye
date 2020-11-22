package com.MWT.webApi.gym.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "alimento")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Alimento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false)
	public int id;
	@Column(name = "descrizione")
	private String descrizione;
	@Column(name = "nome")
	private String nome;
	@Column(name = "grassi")
	public int grassi;
	@Column(name = "proteine")
	public int proteine;
	@Column(name = "carboidrati")
	public int carboidrati;

}
