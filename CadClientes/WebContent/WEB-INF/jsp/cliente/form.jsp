<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<jsp:include page="/WEB-INF/jsp/header.jsp" />

<c:if test="${not empty erro_cliente}">
	<div class="alert alert-error">
		<button type="button" class="close" data-dismiss="alert">&times;</button>
		<strong>${erro_cliente}</strong>
	</div>
</c:if>

<div id="form">
	<form class="form-horizontal" method="post"
		action="<c:url value="adicionar-cliente"/>">
		<fieldset>
			<!-- Form Name -->
			<legend>
				<h2>Adicionar Cliente</h2>
			</legend>
			<!-- Text input-->
			<div class="control-group">
				<label class="control-label" for="cliente.nome">Nome</label>
				<div class="controls">
					<input id="cliente.nome" name="cliente.nome" type="text"
						placeholder="Nome do cliente" class="input-xlarge" required="">
				</div>
			</div>

			<!-- Text input-->
			<div class="control-group">
				<label class="control-label" for="cliente.cpf">CPF</label>
				<div class="controls">
					<input id="cliente.cep" name="cliente.cpf" type="text"
						placeholder="CPF com formato: xxx.xxx.xxx-xx" 
						class="input-xlarge" required=""
						pattern="\d{3}\.\d{3}\.\d{3}-\d{2}"
						x-moz-errormesage="Utilize o formato completo com pontos e traço.">
				</div>
			</div>

			<div class="control-group">
				<label class="control-label" for="cliente.credito">Credito do Cliente</label>
				<div class="controls">
					<input id="cliente.credito" name="cliente.credito" type="text"
						placeholder="Crédito do Cliente" class="input-xlarge" required="">
				</div>
			</div>

			<div class="control-group">
				<label class="control-label" for="cliente.rua">Rua</label>
				<div class="controls">
					<input id="cliente.rua" name="cliente.rua" type="text"
						placeholder="Rua do end do Cliente" class="input-xlarge" required="">
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label" for="cliente.cep">CEP</label>
				<div class="controls">
					<input id="cliente.cep" name="cliente.cep" type="text"
						placeholder="CEP do end do Cliente" class="input-xlarge" required="">
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label" for="cliente.cidade">Cidade</label>
				<div class="controls">
					<input id="cliente.cidade" name="cliente.cidade" type="text"
						placeholder="Cidade do end do Cliente" class="input-xlarge" required="">
				</div>
			</div>

			<!-- Text input-->
			<div class="control-group">
				<label class="control-label" for="cliente.telefone">Telefone</label>
				<div class="controls">
					<input id="cliente.telefone" name="cliente.telefone" type="text"
						placeholder="(xxx) xxxxx-xxxx" class="input-medium" required="">
				</div>
			</div>
		</fieldset>
		<fieldset>
			<legend>
				<h3>Dependentes</h3>
			</legend>
			<div class="control-group">
				<label class="control-label" for="dependente1.nome">Nome do Dependente</label>
				<div class="controls">
					<input id="dependente1.nome" name="dependente1.nome" type="text"
						placeholder="Nome do Dependente" class="input-medium">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="dependente2.nome">Nome do Dependente</label>
				<div class="controls">
					<input id="dependente2.nome" name="dependente2.nome" type="text"
						placeholder="Nome do Dependente" class="input-medium">
				</div>
			</div>
		</fieldset>
		<div class="form-actions">
			<div class="pull-right">
				<button type="submit" class="btn btn-primary">Adicionar</button>
				<button type="reset" class="btn">Limpar</button>
			</div>
		</div>
	</form>
</div>

<script type="text/javascript">
	$('#addNav').addClass('active');
</script>

<jsp:include page="/WEB-INF/jsp/footer.jsp" />