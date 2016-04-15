package br.leg.rr.tce.cgesi.sisaudit.seguranca.bean;

import java.io.Serializable;
import java.security.Principal;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import br.leg.rr.tce.cgesi.sisaudit.bean.AbstractBean;
import br.leg.rr.tce.cgesi.sisaudit.entity.Grupo;
import br.leg.rr.tce.cgesi.sisaudit.entity.Servidor;
import br.leg.rr.tce.cgesi.sisaudit.seguranca.ejb.UsuarioEjb;
import br.leg.rr.tce.cgesi.sisaudit.seguranca.util.SessionControllerUsuarioListener;

@Named
@SessionScoped
public class UsuarioBean extends AbstractBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	protected UsuarioEjb usuarioEjb;
	
	//@EJB
	//protected Conversation conversation;
	
	protected Servidor usuario;
	protected Servidor usuarioSelected;
	protected Grupo grupo;
	protected Grupo grupoSelected;

	//protected Status status;
	//protected Status grupoStatus;
	//protected boolean operatingGrupo;
	//protected boolean changePassword;

	
	@PostConstruct
    public void init() {
        setUsuario(new Servidor());
        //setStatus(Status.LISTING);
        //setGrupoStatus(Status.LISTING);
        //setOperatingGrupo(false);
        //setChangePassword(false);
    }

	
	public List<Servidor> getUsuarios() {
        try {
            return this.usuarioEjb.retrieveUsuarios();
        } catch (Exception exception) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Lista de usuarios: ", exception.getMessage()));
            return null;
        }
    }
	
	
	public void viewUsuario() {
        try {
            if (getUsuarioSelected() != null) {
                setUsuario(getUsuarioSelected());
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Visualizar: ", " usuario ("
                        + getUsuario().getId() + ")"));
                //setStatus(Status.VIEWING);
            } else {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Falha: ", "NÃ£o hÃ¡ usuario selecionada para visualizar."));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Falha: ", "Erro ao visualizar: " + e.getMessage()));
        }
    }
	
	
	public String logout() {

        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();

        try {
            request.logout();
        } catch (Exception e) {
        }
        return "/index.xhtml";
    }

    public boolean isUserLoggedIn() {
        if(remoteUser() != null) {
        	if (getUsuario() == null) {
    				preencherUsuarioLogado();
    		}
            return true;
        }
        return false;
    }
	
    public String remoteUser() {

        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();

        try {
            return request.getRemoteUser();
        } catch (Exception e) {
            return null;
        }

    }
    public String getMostraUser(){
    	String aux1 = remoteUser();
    	Principal aux2 = (Principal) FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
    	
    	if(remoteUser() != null) {
    		
            return FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName();            
    	}
    	return null;
    }
    
    public void preencherUsuarioLogado(){
    	try {
    		String vnome = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName();
    	    usuario = usuarioEjb.pegaLogado(vnome);            
        	
		} catch (Exception e) {
			//return null;
		}
    	
    }
    
    public String logar() {
        boolean estaLogado = false;
        List<Servidor> listaUsuariosLogados = SessionControllerUsuarioListener.getListaServidorsLogados();
        try {
        	String vnome = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName();
    	    usuario = usuarioEjb.pegaLogado(vnome);
            //usuario = usuarioDao.recuperaUsuario(login, senha);            
            if (!listaUsuariosLogados.isEmpty()) {                
                for (Servidor user : listaUsuariosLogados) {
                    if (user.equals(usuario)) {
                        estaLogado = true;
                    }
                }
            } else {
                //return loginPermitido();
            	return "login1";
            }
            if (estaLogado) {
                //FacesUtil.mensagemErro("Erro: o usuário já está logado!!", "");
                return "login2";
            } else {
                //return loginPermitido();
            	return "login3";
            }

        } catch (Exception ex) {
        	//ex.
            //FacesUtil.mensagemErro("Erro ao tentar logar", "Verifique se o nome e a senha estão corretos.");
        }
        return "login0";
    }
    
	public Servidor getUsuario() {
		return usuario;
	}

	public void setUsuario(Servidor usuario) {
		this.usuario = usuario;
	}
	
	
	public Servidor getUsuarioSelected() {
		return usuarioSelected;
	}


	public void setUsuarioSelected(Servidor usuarioSelected) {
		this.usuarioSelected = usuarioSelected;
	}


	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public Grupo getGrupoSelected() {
		return grupoSelected;
	}

	public void setGrupoSelected(Grupo grupoSelected) {
		this.grupoSelected = grupoSelected;
	}
	
	
	
	
	
	
	/*
	 * private final PrincipalImpl principal;
	 * 
	 * @Inject public AutenticacaoServiceBean(Principal principal,
	 * UsuarioRepository usuarioRepository) { this.principal = principal;
	 * this.usuarioRepository = usuarioRepository; }
	 */

}
