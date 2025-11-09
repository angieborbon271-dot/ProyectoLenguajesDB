package com.leones.domain;

/**
 *
 * @author ErickaT
 */

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "provincia")

public class Provincia {
    private Long codProvincia;
    private String nombre;
}
