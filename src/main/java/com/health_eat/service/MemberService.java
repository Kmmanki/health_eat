package com.health_eat.service;

import ch.qos.logback.core.encoder.EchoEncoder;
import com.health_eat.domain.DeleteState;
import com.health_eat.domain.member.Member;
import com.health_eat.domain.member.MemberRepository;
import com.health_eat.web.dto.MemberRequest;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jdk.nashorn.internal.runtime.options.Option;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepo;

    public void saveMember(MemberRequest req) throws  Exception{
        boolean isExists = memberRepo.existsByMemberId(req.getMemberId());
        if(isExists == false){
            req.setMemberPw(BCrypt.hashpw(req.getMemberPw(), BCrypt.gensalt()));
            memberRepo.save(req.toMember());
        }else{
            throw new Exception("중복된 아이디입니다.");
        }
    }

    public Page<Member> memberList(PageRequest pageRequest){
        Page<Member> memberPage = memberRepo.findAll(pageRequest);
        return memberPage;
    }

    public String login(String id, String pw) throws  Exception{
    Member member = memberRepo.findByMemberId(id).orElseThrow(() -> new Exception("존재하지 않는 아이디입니다."));
    if(!BCrypt.checkpw(pw,member.getMemberPw())){
        throw new Exception("비밀번호가 일치하지 않습니다.");
    }else{
        Map<String, Object> obj = new HashMap<>();
        obj.put("id", member.getMemberId());
        obj.put("name", member.getMemberName());
        String jwtString = Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("issueDate", System.currentTimeMillis())
                .setClaims(obj)
                .signWith(SignatureAlgorithm.HS512 , "secret".getBytes("utf-8"))
                .compact();
        System.out.println(jwtString);
    return jwtString;
    }

    }
}
