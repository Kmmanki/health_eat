package com.health_eat.domain.member;

import com.health_eat.domain.member.dto.MemberRequest;
import com.health_eat.util.JWTUtil;
import com.health_eat.web.dto.common.PageModel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MembersRepository memberRepo;


    public void saveMember(MemberRequest req) throws  Exception{
        boolean isExists = memberRepo.existsMemberByMemberId(req.getMemberId());
        if(!isExists){
            req.setMemberPw(BCrypt.hashpw(req.getMemberPw(), BCrypt.gensalt()));
            memberRepo.save(req.toMember());
        }else{
            throw new Exception("중복된 아이디입니다.");
        }
    }

    public PageModel memberList(PageRequest pageRequest){
        Page<Members> memberPage = memberRepo.findAll(pageRequest);
        return new PageModel(memberPage);
    }

    public String login(MemberRequest req) throws  Exception{
    Members member = memberRepo.findByMemberId(req.getMemberId());
    if(!BCrypt.checkpw(req.getMemberPw(),member.getMemberPw())){
        throw new Exception("비밀번호가 일치하지 않거나 존재하지 않는 아이디");
    }else{
        Map<String, Object> obj = new HashMap<>();
        obj.put("id", member.getMemberId());
        obj.put("name", member.getMemberName());

        return JWTUtil.createToken(obj);
    }

    }
}
