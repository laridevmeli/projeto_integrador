package br.com.dh.meli.projeto_integrador.repository;

import br.com.dh.meli.projeto_integrador.enums.State;
import br.com.dh.meli.projeto_integrador.model.CountStocks;
import br.com.dh.meli.projeto_integrador.model.BatchStock;
import br.com.dh.meli.projeto_integrador.model.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.*;

public interface IBatchStockRepository extends JpaRepository<BatchStock, Long> {
    Optional<BatchStock> findBatchStockByProductId(String productId);
    Optional<BatchStock> findBatchStockByBatchNumber(Integer batchNumber);
    List<BatchStock> findBatchStocksByProductId(String productId);
    @Query(value =
            "SELECT b.warehouse_id AS warehouseId, sum(a.current_quantity) AS quantity FROM batch_stock a" +
            " INNER JOIN inbound_order b" +
            " ON a.inbound_order_id = b.id" +
            " WHERE product_sku = :productId" +
            " GROUP BY b.warehouse_id", nativeQuery = true)
    List<CountStocks> countStocksByProductId(String productId);
    boolean existsBatchStocksByProductId(String productId);
    List<BatchStock> findBatchStocksByProductIdAndSectionOrderByBatchNumberAsc(String productId, Section section);
    List<BatchStock> findBatchStocksByProductIdAndSectionOrderByCurrentQuantityAsc(String productId, Section section);
    List<BatchStock> findBatchStocksByProductIdAndSectionOrderByDueDateAsc(String productId, Section section);
    List<BatchStock> findBatchStocksBySectionOrderByDueDateAsc(Section section);
    List<BatchStock> findBatchStocksBySectionAndDueDateLessThanOrderByDueDateAsc(Section section, LocalDate maxDate);
    List<BatchStock> findAllByState(State state);

}
