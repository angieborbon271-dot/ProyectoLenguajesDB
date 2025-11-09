package com.leones.repository;

import com.leones.domain.Provincia;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author ErickaT
 */
public interface ProvinciaRepository 
    extends JpaRepository<Provincia,Long>
{
    
}