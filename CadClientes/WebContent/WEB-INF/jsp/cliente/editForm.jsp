<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<jsp:include page="/WEB-INF/jsp/header.jsp" />

<c:if test="${not empty erroMsg}">
	<div class="alert alert-error">
		<button type="button" class="close" data-dismiss="alert">&times;</button>
		<strong>${erroMsg}</strong>
	</div>
</c:if>

<div id="form">
	<form class="form-horizontal" method="post"
		action='<c:url value="salvar-cliente"/>'>
		<fieldset>
			<!-- Form Name -->
			<legend>
			${cliente_existente}
				<h2>Editar Cliente "${cliente.nome}"</h2>
			</legend>
			<input id="cliente.id" name="cliente.id" type="hidden"
				placeholder="Nome do cliente" class="input-xlarge" required=""
				value="${cliente.id}">

			<div class="control-group">
				<label class="control-label" for="cliente.nome">Nome</label>
				<div class="controls">
					<input id="cliente.nome" name="cliente.nome" type="text"
						placeholder="Nome do cliente" class="input-xlarge" required=""
						value="${cliente.nome}">
				</div>
			</div>

			<!-- Text input-->
			<div class="control-group">
				<label class="control-label" for="cliente.cpf">CPF</label>
				<div class="controls">
					<input id="cliente.cpf" name="cliente.cpf" type="text"
						placeholder="CPF do cliente" class="input-xlarge" required=""
						value="${cliente.cpf}">
				</div>
			</div>

			<div class="control-group">
				<label class="control-label" for="cliente.credito">Crédito</label>
				<div class="controls">
					<input id="cliente.credito" name="cliente.credito" type="text"
						placeholder="Crédito do Cliente" class="input-xlarge" required=""
						value="${cliente.credito}">
				</div>
			</div>

			<div class="control-group">
				<label class="control-label" for="cliente.rua">Rua do Endereço</label>
				<div class="controls">
					<input id="cliente.rua" name="cliente.rua" type="text"
						placeholder="Rua do Endereço do Cliente" class="input-xlarge" required=""
						value="${cliente.rua}">
				</div>
			</div>

			<div class="control-group">
				<label class="control-label" for="cliente.cep">CEP do Endereço</label>
				<div class="controls">
					<input id="cliente.cep" name="cliente.cep" type="text"
						placeholder="CEP do Cliente" class="input-xlarge" required=""
						value="${cliente.cep}">
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label" for="cliente.cidade">Cidade do Cliente</label>
				<div class="controls">
					<input id="cliente.cidade" name="cliente.cidade" type="text"
						placeholder="Cidade do Cliente" class="input-xlarge" required=""
						value="${cliente.cidade}">
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label" for="cliente.dataCadastro">Data do Cadastro</label>
				<div class="controls">
					<input id="cliente.dataCadastro" name="cliente.dataCadastro" type="text"
						class="input-xlarge" required="" disabled="disabled"
						value="${cliente.dataCadastro}">
				</div>
			</div>
			
		</fieldset>
		<fieldset>
			<legend>
				<h3>Dependentes</h3>
			</legend>
			<c:if test="${fn:length(cliente.dependentes) gt 0}">
   				<c:forEach items="${cliente.dependentes}" var="dependente" varStatus="loop">
   					
   					<input id="dependente${loop.index}.id" name="dependente${loop.index}.id" type="hidden"
						value="${dependente.id}">
   					
   					<div class="control-group">
						<label class="control-label" for="dependente${loop.index}.nome">Nome do Dependente</label>
						<div class="controls">
						<input id="dependente${loop.index}.nome" name="dependente${loop.index}.nome" type="text"
							placeholder="Nome do Dependente" class="input-medium"
							value="${dependente.nome} ">
						</div>
					</div>
   				</c:forEach>
			</c:if>
			<c:if test="${fn:length(cliente.dependentes) == 0}">
				<div class="control-group">
				<label class="control-label" for="dependente0.nome">Nome do Dependente</label>
				<div class="controls">
					<input id="dependente0.nome" name="dependente0.nome" type="text"
						placeholder="Nome do Dependente" class="input-medium">
				</div>
			</div>
				<div class="control-group">
				<label class="control-label" for="dependente1.nome">Nome do Dependente</label>
				<div class="controls">
					<input id="dependente1.nome" name="dependente1.nome" type="text"
						placeholder="Nome do Dependente" class="input-medium">
				</div>
			</div>
			</c:if>
			<c:if test="${fn:length(cliente.dependentes) == 1}">
				<div class="control-group">
				<label class="control-label" for="dependente1.nome">Nome do Dependente</label>
				<div class="controls">
					<input id="dependente1.nome" name="dependente1.nome" type="text"
						placeholder="Nome do Dependente" class="input-medium">
				</div>
			</div>
			</c:if>
			
			
		</fieldset>
			<div class="form-actions">
				<div class="pull-right">
					<button type="submit" class="btn btn-primary">Salvar</button>
				</div>
			</div>
		
	</form>
</div>

<jsp:include page="/WEB-INF/jsp/footer.jsp" />