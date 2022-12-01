package ru.skillbox.model;

import lombok.Data;

import javax.persistence.*;

@Embeddable
@Data
public class Avatar {

    private String fileName;
}
