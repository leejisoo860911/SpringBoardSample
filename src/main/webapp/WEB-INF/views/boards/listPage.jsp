<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page session="false"%>

<%@include file="../include/header.jsp"%>

<!-- Main content -->
<section class="content">
	<div class="row">
		<!-- left column -->
		<div class="col-md-12">
			<!-- general form elements -->
			<div class='box'>
				<div class="box-header with-border">
					<h3 class="box-title">Board List</h3>
				</div>
				<div class='box-body'>
					<select name="searchType">
						<option value=""
							<c:out value="${pageSearchVo.searchType == null?'selected':''}"/>>---</option>
						<option value="t"
							<c:out value="${pageSearchVo.searchType eq 't'?'selected':''}"/>>Title</option>
						<option value="c"
							<c:out value="${pageSearchVo.searchType eq 'c'?'selected':''}"/>>Content</option>
						<option value="w"
							<c:out value="${pageSearchVo.searchType eq 'w'?'selected':''}"/>>Writer</option>
						<option value="tc"
							<c:out value="${pageSearchVo.searchType eq 'tc'?'selected':''}"/>>Title OR Content</option>
						<option value="cw"
							<c:out value="${pageSearchVo.searchType eq 'cw'?'selected':''}"/>>Content OR Writer</option>
						<option value="tcw"
							<c:out value="${pageSearchVo.searchType eq 'tcw'?'selected':''}"/>>Title OR Content OR Writer</option>
					</select> <input type="text" name='keyword' id="keywordInput"
						value='${pageSearchVo.keyword }'>
					<button id='searchBtn'>Search</button>
					<button id='newBtn'>New Board</button>
				</div>
			</div>
			<div class="box">
				<div class="box-header with-border">
					<h3 class="box-title">LIST PAGING</h3>
				</div>
				<div class="box-body">
					<table class="table table-bordered">
						<tr>
							<th style="width: 10px">BNO</th>
							<th>TITLE</th>
							<th>WRITER</th>
							<th>REGDATE</th>
							<th style="width: 40px">VIEWCNT</th>
						</tr>

						<c:forEach items="${list}" var="boardVo">
							<tr>
								<td>${boardVo.bno}</td>
								<td><a href='/boards/${boardVo.bno}${pageMaker.makePageSearchUri(pageMaker.pageVo.page)}'>${boardVo.title} [${boardVo.replyCnt}]</a></td>
								<td>${boardVo.writer}</td>
								<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${boardVo.regdate}" /></td>
								<td><span class="badge bg-red">${boardVo.viewCnt }</span></td>
							</tr>

						</c:forEach>

					</table>
				</div>
				<!-- /.box-body -->

				<div class="box-footer">

					<div class="text-center">
						<ul class="pagination">

							<c:if test="${pageMaker.prev}">
								<li><a href="${pageMaker.makePageSearchUri(pageMaker.startPage - 1)}">&laquo;</a></li>
							</c:if>

							<c:forEach begin="${pageMaker.startPage }" end="${pageMaker.endPage }" var="idx">
								<li
									<c:out value="${pageMaker.pageVo.page == idx?'class =active':''}"/>>
									<a href="${pageMaker.makePageSearchUri(idx)}">${idx}</a>
								</li>
							</c:forEach>

							<c:if test="${pageMaker.next && pageMaker.endPage > 0}">
								<li><a href="${pageMaker.makePageSearchUri(pageMaker.endPage +1)}">&raquo;</a></li>
							</c:if>

						</ul>
					</div>
				</div>
				<!-- /.box-footer-->
			</div>
		</div>
		<!--/.col (left) -->
	</div>
	<!-- /.row -->
</section>
<!-- /.content -->

<script>
	$('#searchBtn').on("click", function(event) {
		str = "boards" + '${pageMaker.makePageUri(1)}'
				+ "&searchType=" + $("select option:selected").val()
				+ "&keyword=" + encodeURIComponent($('#keywordInput').val());
		self.location = str;
	});

	$('#newBtn').on("click", function(evt) {
		self.location = "boards/viewRegister?page=${pageSearchVo.page}&perPageNum=${pageSearchVo.perPageNum}";
	});
	
</script>
<%@include file="../include/footer.jsp"%>
