package com.bikerconnect.servicios;

public interface IFotoServicio {

	public String convertirAbase64(byte[] datosImg);
	
	public byte[] convertirAarrayBytes(String imgBase64);
	
	public byte[] cargarFotoPredeterminada();
}
