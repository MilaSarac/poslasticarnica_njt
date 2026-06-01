/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package rs.ac.bg.fon.si.njt.poslasticarnica.mapper;

/**
 *
 * @author Mila
 */

//nije marker interface, ima metode
//treba da ga implementiraju i kolac mapper i stavkaporudzbine mapper
//kada zelimo da napravimo klasu ili interface koji treba da radi sa razlicitim tipovima podataka
//onda koristimo genericke tipove - to koristimo i kod lista <>
//List<E> lista bilo cega, e moze biti bilo sta i svaka lista ce imati iste metode
//GENERICKI MAPPER
//T-Dto obj, E-Entity obj.
public interface DtoEntityMapper<DTO, E> {
    
    //prima E i vraca DTO
    DTO toDto(E e);
    
    //prima DTO i vraca E
    E toEntity(DTO dto);
   
    
}
