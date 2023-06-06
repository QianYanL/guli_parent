package com.atguigu.educenter.controller;


import com.atguigu.commonutils.JwtUtils;
import com.atguigu.commonutils.R;
import com.atguigu.commonutils.ordervo.UcenterMemberOrder;
import com.atguigu.educenter.entity.UcenterMember;
import com.atguigu.educenter.entity.vo.RegisterVo;
import com.atguigu.educenter.service.UcenterMemberService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2023-05-31
 */
@RestController
@RequestMapping("/educenter/member")
@CrossOrigin
public class UcenterMemberController {
    @Autowired
    private UcenterMemberService memberservice;
    @PostMapping("login")
    public R loginUser(@RequestBody UcenterMember member){
        String token = memberservice.login(member);
        return R.ok().data("token",token);
    }

    @PostMapping("register")
    public R registerUser(@RequestBody RegisterVo registerVo){
        memberservice.register(registerVo);
        return R.ok();
    }

    @GetMapping("getMemberInfo")
    public R getMemberInfo(HttpServletRequest request){
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        UcenterMember member = memberservice.getById(memberId);
        return R.ok().data("userInfo",member);
    }
    @PostMapping("getUserInfoOrder/{id}")
    public UcenterMemberOrder getUserInfoOrder(@PathVariable String id){
        UcenterMember ucenterMember = memberservice.getById(id);
        UcenterMemberOrder ucenterMemberOrder = new UcenterMemberOrder();
        BeanUtils.copyProperties(ucenterMember,ucenterMemberOrder);
        return ucenterMemberOrder;
    }
}

