<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>${message("admin.dataDict.add")} - Powered By nbcyl</title>
<meta name="author" content="nbcyl LS" />
<meta name="copyright" content="nbcyl" />
<link href="${base}/resources/admin/css/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/resources/admin/js/jquery.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.tools.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.autocomplete.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/input.js"></script>
<style type="text/css">
.brands label {
	width: 150px;
	display: block;
	float: left;
	padding-right: 6px;
}
</style>
<script type="text/javascript">
$().ready(function() {

	var $inputForm = $("#inputForm");
	
	[@flash_message /]
	
	// 表单验证
	$inputForm.validate({
		rules: {
			type: {
				required: true,
				pattern: /RANK|STARLEVEL|BIGTYPE|SMALLTYPE/
			},
			name:{
				required: true
			},
			code: {
				required: true,
				remote: {
					url: "check_code.jhtml",
					cache: false
				}
			}
		},
		messages: {
			type: {
				pattern: "${message("admin.datadict.typeTitle")}"
			},
			code: {
				remote: "${message("admin.validate.exist")}"
			}
		}
	});
});
</script>
</head>
<body>
	<div class="path">
		<a href="${base}/admin/common/index.jhtml">${message("admin.path.index")}</a> &raquo; ${message("admin.dataDict.add")}
	</div>
	<form id="inputForm" action="save.jhtml" method="post">
		<table class="input">
			<tr>
				<th>
					<span class="requiredField">*</span>${message("DataDict.name")}:
				</th>
				<td>
					<input type="text" id="name" name="name" class="text" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					${message("DataDict.parent")}:
				</th>
				<td>
					<select name="pid">
						<option value="">${message("admin.DataDict.root")}</option>
						[#list dataDict as dataDict]
							<option value="${dataDict.id}">
								[#if dataDict.grade != 0]
									[#list 1..dataDict.grade as i]
										&nbsp;&nbsp;
									[/#list]
								[/#if]
								${dataDict.name}
							</option>
						[/#list]
					</select>
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>${message("DataDict.type")}:
				</th>
				<td>
					<input type="text" name="type" id="type" class="text" maxlength="200" title="${message("admin.datadict.typeTitle")}" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>${message("DataDict.code")}:
				</th>
				<td>
					<input type="text" name="code" class="text" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					${message("DataDict.remark")}:
				</th>
				<td>
					<input type="text" name="remark" class="text" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					${message("admin.common.order")}:
				</th>
				<td>
					<input type="text" name="order" class="text" maxlength="9" />
				</td>
			</tr>
			<tr>
				<th>
					&nbsp;
				</th>
				<td>
					<input type="submit" class="button" value="${message("admin.common.submit")}" />
					<input type="button" id="backButton" class="button" value="${message("admin.common.back")}" />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>