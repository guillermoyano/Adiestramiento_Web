package KaveCanem.adiestramientoweb.servicios;

import KaveCanem.adiestramientoweb.entidad.Imagen;
import KaveCanem.adiestramientoweb.excepciones.MiException;
import KaveCanem.adiestramientoweb.repositorios.ImagenRepositorio;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Guillote
 */
@Service
public class ImagenServicio {

    @Autowired
    private ImagenRepositorio imagenRepositorio;

    @Transactional
    public Imagen guardar(MultipartFile archivo) throws MiException {

        if (archivo != null) {
            try {

                Imagen imagen = new Imagen();

                imagen.setMime(archivo.getContentType());
                imagen.setNombre(archivo.getName());
                imagen.setContenido(archivo.getBytes());

                return imagenRepositorio.save(imagen);

            } catch (Exception e) {

                System.err.println(e.getMessage());
            }
        }
        return null;
    }

    @Transactional
    public Imagen actualizar(MultipartFile archivo, Integer idImagen) throws MiException {
        if (archivo != null) {
            try {

                Imagen imagen = new Imagen();

                if (idImagen != null) {
                    Optional<Imagen> respuesta = imagenRepositorio.findById(idImagen);

                    if (respuesta.isPresent()) {
                        imagen = respuesta.get();
                    }
                }

                imagen.setMime(archivo.getContentType());
                imagen.setNombre(archivo.getName());
                imagen.setContenido(archivo.getBytes());

                return imagenRepositorio.save(imagen);

            } catch (Exception e) {

                System.err.println(e.getMessage());
            }
        }else{
            System.err.println("Error");
        }
        return null;
    }

    @Transactional(readOnly = true)
    public List<Imagen> listarTodos() {
        return imagenRepositorio.findAll();
    }
    
    public Imagen obtenerImagenPorDefectoPerro() throws IOException {
        // Ruta relativa a la carpeta 'static/img' donde se encuentra la imagen por defecto
        String rutaImagenPorDefecto = "static/img/huella.png"; // Actualiza con el nombre y extensión de tu imagen por defecto

        Resource resource = new ClassPathResource(rutaImagenPorDefecto);
        byte[] contenido = Files.readAllBytes(resource.getFile().toPath());

        Imagen imagen = new Imagen();
        imagen.setMime("image/png"); // Actualiza con el MIME type correspondiente a tu imagen por defecto
        imagen.setNombre("huella.png"); // Actualiza con el nombre de tu imagen por defecto
        imagen.setContenido(contenido);

        return imagenRepositorio.save(imagen);
    }
    
    public Imagen obtenerImagenPorDefectoUsuario() throws IOException {
        // Ruta relativa a la carpeta 'static/img' donde se encuentra la imagen por defecto
        String rutaImagenPorDefecto = "static/img/usuario.png"; // Actualiza con el nombre y extensión de tu imagen por defecto

        Resource resource = new ClassPathResource(rutaImagenPorDefecto);
        byte[] contenido = Files.readAllBytes(resource.getFile().toPath());

        Imagen imagen = new Imagen();
        imagen.setMime("image/png"); // Actualiza con el MIME type correspondiente a tu imagen por defecto
        imagen.setNombre("usuario.png"); // Actualiza con el nombre de tu imagen por defecto
        imagen.setContenido(contenido);

        return imagenRepositorio.save(imagen);
    }
}
