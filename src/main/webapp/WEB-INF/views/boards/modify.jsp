<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>

<%@include file="../include/header.jsp"%>

<!-- Main content -->
<section class="content">
	<div class="row">
		<!-- left column -->
		<div class="col-md-12">
			<!-- general form elements -->
			<div class="box box-primary">
				<div class="box-header">
					<h3 class="box-title">READ BOARD</h3>
				</div>
				<!-- /.box-header -->

				<form role="form" method="get"">
					<input type='hidden' name='page' value="${pageSearchVo.page}">
					<input type='hidden' name='perPageNum' value="${pageSearchVo.perPageNum}">
					<input type='hidden' name='searchType' value ="${pageSearchVo.searchType}">
				    <input type='hidden' name='keyword' value ="${pageSearchVo.keyword}">
				    
					<div class="box-body">
						<div class="form-group">
							<label for="exampleInputEmail1">BNO</label> <input type="text" name='bno' class="form-control" value="${boardVo.bno}" readonly="readonly">
						</div>
						<div class="form-group">
							<label for="exampleInputEmail1">Title</label> <input type="text" name='title' class="form-control" value="${boardVo.title}">
						</div>
						<div class="form-group">
							<label for="exampleInputPassword1">Content</label>
							<textarea class="form-control" name="content" rows="3">${boardVo.content}</textarea>
						</div>
						<div class="form-group">
							<label for="exampleInputEmail1">Writer</label>
							<input type="text" name="writer" class="form-control" value="${boardVo.writer}">
						</div>
					</div>
					<!-- /.box-body -->
				</form>
				
				<div class="box-footer">
					<button type="submit" class="btn btn-primary">SAVE</button>
					<button type="submit" class="btn btn-warning">CANCEL</button>
				</div>
				
				<script>
				$(document).ready(function() {
					var formObj = $("form[role='form']");
					
					$(".btn-warning").on("click", function() {
						self.location = "/boards/${boardVo.bno}?page=${pageSearchVo.page}&perPageNum=${pageSearchVo.perPageNum}&searchType=${pageSearchVo.searchType}&keyword=${pageSearchVo.keyword}";
					});
			
					$(".btn-primary").on("click", function() {
						formObj.attr("action", "/boards/${boardVo.bno}");
						formObj.attr("method", "post");
						formObj.append("<input type='hidden' name='_method' value=\"put\">")
						formObj.submit();
					});
			
				});
				</script>
			</div>
			<!-- /.box -->
		</div>
		<!--/.col (left) -->

	</div>
	<!-- /.row -->
</section>
<!-- /.content -->
</div>
<!-- /.content-wrapper -->

<%@include file="../include/footer.jsp"%>
