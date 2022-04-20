
// Create an object called Teacherderived from thePersonclass, and implement a method called teach which receives a string called subject, and
// prints out:[teacher's name] is now teaching [subject].

// Constraint:1.The expected output should be achieved using the keyword .prototype.

let Person = function () { };

Person.prototype.initialize = function (name, age) {
    this.name = name;
    this.age = age;
}

// // TODO: Create the class Teacher and a method teach: 
let Teacher = function () {
    this.teach = function (subject) {
        console.log(`${this.name} is now teaching ${subject}`)
    }
}

Teacher.prototype = new Person();

let him = new Teacher();
him.initialize("Adam", 45);
him.teach("inheritance");

// =============================================================================================

// => I think this question is to test the understanding of OOPs in JS like inheritance, prototype keyword in JS.