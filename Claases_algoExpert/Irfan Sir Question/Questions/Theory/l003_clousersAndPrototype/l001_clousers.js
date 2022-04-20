function add() {
    let a = 10;
    let b = 20;
    function addChild() {
        console.log(a * b + 5);
    }
    addChild();
    return addChild;
}

let catchChild = add();
console.log(catchChild);
catchChild();   // a function is bundled with lexicalscope varables and the procedure is known as clousers.