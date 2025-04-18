package com.example.mrchef;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.ui.Model;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

@Controller
public class RecetasController {

    @GetMapping("/recetas")
    public String login() {
        return "recetas";
    }

    // Método GET para mostrar el formulario de creación de receta
    @GetMapping("recetas/crear")
    public String mostrarFormulario() {
        return "receta";  // Nombre de tu archivo HTML con el formulario
    }

    @PostMapping("/recetas/crear")
    public String crearReceta(@ModelAttribute RecetaDTO receta, Model model) {
        String url = "http://localhost:8080/recetas/crear"; // Ajustalo a tu backend real

        RestTemplate restTemplate = new RestTemplate();
        String token = "Bearer ..."; // Si usás autenticación por token, ponelo aquí

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", token); // Cambié set por add
        headers.setContentType(MediaType.APPLICATION_JSON); // Establecer el tipo de contenido como JSON

        HttpEntity<RecetaDTO> entity = new HttpEntity<>(receta, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                url, HttpMethod.POST, entity, String.class);

        model.addAttribute("message", response.getBody());
        return "resultado"; // Página que muestra que la receta fue creada
    }

}