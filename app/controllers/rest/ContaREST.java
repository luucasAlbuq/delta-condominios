package controllers.rest;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import model.financeiro.Conta;
import play.db.jpa.Transactional;
import play.libs.F.Option;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http.MultipartFormData;
import play.mvc.Http.MultipartFormData.FilePart;
import play.mvc.Result;
import services.CondominioService;
import services.ContaService;
import util.Util;
import beans.CondominioBean;
import beans.ContaBean;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Iterables;
import com.google.common.net.MediaType;

import exceptions.ValidacaoException;

/**
 * Camada REST de recursos para manipulacao de Contas
 * 
 * @author Lucas
 *
 */
public class ContaREST extends Controller {

	private static ContaService contaService = ContaBean.getInstance();
	private static CondominioService condominioService = CondominioBean
			.getInstance();
	private static ObjectMapper mapper = new ObjectMapper();

	/**
	 * Cadastra uma conta vinculado a um condominio no sistema.
	 * 
	 * @param idCondminio
	 * @return
	 * @throws IOException
	 */
	@Transactional
	public static Result cadastraConta(Long idCondminio) throws IOException {
		JsonNode jsonNode = request().body().asJson();
		try {
			Conta conta = mapper.readValue(jsonNode.toString(), Conta.class);
			conta.setCondominio(condominioService
					.consultarCondominio(idCondminio));
			contaService.cadastrarConta(conta, idCondminio);
			return created(Json.toJson(conta));
		} catch (ValidacaoException | IOException ex) {
			return badRequest(ex.getMessage());
		}
	}

	/**
	 * Faz upload de um arquivo associado à conta
	 * 
	 * @param idCondminio
	 *            o condominio da conta
	 * @param idConta
	 *            a conta onde se quer fazer upload do documento
	 */
	@Transactional
	public static Result uploadDocumento(Long idCondominio, Long idConta)
			throws IOException {
		try {
			Conta conta = contaService.consultarConta(idCondominio, idConta);
			MultipartFormData multipart = request().body()
					.asMultipartFormData();
			int numArquivos = multipart.getFiles().size();
			if (numArquivos != 1) {
				badRequest("Número de arquivos inválido");
			}
			FilePart pdf = Iterables.getFirst(multipart.getFiles(), null);
			if (!pdf.getContentType().equals(MediaType.PDF.toString())) {
				badRequest("Media Type: " + pdf.getContentType()
						+ " não aceitável.");
			}
			File pdfFile = null;
			if (pdf != null) {
				pdfFile = pdf.getFile();
				conta.setDocumentoPDF(Util.read(pdfFile));
			}
			contaService.atualizarConta(conta, idConta);
			return created(conta.getDocumentoPDF())
					.as(MediaType.PDF.toString());
		} catch (Exception ex) {
			return internalServerError(ex.getMessage());
		}
	}

	/**
	 * Retorna o arquivo associado à conta
	 * 
	 * @param idCondminio
	 *            o condominio da conta
	 * @param idConta
	 *            a conta onde se quer fazer upload do documento
	 */
	@Transactional
	public static Result getDocumento(Long idCondominio, Long idConta)
			throws IOException {
		try {
			Conta conta = contaService.consultarConta(idCondominio, idConta);
			byte[] bytesDocumento = conta.getDocumentoPDF();
			if (bytesDocumento == null) {
				return notFound("Não há documentos nessa conta.");
			}
			return ok(conta.getDocumentoPDF()).as(MediaType.PDF.toString());
		} catch (ValidacaoException ex) {
			return badRequest(ex.getMessage());
		}
	}

	/**
	 * Atualiza uma conta.
	 * 
	 * @param idCondominio
	 * @param idConta
	 * @return se a atualizacao foi feita com sucesso
	 */
	@Transactional
	public static Result putConta(Long idCondominio, Long idConta) {
		JsonNode jsonNode = request().body().asJson();
		try {
			Conta conta = mapper.readValue(jsonNode.toString(), Conta.class);
			contaService.atualizarConta(conta, idConta);
			return ok(Json.toJson(conta));
		} catch (ValidacaoException | IOException ex) {
			return badRequest(ex.getMessage());
		}
	}

	/**
	 * Retorna uma Conta específica
	 * 
	 * @param Long
	 *            idCondominio
	 * @param Long
	 *            idConta
	 * @return {@link Conta}
	 */
	@Transactional
	public static Result getConta(Long idCondominio, Long idConta) {
		try {
			Conta conta = contaService.consultarConta(idCondominio, idConta);
			return ok(Json.toJson(conta));
		} catch (ValidacaoException ex) {
			return badRequest(ex.getMessage());
		}
	}

	/**
	 * Lista todos as contas de um condominio
	 * 
	 * @param idCondominio
	 * @return contas do condominio
	 * @throws ParseException
	 */
	@Transactional
	public static Result getContasCondominio(Option<String> mesInicio,
			Option<String> mesFim, Long idCondominio) throws ParseException {
		String inicio = mesInicio.getOrElse(null);
		String fim = mesFim.getOrElse(null);
		Date mesInicioFormatado = null;
		if (inicio != null) {
			SimpleDateFormat dataInicioFormatada = new SimpleDateFormat(inicio);
			mesInicioFormatado = dataInicioFormatada.parse(dataInicioFormatada
					.toString());
		}
		Date mesFimFormatado = null;
		if (fim != null) {
			SimpleDateFormat dataFimFormatada = new SimpleDateFormat(fim);
			mesFimFormatado = dataFimFormatada.parse(dataFimFormatada
					.toString());
		}
		try {
			List<Conta> contas = contaService
					.consultarTodasAsContasDoCondominio(mesInicioFormatado,
							mesFimFormatado, idCondominio);
			return ok(Json.toJson(contas));
		} catch (ValidacaoException ex) {
			return badRequest(ex.getMessage());
		}
	}

	/**
	 * Remove uma conta do sistema
	 * 
	 * @param Long
	 *            idCondiminio
	 * @param Long
	 *            idConta
	 * @return Result
	 */
	@Transactional
	public static Result deleteConta(Long idCondiminio, Long idConta) {
		try {
			contaService.removerConta(idConta);
			return ok();
		} catch (Exception ex) {
			return badRequest(ex.getMessage());
		}
	}
}
