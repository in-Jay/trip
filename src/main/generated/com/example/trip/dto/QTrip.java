package com.example.trip.dto;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QTrip is a Querydsl query type for Trip
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTrip extends EntityPathBase<Trip> {

    private static final long serialVersionUID = 851432556L;

    public static final QTrip trip = new QTrip("trip");

    public final StringPath air = createString("air");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath imageUrl = createString("imageUrl");

    public final StringPath personnel = createString("personnel");

    public final StringPath plan = createString("plan");

    public final NumberPath<Double> price = createNumber("price", Double.class);

    public final DatePath<java.time.LocalDate> startDate = createDate("startDate", java.time.LocalDate.class);

    public final StringPath tag = createString("tag");

    public final StringPath title = createString("title");

    public QTrip(String variable) {
        super(Trip.class, forVariable(variable));
    }

    public QTrip(Path<? extends Trip> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTrip(PathMetadata metadata) {
        super(Trip.class, metadata);
    }

}

