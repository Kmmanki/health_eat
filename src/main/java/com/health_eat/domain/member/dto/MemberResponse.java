package com.health_eat.domain.member.dto;

import com.health_eat.domain.DeleteState;
import com.health_eat.domain.member.Members;

import java.time.LocalDateTime;

public class MemberResponse  {
    private long id;
    private String memberId;
    private String memberName;
    private DeleteState deleteState;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public MemberResponse(Members member){
        this.id = member.getId();
        this.memberId = member.getMemberId();
        this.memberName = member.getMemberName();
        this.deleteState = member.getDeleteState();
        this.createdAt = member.getCreatedAt();
        this.modifiedAt = member.getModifiedAt();
    }
}
