function SourcePicker(id) {
	++SourcePicker.nbInstances;
	Tab.call(this, "sourcePicker" + SourcePicker.nbInstances, "Source Picker");
    $(this.mainDiv).text("Source-picker");
}

SourcePicker.prototype = Object.create(Tab.prototype);
SourcePicker.prototype.constructor = SourcePicker;

//Used to generate the div id
SourcePicker.nbInstances = 0;

SourcePicker.prototype.update = function (data) {
};