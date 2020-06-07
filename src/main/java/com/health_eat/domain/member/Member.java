package com.health_eat.domain.member;

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
@Table(schema = "health_eat")
public class Member extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    @Column(nullable = false, length = 10)
    private String memberId;

    @Column(nullable = false)
    private String memberPW;

    @Column(length = 100, nullable = false)
    private String memberName;

//    @Enumerated(EnumType.STRING)
//    @Column(nullable = false)
//    private DeleteState deleteState;

    @Builder
    Member(String memberName, String memberPW, String memberId){
        this.memberName = memberName;
        this.memberPW = memberPW;
        this.memberId = memberId;
    }
}
