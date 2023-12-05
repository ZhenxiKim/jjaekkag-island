package com.jjaekkag.jjaekkagisland.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jeongheekim
 * @date 12/2/23
 */
@Getter
@Entity
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberSeq;

    @Size(min = 10, max = 20)
    @Column(unique = true)
    private String email;

    @Size(min = 1, max = 20)
    private String name;

    @Size(min = 10, max = 60)
    private String password;

    @JsonIgnore
    @OneToMany(mappedBy = "member")
    private List<Reservation> reservations = new ArrayList<>();

}
