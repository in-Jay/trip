package com.example.trip.subDto;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QAirplane is a Querydsl query type for Airplane
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAirplane extends EntityPathBase<Airplane> {

    private static final long serialVersionUID = 63918217L;

    public static final QAirplane airplane = new QAirplane("airplane");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QAirplane(String variable) {
        super(Airplane.class, forVariable(variable));
    }

    public QAirplane(Path<? extends Airplane> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAirplane(PathMetadata metadata) {
        super(Airplane.class, metadata);
    }

}

