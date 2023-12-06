package com.jjaekkag.jjaekkagisland.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jeongheekim
 * @date 12/2/23
 */
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "store")
public class Store extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long storeSeq;
    private String address;

    @Size(max = 50)
    private String name;
    @OneToMany(mappedBy = "store")
    private List<Lesson> lessons = new ArrayList<>();

}
