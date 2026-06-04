/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.si.njt.poslasticarnica.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import rs.ac.bg.fon.si.njt.poslasticarnica.entity.impl.Radnik;
import rs.ac.bg.fon.si.njt.poslasticarnica.repository.MyAppRepository;

/**
 *
 * @author Mila
 */

@Repository
public class RadnikRepository implements MyAppRepository<Radnik, Long> {

    @PersistenceContext
    private EntityManager entityManager;
    
    @Override
    public List<Radnik> findAll() {
        return entityManager.createQuery(
                "SELECT r FROM Radnik r", 
                Radnik.class).getResultList();
    }

    @Override
    public Radnik findById(Long id) throws Exception {
        Radnik radnik = entityManager.find(Radnik.class, id);
        if(radnik == null){
            throw new Exception("Radnik nije pronadjen!");
        }
        return radnik;
    }

    @Override
    @Transactional
    public void save(Radnik entity) {
        if(entity.getIdRadnik()== null){
            entityManager.persist(entity);
        } else{
            entityManager.merge(entity);
        }
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Radnik radnik = entityManager.find(Radnik.class, id);
        if(radnik != null){
            entityManager.remove(radnik);
        }
    }

    //Dodatne metode
    
    /*public Radnik findByUsername(String username){
        List<Radnik> list = entityManager.createQuery("SELECT r FROM Radnik r WHERE r.username = :un", Radnik.class)
                .setParameter("un", username).getResultList();
        return list.isEmpty() ? null : list.get(0);
    }*/
    
    public Optional<Radnik> findByUsername(String username) {
        List<Radnik> list = entityManager.createQuery(
                "SELECT r FROM Radnik r WHERE r.username = :un", Radnik.class)
                .setParameter("un", username)
                .getResultList();

        // Ako je lista prazna, vraćamo praznu "kutiju" (Optional.empty())
        // Ako nije, uzimamo prvi element i pakujemo ga u Optional.of()
        return list.isEmpty() ? Optional.empty() : Optional.of(list.get(0));
    }
    public boolean existsByUsername(String username){
        Long c = entityManager.createQuery("SELECT COUNT(r) FROM Radnik r WHERE r.username = :un", Long.class)
                .setParameter("un", username).getSingleResult();
        return c > 0;
    }
    
    public boolean existsByEmail(String email){
        Long c = entityManager.createQuery("SELECT COUNT(r) FROM Radnik r WHERE r.email = :em", Long.class)
                .setParameter("em", email).getSingleResult();
        return c > 0;
    }
}
