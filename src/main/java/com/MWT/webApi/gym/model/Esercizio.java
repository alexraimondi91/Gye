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
@Table(name = "esercizio")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Esercizio {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false)
	public int id;
	@Column(name = "descrizione")
	private String descrizione;
	@Column(name = "nome")
	private String nome;
	@Column(name = "url")
	private String url;
	@Column(name = "kg")
	private String kg;	
	@Column(name = "ripetizioni")
	private String ripetizioni;

}
