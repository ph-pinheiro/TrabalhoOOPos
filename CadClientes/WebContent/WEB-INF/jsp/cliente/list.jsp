<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<jsp:include page="/WEB-INF/jsp/header.jsp" />

<h2>Lista dos Clientes</h2>

<c:if test="${not empty msg}">
	<div class="alert alert-success">
		<button type="button" class="close" data-dismiss="alert">&times;</button>
		<strong>${msg}</strong>
	</div>
</c:if>

<div id="listagem">
	<form class="form-search well well-small" method="post" action='<c:url value="buscar"/>'>
		<input name="search" id="search" type="text" class="input-medium search-query" placeholder="Termo da busca">
		<button type="submit" class="btn">Buscar</button>
	</form>
	
	<table class="table table-bordered table-hover table-striped">
		<thead>
			<tr>
				<th>Nome</th>
				<th>CPF</th>
				<th>Crédito</th>
				<th>Rua</th>
				<th>CEP</th>
				<th>Cidade</th>
				<th>Dependentes</th>
				<th>Data Cadastro</th>
				<th style="width: 5%">Editar</th>
				<th style="width: 5%">Excluir</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${fn:length(clienteList) gt 0}">
					<c:forEach items="${clienteList}" var="cliente">
						<tr>
							<td>${cliente.nome}</td>
							<td>${cliente.cpf}</td>
							<td>${cliente.credito}</td>
							<td>${cliente.rua}</td>
							<td>${cliente.cep}</td>
							<td>${cliente.cidade}</td>
							<td>${fn:length(cliente.dependentes)}</td>
							<td>${cliente.dataCadastro}</td>
							<td><a class="btn btn-warning"
								href='<c:url value="editar"><c:param name="id" value="${cliente.id}" /></c:url>'>
								<i class="icon-edit"></i></a></td>
							<td><a class="btn btn-danger delete-btn"
							     href='<c:url value="excluir"><c:param name="id" value="${cliente.id}" /></c:url>'> 
							       <i class="icon-remove"></i></a></td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<td colspan="6" style="text-align: center;">Nenhum Cliente
						Cadastrado</td>
				</c:otherwise>
			</c:choose>
		</tbody>
	</table>
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