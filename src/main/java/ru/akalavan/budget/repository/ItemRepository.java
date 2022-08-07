package ru.akalavan.budget.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.akalavan.budget.model.Item;

/**
 * Class ItemRepository
 *
 * @author Вагин Михаил
 * date 04.08.2022
 */
public interface ItemRepository extends JpaRepository<Item, Long> {
}
