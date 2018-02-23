package br.com.maiscadastros.controller;

import java.util.ArrayList;
import java.util.List;
import br.com.maiscadastros.dao.VendaDao;
import br.com.maiscadastros.dto.VendaDto;
import br.com.maiscadastros.model.Venda;

public class VendaController {
	public VendaDto cadastrarVenda(Venda pVenda) {
		// Verificar as informações
		if (pVenda == null) {
			return new VendaDto(false, "Tentativa de inclusão de Venda nulo");
		}

		// Criando o objeto de persistência
		VendaDao tDao = new VendaDao();

		// Verificando se o Venda já existe
		Venda tVenda = tDao.recovery(pVenda.getId());
		if (tVenda != null) {
			return new VendaDto(false, "Já existe uma Venda com o id informado");
		}

		// Incluindo o Venda
		tVenda = tDao.create(pVenda);
		if (tVenda == null) {
			return new VendaDto(false, "Erro no processo de inclusão");
		}

		// Retornando o indicativo de sucesso
		return new VendaDto(true, "Venda incluída com sucesso", tVenda);
	}

	public VendaDto recuperarVenda(int pId) {
		// Verificar as informações
		if (pId <= 0) {
			return new VendaDto(false, "Identificador do Venda inválido");
		}

		// Criando o objeto de persistência
		VendaDao tDao = new VendaDao();

		// Recuperando o Venda
		Venda tVenda = tDao.recovery(pId);
		if (tVenda == null) {
			return new VendaDto(false, "Não existe Venda com o identificador informado");
		}

		// Retornando o indicativo de sucesso
		return new VendaDto(true, "Venda recuperado com sucesso", tVenda);
	}

	public VendaDto atualizarVenda(Venda pVenda) {
		// Verificar as informações
		if (pVenda == null) {
			return new VendaDto(false, "Tentativa de atualização de Venda nulo");
		}

		// Criando o objeto de persistência
		VendaDao tDao = new VendaDao();

		// Recuperando o Venda
		Venda tVenda = tDao.recovery(pVenda.getId());
		if (tVenda == null) {
			return new VendaDto(false, "Não existe Venda com o identificador informado");
		}

		if (pVenda.getNotaFiscal() != tVenda.getNotaFiscal()) {
			// Verificando se existe um Venda com o novo CPF
			tVenda = tDao.recoveryByNotaFiscal(pVenda.getNotaFiscal());
			if (tVenda != null) {
				return new VendaDto(false, "Já existe Venda com a Nota Fiscal informada");
			}
		}

		// Atualizando o Venda
		tVenda = tDao.update(pVenda);
		if (tVenda == null) {
			return new VendaDto(false, "Não existe Venda com o identificador informado");
		}

		// Retornando o indicativo de sucesso
		return new VendaDto(true, "Venda alterado com sucesso", tVenda);
	}

	public VendaDto removeVenda(int pId) {
		// Verificar as informações
		if (pId <= 0) {
			return new VendaDto(false, "Identificador do Venda inválido");
		}

		// Criando o objeto de persistência
		VendaDao tDao = new VendaDao();

		// Incluindo o Venda
		if (tDao.delete(pId)) {
			// Retornando o indicativo de sucesso
			return new VendaDto(true, "Venda removido com sucesso");
		}

		// Retornando o indicativo de erro
		return new VendaDto(false, "Erro no processo de remoção");
	}

	public VendaDto pesquisarVendasPorDescricao(String pDescricao) {
		// Criando a lista de retorno
		List<Venda> tLista = new ArrayList<>();

		// Criando o objeto de persistência
		VendaDao tDao = new VendaDao();

		// Recuperando o Venda
		tLista = tDao.searchByDescricao(pDescricao);

		// Retornando o indicativo de sucesso
		return new VendaDto(true, "Lista de Vendas recuperada com sucesso", tLista);
	}

	public VendaDto pesquisar() {
		// Criando a lista de retorno
		List<Venda> tLista = new ArrayList<>();

		// Criando o objeto de persistência
		VendaDao tDao = new VendaDao();

		// Recuperando o Venda
		tLista = tDao.search();

		// Retornando o indicativo de sucesso
		return new VendaDto(true, "Lista de Vendas recuperada com sucesso", tLista);
	}
}
