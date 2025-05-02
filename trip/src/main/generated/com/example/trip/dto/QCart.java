package com.example.trip.dto;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCart is a Querydsl query type for Cart
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCart extends EntityPathBase<Cart> {

    private static final long serialVersionUID = 850910055L;

    public static final QCart cart = new QCart("cart");

    public final StringPath goodsCode = createString("goodsCode");

    public final StringPath goodsImageUrl = createString("goodsImageUrl");

    public final StringPath goodsName = createString("goodsName");

    public final NumberPath<Integer> goodsPrice = createNumber("goodsPrice", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> quantity = createNumber("quantity", Integer.class);

    public QCart(String variable) {
        super(Cart.class, forVariable(variable));
    }

    public QCart(Path<? extends Cart> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCart(PathMetadata metadata) {
        super(Cart.class, metadata);
    }

}

