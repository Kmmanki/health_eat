package com.health_eat.web;


import com.health_eat.domain.member.MemberService;
import com.health_eat.web.dto.MemberRequest;
import com.health_eat.web.dto.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "/api")
@RequiredArgsConstructor
public class MemberAPIController {

    private final MemberService memberService;

    @PostMapping(value = "/members", consumes = {"application/json"})
    public ResponseEntity<Result> joinMember(@RequestBody MemberRequest memberRequest){
        Result result = new Result();
        try {
            memberService.saveMember(memberRequest);
            result.setCode(200L);
            result.setMsg("가입되었습니다.");
        }catch (Exception e){
            result.setCode(400L);
            result.setMsg(e.getMessage());
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/members/{page}/{size}")
    public ResponseEntity<Result> memberList(@PathVariable int size, @PathVariable int page){
        Result result = new Result();
        result.setData( memberService.memberList(new PageRequest(page, size, Sort.Direction.DESC, "id")));
        result.setCode(200L);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/members/login/{id}/{pw}")
    public  ResponseEntity<Result> login(@PathVariable String id, @PathVariable String pw, HttpServletResponse response){
        Result result = new Result();
        try {
            response.setHeader("Authorization", memberService.login(id,pw));
            result.setCode(200L);
            result.setMsg("로그인 되었습니다.");

        }catch (Exception e){
            result.setCode(400L);
            result.setMsg(e.getMessage());
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
