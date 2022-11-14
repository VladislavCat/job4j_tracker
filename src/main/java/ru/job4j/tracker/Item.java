package ru.job4j.tracker;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"created"})
public class Item {
    private int id;
    private String name;
    private LocalDateTime created = LocalDateTime.now();

    public Item(String itemName) {
        name = itemName;
    }
}