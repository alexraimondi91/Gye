package com.MWT.webApi.gym.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "utente")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Utente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false)
	private int id;
	@Column(name = "nome")
	private String nome;
	@Column(name = "cognome")
	private String cognome;
	@Column(name = "email")
	private String email;
	@Column(name = "data_nascita")
	private Date dataNascita;
	@Column(name = "user_name")
	private String userName;
	@Column(name = "password")
	private String password;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(	name = "utenti_ruoli", 
				joinColumns = @JoinColumn(name = "utente_id"), 
				inverseJoinColumns = @JoinColumn(name = "ruoli_id"))
	private List<Ruoli> roles;
	
	@ManyToOne(fetch = FetchType.LAZY , optional = true)
	private SchedaEsercizio schedaEsercizio;
	
	@OneToMany(
	        mappedBy = "utente",
	        cascade = CascadeType.ALL,
	        orphanRemoval = true
	    )
    private List<Dieta> diete;

}
