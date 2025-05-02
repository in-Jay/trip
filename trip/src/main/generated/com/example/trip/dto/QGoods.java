package com.example.trip.dto;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QGoods is a Querydsl query type for Goods
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QGoods extends EntityPathBase<Goods> {

    private static final long serialVersionUID = 612515823L;

    public static final QGoods goods = new QGoods("goods");

    public final NumberPath<Integer> baseQuantity = createNumber("baseQuantity", Integer.class);

    public final StringPath goodsCode = createString("goodsCode");

    public final StringPath goodsImageUrl = createString("goodsImageUrl");

    public final StringPath goodsName = createString("goodsName");

    public final StringPath goodsOrigin = createString("goodsOrigin");

    public final NumberPath<Integer> goodsPrice = createNumber("goodsPrice", Integer.class);

    public final StringPath goodsSize = createString("goodsSize");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath material = createString("material");

    public final NumberPath<Integer> minQuantity = createNumber("minQuantity", Integer.class);

    public final StringPath productionTime = createString("productionTime");

    public QGoods(String variable) {
        super(Goods.class, forVariable(variable));
    }

    public QGoods(Path<? extends Goods> path) {
        super(path.getType(), path.getMetadata());
    }

    public QGoods(PathMetadata metadata) {
        super(Goods.class, metadata);
    }

}

