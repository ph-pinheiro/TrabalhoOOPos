<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<jsp:include page="/WEB-INF/jsp/header.jsp" />

<h2>Controle de Dependentes</h2>

<c:if test="${not empty msg}">
	<div class="alert alert-success">
		<button type="button" class="close" data-dismiss="alert">&times;</button>
		<strong>${msg}</strong>
	</div>
</c:if>

<div id="listagem">
	
	<h3>Dados do Cliente</h3>
	<p><strong>Nome:</strong> ${cliente.nome}</p>
	<p><strong>CPF:</strong> ${cliente.cpf}</p>
	<p><strong>Crédito do Cliente:</strong> ${cliente.credito}</p>
	<p><strong>Rua do Endereço:</strong> ${cliente.rua}</p>
	<p><strong>CEP do Cliente:</strong> ${cliente.cep}</p>
	<p><strong>Cidade do Cliente:</strong> ${cliente.cidade}</p>
	
	<hr/>
	
	<h3>Dependentes</h3>
	
	<table class="table table-bordered table-hover table-striped">
		<thead>
			<tr>
				<th>Dependentes</th>
				<th style="width: 5%">Editar</th>
				<th style="width: 5%">Excluir</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${fn:length(cliente.dependentes) gt 0}">
					<c:forEach items="${cliente.dependentes}" var="dependente">
						<tr>
							<td>${dependente.nome}</td>
							<td><a class="btn btn-warning"
								href='<c:url value="editDep"><c:param name="idDependente" value="${dependente.id}" />
								<c:param name="idCliente" value="${cliente.id}" /></c:url>'>
								<i class="icon-edit"></i></a></td>
							<td><a class="btn btn-danger delete-btn"
							     href='<c:url value="excluirDeps"><c:param name="idDependente" value="${dependente.id}" />
							     <c:param name="idCliente" value="${cliente.id}" /></c:url>'> 
							       <i class="icon-remove"></i></a></td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<td colspan="6" style="text-align: center;">Nenhum Dependente
						Cadastrado</td>
				</c:otherwise>
			</c:choose>
		</tbody>
	</table>
	
	<c:if test="${fn:length(cliente.dependentes) < 2}">
		<form class="form-horizontal" method="post" action='<c:url value="salvar-dependente"/>'>
			<fieldset>
				
				<input id="cliente.id" name="cliente.id" type="hidden"
				value="${cliente.id}">
				
				<div class="control-group">
				<label class="control-label" for="dependente.nome">Nome</label>
				<div class="controls">
					<input id="dependente.nome" name="dependente.nome" type="text"
						placeholder="Nome do dependente" class="input-xlarge" required="">
					<button type="submit" class="btn btn-primary">Adicionar</button>
				</div>
			</div>
			</fieldset>
		</form>
	</c:if>
	<c:if test="${fn:length(cliente.dependentes) < 1}">
		<form class="form-horizontal" method="post" action='<c:url value="salvar-dependente"/>'>
			<fieldset>
				
				<input id="cliente.id" name="cliente.id" type="hidden"
				value="${cliente.id}">
				
				<div class="control-group">
				<label class="control-label" for="dependente.nome">Nome</label>
				<div class="controls">
					<input id="dependente.nome" name="dependente.nome" type="text"
						placeholder="Nome do dependente" class="input-xlarge" required="">
					<button type="submit" class="btn btn-primary">Adicionar</button>
				</div>
			</div>
			</fieldset>
		</form>
	</c:if>
	
</div>

<!-- TODO: Verificar problema na janela de confirmação de exclusão (id não passa pra janela) -->

<!--  
<div id="confirma-excluir" class="modal hide fade">
	<div class="modal-header">
		<a href="#" class="close " data-dismiss="modal" aria-hidden="true">&times;</a>
		<h3>Confirmar Exclusão</h3>
	</div>
	<div class="modal-body">
		<p>Deseja Realmente Excluir este Cliente?</p>
	</div>
	<div class="modal-footer">
		<a href="<c:url value="excluir"/>/?id=" class="btn danger btn-primary">Sim</a>
		<a href="javascript:$('#confirma-excluir').modal('hide')"
			class="btn secondary">Não</a>
	</div>
</div>


<script type="text/javascript">
	$('#listNav').addClass('active');
	setTimeout("$('.alert').fadeOut(1000);", 3000);

	$('.delete-btn').on('click', function(e) {
		e.preventDefault();

		var id = $('.delete-btn').data('id');
		$('#confirma-excluir').data('id', id).modal('show');
	});

	$('#confirma-excluir').on('show', function() {
		var id = $('.delete-btn').data('id');
		var removeBtn = $(this).find('.danger');

		removeBtn.attr('href', '');
		removeBtn.attr('href', '<c:url value="excluir"/>' + '?id=' + id);
	});
</script>

-->

<jsp:include page="/WEB-INF/jsp/footer.jsp" />