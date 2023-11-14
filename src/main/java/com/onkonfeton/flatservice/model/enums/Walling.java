package com.onkonfeton.flatservice.model.enums;

public enum Walling {
    BRICK,
    MONOLITH,
    PANEL,
    BLOCK;

    public static Walling convertFromString(String wall){
        return switch (wall) {
            case "Блочный" -> BLOCK;
            case "Монолитный" -> MONOLITH;
            case "Панельный" -> PANEL;
            case "Кирпичный" -> BRICK;
            default -> throw new RuntimeException("No such type exists: " + wall);
        };
    }
}
