package fr.dawan.demospringmvc.services;

import fr.dawan.demospringmvc.entities.Utilisateur;
import fr.dawan.demospringmvc.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
public class UtilisateurServiceImpl implements IUtilisateurService{

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Override
    public List<Utilisateur> getAll() throws Exception {

        List<Utilisateur> result = utilisateurRepository.findAll();
        for(Utilisateur u : result){

            byte[] encoded64 = Base64.getEncoder().encode(u.getPhoto());
            String strBase64 = new String(encoded64, "utf-8");
            u.setBase64Image(strBase64);
        }

        return result;
    }

    @Override
    public void saveOrUpdate(Utilisateur u) throws Exception {
        if(u.getId() == 0){
            u.setPassword(encoder.encode(u.getPassword()));
        }else{
            //vérifier si password modifié côté FRONT -> le comparer au password de la BD
            Utilisateur userDb = utilisateurRepository.findById(u.getId()).get();
            if(!userDb.getPassword().equals(u.getPassword())){
                u.setPassword(encoder.encode(u.getPassword()));
            }
        }

        utilisateurRepository.saveAndFlush(u);
    }

    @Override
    public void delete(long id) throws Exception {
        utilisateurRepository.deleteById(id);
    }

    @Override
    public Utilisateur findByUsername(String username) throws Exception {
        return utilisateurRepository.findByUsername(username);
    }

    @Override
    public Utilisateur findById(long id) throws Exception {
        return utilisateurRepository.findById(id).get();
    }

    @Override
    public Utilisateur checkLogin(String username, String password) throws Exception {

        Utilisateur user = utilisateurRepository.findByUsername(username);
        if(user != null && encoder.matches(password, user.getPassword())){
            //encode photo en base 64
            return user;
        }

        return null;
    }
}
