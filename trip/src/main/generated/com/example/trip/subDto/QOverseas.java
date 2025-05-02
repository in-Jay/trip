package com.example.trip.subDto;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QOverseas is a Querydsl query type for Overseas
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QOverseas extends EntityPathBase<Overseas> {

    private static final long serialVersionUID = 1270952463L;

    public static final QOverseas overseas = new QOverseas("overseas");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QOverseas(String variable) {
        super(Overseas.class, forVariable(variable));
    }

    public QOverseas(Path<? extends Overseas> path) {
        super(path.getType(), path.getMetadata());
    }

    public QOverseas(PathMetadata metadata) {
        super(Overseas.class, metadata);
    }

}

