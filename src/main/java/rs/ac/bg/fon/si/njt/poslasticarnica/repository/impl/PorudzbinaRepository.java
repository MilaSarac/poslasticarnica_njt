/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.si.njt.poslasticarnica.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.stereotype.Repository;
import rs.ac.bg.fon.si.njt.poslasticarnica.entity.impl.Porudzbina;
import rs.ac.bg.fon.si.njt.poslasticarnica.repository.MyAppRepository;

/**
 *
 * @author Mila
 */

@Repository
public class PorudzbinaRepository implements MyAppRepository<Porudzbina, Long> {

    @PersistenceContext
    private EntityManager entityManager;
    
    @Override
    public List<Porudzbina> findAll() {
        return entityManager.createQuery(
                "SELECT p FROM Porudzbina p", 
                Porudzbina.class).getResultList();
    }

    @Override
    public Porudzbina findById(Long id) throws Exception {
        Porudzbina porudzbina = entityManager.find(Porudzbina.class, id);
        if(porudzbina == null){
            throw new Exception("Porudzbina nije pronadjeno!");
        }
        return porudzbina;
    }

    @Override
    @Transactional
    public void save(Porudzbina entity) {
        if(entity.getId() == null){
            entityManager.persist(entity);
        } else{
            entityManager.merge(entity);
        }
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Porudzbina porudzbina = entityManager.find(Porudzbina.class, id);
        if(porudzbina != null){
            entityManager.remove(porudzbina); //orphanRemoval brise i stavke
        }
    }
    
}
