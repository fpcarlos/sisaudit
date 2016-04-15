package br.leg.rr.tce.cgesi.sisaudit.seguranca.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import br.leg.rr.tce.cgesi.sisaudit.entity.Servidor;

public class SessionControllerUsuarioListener implements HttpSessionAttributeListener{
	private Servidor servidorLogado;
    private static List<Servidor> listaServidorsLogados = new ArrayList<Servidor>();
    private static int qtdeServidorsLogados = 0;
    private static Map<Servidor, HttpSession> ServidorSessao = new HashMap<Servidor, HttpSession>();

    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        if (event.getValue() instanceof Servidor) {
            servidorLogado = (Servidor) event.getValue();
            System.out.println("Servidor entrou > " + event.getValue());
            qtdeServidorsLogados++;
            System.out.println("Servidor logou. Quantidade de Servidors logados = " + qtdeServidorsLogados);
            listaServidorsLogados.add(servidorLogado);         
            ServidorSessao.put(servidorLogado, event.getSession());
            System.out.println("ServidorSessao " + ServidorSessao);
        }
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {        
        if (event.getValue() instanceof Servidor) {
            System.out.println("Servidor saiu > " + event.getValue());
            qtdeServidorsLogados--;
            listaServidorsLogados.remove(servidorLogado);
            ServidorSessao.remove(servidorLogado);
            System.out.println("ServidorSessao " + ServidorSessao);
            System.out.println("Servidor saiu. Quantidade de Servidors logados = " + qtdeServidorsLogados);

            //closeSession();
            //getServletRequest().logout();
        }
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {
        if (event.getValue() instanceof Servidor) {
            System.out.println("?? attributeReplaced.....");
        }
    }

    public static List<Servidor> getListaServidorsLogados() {
        return listaServidorsLogados;
    }
	
}
