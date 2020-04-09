<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div id="departConfirmModal" class="modal fade" tabindex="-1" role="dialog">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title">系统弹窗</h4>
			</div>
			<div class="modal-body">
				<p id="departConfirmParagraph"></p>
			</div>
			<div class="modal-footer">
				<button id="departRemoveBtn" type="button" class="btn btn-danger">
                    <i class="glyphicon glyphicon-remove"></i> 删除
                </button>
			</div>
		</div>
	</div>
</div>