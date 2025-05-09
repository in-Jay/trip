package com.example.trip.subDto;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QDomestic is a Querydsl query type for Domestic
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDomestic extends EntityPathBase<Domestic> {

    private static final long serialVersionUID = 1874358869L;

    public static final QDomestic domestic = new QDomestic("domestic");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QDomestic(String variable) {
        super(Domestic.class, forVariable(variable));
    }

    public QDomestic(Path<? extends Domestic> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDomestic(PathMetadata metadata) {
        super(Domestic.class, metadata);
    }

}

