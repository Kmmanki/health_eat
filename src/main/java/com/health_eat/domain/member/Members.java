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
public class Members extends BaseTimeEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id ;

    @Column(nullable = false)
    private String memberId;

    @JsonIgnore
    @Column(nullable = false, name = "member_pw")
    private String memberPw;

    @Column( nullable = false)
    private String memberName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DeleteState deleteState;

    @Builder
    Members(String memberName, String memberPw, String memberId, DeleteState deleteState){
        this.memberName = memberName;
        this.memberPw = memberPw;
        this.memberId = memberId;
        this.deleteState = deleteState;
    }
}
