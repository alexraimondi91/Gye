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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "scheda_esercizio")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SchedaEsercizio {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false)
	public int id;
	@Column(name = "descrizione")
	private String descrizione;
	@Column(name = "nome")
	private String nome;
	@Column(name = "data_inserimento")
	private Date dataInserimento;
	
	@OneToMany(
	        mappedBy = "schedaEsercizio",
	        cascade = CascadeType.ALL
	    )
    private List<Utente> utenti;
	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "esercizio_id")
    private List<Esercizio> esercizi;

}
