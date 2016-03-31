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
<c:if test="${not empty msg}">
	<div class="alert alert-success">
		<button type="button" class="close" data-dismiss="alert">&times;</button>
		<strong>${msg}</strong>
	</div>
</c:if>

<div id="form">
	<form class="form-horizontal" method="post"
		action='<c:url value="salvar-dependente"/>'>
		<fieldset>
			<!-- Form Name -->
			<legend>
				<h2>Editar Dependente "${dependente.nome}"</h2>
			</legend>
			<input id="cliente.id" name="cliente.id" type="hidden"
				value="${dependente.parente.id}">
			<input id="dependente.id" name="dependente.id" type="hidden"
				value="${dependente.id}">

			<div class="control-group">
				<label class="control-label" for="dependente.nome">Nome</label>
				<div class="controls">
					<input id="dependente.nome" name="dependente.nome" type="text"
						placeholder="Nome do dependente" class="input-xlarge" required=""
						value="${dependente.nome}">
				</div>
			</div>		
			
		</fieldset>
			<div class="form-actions">
				<div class="pull-right">
					<button type="submit" class="btn btn-primary">Salvar</button>
					<a href="<c:url value="listDeps"><c:param name="id" value="${dependente.parente.id}" />
					</c:url>" class="btn btn-primary">Voltar</a>
				</div>
			</div>
	</form>
</div>

<jsp:include page="/WEB-INF/jsp/footer.jsp" />