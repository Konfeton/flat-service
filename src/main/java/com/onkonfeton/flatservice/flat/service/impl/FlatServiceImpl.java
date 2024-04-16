package com.onkonfeton.flatservice.flat.service.impl;


import com.onkonfeton.flatservice.flat.dto.FlatLightDTO;
import com.onkonfeton.flatservice.flat.dto.onliner.AreaDTO;
import com.onkonfeton.flatservice.flat.dto.onliner.Location;
import com.onkonfeton.flatservice.flat.model.Flat;
import com.onkonfeton.flatservice.flat.repository.FlatRepository;
import com.onkonfeton.flatservice.flat.service.FlatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FlatServiceImpl implements FlatService {

    private final FlatRepository flatRepository;
    public List<FlatLightDTO> getAll() {
        List<Object[]> flatsFromDb = flatRepository.getAllCompact();
        List<FlatLightDTO> flats = new LinkedList<>();
        // todo create separate class for area and location (not from model and onliner)
        for (Object[] row : flatsFromDb) {
            flats.add(
                    FlatLightDTO.builder()
                            .id((Long) row[0])
                            .numberOfRooms((int) row[1])
                            .floor((int) row[2])
                            .numberOfFloors((int) row[3])
                            .photo((String) row[4])
                            .area(new AreaDTO((Integer) row[5], (Integer) row[6], (Integer) row[7]))
                            .location(new Location((String) row[8], (String) row[8], Double.parseDouble(row[9].toString()), Double.parseDouble(row[10].toString())))
                            .price((double) row[11])
                            .url(row[12].toString())
                            .createdAt(row[13].toString())
                            .lastTimeUp(row[14].toString())
                            .build()
            );
        }

        return flats;
    }

    public Flat getOne(long id) {
        return flatRepository.getReferenceById(id);
    }

    @Override
    public void save(Flat flat) {
        flatRepository.save(flat);
    }
}
