package br.leg.rr.tce.cgesi.sisaudit.seguranca.filtro;

import java.io.IOException;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.leg.rr.tce.cgesi.sisaudit.seguranca.bean.UsuarioBean;
import br.leg.rr.tce.cgesi.sisaudit.seguranca.ejb.UsuarioEjb;
import br.leg.rr.tce.cgesi.sisaudit.seguranca.util.PropriedadesSistema;

public class Filtro implements Filter {

	public Filtro() {
	}

	@Inject
	private transient UsuarioBean usuarioBean;

	@EJB
	private UsuarioEjb usuarioEjb;

	public void init(FilterConfig filterconfig) throws ServletException {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		String origem = ((HttpServletRequest) request).getHeader("Referer");
		if (origem == null) {
			origem = "nulo";
		}

		HttpServletRequest requestExterno = (HttpServletRequest) request;
		/*
		if (usuarioBean.getUsuario() == null) {
			if (requestExterno.getRemoteUser() != null)
				usuarioBean.preencherUsuarioLogado();
		}
   	    */

		HttpSession s = ((HttpServletRequest) request).getSession();

		if (usuarioBean.getUsuario() != null) {
			usuarioBean.preencherUsuarioLogado();
			chain.doFilter(request, response);

		} else {
			s.invalidate();
			HttpServletResponse res = (HttpServletResponse) response;
			res.sendRedirect(PropriedadesSistema.SERVIDOR_REDIRECT);
		}

	}

	public void destroy() {
	}
}
