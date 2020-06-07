package com.health_eat.web.dto;

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
    private String memberPW;
    private String memberName;

    @Builder
    public MemberRequest(String memberName, String memberPW, String memberId){
        this.memberName = memberName;
        this.memberPW = memberPW;
        this.memberId = memberId;
    }

    public Member toMember(){
        Member member = Member.builder()
                .memberName(this.memberName)
                .memberPW(this.memberPW)
                .memberId(this.memberId).build();
        return member;
    }
}
