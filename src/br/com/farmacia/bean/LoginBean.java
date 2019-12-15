package br.com.farmacia.bean;

import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.farmacia.dao.FuncionarioHibernateDAO;
import br.com.farmacia.model.Funcionario;
import br.com.farmacia.util.JSFUtil;
import br.com.farmacia.util.PasswordMD5;
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
	private String novaSenha = new String();

	public void entrarFuncionario() throws IOException {
		System.out.println(SessionUtil.getParam(result));
		funcionario = funDAO.buscarPorId(Long.parseLong(usuario));
		PasswordMD5 crip = new PasswordMD5();
		if (funcionario.getCodigo() == Long.parseLong(usuario)) {
			if (funcionario.getSenha().equals(senha)) {
				FacesContext.getCurrentInstance().getExternalContext().redirect("faces/pages/index.xhtml");
				SessionUtil.setParam(result, funcionario.getCodigo());
			} 
			else if (funcionario.getSenha().equals(crip.conversaoStringMD5(senha))) {
				FacesContext.getCurrentInstance().getExternalContext().redirect("faces/pages/index.xhtml");
				SessionUtil.setParam(result, funcionario.getCodigo());
			} 
			else {
				JSFUtil.adicionarMensagemErro("", "ERRO: Acesso incorreto.");
			}
		} 
		else {
			JSFUtil.adicionarMensagemErro("", "ERRO: Acesso incorreto.");
		}
	}

	public void logout() throws IOException {
		SessionUtil.remove(result);
		FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
		System.out.println(SessionUtil.getParam(result));
	}

	public void trocarSenha() {
		String usuarioTroca = SessionUtil.getParam(result).toString();
		PasswordMD5 crip = new PasswordMD5();
		funcionario = funDAO.buscarPorId(Long.parseLong(usuarioTroca));
		if (!funcionario.getSenha().equals(novaSenha)) {
			if (!funcionario.getSenha().equals(crip.conversaoStringMD5(novaSenha))) {
				funcionario.setSenha(crip.conversaoStringMD5(novaSenha));
				funDAO.atualizar(funcionario);
				JSFUtil.adicionarMensagemSucesso("", "SUCESSO: Senha Alterada com sucesso.");
			} else {
				JSFUtil.adicionarMensagemErro("", "ERRO: Não foi possivel alterar a senha");
			}
		} else {
			JSFUtil.adicionarMensagemErro("", "ERRO: Não foi possivel alterar a senha");
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

	public String getNovaSenha() {
		return novaSenha;
	}

	public void setNovaSenha(String novaSenha) {
		this.novaSenha = novaSenha;
	}

}
