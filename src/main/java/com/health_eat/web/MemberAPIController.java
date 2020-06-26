package com.health_eat.web;


import com.health_eat.domain.member.MemberService;
import com.health_eat.domain.member.dto.MemberRequest;
import com.health_eat.domain.Result;
import com.health_eat.web.dto.common.PageableSearchRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
@RequestMapping(value = "/api")
@RequiredArgsConstructor
public class MemberAPIController {

    private final MemberService memberService;

    @PostMapping(value = "/members", consumes = {"application/json"})
    public ResponseEntity<Result> joinMember(@RequestBody MemberRequest memberRequest){
        Result result;
        try {
            memberService.saveMember(memberRequest);
            result = Result.OK(null);
        }catch (Exception e){
            result = Result.ERROR(e.getMessage());
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/members/list")
    public ResponseEntity<Result> memberList(PageableSearchRequest req){
        Result result;
        try {
            result = Result.OK( memberService.memberList(PageRequest.of(req.getPage(), req.getSize(), Sort.Direction.DESC, "id")));
        }catch (Exception e){
            result = Result.ERROR(e.getMessage());
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/members/login")
    public  ResponseEntity<Result> login(@RequestBody MemberRequest req, HttpServletResponse response){
        Result result;
        try {
            Map<String, String> tokenMap = memberService.login(req);
            response.setHeader("accessToken", tokenMap.get("accessToken"));
           result= Result.OK(null);

        }catch (Exception e){
            result = Result.ERROR(e.getMessage());
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/members/logout")
    public  ResponseEntity<Result> logout( HttpServletResponse response){
        Result result;
        try {
            String accessToken = response.getHeader("accessToken");
            memberService.logout(accessToken);
            response.setHeader("accessToken", "");
            result= Result.OK(null);

        }catch (Exception e){
            result = Result.ERROR(e.getMessage());
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
