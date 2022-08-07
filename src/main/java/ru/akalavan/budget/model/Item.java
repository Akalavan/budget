package ru.akalavan.budget.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Class Item
 *
 * @author Вагин Михаил
 * date 04.08.2022
 */
@Entity
@Table
@EqualsAndHashCode(of = {"id"})
public class Item {

    @Id
    @SequenceGenerator(name = "item_sequence", sequenceName = "item_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_sequence")
    @JsonView(Views.IdName.class)
    private Long id;
    @JsonView(Views.IdName.class)
    private String name;

    @Column(updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime creationDate;

    public Item() {
    }

    public Item(String name, LocalDateTime creationDate) {
        this.name = name;
        this.creationDate = creationDate;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
