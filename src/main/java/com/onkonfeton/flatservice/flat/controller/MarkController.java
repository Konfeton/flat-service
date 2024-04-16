package com.onkonfeton.flatservice.flat.controller;

import com.onkonfeton.flatservice.flat.model.EvaluatedFlats;
import com.onkonfeton.flatservice.flat.repository.EvaluatedFlatsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1/marks")
public class MarkController {

    private final EvaluatedFlatsRepository  evaluatedFlatsRepository;
    @PostMapping
    public void addMarks(@RequestBody MarksDTO marks){
        return;
    }
    @GetMapping
    public EvaluatedFlats getMarks(){
        return evaluatedFlatsRepository.getReferenceById(1L);
    }
}
