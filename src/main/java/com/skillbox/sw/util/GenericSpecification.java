package com.skillbox.sw.util;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.Path;
import java.time.LocalDate;
import java.util.List;

/**
 * Helper class to build specification
 * @param <T> takes any entity to work with
 * @see com.skillbox.sw.service.FriendshipService for usage examples
 */

@Component
public class GenericSpecification<T> {

  /**
   * @param fieldName field of T-type object to filter
   * @param operation in : check if fieldParam presents in list
   *    *             <p> % like if @param fieldName typeof String, equals otherwise
   *                  <p> > greater than or equal
   *                  <p> < less than or equal
   * @param filter value to filter
   * @return spec for a T type with defined filters
   */

  public Specification<T> byField(
    String fieldName,
    String operation,
    Object filter
  ) {
    return (r, q, b) -> {
      Path p = r.get(fieldName);
      if (operation.equals("in")) {
        return p.in((List) filter);
      }
      else if (operation.equalsIgnoreCase("%")) {
        if (p.getJavaType() == String.class) {
          return b.like(p, "%" + filter.toString() + "%");
        } else {
          return b.equal(p, filter);
        }
      }
      else if (operation.equals(">")) {
        if (p.getJavaType() == LocalDate.class) {
          return b.greaterThanOrEqualTo(p, (LocalDate) filter);
        }
        return b.greaterThanOrEqualTo(p, (LocalDate)filter);
      }
      else if (operation.equals("<")) {
        if (p.getJavaType() == LocalDate.class) {
          return b.lessThanOrEqualTo(p, (LocalDate) filter);
        }
        return b.lessThanOrEqualTo(p, filter.toString());
      }
      return null;
    };
  }

  /**
   * @param fieldName field of T-type object to filter by it's field
   * @param fieldParam field of @param fieldName to filter
   * @param operation in : check if fieldParam presents in list
   *                  <p> % like if @param fieldParam typeof String, equals otherwise
   *                  <p> > greater than or equal
   *                  <p> < less than or equal
   * @param filter value to filter
   * @return spec for a T type with defined filters
   */

  public Specification<T> byFieldParam(
    String fieldName,
    String operation,
    String fieldParam,
    Object filter
  ) {
    return (r, q, b) -> {
      Path p = r.get(fieldName).get(fieldParam);
      if (operation.equals("in")) {
        return p.in((List) filter);
      }
      else if (operation.equals("%")) {
        if (p.getJavaType() == String.class) {
          return b.like(p, "%" + filter.toString() + "%");
        } else {
          return b.equal(p, filter);
        }
      }
      else if (operation.equals(">")) {
        if (p.getJavaType() == LocalDate.class) {
          return b.greaterThanOrEqualTo(p, (LocalDate) filter);
        }
        return b.greaterThanOrEqualTo(p, (LocalDate)filter);
      }
      else if (operation.equals("<")) {
        if (p.getJavaType() == LocalDate.class) {
          return b.lessThanOrEqualTo(p, (LocalDate) filter);
        }
        return b.lessThanOrEqualTo(p, filter.toString());
      }
      return null;
    };
  }
}
