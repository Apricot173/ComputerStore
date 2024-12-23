/*按加号数量增*/
function addNum(rid, changeNum) {
	var n = parseInt($("#num-"+rid).val());
	//console.log("rid:" + rid + " changeNum:" + changeNum + " n:" + n);
	if (n + changeNum > 0) {
		$("#num-"+rid).val(n + changeNum);
		updateNum(rid, changeNum);
		calcRow(rid);
	} else {
		alert("商品数量不能小于0！");
	}
}

// 更新数量
function updateNum(cid, num){
	$.ajax({
		url: "/cart/updateCart/" + cid + "/" + num,
		type: "PUT",
		success: function(result) {
			if (result.status === 200) {
				console.log("更新数据");
				showCart();
			}
		},
		error: function(xhr) {
			alert("更新失败: +" + xhr.message);
		}
	});
}

/*全选全不选*/
function checkall(ckbtn) {
	$(".ckitem").prop("checked", $(ckbtn).prop("checked"));
	calcTotal();
}
//删除按钮
function delCartItem(btn, cid) {

		$(btn).parents("tr").remove();
		$.ajax({
			url: "/cart/deleteCart/" + cid,
			type: "DELETE",
			success: function(result) {
				if (result.status === 200) {
					alert("删除成功！");
					showCart();
				} else {
					alert("删除失败！");
				}
			}
		});
		calcTotal();

}
//批量删除按钮
function selDelCart() {
	if (confirm("确定要删除所有选中的商品吗？")) {
		//遍历所有tr
		for (var i = $(".ckitem").length - 1; i >= 0; i--) {
			//如果选中
			if ($(".ckitem")[i].checked) {
				// 拿到该条记录的cid
				let tool = $($(".ckitem")[i]).parents("tr").children(":eq(0)").html().match(/value="(\d+)"/);
				let cid = tool[1];
				//console.log("cid:" + cid);
				// 删除后台数据
				delCartItem(this, cid);

				// 删除前端展示
				$($(".ckitem")[i]).parents("tr").remove();
			}
		}
	}
	calcTotal();
}
$(function() {
	//单选一个也得算价格
	$(".ckitem").click(function() {
			calcTotal();
		})
		//开始时计算价格
		calcTotal();
})
//计算单行小计价格的方法
function calcRow(rid) {
	//取单价
	let vprice = parseFloat($("#price-"+rid).val());
	//取数量
	let vnum = parseFloat($("#num-"+rid).val());
	//小计金额
	let vtotal = vprice * vnum;
	//赋值
	$("#total-price-"+rid).val("¥calcRow" + vtotal);
}
//计算总价格的方法

function calcTotal() {
	//选中商品的数量
	var vselectCount = 0;
	//选中商品的总价
	var vselectTotal = 0;

	//循环遍历所有tr
	for (var i = 0; i < $(".cart-body tr").length; i++) {
		//计算每个商品的价格小计开始
		//取出1行
		var $tr = $($(".cart-body tr")[i]);

		let vprice = parseFloat($tr.children(":eq(3)").children("span").html());
		let vnum = parseFloat($tr.children(":eq(4)").children(".num-text").val());
		let vtotal = vprice * vnum;
		//$tr.children(":eq(5)").children("span").html(vtotal);
		//计算每个商品的价格小计结束

		//检查是否选中
		if ($tr.children(":eq(0)").children(".ckitem").prop("checked")) {
			//计数
			vselectCount++;
			//计总价
			vselectTotal += vtotal;
		}
		//将选中的数量和价格赋值
		$("#selectTotal").html(vselectTotal);
		$("#selectCount").html(vselectCount);
	}
}