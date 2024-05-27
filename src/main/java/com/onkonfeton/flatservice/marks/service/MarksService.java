package com.onkonfeton.flatservice.marks.service;

import com.onkonfeton.flatservice.flat.model.Flat;
import com.onkonfeton.flatservice.flat.service.FlatService;
import com.onkonfeton.flatservice.marks.controller.dto.MarksDto;
import com.onkonfeton.flatservice.marks.model.Marks;
import com.onkonfeton.flatservice.marks.model.UserFlatKey;
import com.onkonfeton.flatservice.marks.repository.MarksRepository;
import com.onkonfeton.flatservice.user.model.User;
import com.onkonfeton.flatservice.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class MarksService {
    private final MarksRepository marksRepository;
    private final UserService userService;
    private final FlatService flatService;



    public Marks saveMarks(MarksDto marksDTO, Principal principal, Long flatId){

        User user = userService.findByEmail(principal.getName()).orElseThrow();
        Flat flat = flatService.getOne(flatId);
        ModelMapper mapper = new ModelMapper();
        Marks marks = mapper.map(marksDTO, Marks.class);
        marks.setId(new UserFlatKey(user.getId(), flatId));
        marks.setUser(user);
        marks.setFlat(flat);
        marks.setCreatedAt(LocalDateTime.now());
        return marksRepository.save(marks);
    }

    public Marks getMarks(Principal principal, Long flatId) {
        User user = userService.findByEmail(principal.getName()).orElseThrow();
        Marks marks = marksRepository.findById(new UserFlatKey(user.getId(), flatId)).orElseThrow();
        return marks;
    }

    public Marks updateMarks(MarksDto marksDTO, Principal principal, Long flatId) {
        Marks marks = getMarks(principal, flatId);
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setSkipNullEnabled(true);
        mapper.map(marksDTO, marks);
        return marksRepository.save(marks);
    }
}
