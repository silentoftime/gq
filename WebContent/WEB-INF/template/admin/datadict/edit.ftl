<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>${message("admin.productCategory.edit")} - Powered By nbcyl</title>
<meta name="author" content="nbcyl LS" />
<meta name="copyright" content="nbcyl" />
<link href="${base}/resources/admin/css/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/resources/admin/js/jquery.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.tools.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.validate.js"></script>
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
					url: "check_code.jhtml?previousCode=${dataDict.code}",
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
		<a href="${base}/admin/common/index.jhtml">${message("admin.path.index")}</a> &raquo; ${message("admin.dataDict.edit")}
	</div>
	<form id="inputForm" action="update.jhtml" method="post">
		<input type="hidden" name="id" value="${dataDict.id}" />
		<table class="input">
			<tr>
				<th>
					<span class="requiredField">*</span>${message("DataDict.name")}:
				</th>
				<td>
					<input type="text" id="name" name="name" class="text" value="${dataDict.name}" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					${message("DataDict.parent")}:
				</th>
				<td>
					<select name="pid">
						<option value="">${message("admin.DataDict.root")}</option>
						[#list dataDictAll as dataDictAll]
							[#if dataDictAll != dataDict && !children?seq_contains(dataDictAll)]
								<option value="${dataDictAll.id}"[#if dataDictAll == dataDict.parent] selected="selected"[/#if]>
									[#if dataDictAll.grade != 0]
										[#list 1..dataDictAll.grade as i]
											&nbsp;&nbsp;
										[/#list]
									[/#if]
									${dataDictAll.name}
								</option>
							[/#if]
						[/#list]
					</select>
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>${message("DataDict.type")}:
				</th>
				<td>
					<input type="text" id="type" name="type" class="text" value="${dataDict.type}" maxlength="200" title="${message("admin.datadict.typeTitle")}" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>${message("DataDict.code")}:
				</th>
				<td>
					<input type="text" name="code" class="text" value="${dataDict.code}" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					${message("DataDict.remark")}:
				</th>
				<td>
					<input type="text" name="remark" class="text" value="${dataDict.remark}" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					${message("admin.common.order")}:
				</th>
				<td>
					<input type="text" name="order" class="text" value="${dataDict.order}" maxlength="9" />
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