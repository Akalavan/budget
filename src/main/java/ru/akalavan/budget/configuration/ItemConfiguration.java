package ru.akalavan.budget.configuration;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.akalavan.budget.model.Item;
import ru.akalavan.budget.repository.ItemRepository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Class ItemConfigeration
 *
 * @author Вагин Михаил
 * date 04.08.2022
 */

@Configuration
public class ItemConfiguration {

    @Bean
    public CommandLineRunner commandLineRunner(ItemRepository itemRepository) {
        return args -> {
            itemRepository.saveAll(List.of(
                    new Item("Curd", LocalDateTime.now()),
                    new Item("Sour cream", LocalDateTime.now())
            ));
        };
    }
}
