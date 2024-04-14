/*package com.AnkitIndia.jwtauthentication.model;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
	@Entity
	@Table(name = "testuserscontact", catalog = "ankitindia")
	public class Testuserscontact implements Serializable{

	    @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    @Column(name = "id")
	    private Integer id;
	    private Integer phoneNo;
	    
	    @OneToOne(fetch = FetchType.LAZY, optional = false)
	    @JoinColumn(name = "user_id", nullable = false)
	    private Testusers testusers;

	}

*/