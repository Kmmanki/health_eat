package com.health_eat.web.dto;

import com.health_eat.domain.DeleteState;
import com.health_eat.domain.member.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@Setter
public class MemberRequest {
    private String memberId;
    private String memberPw;
    private String memberName;
    private DeleteState deleteState = DeleteState.N;

    private int size = 10;
    private int page = 0;

    @Builder
    public MemberRequest(String memberName, String memberPw, String memberId, DeleteState deleteState){
        this.memberName = memberName;
        this.memberPw = memberPw;
        this.memberId = memberId;
        this.deleteState =deleteState;
    }

    public Member toMember(){

        Member member = Member.builder()
                .memberName(this.memberName)
                .memberPw(this.memberPw)
                .memberId(this.memberId)
                .deleteState(this.deleteState).build();
        return member;
    }
}
