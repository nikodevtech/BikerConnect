package com.bikerconnect.servicios;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bikerconnect.dtos.QuedadaDTO;
import com.bikerconnect.entidades.Comentario;
import com.bikerconnect.entidades.Like;
import com.bikerconnect.entidades.Quedada;
import com.bikerconnect.entidades.Usuario;
import com.bikerconnect.repositorios.ComentarioRepositorio;
import com.bikerconnect.repositorios.LikeRepositorio;
import com.bikerconnect.repositorios.QuedadaRepositorio;
import com.bikerconnect.repositorios.UsuarioRepositorio;

import jakarta.persistence.PersistenceException;
import jakarta.transaction.Transactional;

/**
 * Servicio que implementa los metodos de la interface {@link IQuedadaServicio}
 * y en esta clase es donde se entra al detalle de la logica de dichos métodos
 * para la gestión de las quedadas.
 */
@Service
@Transactional
public class QuedadaServicioImpl implements IQuedadaServicio {

	@Autowired
	private QuedadaRepositorio quedadaRepo;

	@Autowired
	private IQuedadaToDto toDto;

	@Autowired
	private IQuedadaToDao toDao;

	@Autowired
	private UsuarioRepositorio usuarioRepo;
	
	@Autowired
	private ComentarioRepositorio comentarioRepo;
	
	@Autowired
	private LikeRepositorio likeRepositorio;

	@Override
	public List<QuedadaDTO> obtenerQuedadas() {
		return toDto.listaQuedadToDto(quedadaRepo.findAll());
	}

	@Override
	public boolean crearQuedada(QuedadaDTO quedadaDTO) {
		try {
			quedadaRepo.save(toDao.quedadaToDao(quedadaDTO));
			return true;
		} catch (PersistenceException e) {
			System.out.println("\n[ERROR QuedadaServicioImpl - crearQuedada()] - Al registrar nueva quedada: " + e);
			return false;
		}
	}

	@Override
	public QuedadaDTO obtenerQuedadaPorId(Long id) {
		try {
			QuedadaDTO quedada = new QuedadaDTO();
			quedada = toDto.quedadaToDto(quedadaRepo.findById(id).get());
			return quedada;
		} catch (IllegalArgumentException e) {
			System.out.println(
					"\n[ERROR QuedadaServicioImpl - otenerQuedadaPorId()] - Al buscar una quedada por su id: " + e);
		}
		return null;
	}

	@Override
	public String unirseQuedada(Long idQuedada, String emailUsuario) {
		try {
			Quedada q = quedadaRepo.findById(idQuedada).orElse(null);
			Usuario u = usuarioRepo.findFirstByEmail(emailUsuario);

			if (u == null || q == null) {
				return "Usuario o quedada no encontrados";
			}

			if(q.getFechaHoraEncuentro().before(Calendar.getInstance())) {
				return "La quedada ya ha pasado";
			}
			
			if(q.getEstado().equals("Completada")) {
				return "La quedada está completada";
			}
			if (u.getQuedadasParticipante().contains(q) || q.getUsuariosParticipantes().contains(u)) {
				return "Ya estás unido a esta quedada";
			}

			u.getQuedadasParticipante().add(q);
			q.getUsuariosParticipantes().add(u);

			usuarioRepo.save(u);
			quedadaRepo.save(q);
			
			return "Usuario unido a la quedada";


		} catch (IllegalArgumentException e) {
			System.out.println(
					"\n[ERROR QuedadaServicioImpl - unirseQuedada()] - Error de argumento incorrecto al unirse a una quedada: "
							+ e);
		} catch (PersistenceException e) {
			System.out.println(
					"\n[ERROR QuedadaServicioImpl - unirseQuedada()] - Error de persistencia al unirse a una quedada: "
							+ e);
		}
		return "";
	}

	@Override
	public boolean estaUsuarioUnido(Long idQuedada, String emailUsuario) {
		Quedada q = quedadaRepo.findById(idQuedada).orElse(null);
		Usuario u = usuarioRepo.findFirstByEmail(emailUsuario);

		return (q != null && u != null
				&& (u.getQuedadasParticipante().contains(q) || q.getUsuariosParticipantes().contains(u)));
	}

