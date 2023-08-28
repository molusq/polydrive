console.log("Script loaded successfully ");
Java.perform(function x() { //Silently fails without the sleep from the python code
    console.log("Inside java perform function");
    //get a wrapper for our class
    var my_class = Java.use("sg.vantagepoint.a.a");
    var string_class = Java.use("java.lang.String");
    var build = Java.use("android.os.Build");
    //replace the original implmenetation of the function `fun` with our custom function
    my_class.a.implementation = function (arr1, arr2) {
        //print the original arguments
        console.log("original call: a(" + arr1 + ", " + arr2 + ")");
        //call the original implementation
        var original = this.a(arr1, arr2);
        console.log("Build tags: " + build.TAGS.value);
        console.log("Return: " + string_class.$new(original));

        return original;
    }
});