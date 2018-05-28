 function clickCheckbox(o){
    if(o.checked==true)
      selectAll();
    else
      disSelectAll();
  }
  function selectAll(){
    var checkBoxName=document.form1.itemSelect;
    for(var i=0;i<checkBoxName.length;i++){
	  checkBoxName[i].checked=true;
	}
  }

  function disSelectAll(){
    var checkBoxName=document.form1.itemSelect;
    for(var i=0;i<checkBoxName.length;i++){
	  checkBoxName[i].checked=false;
	}
  }