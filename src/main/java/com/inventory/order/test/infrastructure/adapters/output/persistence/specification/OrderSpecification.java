package com.inventory.order.test.infrastructure.adapters.output.persistence.specification;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.inventory.order.test.application.dto.OrderSearchRequest;
import com.inventory.order.test.infrastructure.entity.OrderEntity;

import jakarta.persistence.criteria.Predicate;

public class OrderSpecification {

    public static Specification<OrderEntity>
    search(OrderSearchRequest request) {

        return (root, query, cb) -> {

            List<Predicate> predicates =
                    new ArrayList<>();

            if (request.getStatus() != null) {

                predicates.add(
                        cb.equal(
                                root.get("status"),
                                request.getStatus()));
            }

            if (request.getUserId() != null) {

                predicates.add(
                        cb.equal(
                                root.get("userId"),
                                request.getUserId()));
            }

            if (request.getMinTotal() != null) {

                predicates.add(
                        cb.greaterThanOrEqualTo(
                                root.get("total"),
                                request.getMinTotal()));
            }

            if (request.getMaxTotal() != null) {

                predicates.add(
                        cb.lessThanOrEqualTo(
                                root.get("total"),
                                request.getMaxTotal()));
            }

            return cb.and(
                    predicates.toArray(
                            new Predicate[0]));
        };
    }
}