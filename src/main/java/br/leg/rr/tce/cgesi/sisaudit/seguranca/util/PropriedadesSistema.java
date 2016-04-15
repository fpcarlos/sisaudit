package br.leg.rr.tce.cgesi.sisaudit.seguranca.util;

public class PropriedadesSistema {
	// Estacao de Desenvolvimento
	// public static String SERVIDOR = "http://192.168.0.45";
	// public static String SERVIDOR_REDIRECT =
	// "http://192.168.0.45/ponto.html";
	// public static String SERVIDOR_ORIGEM = "http://192.168.0.45/ponto.html";

	// Estacao de Desenvolvimento sem rede
	public static String SERVIDOR = "http://localhost";
	public static String SERVIDOR_REDIRECT = "http://localhost:8080/relatorio/login.xhtml";
	public static String SERVIDOR_ORIGEM = "http://localhost:8080/relatorio/login.xhtml";

	// Servidor de Produção
	// public static String SERVIDOR = "http://192.168.0.222";
	// public static String SERVIDOR_ORIGEM =
	// "http://tcenet.tcerr.gov.br/TCEBrowser/redirSicad.php";
	// public static String SERVIDOR_REDIRECT =
	// "http://tcenet.tcerr.gov.br/php5/Intranet/Login.form.php";
}
