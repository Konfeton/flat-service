package com.onkonfeton.flatservice.likedflats.controller;

import com.onkonfeton.flatservice.flat.converter.FlatConverter;
import com.onkonfeton.flatservice.flat.dto.FlatWithoutParams;
import com.onkonfeton.flatservice.flat.model.Flat;
import com.onkonfeton.flatservice.likedflats.controller.dto.RequestDTO;
import com.onkonfeton.flatservice.likedflats.controller.dto.ResponseDTO;
import com.onkonfeton.flatservice.likedflats.model.LikedFlats;
import com.onkonfeton.flatservice.likedflats.service.LikedFlatsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/flats/liked")
@RequiredArgsConstructor
@CrossOrigin
public class LikedFlatsController {
    private final LikedFlatsService likedFlatsService;
    private final FlatConverter flatConverter;

    @PostMapping
    public ResponseEntity<ResponseDTO> addLike(Principal principal, @RequestBody RequestDTO request){
        LikedFlats saved = likedFlatsService.save(principal, request.getFlatId());

        return new ResponseEntity<>(new ResponseDTO(saved.getId().getUserId(), saved.getId().getFlatId()), HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteLike(Principal principal, @RequestBody RequestDTO request){
        likedFlatsService.delete(principal, request.getFlatId());

        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<FlatWithoutParams>> getLastLiked(Principal principal){
        List<Flat> likedFlats = likedFlatsService.getLikedFlats(principal);
        List<FlatWithoutParams> flats = likedFlats.stream()
                .map(flatConverter::toDtoWithoutParams)
                .collect(Collectors.toList());
        return new ResponseEntity<>(flats, HttpStatus.OK);
    }
}
