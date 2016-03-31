<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>




<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="author" content="Pedro H Pinheiro" />
<meta name="description" content="Trabalho de pós Graduação - Complemento">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">

<script src="http://code.jquery.com/jquery.js"></script>
<script src="js/bootstrap.min.js"></script>
<style type="text/css">
	.controls input {
		height: 30px;
	}
</style>
<title>Clientes</title>
</head>
<body>
	<div class="container">
		<div id="header">
			<div class="navbar">
				<div class="navbar-inner">
					<a class="brand" href=".">Controle de Clientes</a>
					<ul class="nav">
						<li id="listNav"><a href="list">Lista</a></li>
						<li id="addNav"><a href="form">Adicionar Clientes</a></li>
					</ul>
					<ul class="nav pull-right">
						<li class="divider-vertical"></li>
						<li><a href="https://github.com/ph-pinheiro/TrabalhoOOPos.git">repo</a></li>
					</ul>
				</div>
			</div>
		</div>

		<div id="content">