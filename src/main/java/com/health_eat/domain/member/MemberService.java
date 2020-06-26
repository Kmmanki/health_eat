package com.health_eat.domain.member;

import com.health_eat.domain.member.dto.MemberRequest;
import com.health_eat.domain.token.JwtRedisRepository;
import com.health_eat.domain.token.RefreshToken;
import com.health_eat.domain.token.RefreshTokenRepository;
import com.health_eat.domain.token.Token;
import com.health_eat.util.JWTUtil;
import com.health_eat.web.dto.common.PageModel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final JwtRedisRepository redisRepository;
    private final MembersRepository memberRepo;
    private final HttpServletResponse httpResopnse;
    private final RefreshTokenRepository refreshTokenRepository;

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

    public Map<String,String> login(MemberRequest req) throws  Exception{
        Map<String,String> tokenMap = new HashMap<>();
        Members member = memberRepo.findByMemberId(req.getMemberId());

    if(!BCrypt.checkpw(req.getMemberPw(),member.getMemberPw())){
        throw new Exception("비밀번호가 일치하지 않거나 존재하지 않는 아이디");
    }else{
        Map<String, Object> obj = new HashMap<>();
        obj.put("memberId", member.getMemberId());
        obj.put("memberName", member.getMemberName());
        String accessToken = JWTUtil.createToken(obj);
        String refreshToken = JWTUtil.createRefreshToken(obj);
        Token t = redisRepository.save(Token.builder().jwt(jwt).cratedAt(LocalDateTime.now()).build());
        refreshTokenRepository.save(RefreshToken.builder().jwt(refreshToken).build());
        tokenMap.put("accessToken", accessToken);
        tokenMap.put("refreshToken", refreshToken);
        return tokenMap;
    }

    }

    public void logout(String accessToken){
        redisRepository.deleteById(accessToken);
        refreshTokenRepository.deleteById(accessToken);
    }
}
