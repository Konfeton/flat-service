package com.onkonfeton.flatservice.flat.service.impl;


import com.onkonfeton.flatservice.flat.controller.dto.ImportanceRequest;
import com.onkonfeton.flatservice.flat.controller.dto.RequestParamsDTO;
import com.onkonfeton.flatservice.flat.converter.FlatConverter;
import com.onkonfeton.flatservice.flat.model.Flat;
import com.onkonfeton.flatservice.flat.model.FlatParams;
import com.onkonfeton.flatservice.flat.repository.FlatRepository;
import com.onkonfeton.flatservice.flat.service.FlatService;
import com.onkonfeton.flatservice.flat.service.impl.pojo.AverageMarks;
import com.onkonfeton.flatservice.flat.service.impl.pojo.ImportanceOf;
import com.onkonfeton.flatservice.user.model.User;
import com.onkonfeton.flatservice.user.service.UserService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FlatServiceImpl implements FlatService {

    private final FlatRepository flatRepository;
    private final EntityManager em;
    private final FlatConverter converter;
    private final UserService userService;
    private static final int DEFAULT_PAGE_SIZE = 12;

    @Override
    public List<Flat> getAll() {
        return flatRepository.findAll();
    }

    public Flat getOne(long id) {
        Flat flat = flatRepository.findById(id).orElseThrow(NoSuchElementException::new);

        return flat;
    }

    public Page<Flat> getByCriteria(RequestParamsDTO params) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Flat> query = builder.createQuery(Flat.class);
        Root<Flat> root = query.from(Flat.class);
        Join<Flat, FlatParams> rootFlatParams = root.join("flatParams");
        List<Predicate> predicates = new ArrayList<>();

        if (params.getMinPrice() != null) {
            predicates.add(builder.greaterThanOrEqualTo(root.get("price"), params.getMinPrice()));
        }
        if (params.getMaxPrice() != null) {
            predicates.add(builder.lessThanOrEqualTo(root.get("price"), params.getMaxPrice()));
        }
        if (params.getNumberOfRooms() != null && !params.getNumberOfRooms().isEmpty()) {
            predicates.add(root.get("numberOfRooms").in(params.getNumberOfRooms()));
        }
        if (params.getMinSquare() != null) {
            predicates.add(builder.greaterThanOrEqualTo(root.get("area").get("total"), params.getMinSquare()));
        }
        if (params.getMaxSquare() != null) {
            predicates.add(builder.lessThanOrEqualTo(root.get("area").get("total"), params.getMaxSquare()));
        }
        if (params.getResale() != null && !params.getResale().isEmpty()) {
            predicates.add(rootFlatParams.get("resale").in(params.getResale()));
        }

        if (params.getWalling() != null && !params.getWalling().isEmpty()) {
            predicates.add(rootFlatParams.get("walling").in(params.getWalling()));
        }
        if (params.getAddress() != null && !params.getAddress().isEmpty()) {
            predicates.add(builder.like(rootFlatParams.get("location").get("address"), "%" + params.getAddress() + "%"));
        }


        query.where(
                builder.and(predicates.toArray(new Predicate[0]))
        );

        if (params.getSortBy() == null) {
            query.orderBy(builder.desc(root.get("lastTimeUp")));
        } else {
            String[] split = params.getSortBy().split(":");
            String direction = split[1];
            String sortParam = split[0];
            if (direction.equals("asc")) {
                query.orderBy(builder.asc(root.get(sortParam)));
            } else {
                query.orderBy(builder.desc(root.get(sortParam)));
            }
        }


        TypedQuery<Flat> typedQuery = em.createQuery(query);

        int page = params.getPage();
        typedQuery.setFirstResult(page * DEFAULT_PAGE_SIZE);
        typedQuery.setMaxResults(DEFAULT_PAGE_SIZE);


        List<Flat> resultList = typedQuery.getResultList();

        if (resultList.isEmpty()) {
            throw new NoResultException("Нет квартир по такому запросу");
        }



        Long totalRecords = getTotalRecords(em, params);
        int totalPages = (int) Math.ceil((double) totalRecords / DEFAULT_PAGE_SIZE);




        return new PageImpl<>(resultList, PageRequest.of(page, DEFAULT_PAGE_SIZE), totalPages);
    }

    private Long getTotalRecords(EntityManager em, RequestParamsDTO params){
        CriteriaBuilder countBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Long> countQuery = countBuilder.createQuery(Long.class);
        Root<Flat> countRoot = countQuery.from(Flat.class);


        Join<Flat, FlatParams> countFlatParams = countRoot.join("flatParams");
        List<Predicate> predicates = new ArrayList<>();

        if (params.getMinPrice() != null) {
            predicates.add(countBuilder.greaterThanOrEqualTo(countRoot.get("price"), params.getMinPrice()));
        }
        if (params.getMaxPrice() != null) {
            predicates.add(countBuilder.lessThanOrEqualTo(countRoot.get("price"), params.getMaxPrice()));
        }
        if (params.getNumberOfRooms() != null && !params.getNumberOfRooms().isEmpty()) {
            predicates.add(countRoot.get("numberOfRooms").in(params.getNumberOfRooms()));
        }
        if (params.getMinSquare() != null) {
            predicates.add(countBuilder.greaterThanOrEqualTo(countRoot.get("area").get("total"), params.getMinSquare()));
        }
        if (params.getMaxSquare() != null) {
            predicates.add(countBuilder.lessThanOrEqualTo(countRoot.get("area").get("total"), params.getMaxSquare()));
        }
        if (params.getResale() != null && !params.getResale().isEmpty()) {
            predicates.add(countFlatParams.get("resale").in(params.getResale()));
        }

        if (params.getWalling() != null && !params.getWalling().isEmpty()) {
            predicates.add(countFlatParams.get("walling").in(params.getWalling()));
        }
        if (params.getAddress() != null && !params.getAddress().isEmpty()) {
            predicates.add(countBuilder.like(countFlatParams.get("location").get("address"), "%" + params.getAddress() + "%"));
        }



        countQuery.select(countBuilder.count(countRoot))
                .where(countBuilder.and(predicates.toArray(new Predicate[0])));

        Long result = em.createQuery(countQuery).getSingleResult();
        return result;
    }

    @Override
    public void save(Flat flat) {
        flatRepository.save(flat);
    }

    public List<Flat> getEvaluatedFlats(Principal principal) {
        User user = userService.findByEmail(principal.getName()).orElseThrow();
        return flatRepository.findFlatsByUserId(user.getId());
    }

    @Override
    public List<Flat> getUniqueFlats(ImportanceRequest request) {

        double priceOfOneMark = getPriceOfOneMark(request);
        ImportanceOf importanceOf = getImportanceOfParams(request, priceOfOneMark);

        List<Object[]> results = flatRepository.findAverageMarksForEachFlat();
        Map<Long, AverageMarks> averageMarks = getAverageMarks(results);

        Map<Long, Double> flatsWithIntegralPreference = getIntegralPreferences(importanceOf, averageMarks);

        System.out.println("квартиры и интегральные показатели: " + flatsWithIntegralPreference);

        HashMap<Long, Double> sorted = flatsWithIntegralPreference.entrySet().stream()
                .sorted(Map.Entry.<Long, Double>comparingByValue().reversed())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));

        System.out.println("sorted: " + sorted);

        List<Flat> flats = new ArrayList<>();

        for (Long id : sorted.keySet()) {
            flats.add(getOne(id));
        }

        return flats;
    }

    private Map<Long, Double> getIntegralPreferences(ImportanceOf importanceOf, Map<Long, AverageMarks> averageMarks){
        Map<Long, Double> flatsWithIntegralPreference = new HashMap<>();


        double integralPreference;


        for (Map.Entry<Long, AverageMarks> flat : averageMarks.entrySet()) {
            AverageMarks avgMark = flat.getValue();
            integralPreference = 0;

            integralPreference += importanceOf.getPrice() * avgMark.getAvgPrice();
            integralPreference += importanceOf.getNumberOfRooms() * avgMark.getAvgNumberOfRooms();
            integralPreference += importanceOf.getYear() * avgMark.getAvgYear();
            integralPreference += importanceOf.getFloor() * avgMark.getAvgFloor();
            integralPreference += importanceOf.getNumberOfFloors() * avgMark.getAvgNumberOfFloors();
            integralPreference += importanceOf.getTotalArea() * avgMark.getAvgTotalArea();
            integralPreference += importanceOf.getLivingArea() * avgMark.getAvgLivingArea();
            integralPreference += importanceOf.getKitchenArea() * avgMark.getAvgKitchenArea();
            integralPreference += importanceOf.getWalling() * avgMark.getAvgWalling();
            integralPreference += importanceOf.getTimeToMetro() * avgMark.getAvgTimeToMetro();
            integralPreference += importanceOf.getTimeToMall() * avgMark.getAvgTimeToMall();
            integralPreference += importanceOf.getTimeToClinic() * avgMark.getAvgTimeToClinic();
            integralPreference += importanceOf.getTimeToKindergarten() * avgMark.getAvgTimeToKindergarten();
            integralPreference += importanceOf.getTimeToSchool() * avgMark.getAvgTimeToSchool();
            integralPreference += importanceOf.getDistrict() * avgMark.getAvgDistrict();
            integralPreference += importanceOf.getAddress() * avgMark.getAvgAddress();
            flatsWithIntegralPreference.put(flat.getKey(), integralPreference);
        }


        return flatsWithIntegralPreference;
    }

    private Map<Long, AverageMarks> getAverageMarks(List<Object[]> results){
        Map<Long, AverageMarks> averageMarks = new HashMap<>();
        for (Object[] row : results) {
            AverageMarks averageMark = new AverageMarks();

            averageMarks.put(((Number) row[0]).longValue(), averageMark);
            averageMark.setAvgPrice( Double.parseDouble(row[1].toString()));
            averageMark.setAvgNumberOfRooms( Double.parseDouble(row[2].toString()));
            averageMark.setAvgYear( Double.parseDouble(row[3].toString()));
            averageMark.setAvgFloor( Double.parseDouble(row[4].toString()));
            averageMark.setAvgNumberOfFloors( Double.parseDouble(row[5].toString()));
            averageMark.setAvgTotalArea( Double.parseDouble(row[6].toString()));
            averageMark.setAvgLivingArea( Double.parseDouble(row[7].toString()));
            averageMark.setAvgKitchenArea( Double.parseDouble(row[8].toString()));
            averageMark.setAvgWalling( Double.parseDouble(row[9].toString()));
            averageMark.setAvgTimeToMetro( Double.parseDouble(row[10].toString()));
            averageMark.setAvgTimeToMall( Double.parseDouble(row[11].toString()));
            averageMark.setAvgTimeToClinic( Double.parseDouble(row[12].toString()));
            averageMark.setAvgTimeToKindergarten( Double.parseDouble(row[13].toString()));
            averageMark.setAvgTimeToSchool( Double.parseDouble(row[14].toString()));
            averageMark.setAvgDistrict( Double.parseDouble(row[15].toString()));
            averageMark.setAvgAddress( Double.parseDouble(row[16].toString()));

            averageMarks.put(((Number) row[0]).longValue(), averageMark);
        }

        return averageMarks;
    }


    private ImportanceOf getImportanceOfParams(ImportanceRequest request, double priceOfOneMark) {
        return new ImportanceOf(
                priceOfOneMark * request.getPrice(),
                priceOfOneMark * request.getNumberOfRooms(),
                priceOfOneMark * request.getYear(),
                priceOfOneMark * request.getFloor(),
                priceOfOneMark * request.getNumberOfFloors(),
                priceOfOneMark * request.getTotalArea(),
                priceOfOneMark * request.getLivingArea(),
                priceOfOneMark * request.getKitchenArea(),
                priceOfOneMark * request.getWalling(),
                priceOfOneMark * request.getTimeToMetro(),
                priceOfOneMark * request.getTimeToMall(),
                priceOfOneMark * request.getTimeToClinic(),
                priceOfOneMark * request.getTimeToKindergarten(),
                priceOfOneMark * request.getTimeToSchool(),
                priceOfOneMark * request.getDistrict(),
                priceOfOneMark * request.getAddress()
        );
    }

    private double getPriceOfOneMark(ImportanceRequest request) {
        int sum = 0;

        sum += request.getPrice();
        sum += request.getNumberOfRooms();
        sum += request.getYear();
        sum += request.getFloor();
        sum += request.getNumberOfFloors();
        sum += request.getTotalArea();
        sum += request.getLivingArea();
        sum += request.getKitchenArea();
        sum += request.getWalling();
        sum += request.getTimeToMetro();
        sum += request.getTimeToMall();
        sum += request.getTimeToClinic();
        sum += request.getTimeToKindergarten();
        sum += request.getTimeToSchool();
        sum += request.getDistrict();
        sum += request.getAddress();

        double priceOfOneMark = 1.0 / sum;
        return priceOfOneMark;
    }
}
