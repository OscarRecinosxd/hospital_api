package com.grupo25.hospital.models.entities;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity(name = "token")
public class Token {
	@Id
	@Column(name = "id_token")
	@SequenceGenerator(name = "token_id_gen", sequenceName = "token_id_token_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "token_id_gen")
	private Long id_token;
	
	@Column(name = "content")
	private String content;
	
	@Column(name = "active", insertable = false)
	private Boolean active;
	
	@Column(name = "timestamp", insertable = false, updatable = false)
	private Timestamp timestamp;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_person")
	private Person person;

	public Token() {
		super();
	}

	public Token(String content, Person person) {
		super();
		this.content = content;
		this.person = person;
	}

	public Long getId_token() {
		return id_token;
	}

	public void setId_token(Long id_token) {
		this.id_token = id_token;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
	
	@Override
	public String toString() {
		return "Token [id=" + id_token + ", content=" + content + ", active=" + active + ", timestamp=" + timestamp
				+ ", person=" + person + "]";
	}
}
