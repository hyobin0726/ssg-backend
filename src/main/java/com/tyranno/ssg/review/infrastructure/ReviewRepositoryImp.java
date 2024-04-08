package com.tyranno.ssg.review.infrastructure;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.tyranno.ssg.category.domain.QCategory;
import com.tyranno.ssg.product.domain.Product;
import com.tyranno.ssg.review.domain.QReview;
import jakarta.annotation.Nullable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReviewRepositoryImp extends QuerydslRepositorySupport {
    private final JPAQueryFactory jpaQueryFactory;

    public ReviewRepositoryImp(JPAQueryFactory jpaQueryFactory) {
        super(Product.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    public List<Long> searchReviewIdsByProductId(Long productId, Integer sortCriterion, @Nullable Integer page) {
        OrderSpecifier<?> orderSpecifier = createOrderSpecifier(sortCriterion);
        QReview review = QReview.review;
        com.querydsl.jpa.impl.JPAQuery<Long> query = jpaQueryFactory
                .select(review.id)
                .from(review)
                .where(review.product.id.eq(productId))
                .orderBy(orderSpecifier);

        if (page != null && page > 0) {
            // page가 제공된 경우, 해당 인덱스 이후의 리뷰을 가져오도록 offset 설정
            int offset = (page - 1) * 20; // 20개씩 끊어서 가져오므로 20을 곱해야 함
            query.offset(offset).limit(20);
        } else {
            // page가 null이거나 0 이하인 경우, 처음부터 20개의 리뷰를 가져오도록 설정
            query.limit(20);
        }

        return query.fetch();
    }
    private OrderSpecifier<?> createOrderSpecifier(Integer sortCriterion) {
        QReview review = QReview.review;
        return switch (sortCriterion) {
            case 1 -> new OrderSpecifier<>(Order.DESC, review.rate);
            case 2 -> new OrderSpecifier<>(Order.ASC, review.rate);
            default -> new OrderSpecifier<>(Order.DESC, review.createdAt);
        };
    }

}