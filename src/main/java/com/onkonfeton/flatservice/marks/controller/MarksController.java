package com.onkonfeton.flatservice.marks.controller;

import com.onkonfeton.flatservice.marks.service.MarksService;
import com.onkonfeton.flatservice.marks.controller.dto.MarksDto;
import com.onkonfeton.flatservice.marks.controller.dto.MarksResponse;
import com.onkonfeton.flatservice.marks.model.Marks;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/marks")
@RequiredArgsConstructor
@CrossOrigin
public class MarksController {

    private final MarksService marksService;

    @PostMapping("/{flatId}")
    public ResponseEntity<MarksResponse> createMarks(@RequestBody MarksDto marksDTO,
                                                     Principal principal,
                                                     @PathVariable Long flatId){

        Marks marks = marksService.saveMarks(marksDTO, principal, flatId);
        ModelMapper mapper = new ModelMapper();
        MarksResponse response = mapper.map(marks, MarksResponse.class);
        response.setUserId(marks.getId().getUserId());
        response.setFlatId(marks.getId().getFlatId());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{flatId}")
    public ResponseEntity<MarksDto> getMarks(Principal principal,
                                             @PathVariable Long flatId){

        Marks marks = marksService.getMarks(principal, flatId);
        ModelMapper mapper = new ModelMapper();
        MarksDto marksDTO = mapper.map(marks, MarksDto.class);
        return new ResponseEntity<>(marksDTO, HttpStatus.OK);
    }

    @PatchMapping("/{flatId}")
    public ResponseEntity<MarksResponse> updateMarks(@RequestBody MarksDto marksDTO,
                                                     Principal principal,
                                                     @PathVariable Long flatId){

        Marks marks = marksService.updateMarks(marksDTO, principal, flatId);
        ModelMapper mapper = new ModelMapper();
        MarksResponse response = mapper.map(marks, MarksResponse.class);
        response.setUserId(marks.getId().getUserId());
        response.setFlatId(marks.getId().getFlatId());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
