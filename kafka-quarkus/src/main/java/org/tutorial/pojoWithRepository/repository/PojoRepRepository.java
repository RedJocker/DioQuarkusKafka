package org.tutorial.pojoWithRepository.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.tutorial.pojoWithRepository.entity.PojoRepEntity;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
public class PojoRepRepository implements PanacheRepository<PojoRepEntity> {

    @Override
    public Optional<PojoRepEntity> findByIdOptional(Long aLong) {
        return PanacheRepository.super.findByIdOptional(aLong);
    }

    public List<PojoRepEntity> customFindAll() {
        return listAll();
    }

    public List<PojoRepEntity> customFindQuery(String lessThan) {
        final Map<String, Object> queryParams = Map.of(
                "name", lessThan
        );

        return this.stream("name < :name", queryParams).collect(Collectors.toList());
    }
}
