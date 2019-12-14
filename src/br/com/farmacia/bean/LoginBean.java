package br.com.farmacia.bean;

import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.farmacia.dao.FuncionarioHibernateDAO;
import br.com.farmacia.model.Funcionario;
import br.com.farmacia.util.JSFUtil;
import br.com.farmacia.util.SessionUtil;

@ManagedBean(name = "MBLogin")
@SessionScoped
@ViewScoped
public class LoginBean {

	private Funcionario funcionario = new Funcionario();
	private Boolean login;
	private FuncionarioHibernateDAO funDAO = new FuncionarioHibernateDAO();
	private String usuario = new String();
	private String senha = new String();
	private String result = "Logado";

	public void entrarFuncionario() throws IOException {
		System.out.println(SessionUtil.getParam(result));
		funcionario = funDAO.buscarPorId(Long.parseLong(usuario));
		if (funcionario.getCodigo() == Long.parseLong(usuario)) {
			if (funcionario.getSenha().equals(senha)) {
				FacesContext.getCurrentInstance().getExternalContext().redirect("faces/pages/index.xhtml");
				SessionUtil.setParam(result, funcionario.getCodigo());
				System.out.println(SessionUtil.getParam(result));
				System.out.println(SessionUtil.getSession());
			}
		} else {
			JSFUtil.adicionarMensagemSucesso("", "ERRO: Acesso incorreto.");
		}
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Boolean getLogin() {
		return login;
	}

	public void setLogin(Boolean login) {
		this.login = login;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

}
