package com.onkonfeton.flatservice.flat.controller;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MarksDTO {
    int region;
    /*
        район (1-5)
        достопность к метро (1-5)
        близость до школы (1-5)
        тип дома (хрущевка... // придумать что да как
        близость к магазину
        удалённость от центра города
        ремонт (как качественно сделан...)
        налицие балкона
        насколько большая должна быть
        ... отдельно жилая площадь и площадь кухни
     */
}
