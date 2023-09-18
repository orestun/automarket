package com.automarket.repository;

import com.automarket.dto.VehicleSearchCriteriaDto;
import com.automarket.model.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class VehicleCriteriaRepository {
    private final CriteriaBuilder criteriaBuilder;
    private final EntityManager entityManager;

    public VehicleCriteriaRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.criteriaBuilder = entityManager.getCriteriaBuilder();
    }

    public Page<Vehicle> getVehiclesWithAllCriteria(VehicleSearchCriteriaDto criteria, Pageable pageable){
        CriteriaQuery<Vehicle> query = criteriaBuilder.createQuery(Vehicle.class);
        Root<Vehicle> vehicleQuery = query.from(Vehicle.class);
        Join<Vehicle, VehicleHistory> historyJoin = vehicleQuery.join("history", JoinType.LEFT);
        Join<Vehicle, VehicleOrderInfo> orderInfoJoin = vehicleQuery.join("orderInfo", JoinType.LEFT);
        Join<Vehicle, VehicleBasicInfo> basicInfoJoin = vehicleQuery.join("basicInfo", JoinType.LEFT);
        Join<Vehicle, VehicleTechnicalData> technicalDataJoin = vehicleQuery.join("technicalData", JoinType.LEFT);

        Predicate predicate = getPredicatesForAllCriteria(criteria, vehicleQuery, historyJoin, orderInfoJoin, basicInfoJoin, technicalDataJoin);
        query.where(predicate);
        TypedQuery<Vehicle> typedQuery = entityManager.createQuery(query);
        List<Vehicle> resultVehicleList = typedQuery.getResultList();
        System.out.println(resultVehicleList.size());
        return new PageImpl<>(resultVehicleList, pageable, resultVehicleList.size());
    }

    private Predicate getPredicatesForAllCriteria(VehicleSearchCriteriaDto criteria,
                                                  Root<Vehicle> root,
                                                  Join<Vehicle, VehicleHistory> historyJoin,
                                                  Join<Vehicle, VehicleOrderInfo> orderInfoJoin,
                                                  Join<Vehicle, VehicleBasicInfo> basicInfoJoin,
                                                  Join<Vehicle, VehicleTechnicalData> technicalDataJoin){
        Predicate rootPredicate = getPredicatesForVehicleRoot(criteria, root);
        Predicate historyPredicate = getPredicatesForJoinVehicleAndHistory(criteria, historyJoin);
        Predicate orderInfoPredicate = getPredicatesForJoinVehicleAndOrderInfo(criteria, orderInfoJoin);
        Predicate basicInfoPredicate = getPredicatesForJoinVehicleAndBasicInfo(criteria, basicInfoJoin);
        Predicate technicalDataPredicate = getPredicatesForJoinVehicleAndTechnicalData(criteria, technicalDataJoin);
        List<Predicate> predicates = new ArrayList<>(List.of(rootPredicate, historyPredicate, orderInfoPredicate, basicInfoPredicate, technicalDataPredicate));
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

    private Predicate getPredicatesForVehicleRoot(VehicleSearchCriteriaDto criteria,
                                 Root<Vehicle> root){
        List<Predicate> predicates = new ArrayList<>();
        if(Objects.nonNull(criteria.getMake())){
            predicates.add(buildEqualCriteriaForRoot("make", criteria.getMake(), root));
        }
        if(Objects.nonNull(criteria.getModel())){
            predicates.add(buildEqualCriteriaForRoot("model", criteria.getModel(), root));
        }
        if(Objects.nonNull(criteria.getConfiguration())){
            predicates.add(buildLikeCriteriaForRoot(criteria.getConfiguration(), root));
        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

    private Predicate getPredicatesForJoinVehicleAndTechnicalData(
            VehicleSearchCriteriaDto criteria, Join<Vehicle, VehicleTechnicalData> technicalDataJoin){
        List<Predicate> predicates = new ArrayList<>();
        if(Objects.nonNull(criteria.getGearbox())){
            predicates.add(buildEqualCriteriaForJoin("engine", String.valueOf(criteria.getEngine().ordinal()), technicalDataJoin));
        }
        if(Objects.nonNull(criteria.getGearbox())){
            predicates.add(buildEqualCriteriaForJoin("gearbox", String.valueOf(criteria.getGearbox().ordinal()), technicalDataJoin));
        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

    private Predicate getPredicatesForJoinVehicleAndBasicInfo(
            VehicleSearchCriteriaDto criteria, Join<Vehicle, VehicleBasicInfo> basicInfoJoin){
        List<Predicate> predicates = new ArrayList<>();
        if(Objects.nonNull(criteria.getExteriorColor())){
            predicates.add(buildEqualCriteriaForJoin("exterior_color", String.valueOf(criteria.getExteriorColor().ordinal()), basicInfoJoin));
        }
        if(Objects.nonNull(criteria.getInteriorColor())){
            predicates.add(buildEqualCriteriaForJoin("interior_color", String.valueOf(criteria.getInteriorColor().ordinal()), basicInfoJoin));
        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

    private Predicate getPredicatesForJoinVehicleAndOrderInfo(
            VehicleSearchCriteriaDto criteria, Join<Vehicle, VehicleOrderInfo> orderInfoJoin){
        List<Predicate> predicates = new ArrayList<>();
        if(Objects.nonNull(criteria.getCountry())){
            predicates.add(buildEqualCriteriaForJoin("country", criteria.getCountry(), orderInfoJoin));
        }
        if(Objects.nonNull(criteria.getState())){
            predicates.add(buildEqualCriteriaForJoin("state", criteria.getState(), orderInfoJoin));
        }
        if(Objects.nonNull(criteria.getCity())){
            predicates.add(buildEqualCriteriaForJoin("city", criteria.getCity(), orderInfoJoin));
        }
        if(criteria.getMaxPrice() > 0){
            predicates.add(buildBetweenCriteriaForJoin("price", criteria.getMinPrice(), criteria.getMaxPrice(), orderInfoJoin));
        } else{
            predicates.add(buildGreaterCriteriaForJoin("price", criteria.getMinPrice(), orderInfoJoin));
        }
        if(Objects.nonNull(criteria.getIsTradeInIncluded())){
            predicates.add(handleBooleanCriteriaForJoin("isTradeInIncluded", criteria.getIsTradeInIncluded(), orderInfoJoin));
        }
        if(Objects.nonNull(criteria.getIsAuctionIncluded())){
            predicates.add(handleBooleanCriteriaForJoin("isAuctionIncluded", criteria.getIsAuctionIncluded(), orderInfoJoin));
        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

    private Predicate getPredicatesForJoinVehicleAndHistory(VehicleSearchCriteriaDto criteria, Join<Vehicle, VehicleHistory> historyJoin){
        List<Predicate> predicates = new ArrayList<>();
        if(criteria.getMaxMileage() > 0){
            predicates.add(buildBetweenCriteriaForJoin("mileage", criteria.getMinMileage(), criteria.getMaxMileage(), historyJoin));
        } else{
            predicates.add(buildGreaterCriteriaForJoin("mileage", criteria.getMinMileage(), historyJoin));
        }
        if(criteria.getMaxYear() > 0){
            predicates.add(buildBetweenCriteriaForJoin("year", criteria.getMinYear(), criteria.getMaxYear(), historyJoin));
        } else{
            predicates.add(buildGreaterCriteriaForJoin("year", criteria.getMinYear(), historyJoin));
        }
        if(Objects.nonNull(criteria.getWasInAccident())){
            predicates.add(handleBooleanCriteriaForJoin("wasInAccident", criteria.getWasInAccident(), historyJoin));
        }
        if(Objects.nonNull(criteria.getWasMoved())){
            predicates.add(handleBooleanCriteriaForJoin("wasMoved", criteria.getWasMoved(), historyJoin));
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

    private Predicate handleBooleanCriteriaForJoin(String expression, boolean criteria, Join<?, ?> join){
        if(criteria){
            return buildBooleanTrueCriteriaForJoin(expression, join);
        }
        return buildBooleanFalseCriteriaForJoin(expression, join);
    }

    private Predicate buildEqualCriteriaForRoot(String expression, String criteria, Root<?> root){
        return criteriaBuilder.equal(root.get(expression), criteria);
    }

    private Predicate buildEqualCriteriaForJoin(String expression, String criteria, Join<?, ?> join){
        return criteriaBuilder.equal(join.get(expression), criteria);
    }

    private Predicate buildLikeCriteriaForRoot(String criteria, Root<?> root){
        return criteriaBuilder.like(root.get("configuration"), "%" + criteria + "%");
    }

    private Predicate buildBetweenCriteriaForJoin(String expression, Integer firthCriteria, Integer secondCriteria, Join<?,?> join){
        return criteriaBuilder.between(join.get(expression), firthCriteria, secondCriteria);
    }

    private Predicate buildGreaterCriteriaForJoin(String expression, Integer criteria, Join<?, ?> join){
        return criteriaBuilder.greaterThan(join.get(expression), criteria);
    }

    private Predicate buildBooleanTrueCriteriaForJoin(String expression, Join<?, ?> join){
        return criteriaBuilder.isTrue(join.get(expression));
    }
    private Predicate buildBooleanFalseCriteriaForJoin(String expression, Join<?, ?> join){
        return criteriaBuilder.isFalse(join.get(expression));
    }
}
