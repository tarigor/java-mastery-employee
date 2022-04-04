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
            return employeeRepositorySpec.findAll(buildSpecification(filterParameters))
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

    private Specification<Employee> buildSpecification(Map<String, Object> map) {
        List<String> keys = new ArrayList<>(map.keySet());
        Specification<Employee> specification = Specification.where(getByParameter(keys.get(0), map.get(keys.get(0))));
        if (map.size() > 1) {
            for (int i = map.size() - 1; i < map.size(); i++) {
                specification = specification.and(getByParameter(keys.get(i), map.get(keys.get(i))));
            }
        }
        if (map.size() > 5) {
            throw new UnsupportedOperationException("the maximum count of the filtering parameters are exceeded. Should be not more than 5.");
        }
        return specification;
    }
}
