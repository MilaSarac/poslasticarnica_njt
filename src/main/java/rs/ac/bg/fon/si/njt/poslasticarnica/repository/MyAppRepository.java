/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package rs.ac.bg.fon.si.njt.poslasticarnica.repository;

import java.util.List;

/**
 *
 * @author Mila
 */

//ovde se nalaze sve moguce metode koje se rade sa bazom
//genericki interface
//E-objekat nad kojim vrsimo operaciju
//ID-tip entiteta za koji se radi operacija
//ID-Entitet E ima tip kljuca, primarni kljuc ID tipa npr. Long
public interface MyAppRepository<E, ID> {
    
    List<E> findAll();
    E findById(ID id) throws Exception;
    void save(E entity); //za kreiranje(nema id) i azuriranje(ima id)
    void deleteById(ID id);
    
}
