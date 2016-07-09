package com.pie.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * Repository扩展接口�? * @author bruce_000
 *
 * @param <T>
 * @param <ID>
 */
@NoRepositoryBean
public interface IBaseRepository<T, ID extends Serializable> extends
        JpaRepository<T, ID>, JpaSpecificationExecutor<T>{

}
