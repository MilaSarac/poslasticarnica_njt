/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.si.njt.poslasticarnica.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import rs.ac.bg.fon.si.njt.poslasticarnica.entity.impl.Korisnik;
import rs.ac.bg.fon.si.njt.poslasticarnica.repository.MyAppRepository;
import java.util.Optional;

/**
 *
 * @author Mila
 */

@Repository
public class KorisnikRepository implements MyAppRepository<Korisnik, Long> {

    @PersistenceContext
    private EntityManager entityManager;
    
    //U JPQL-u (Java Persistence Query Language) ne koristi se naziv tabele
    //iz baze, već naziv entiteta.
    @Override
    public List<Korisnik> findAll() {
        return entityManager.createQuery(
                "SELECT k FROM Korisnik k", 
                Korisnik.class).getResultList();
    }

    @Override
    public Korisnik findById(Long id) throws Exception {
        Korisnik korisnik = entityManager.find(Korisnik.class, id);
        if(korisnik == null){
            throw new Exception("Korisnik nije pronadjen!");
        }
        return korisnik;
    }

    @Override
    @Transactional
    public void save(Korisnik entity) {
        if(entity.getIdKorisnik()== null){
            entityManager.persist(entity);
        } else{
            entityManager.merge(entity);
        }
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Korisnik korisnik = entityManager.find(Korisnik.class, id);
        if(korisnik != null){
            entityManager.remove(korisnik);
        }
    }
    
    //Dodatne metode
    
    /*public Korisnik findByUsername(String username){
        List<Korisnik> list = entityManager.createQuery("SELECT k FROM Korisnik k WHERE k.username = :un", Korisnik.class)
                .setParameter("un", username).getResultList();
        return list.isEmpty() ? null : list.get(0);
    }*/
    
    public Optional<Korisnik> findByUsername(String username) {
        List<Korisnik> list = entityManager.createQuery(
                "SELECT k FROM Korisnik k WHERE k.username = :un", Korisnik.class)
                .setParameter("un", username)
                .getResultList();

        return list.isEmpty() ? Optional.empty() : Optional.of(list.get(0));
    }
    
    public boolean existsByUsername(String username){
        Long c = entityManager.createQuery("SELECT COUNT(k) FROM Korisnik k WHERE k.username = :un", Long.class)
                .setParameter("un", username).getSingleResult();
        return c > 0;
    }
    
    public boolean existsByEmail(String email){
        Long c = entityManager.createQuery("SELECT COUNT(k) FROM Korisnik k WHERE k.email = :em", Long.class)
                .setParameter("em", email).getSingleResult();
        return c > 0;
    }
}
