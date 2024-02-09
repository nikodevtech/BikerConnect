package com.bikerconnect.servicios;

/**
 * Interface donde se declaran los metodos necesarios para el tratamiento de imagenes.
 */
public interface IFotoServicio {

	/**
	 * Convierte un array de bytes en una cadena base64 estandar.
	 * @param datosImg los datos de la imagen como un array de bytes
	 * @return la imagen convertida en una cadena base64
	 */
	public String convertirAbase64(byte[] datosImg);
	
	/**
	 * Convierte una cadena base64 estandar en un array de bytes.
	 * @param imgBase64 los datos de la imagen en base64
	 * @return la imagen convertida en un array de bytes
	 */
	public byte[] convertirAarrayBytes(String imgBase64);
	
	/**
	 * Carga los datos del sistema de archivo del proyecto la imagen predeterminada que 
	 * se establecerá como imagen por defecto para cada usuario en el registro
	 * @return la imagen como un array de bytes
	 */
	public byte[] cargarFotoPredeterminada();
}
