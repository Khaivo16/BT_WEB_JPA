
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- JSTL tag library declaration for Jakarta -->
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Xem Video</title>
</head>
<body>
	<c:if test="${video.video_file.substring(0,5) == 'https'}">
    <c:set var="videoId" value="${fn:substringAfter(video.video_file, 'v=')}" />
    <iframe width="560" height="315" 
            src="https://www.youtube.com/embed/${videoId}" 
            frameborder="0" 
            allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" 
            allowfullscreen>
    </iframe>
</c:if>
	<c:if test="${video.video_file.substring(0,5) != 'https'}">
		<c:url value="/video?fname=${video.video_file}" var="videoUrl"></c:url>
		<td><video width="320" height="240" controls>
			<source src="${videoUrl}" type="video/mp4">
			Trình duyệt của bạn không hỗ trợ thẻ video.
</video>
</td>
	</c:if>


</body>
</html>


