package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Patient {
    private Long id;
    private String taj;
    private String name;
    private int birthYear;
    private String surgeryType;
    private String surgeon;
}
