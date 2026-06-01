/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.si.njt.poslasticarnica;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 * @author Mila
 */

@SpringBootApplication

//kombinuje 3 anotacije:
//@Configuration - kaze da je konfiguraciona klasa i ne sadrzi nikakvu logiku, moze da definise bean objekte
//@EnableAutoConfiguration - omogucava da spring projekat automatski konfigurise celu aplikaciju i 
//sve na osnovu biblioteka iz pom.xml
//@ComponentScan - uzima paket u kom se nalazi ova klasa i sve njegove pakete, foldere i klase ce da skenira 
//i da proveri koji od njih imaju neku od ovih anotacija
//@Component, @Service, @Repository, @Controller i onda ce da razvrsta koja klasa nam je sta
//zato ova klasa nora da se nalazi u nadpaketu da bi mogla da skenira sve u okviru njega

public class PoslasticarnicaApplication {
    
    public static void main(String[] args) {
        
        //pokrece spring app, kreira application context(mozak app) koji je zaduzen za upravljanje bean-ovima
        //ucitava se sve entiteti, controlleri
        //sama ce da pokrene tomcat server i omogucice da primamo http zahteve i saljemo http odgovore
        
        SpringApplication.run(PoslasticarnicaApplication.class, args);
    }
}
