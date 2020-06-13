package com.health_eat.domain.member.dto;

import com.health_eat.domain.DeleteState;
import com.health_eat.domain.member.Members;
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



    @Builder
    public MemberRequest(String memberName, String memberPw, String memberId, DeleteState deleteState){
        this.memberName = memberName;
        this.memberPw = memberPw;
        this.memberId = memberId;
        this.deleteState =deleteState;
    }

    public Members toMember(){

        return Members.builder()
                .memberName(this.memberName)
                .memberPw(this.memberPw)
                .memberId(this.memberId)
                .deleteState(this.deleteState).build();
    }
}
