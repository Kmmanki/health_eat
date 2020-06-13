package com.health_eat.domain.member;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.health_eat.domain.BaseTimeEntity;
import com.health_eat.domain.DeleteState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Member extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    @Column(nullable = false)
    private String memberId;

    @Column(nullable = false)
    private String memberPw;

    @Column( nullable = false)
    private String memberName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DeleteState deleteState;

    @Builder
    Member(String memberName, String memberPw, String memberId, DeleteState deleteState){
        this.memberName = memberName;
        this.memberPw = memberPw;
        this.memberId = memberId;
        this.deleteState = deleteState;
    }
}
