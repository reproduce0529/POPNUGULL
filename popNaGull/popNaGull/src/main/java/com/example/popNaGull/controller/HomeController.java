package com.example.popNaGull.controller;

import com.example.popNaGull.dto.DataDto;
import com.example.popNaGull.entity.Nugull;
import com.example.popNaGull.repository.NugullRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final NugullRepository nugullRepository;

    @GetMapping("/")
    public ModelAndView goToHome() {
        ModelAndView mv = new ModelAndView("/index");

        HttpServletRequest req = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
        String ip = req.getHeader("X-FORWARDED-FOR");
        if (ip == null) {
            ip = req.getRemoteAddr();
        }

        Optional<Nugull> nugull = nugullRepository.findByUserIP(ip);
        Long value;

        if (nugull.isPresent()) {
            value = nugull.get().getUserCnt();
        } else {
            value = 0L;
        }

        List<Nugull> nugulls = nugullRepository.findAll(Sort.by(Sort.Direction.DESC, "userCnt"));

        System.out.println(nugulls.get(0).getUserCnt());

        mv.addObject("userip",  ip);
        mv.addObject("value",  value);
        mv.addObject("rank", nugulls);

        return mv;
    }


    @PostMapping("/post")
    public String InsertData(DataDto dataDto) {
        String ip = dataDto.getIp();
        Long value = dataDto.getValue();
        Optional<Nugull> nugullOpt = nugullRepository.findByUserIP(ip);
        Nugull nugull;

        if (nugullOpt.isPresent()) {
            nugull = nugullOpt.get();

            nugull.setUserCnt(value);
        } else {
            nugull = Nugull.builder()
                    .userIdx(null)
                    .userIP(ip)
                    .userCnt(0L).build();
        }

        nugullRepository.save(nugull);

        return "redirect:/";
    }
}
