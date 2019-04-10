function num_add(add_a) {
	var number = add_a.nextElementSibling;
	number.value++;
}

function num_minus(minus_a) {
	var number = minus_a.parentNode.firstElementChild.nextElementSibling;

	if (number.value > 1) {
		number.value--;
	} else {
		alert('数量不能小于1');
	}
}

function change(c) {
	if (c.value <= 0) {
		alert("数量不能小于1")
		c.value=1;
	} 
}
