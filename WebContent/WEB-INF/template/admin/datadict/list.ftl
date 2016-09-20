<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>${message("admin.dataDict.list")} - Powered By nbcyl</title>
<meta name="author" content="nbcyl LS" />
<meta name="copyright" content="nbcyl" />
<link href="${base}/resources/admin/css/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/resources/admin/js/jquery.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/list.js"></script>
<script type="text/javascript">
$().ready(function() {

	var $delete = $("#listTable a.delete");
	
	[@flash_message /]

	// 删除
	$delete.click(function() {
		var $this = $(this);
		$.dialog({
			type: "warn",
			content: "${message("admin.dialog.deleteConfirm")}",
			onOk: function() {
				$.ajax({
					url: "delete.jhtml",
					type: "POST",
					data: {id: $this.attr("val")},
					dataType: "json",
					cache: false,
					success: function(message) {
						$.message(message);
						if (message.type == "success") {
							$this.closest("tr").remove();
						}
					}
				});
			}
		});
		return false;
	});

});
</script>
</head>
<body>
	<div class="path">
		<a href="${base}/admin/common/index.jhtml">${message("admin.path.index")}</a> &raquo; ${message("admin.dataDict.list")}
	</div>
	<div class="bar">
		<a href="add.jhtml" class="iconButton">
			<span class="addIcon">&nbsp;</span>${message("admin.common.add")}
		</a>
		<a href="javascript:;" id="refreshButton" class="iconButton">
			<span class="refreshIcon">&nbsp;</span>${message("admin.common.refresh")}
		</a>
	</div>
	<table id="listTable" class="list">
		<tr>
			<th>
				<span>${message("DataDict.name")}</span>
			</th>
			<th>
				<span>${message("DataDict.type")}</span>
			</th>
			<th>
				<span>${message("DataDict.remark")}</span>
			</th>
			<th>
				<span>${message("admin.common.handle")}</span>
			</th>
		</tr>
		[#list dataDict as dataDict]
			<tr>
				<td>
					<span style="margin-left: ${dataDict.grade * 20}px;[#if dataDict.grade == 0] color: #000000;[/#if]">
						${dataDict.name}
					</span>
				</td>
				<td>
					${dataDict.type}
				</td>
				<td>
					${dataDict.remark}
				</td>
				<td>
					<a href="${base}${dataDict.path}" target="_blank">[${message("admin.common.view")}]</a>
					<a href="edit.jhtml?id=${dataDict.id}">[${message("admin.common.edit")}]</a>
					<a href="javascript:;" class="delete" val="${dataDict.id}">[${message("admin.common.delete")}]</a>
				</td>
			</tr>
		[/#list]
	</table>
</body>
</html>