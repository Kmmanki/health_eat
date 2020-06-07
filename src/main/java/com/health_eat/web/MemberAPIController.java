package com.health_eat.web;


import com.health_eat.service.MemberService;
import com.health_eat.web.dto.MemberRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api", consumes = {"application/json"})
@RequiredArgsConstructor
public class MemberAPIController {

    private final MemberService memberService;

    @PostMapping("/members")
    public void joinMember(@RequestBody MemberRequest memberRequest){
        memberService.saveMember(memberRequest);

    }
}
