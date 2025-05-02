package com.example.trip.subDto;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QHotel is a Querydsl query type for Hotel
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QHotel extends EntityPathBase<Hotel> {

    private static final long serialVersionUID = -1042189347L;

    public static final QHotel hotel = new QHotel("hotel");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QHotel(String variable) {
        super(Hotel.class, forVariable(variable));
    }

    public QHotel(Path<? extends Hotel> path) {
        super(path.getType(), path.getMetadata());
    }

    public QHotel(PathMetadata metadata) {
        super(Hotel.class, metadata);
    }

}

