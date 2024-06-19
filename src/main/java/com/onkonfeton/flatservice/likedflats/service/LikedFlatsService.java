package com.onkonfeton.flatservice.likedflats.service;

import com.onkonfeton.flatservice.flat.model.Flat;
import com.onkonfeton.flatservice.flat.service.FlatService;
import com.onkonfeton.flatservice.likedflats.model.LikedFlats;
import com.onkonfeton.flatservice.likedflats.model.LikedFlatsKey;
import com.onkonfeton.flatservice.likedflats.repository.LikedFlatsRepository;
import com.onkonfeton.flatservice.user.model.User;
import com.onkonfeton.flatservice.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LikedFlatsService {
    private final LikedFlatsRepository likedFlatsRepository;
    private final FlatService flatService;
    private final UserService userService;

    public List<Flat> getLikedFlats(Principal principal){
        User user = userService.findByEmail(principal.getName()).orElseThrow();
        List<LikedFlats> byUser = likedFlatsRepository.findByUserOrderByTimestampDesc(user);
        return byUser.stream()
                .map(LikedFlats::getFlat)
                .collect(Collectors.toList());

    }

    public LikedFlats save(Principal principal, long flatId) {
        User user = userService.findByEmail(principal.getName()).orElseThrow();
        Flat flat = flatService.getOne(flatId);
        LikedFlatsKey id = new LikedFlatsKey(user.getId(), flatId);


        Optional<LikedFlats> liked = likedFlatsRepository.findById(id);
        LikedFlats likedFlat;
        if (liked.isPresent()){
            likedFlat = liked.get();
            likedFlat.setTimestamp(LocalDateTime.now());
        }else {
            likedFlat = new LikedFlats(
                    id,
                    user,
                    flat,
                    LocalDateTime.now()
            );
        }
        return likedFlatsRepository.save(likedFlat);
    }

    public void delete(Principal principal, long flatId) {
        User user = userService.findByEmail(principal.getName()).orElseThrow();
        LikedFlatsKey id = new LikedFlatsKey(user.getId(), flatId);


        Optional<LikedFlats> liked = likedFlatsRepository.findById(id);
        likedFlatsRepository.delete(liked.orElseThrow());

    }
}
