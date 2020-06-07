package com.health_eat.service;

import com.health_eat.domain.member.MemberRepository;
import com.health_eat.web.dto.MemberRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepo;

    public void saveMember(MemberRequest req){
//        boolean isExists = memberRepo.existsByMemberId(req.getMemberId());
//        if(isExists == false){
            System.out.println(req.getMemberPW());
            req.setMemberPW(BCrypt.hashpw(req.getMemberPW(), BCrypt.gensalt()));
            System.out.println(req.getMemberPW());
            memberRepo.save(req.toMember());

//        }
    }
}
