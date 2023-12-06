package com.jjaekkag.jjaekkagisland.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jeongheekim
 * @date 12/2/23
 */
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "member")
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberSeq;

    @Size(min = 10, max = 200)
    @Column(unique = true)
    private String email;

    @Size(min = 1, max = 20)
    private String name;

    @Size(min = 10, max = 1000)
    private String password;

    @JsonIgnore
    @OneToMany(mappedBy = "member")
    private List<Reservation> reservations = new ArrayList<>();

}
