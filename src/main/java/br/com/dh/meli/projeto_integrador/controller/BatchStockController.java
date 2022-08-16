package br.com.dh.meli.projeto_integrador.controller;

import br.com.dh.meli.projeto_integrador.dto.BatchStockDTO;
import br.com.dh.meli.projeto_integrador.dto.SectionDTO;
import br.com.dh.meli.projeto_integrador.enums.Category;
import br.com.dh.meli.projeto_integrador.enums.ParamOrderBy;
import br.com.dh.meli.projeto_integrador.enums.State;
import br.com.dh.meli.projeto_integrador.model.BatchStock;
import br.com.dh.meli.projeto_integrador.model.Section;
import br.com.dh.meli.projeto_integrador.service.IBatchStockService;
import br.com.dh.meli.projeto_integrador.service.ISectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDate;
import java.util.*;

/**
 * BatchStock endpoints
 *
 * @author Isaias Finger
 */
@RestController
@RequestMapping("/api/v1/fresh-products/batch-stock")
public class BatchStockController {

    @Autowired
    IBatchStockService service;

    @Autowired
    ISectionService sectionService;

    /**
     * List all Sections by ProductId
     * with Optional sort by L, V, Q
     *
     * @param productId
     * @param order
     * @return ResponseEntity<List < SectionDTO>>
     * @author Isaias Finger, Rebecca Cruz
     */
    @GetMapping("/list")
    public ResponseEntity<List<SectionDTO>> listAllByProductIdAndSort(
            @RequestParam @Valid @NotEmpty String productId,
            @RequestParam(required = false) Optional<String> order) {
        Optional<ParamOrderBy> orderBy = Optional.empty();
        if (order.isPresent()) {
            orderBy = Optional.of(ParamOrderBy.valueOfByCode(order.get()));
        }
        return ResponseEntity.ok(sectionService.findAllByProductIdAndSort(productId, orderBy));
    }

    /**
     * List all BatchStock by Section and Order by DueDate
     *
     * @param sectionCode
     * @return ResponseEntity<List < BatchStockDTO>>
     * @author Isaias Finger
     */
    @GetMapping("/due-date")
    public ResponseEntity<List<BatchStockDTO>> listAllBySectionOrderByDueDate(
            @RequestParam @Valid @NotEmpty String sectionCode,
            @RequestParam @Valid @NotNull @PositiveOrZero Integer days) {
        List<Section> sections = sectionService.findAllByCode(sectionCode);
        LocalDate limitDate = LocalDate.now().plusDays(days);
        return ResponseEntity.ok(service.toDTOs(service.findAllBySectionsAndByDueDateLessThan(sections, limitDate)));
    }

    /**
     * List all BatchStock by Category and by Due Date less than X days
     *
     * @param category
     * @param days
     * @return ResponseEntity<List < BatchStockDTO>>
     * @author Isaias Finger
     */
    @GetMapping("/due-date/list")
    public ResponseEntity<List<BatchStockDTO>> listAllByCategoryAndByDueDateLessThan(
            @RequestParam @Valid @NotEmpty String category,
            @RequestParam @Valid @NotNull @PositiveOrZero Integer days) {
        List<Section> sections = sectionService.findAllByCategory(Category.getEnumName(category));
        LocalDate limitDate = LocalDate.now().plusDays(days);
        return ResponseEntity.ok(service.toDTOs(service.findAllBySectionsAndByDueDateLessThan(sections, limitDate)));
    }
    /**
     * List all BatchStock by State
     *
     * @param state
     * @return ResponseEntity<List < BatchStockDTO>>
     * @author Larissa Navarro
     */
    @GetMapping("/due-date/state/list")
    public ResponseEntity<List<BatchStockDTO>> listAllByState(
            @RequestParam @Valid @NotNull @PositiveOrZero Optional<Integer> state) {
        State stateOrder = State.valueOf(state.get());
        List<BatchStock>  batches = service.findAllByState(stateOrder);
        return ResponseEntity.ok(service.toDTOs(batches));

    }
    /**
     * Update all BatchStock by due Date
     *
     * @return ResponseEntity<List < BatchStockDTO>>
     * @author Larissa Navarro
     */

    @PutMapping("/due-date")
    public ResponseEntity<List<BatchStockDTO>> updateAllByDueDate(){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.toDTOs(service.updateBatchStocksByDueDate()));
    }

    /**
     * Delete all BatchStock by state expired
     *
     * @return ResponseEntity<List < BatchStockDTO>>
     * @author Larissa Navarro
     */

    @DeleteMapping("/due-date")
    public ResponseEntity<Void> DeleteAllExpired(){
        service.deleteBatchStocksExpired();
        return ResponseEntity.noContent().build();
    }

}
