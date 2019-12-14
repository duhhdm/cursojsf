package br.com.farmacia.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class JSFUtil {
	
	public static Boolean isPostBack() {
		return FacesContext.getCurrentInstance().isPostback();
	}
	
	public static Boolean isNotPostBack() {
		return !isPostBack();
	}
	
	public static void adicionarMensagemSucesso(String mensagem, String titulo) {
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, titulo, mensagem);
		FacesContext contexto = FacesContext.getCurrentInstance();
		contexto.addMessage(null, msg);
	}
	
	public static void adicionarMensagemErro(String mensagem, String titulo) {
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, titulo, mensagem);
		FacesContext contexto = FacesContext.getCurrentInstance();
		contexto.addMessage(null, msg);
	}
}
