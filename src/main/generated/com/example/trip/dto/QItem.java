package com.example.trip.dto;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QItem is a Querydsl query type for Item
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QItem extends EntityPathBase<Item> {

    private static final long serialVersionUID = 851106650L;

    public static final QItem item = new QItem("item");

    public final StringPath category1 = createString("category1");

    public final StringPath category2 = createString("category2");

    public final StringPath category3 = createString("category3");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath image = createString("image");

    public final StringPath link = createString("link");

    public final NumberPath<Integer> lprice = createNumber("lprice", Integer.class);

    public final StringPath mallName = createString("mallName");

    public final StringPath title = createString("title");

    public QItem(String variable) {
        super(Item.class, forVariable(variable));
    }

    public QItem(Path<? extends Item> path) {
        super(path.getType(), path.getMetadata());
    }

    public QItem(PathMetadata metadata) {
        super(Item.class, metadata);
    }

}

