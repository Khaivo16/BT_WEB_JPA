<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<form
	action="${pageContext.request.contextPath}/admin/category/update" method = "post" enctype="multipart/form-data">
	<input type="text" id="categoryid" name="categoryid" hidden="hidden"
		value="${cate.categoryId}"><br> <label for="fname">Category
		name:</label><br> 
	<input type="text" id="categoryname" name="categoryname" value = "${cate.categoryname }"><br> 
	<label for="image">Images:</label><br>
	<c:if test="${ cate.images.substring(0,5)=='https'}">
				<c:url value="${cate.images }" var="imgUrl"></c:url>
			</c:if>

			<c:if test="${ cate.images.substring(0,5)!='https'}">
				<c:url value="/image?fname=${cate.images}" var="imgUrl"></c:url>
			</c:if>

			<img height="150" width="200" src="${imgUrl}" id = "images" />
	<input type = "file" onchange= "chooseFile(this)" id="images" name = "images" value = "${cate.images}" ><br>
	<input type="text" id="posterURL" name="posterURL" placeholder="Nhập URL hình ảnh" oninput="chooseUrl(this)" ><br>
	<p>Status:</p>
	<!-- Radio button cho trạng thái đang hoạt động -->
	<input type="radio" id="ston" name="status" value="1"
		${cate.status == 1 ? 'checked' : ''}> <label for="ston">Đang
		hoạt động</label><br>

	<!-- Radio button cho trạng thái khóa -->
	<input type="radio" id="stoff" name="status" value="0"
		${cate.status == 0 ? 'checked' : ''}> <label for="stoff">Khóa</label><br>

	<!-- Nút cập nhật -->
	<input type="submit" value="Update">

</form>
