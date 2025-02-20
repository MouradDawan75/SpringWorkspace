package fr.dawan.demospringmvc.services;

import fr.dawan.demospringmvc.entities.Utilisateur;

import java.util.List;

public interface IUtilisateurService {

    List<Utilisateur> getAll() throws Exception;
    void saveOrUpdate(Utilisateur u) throws  Exception;
    void delete(long id) throws  Exception;;
    Utilisateur findByUsername(String username) throws  Exception;
    Utilisateur findById(long id) throws  Exception;

    Utilisateur checkLogin(String username, String password) throws  Exception;;
}
