package com.hirehub.skill.controller;

import com.hirehub.skill.dto.CreateSkillRequest;
import com.hirehub.skill.dto.SkillResponse;
import com.hirehub.skill.service.SkillService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.web.ReactiveOffsetScrollPositionHandlerMethodArgumentResolver;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/skills")
@RequiredArgsConstructor
public class SkillController {

    private final SkillService skillService;

    @PostMapping
    public ResponseEntity<SkillResponse> createSkill(@Valid @RequestBody CreateSkillRequest request){
        SkillResponse response = skillService.createSkill(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }
}