	@Override
	public boolean cancelarAsistenciaQuedada(Long idQuedada, String emailUsuario) {
		try {
			Quedada q = quedadaRepo.findById(idQuedada).orElse(null);
			Usuario u = usuarioRepo.findFirstByEmail(emailUsuario);

			if (u == null || q == null) {
				return false;
			}

			if (!u.getQuedadasParticipante().contains(q) || !q.getUsuariosParticipantes().contains(u)) {
				return false; // El usuario no está unido a la quedada, no se puede cancelar
			}

			u.getQuedadasParticipante().remove(q);
			q.getUsuariosParticipantes().remove(u);

			usuarioRepo.save(u);
			quedadaRepo.save(q);

			return true;

		} catch (IllegalArgumentException e) {
			System.out.println(
					"\n[ERROR QuedadaServicioImpl - cancelarAsistenciaQuedada()] - Error de argumento incorrecto al cancelar asistencia a una quedada: "
							+ e);
			return false;
		} catch (PersistenceException e) {
			System.out.println("\n[ERROR QuedadaServicioImpl - cancelarAsistenciaQuedada()] - Error de persistencia al cancelar asistencia a una quedada: "+ e);
			return false;
		}
	}

	@Override
	public boolean verificarParticipantesQuedada(long idQuedada) {
		try {			
			Quedada q = quedadaRepo.findById(idQuedada).orElse(null);
			if(q != null) {
				if(q.getUsuariosParticipantes().size() > 1) {
					return true;
				} else {
					return false;
				}
			}

		} catch (IllegalArgumentException e) {
			System.out.println("\n[ERROR QuedadaServicioImpl - verificarParticipantesQuedada()] - Error, no se pudo al verificar los participantes de una quedada: "+ e);
		}
		return false;     
	}
	
	@Override
	public String cancelarQuedada(long idQuedada) {
		try {
			Quedada q = quedadaRepo.findById(idQuedada).orElse(null);
			if(q != null) {
				if(q.getEstado().equals("Completada")) {
					return "Quedada completada";
				} else if(q.getUsuariosParticipantes().size() > 0) {
					return "Usuarios participantes";					
				} else {
					q.setEstado("Cancelada");
					quedadaRepo.save(q);
					return "Quedada cancelada";			}
				
			}
		} catch(IllegalArgumentException e) {
			System.out.println("\n[ERROR QuedadaServicioImpl - cancelarQuedada()] - Error de persistencia al verificar las quedadas completadas: "+ e);
			return "Error al cancelar la quedada";
		}
		return "";
	}

	@Override
	public void actualizarQuedada(long idQuedada) {
		try {
			Quedada q = quedadaRepo.findById(idQuedada).orElse(null);
			if(q != null) {
				q.setEstado("Completada");	
				quedadaRepo.save(q);
			}
		} catch (PersistenceException e) {
			System.out.println("\n[ERROR QuedadaServicioImpl - actualizarQuedada()] - Error de persistencia al marcar como completada una quedada: "+ e);
		}
		
	}

	@Override
	public void agregarComentario(Long idQuedada, String contenido, String emailUsuario) {
		try {
			Quedada q = quedadaRepo.findById(idQuedada).orElse(null);
			if(q != null) {
				Comentario c = new Comentario();
				c.setContenido(contenido);
				c.setQuedada(q);
				c.setFechaComentario(Calendar.getInstance());
				c.setUsuarioAutor(emailUsuario);
				comentarioRepo.save(c);
			}	
		} catch (PersistenceException e) {
			System.out.println("\n[ERROR QuedadaServicioImpl - agregarComentario()] - Error de persistencia al comentar una quedada: "+ e);
		}
	}

	@Override
	public void darLike(long idComentario, String usuarioAutor) {
		try {
			Like like = new Like();
			Comentario comentario = comentarioRepo.findById(idComentario).orElse(null);
			like.setComentario(comentario);
			like.setFechaHoraLike(Calendar.getInstance());
			like.setUsuarioAutor(usuarioAutor);
			likeRepositorio.save(like);
		} catch (IllegalArgumentException e) {
			System.out.println("\n[ERROR QuedadaServicioImpl - darLike()] - Error de argumento incorrecto al dar like: "+ e);
		} catch (PersistenceException e) {
			System.out.println("\n[ERROR QuedadaServicioImpl - darLike()] - Error de persistencia al dar like: "+ e);
		}
		
		
	}

}
