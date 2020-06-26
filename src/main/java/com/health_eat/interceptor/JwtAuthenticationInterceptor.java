package com.health_eat.interceptor;

import com.health_eat.domain.token.JwtRedisRepository;
import com.health_eat.domain.token.RefreshToken;
import com.health_eat.domain.token.RefreshTokenRepository;
import com.health_eat.util.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public class JwtAuthenticationInterceptor extends HandlerInterceptorAdapter {

    private final JwtRedisRepository jwtRedisRepository;
    private final RefreshTokenRepository refreshTokenRepository;

    @Override
    @Transactional
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String accessToken = request.getHeader("accessToken");
        RefreshToken refreshToken = refreshTokenRepository.findById(accessToken).get();
        if(accessToken != null ){
            //1 ac 토큰 살음 && redis 있음 => return true
            //2 ac token 죽음  => refreshToken 가져와서 accesstoken 만들고 redis 추가 => refresh 추가
                //refresh 도 만료? exception

            if(JWTUtil.checkExpired(accessToken) && jwtRedisRepository.findById(accessToken).get().getJwt().equals(accessToken)){
                return true;
            }else if(!JWTUtil.checkExpired(accessToken) && JWTUtil.checkExpired(refreshToken.getRefreshToken())){

                Map<String, Object> obj = new HashMap<>();
                obj.put("memberId", JWTUtil.getFild(refreshToken.getRefreshToken(), "memberId"));
                obj.put("memberName", JWTUtil.getFild(refreshToken.getRefreshToken(), "memberName"));
                String newAccessToken = JWTUtil.createToken(obj);
                refreshToken.updateAcessToken(newAccessToken);
                response.setHeader("accessToken",newAccessToken);
                return true;
            }else{
                throw new Exception("로그인이 필요합니다.");
            }

            return true;

        }else{
            throw new Exception("로그인이 필요합니다.");
        }

    }


}
