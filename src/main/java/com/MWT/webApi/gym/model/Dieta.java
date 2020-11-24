package com.MWT.webApi.gym.model;

import java.util.Date;
import java.util.List;
import java.util.Set;

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
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "dieta")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dieta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false)
	public int id;	
	@CreationTimestamp
	@Column(name = "data_inserimento")
	private Date dataInserimento;	
	@Column(name = "data_scadenza")
	private Date dataScadenza;	
	@Column(name = "info")
	private String info;
	@Column(name = "nome")
	private String nome;
	@Column(name = "kcal")
	public int kcal;	
	
	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY)
    private Utente utente;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "dieta_alimento", joinColumns = 
	@JoinColumn(name = "dieta_id", referencedColumnName = "id", nullable = true), 
				inverseJoinColumns = @JoinColumn(name = "alimento_id", referencedColumnName = "id"))
	private List<Alimento> alimenti;


}
