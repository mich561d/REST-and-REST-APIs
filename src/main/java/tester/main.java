/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tester;

import facade.PetFacade;
import java.util.Date;
import javax.persistence.Persistence;

/**
 *
 * @author mich5
 */
public class main {
    
    public static void main(String[] args) {
        System.out.println(new PetFacade(Persistence.createEntityManagerFactory("pu")).getAllPetsByEvent(new Date(2015, 7, 7)));
    }
}
