/*package com.AnkitIndia.jwtauthentication.model;


import javax.persistence.*;

import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "testusers", catalog = "ankitindia")
public class Testusers {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Integer id;
    @Column(name = "name")
    private String name;
    private Integer salary;
    private String teamName;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private List<Testuserslog> usersLogs;
    
    @OneToOne(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL,
            mappedBy = "testusers")
    private Testuserscontact testuserscontact;
    
}

*/