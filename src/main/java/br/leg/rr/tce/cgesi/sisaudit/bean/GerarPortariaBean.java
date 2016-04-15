package br.leg.rr.tce.cgesi.sisaudit.bean;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletContext;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import br.leg.rr.tce.cgesi.sisaudit.comum.util.Util;
import br.leg.rr.tce.cgesi.sisaudit.ejb.AuditoriaEjb;
import br.leg.rr.tce.cgesi.sisaudit.ejb.PortariaEjb;
import br.leg.rr.tce.cgesi.sisaudit.entity.Portaria;

@Named("gerarPortariaBean")
@SessionScoped
public class GerarPortariaBean extends AbstractBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private AuditoriaEjb auditoriaEjb;

	@EJB
	private PortariaEjb portariaEjb;

	private StreamedContent file;

	public GerarPortariaBean() {
		super();
	}

	public void mesclarPortariaComModelo(Portaria aux) throws Exception {

		ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext()
				.getContext();

		String filePath = servletContext.getRealPath("/resources/modeloDocs/Teste.doc");
		// response.setContentType("application/pdf");

		POIFSFileSystem fs = null;

		String tipoArq = "docx";
		Portaria portaria = new Portaria();
		portaria = portariaEjb.pegarPortaria(aux.getId());
		SimpleDateFormat fd = new SimpleDateFormat("dd/MM/yyyy");
		try {
			fs = new POIFSFileSystem(new FileInputStream(filePath));
			HWPFDocument doc = new HWPFDocument(fs);
			Range range = doc.getRange();
			range.replaceText("#NUMEROANOPORTARIA#", portaria.getNumeroPortaria() + "/" + portaria.getAnoPortaria());
			if (portaria.getTipoFiscalizacao().getId() != null)
				range.replaceText("#TIPOAUDITORIA#", portaria.getTipoFiscalizacao().getNome());
			else
				range.replaceText("#TIPOAUDITORIA#", "TIPO DA AUDITORIA NÃO FOI DEFINIDO");
			// #ListaUG
			range.replaceText("#LISTAUG#", portaria.getListaSiglaUnidadeGestoraDaPortaria());
			// #PlanInicio
			// String PlanInicio = fd.format(portaria.getPlanInicio());
			range.replaceText("#PLANINICIO#", fd.format(portaria.getPlanInicio()));
			// #RelFim
			// String RelFim = fd.format(portaria.getRelaFim());
			range.replaceText("#RELFIM#", fd.format(portaria.getRelaFim()));
			// #Objetivo
			range.replaceText("#OBJETIVO#", portaria.getObjetivo());
			// #DATAATUAL#
			range.replaceText("#DATAATUAL#", Util.hoje().toString());
			// range.replaceText("#TABELASERVIDORES#", table);
			
			saveWord(filePath, doc, tipoArq);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void saveWord(String filePath, HWPFDocument doc, String tipoArq) throws FileNotFoundException, IOException {
		FileOutputStream out = null;
		ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext()
				.getContext();

		String filePath2 = servletContext.getRealPath("/resources/modeloDocs/Teste2.doc");
		try {
			out = new FileOutputStream(filePath2);
			doc.write(out);
			downloadPortaria(filePath2, tipoArq);
		} finally {
			out.close();
		}
	}

	

	public void downloadPortaria(String nomeArq, String tipoArq) {
		File f = new File(nomeArq);
		try {
			FileInputStream stream = new FileInputStream(f);
			file = new DefaultStreamedContent(stream,
					"application/application/vnd.ms-word",
					"Portaria.doc");
			//docx: vnd.openxmlformats-officedocument.wordprocessingml.document.main+xml
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public StreamedContent getFile() {
		return file;
	}

	public void setFile(StreamedContent file) {
		this.file = file;
	}

}
