package com.tarigor.javamastery.service.impl;

import com.tarigor.javamastery.dto.EmployeeDTO;
import com.tarigor.javamastery.entity.Employee;
import com.tarigor.javamastery.repository.EmployeeRepositorySpec;
import com.tarigor.javamastery.service.EmployeeFilterService;
import com.tarigor.javamastery.service.EmployeeServiceUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeFilterServiceImpl implements EmployeeFilterService {

    private final EmployeeRepositorySpec employeeRepositorySpec;
    private final EmployeeServiceUtil employeeServiceUtil;

    @Override
    public List<EmployeeDTO> findAll(Map<String, Object> filterParameters) {
        if (!filterParameters.isEmpty()) {
            return employeeRepositorySpec.findAll(getSpecification(filterParameters))
                    .stream()
                    .map(employeeServiceUtil::convertFromEntityToDto)
                    .collect(Collectors.toCollection(ArrayList::new));
        } else {
            log.info("'find all' query has proceeded'");
            return employeeRepositorySpec.findAll()
                    .stream()
                    .map(employeeServiceUtil::convertFromEntityToDto)
                    .collect(Collectors.toCollection(ArrayList::new));
        }
    }

    private Specification<Employee> getByParameter(String parameterName, Object parameterValue) {
        try {
            Long.parseLong((String) parameterValue);
            return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(parameterName), parameterValue));
        } catch (NumberFormatException e) {
            return ((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get(parameterName), "%" + parameterValue + "%"));
        }
    }

    private Specification<Employee> getSpecification(Map<String, Object> filterParameters) {
        List<String> keys = new ArrayList<>(filterParameters.keySet());
        switch (filterParameters.size()) {
            case 1:
                log.info("filter by 1 parameter has proceeded");
                return Specification.where(getByParameter(keys.get(0), filterParameters.get(keys.get(0))));
            case 2:
                log.info("filter by 2 parameters has proceeded");
                return Specification.where(
                        getByParameter(keys.get(0), filterParameters.get(keys.get(0)))
                                .and(getByParameter(keys.get(1), filterParameters.get(keys.get(1))))
                );
            case 3:
                log.info("filter by 3 parameters has proceeded");
                return Specification.where(
                        getByParameter(keys.get(0), filterParameters.get(keys.get(0)))
                                .and(getByParameter(keys.get(1), filterParameters.get(keys.get(1))))
                                .and(getByParameter(keys.get(2), filterParameters.get(keys.get(2))))
                );
            case 4:
                log.info("filter by 4 parameters has proceeded");
                return Specification.where(
                        getByParameter(keys.get(0), filterParameters.get(keys.get(0)))
                                .and(getByParameter(keys.get(1), filterParameters.get(keys.get(1))))
                                .and(getByParameter(keys.get(2), filterParameters.get(keys.get(2))))
                                .and(getByParameter(keys.get(3), filterParameters.get(keys.get(3))))
                );
            case 5:
                log.info("filter by 5 parameters has proceeded");
                return Specification.where(
                        getByParameter(keys.get(0), filterParameters.get(keys.get(0)))
                                .and(getByParameter(keys.get(1), filterParameters.get(keys.get(1))))
                                .and(getByParameter(keys.get(2), filterParameters.get(keys.get(2))))
                                .and(getByParameter(keys.get(3), filterParameters.get(keys.get(3))))
                                .and(getByParameter(keys.get(4), filterParameters.get(keys.get(4))))
                );
            default:
                throw new RuntimeException("the maximum count of the filtering parameters are exceeded. Should be not more than 5.");
        }
    }
}
