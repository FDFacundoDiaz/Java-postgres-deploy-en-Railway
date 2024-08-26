package com.sergicode.apirest.apirest.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.RestController;

import com.sergicode.apirest.apirest.Entities.Libro;
import com.sergicode.apirest.apirest.Repositories.LibroRepository;

@RestController
@RequestMapping("/libros")
public class LibroController {

     @Autowired
     private LibroRepository libroRepository;

     @GetMapping
    public List<Libro> getAllLibros() {
        return libroRepository.findAll();
    }

    @PostMapping 
    public Libro createLibro (@RequestBody Libro libro) {
        return libroRepository.save(libro);
    }
    
    @DeleteMapping ("/{id}")
    public String deleteLibro (@PathVariable Long id) {
    
        Libro libro = libroRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("No se encontró el libro con el ID: " + id));
         
        libroRepository.delete(libro);

        return "El libro con el ID: " + id + "fué eliminado exitosamente";
    
    }

    @GetMapping ("/{id}")
    public Libro getLibroById (@PathVariable Long id) {
        return libroRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("No se encontró el libro con el ID: " + id));
    }       

    @PutMapping ("/{id}")
    public Libro updateLibro (@PathVariable Long id,
                              @RequestBody Libro detallesLibro) {
       
        //Asigno los datos nuevos en una variable
        Libro libro = libroRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("No se encontró el libro con el ID: " + id));
         
        //Modifico los datos 
        libro.setNombre(detallesLibro.getNombre());
        libro.setDescripcion(detallesLibro.getDescripcion());
        
        //Actualizo la base de datos y la retorno
        return libroRepository.save(libro);
         


    }       

             
        
    }




