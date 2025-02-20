package fr.dawan.demospringmvc.controllers;

import fr.dawan.demospringmvc.entities.Utilisateur;
import fr.dawan.demospringmvc.services.IUtilisateurService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class AccountController {

    @Autowired
    private IUtilisateurService utilisateurService;

    @GetMapping("/login")
    public String login() throws Exception{
        return "login";
    }


    @PostMapping("/authenticate")
    public String authenticate(Model model, @RequestParam("username") String username, @RequestParam("password") String password,
                               HttpSession session) throws Exception{
        Utilisateur user = utilisateurService.checkLogin(username,password);
        if(user != null){
            //connexion OK
            //déclencher un suivi de session: par défaut une session à une durée de 25 mn

            session.setAttribute("user", user);
            session.setAttribute("username", user.getUsername());
            session.setAttribute("image", user.getBase64Image());
            session.setAttribute("admin", user.isAdmin());

            return "redirect:/";
        }
        model.addAttribute("erreur", "Echec connexion......");
        return "login";
    }


    @GetMapping("/create-account")
    public String createAccount() throws Exception{
        return "create-account";
    }

    @PostMapping("/create-account")
    public String addAccount(Model model, @RequestParam("username") String username, @RequestParam("password") String password,
                             @RequestParam("file")MultipartFile file) throws Exception{

        Utilisateur u = new Utilisateur(username,password,false,file.getBytes());
        utilisateurService.saveOrUpdate(u);

        model.addAttribute("success", true);
        return "create-account";
    }


    @GetMapping("/logout")
    public String logout(HttpSession session) throws Exception{
        session.invalidate();

        return "redirect:/";
    }
}
