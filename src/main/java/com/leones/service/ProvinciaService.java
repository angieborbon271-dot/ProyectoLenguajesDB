package com.leones.service;

import com.leones.domain.Provincia;
import com.leones.repository.ProvinciaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ErickaT
 */
public class ProvinciaService {
    //Se define un único objeto para todos los usuarios
    //y se crea automáticamente

    @Autowired
    private ProvinciaRepository provinciaRepository;

    @Transactional(readOnly = true)
    public List<Provincia> getProvincias() {
        var lista = provinciaRepository.findAll();
        return lista;
    }

    @Transactional(readOnly = true)
    public Provincia getProvincia(Provincia provincia) {
        return provinciaRepository.findById(provincia.getCodProvincia()).
                orElse(null);
    }

    @Transactional
    public void save(Provincia provincia) {
        provinciaRepository.save(provincia);
    }

    @Transactional
    public boolean delete(Provincia provincia) {
        try {
            provinciaRepository.delete(provincia);
            provinciaRepository.flush();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
