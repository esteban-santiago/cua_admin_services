package com.cua.admin.repositories.sales;

import com.cua.admin.model.sales.SaleOrder;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SaleOrderRepository extends JpaRepository<SaleOrder, Long> {

    SaleOrder findById(Long id);

}
