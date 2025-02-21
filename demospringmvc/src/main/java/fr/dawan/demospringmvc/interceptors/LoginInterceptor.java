package fr.dawan.demospringmvc.interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class LoginInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        System.out.println(">>>>>>>>>>>>>> login intercepteur");
        System.out.println(request.getRequestURI());

        //vérifier si l'utilisateur est connecté
        if(request.getRequestURI().contains("/utilisateurs/") || request.getRequestURI().contains("/products/") ){

            if(request.getSession().getAttribute("user") == null){

                //redirection de la réponse vers la page login
                /*
                request.getContextPath(): renvoie server:port (localhost:8080)
                 */
                response.sendRedirect(request.getContextPath()+"/login");

            }

            /*
            Option1: masquer les liens pour les user non admin
            Option2: gestion des accès admin dans un inetrcepteur

            if(request.getRequestURI().contains("/utilisateurs/") ){

                if((boolean)request.getSession().getAttribute("admin") == false){
                    response.sendRedirect(request.getContextPath()+"/unauthorized");
                }

            }*/
        }

        return true;
    }
}
