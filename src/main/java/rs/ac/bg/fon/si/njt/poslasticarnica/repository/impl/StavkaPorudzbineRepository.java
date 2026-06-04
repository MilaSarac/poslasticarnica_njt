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
import rs.ac.bg.fon.si.njt.poslasticarnica.entity.impl.StavkaPorudzbine;
import rs.ac.bg.fon.si.njt.poslasticarnica.repository.MyAppRepository;

/**
 *
 * @author Mila
 */

@Repository
public class StavkaPorudzbineRepository implements MyAppRepository<StavkaPorudzbine, Long> {

    @PersistenceContext
    private EntityManager entityManager;
    
    @Override
    public List<StavkaPorudzbine> findAll() {
        return entityManager.createQuery(
                "SELECT sp FROM StavkaPorudzbine sp", 
                StavkaPorudzbine.class).getResultList();
    }

    @Override
    public StavkaPorudzbine findById(Long id) throws Exception {
        StavkaPorudzbine stavkaPorudzbine = entityManager.find(StavkaPorudzbine.class, id);
        if(stavkaPorudzbine == null){
            throw new Exception("Stavka porudzbine nije pronadjeno!");
        }
        return stavkaPorudzbine;
    }

    @Override
    @Transactional
    public void save(StavkaPorudzbine entity) {
        if(entity.getRb() == null){
            entityManager.persist(entity);
        } else{
            entityManager.merge(entity);
        }
    }


    @Override
    @Transactional
    public void deleteById(Long id) {
        StavkaPorudzbine stavkaPorudzbine = entityManager.find(StavkaPorudzbine.class, id);
        if(stavkaPorudzbine != null){
            entityManager.remove(stavkaPorudzbine);
        }
    }
    
    /*public List<StavkaPorudzbine> findByPorudzbina(Long idPorudzbina) {
    }

    public void deleteByPorudzbinaId(Long idPorudzbina) {
    }

    public void saveAll(List<StavkaPorudzbine> stavke) {
    }

    public void delete(StavkaPorudzbine stara) {
    }

    public List<StavkaPorudzbine> findByIdRacun(Long idPorudzbina) {
    }*/
    
}
