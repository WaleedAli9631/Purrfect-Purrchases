// First we want to disable edit button, we are already in edit mode
$('#btnReadOnly').attr("disabled", true);

// Bind function to Read Only button
$('#btnReadOnly').click(function() {
  // Get all input fields used for Domino
  var inputs = $('[data-dominofield]');
  // Process each field
  inputs.each(function() {
    // Build new DIV element
    var input = $(this);
    var div = '<div class="fieldReadOnly" ';
    div += 'data-dominofield="' + input.data('dominofield') + '" ';
    div += 'id="' + input.attr('id') + '">';
    div += input.val() + '</div>';
    // Insert ther new div element in front of input field
    input.before(div);
    // Remove input field
    input.remove();
  });
  $(".btn").attr('disabled', false);
  $(this).attr('disabled', true);
});

// Bind function to Read Only button
$('#btnEdit').click(function() {
  // Get all input fields used for Domino
  var divs = $('[data-dominofield]');
  // Process each field
  divs.each(function() {
    // Build new INPUT element
    var div = $(this);
    var input = '<input type="text" class="form-control" ';
    input += 'data-dominofield="' + div.data('dominofield') + '" ';
    input += 'value="' + div.html() + '" ';
    input += 'id="' + div.attr('id') + '">';
    // Insert ther new iinp element in front of existing div field
    div.before(input);
    // Remove input field
    div.remove();
  });
  $(".btn").attr('disabled', false);
  $(this).attr('disabled', true);
});
