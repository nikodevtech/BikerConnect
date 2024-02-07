package com.bikerconnect.servicios;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bikerconnect.dtos.UsuarioDTO;
import com.bikerconnect.entidades.Usuario;

/**
 * Servicio que implementa los metodos de la interface {@link IUsuarioToDao} 
 * y en esta clase es donde se entra al detalle de la logica de dichos métodos
 * para el paso de usuarioDTO a DAO
 */
@Service
public class UsuarioToDaoImpl implements IUsuarioToDao {

	@Autowired
	private IFotoServicio fotoServicio;
	
	@Override
	public Usuario usuarioToDao(UsuarioDTO usuarioDTO) {

		Usuario usuarioDao = new Usuario();

		try {
			usuarioDao.setId(usuarioDTO.getId());
			usuarioDao.setNombreApellidos(usuarioDTO.getNombreUsuario() + " " + usuarioDTO.getApellidosUsuario());
			usuarioDao.setEmail(usuarioDTO.getEmailUsuario());
			usuarioDao.setPassword(usuarioDTO.getClaveUsuario());
			usuarioDao.setTelefono(usuarioDTO.getTlfUsuario());
			if(usuarioDTO.getFoto() != null) {
				usuarioDao.setFoto(fotoServicio.convertirAarrayBytes(usuarioDTO.getFoto()));
			}
			
			return usuarioDao;

		} catch (Exception e) {
			System.out.println(
					"\n[ERROR UsuarioToDaoImpl - toUsuarioDao()] - Al convertir usuarioDTO a usuarioDAO (return null): "
							+ e);
			return null;
		}

	}

	@Override
	public List<Usuario> listUsuarioToDao(List<UsuarioDTO> listaUsuarioDTO) {

		List<Usuario> listaUsuarioDao = new ArrayList<>();

		try {
			for (UsuarioDTO usuarioDTO : listaUsuarioDTO) {
				listaUsuarioDao.add(usuarioToDao(usuarioDTO));
			}

			return listaUsuarioDao;

		} catch (Exception e) {
			System.out.println(
					"\n[ERROR UsuarioToDaoImpl - toListUsuarioDao()] - Al convertir lista de usuarioDTO a lista de usuarioDAO (return null): "
							+ e);
		}
		return null;
	}


}
